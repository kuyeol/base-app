import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

@Service
public class PostgresNotifyToKafkaService {

    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/yourdb";
    private static final String POSTGRES_USER = "youruser";
    private static final String POSTGRES_PASSWORD = "yourpassword";

    private static final String KAFKA_TOPIC = "new_posts";
    private static final String KAFKA_BROKER = "localhost:9092";

    private KafkaProducer<String, String> kafkaProducer;

    public PostgresNotifyToKafkaService() {
        // Kafka 프로듀서 설정
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(kafkaProps);

        // PostgreSQL에서 LISTEN 시작
        startListeningToPostgres();
    }

    public void startListeningToPostgres() {
        try (Connection conn = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                // PostgreSQL LISTEN 명령어 실행
                stmt.execute("LISTEN new_post_channel");

                // 무한 루프: PostgreSQL 알림 수신 대기
                while (true) {
                    // PostgreSQL 서버로부터 알림 수신 여부를 확인
                    ResultSet rs = stmt.executeQuery("SELECT 1");
                    ((org.postgresql.PGConnection) conn).getNotifications();

                    // 알림이 도착하면
                    if (rs.next()) {
                        org.postgresql.PGNotification[] notifications = ((org.postgresql.PGConnection) conn).getNotifications();

                        if (notifications != null) {
                            for (org.postgresql.PGNotification notification : notifications) {
                                String payload = notification.getParameter();

                                // Kafka로 알림 전송
                                kafkaProducer.send(new ProducerRecord<>(KAFKA_TOPIC, payload));

                                System.out.println("Sent to Kafka: " + payload);
                            }
                        }
                    }

                    // 1초 대기 후 다시 알림 확인
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






