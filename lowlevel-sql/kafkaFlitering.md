# kfaka impontence properties

kafka.bootstrap.servers=http://HOST_IP:9094
mp.messaging.outgoing.message-out.topic=message-queue
mp.messaging.outgoing.message-out.value.serializer=com.etzgh.QueryService.util.kafka.MessageSerializer
mp.messaging.outgoing.message-out.key.serializer=org.apache.kafka.common.serialization.StringSerializer
mp.messaging.outgoing.message-out.enable.idempotence=true
mp.messaging.outgoing.message-out.acks=all



mp.messaging.incoming.update-in.topic=status-queue
mp.messaging.incoming.update-in.auto.offset.reset=earliest
mp.messaging.incoming.update-in.value.deserializer=com.etzgh.QueryService.util.kafka.MessageStatusDeserializer
mp.messaging.incoming.update-in.key.deserializer=org.apache.kafka.common.serialization.StringDeserializer



kafka.bootstrap.servers=http://HOST_:9094
mp.messaging.outgoing.update-out.topic=status-queue
mp.messaging.outgoing.update-out.value.serializer=com.etzgh.MessageService.util.kafka.MessageStatusSerializer
mp.messaging.outgoing.update-out.key.serializer=org.apache.kafka.common.serialization.StringSerializer


mp.messaging.incoming.message-in.topic=message-queue
mp.messaging.incoming.message-in.auto.offset.reset=earliest
mp.messaging.incoming.message-in.value.deserializer=com.etzgh.MessageService.util.kafka.MessageDeserializer
mp.messaging.incoming.message-in.key.deserializer=org.apache.kafka.common.serialization.StringDeserializer




```java
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class KafkaConsumer {

    // 중복 메시지를 필터링하기 위한 Set
    private Set<String> processedMessages = ConcurrentHashMap.newKeySet();

    @Incoming("my-kafka-topic")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING) // 메시지를 처리하기 전에 Ack
    public void consume(String message) {
        String messageId = extractMessageId(message); // 메시지의 ID 추출

        if (processedMessages.contains(messageId)) {
            System.out.println("중복된 메시지: " + messageId);
            return; // 중복된 메시지이므로 처리하지 않음
        }

        // 메시지 처리 로직
        try {
            processMessage(message);
            processedMessages.add(messageId); // 처리된 메시지 ID 저장
        } catch (Exception e) {
            // 예외 발생 시 실패 처리 (예: 대기열로 보내기)
            handleFailedMessage(message);
        }
    }

    private String extractMessageId(String message) {
        // 메시지에서 고유 ID 추출 (예를 들어 JSON 파싱 등)
        return message; // 예시
    }

    private void processMessage(String message) {
        // 메시지 처리 로직 (비즈니스 로직)
        System.out.println("메시지 처리: " + message);
    }

    private void handleFailedMessage(String message) {
        // 실패한 메시지 처리 (대기열로 보내기 등)
        System.out.println("메시지 처리 실패, 대기열로 이동: " + message);
        // 실패한 메시지를 Redis 또는 별도 대기열에 저장하여 추후 재시도 가능
    }
}

```






```java

import io.quarkus.scheduler.Scheduled;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FailedMessageRetryService {

    @Inject
    RedisService redisService; // Redis 서비스 또는 대기열

    @Scheduled(every = "10s") // 10초마다 재시도
    public void retryFailedMessages() {
        List<String> failedMessages = redisService.getFailedMessages();

        for (String message : failedMessages) {
            try {
                // 재시도 로직
                processMessage(message);
                redisService.removeFailedMessage(message); // 성공 시 대기열에서 제거
            } catch (Exception e) {
                // 여전히 실패 시 처리
                System.out.println("재시도 실패: " + message);
            }
        }
    }

    private void processMessage(String message) {
        // 재시도 시 메시지 처리 로직
        System.out.println("메시지 재시도 처리: " + message);
    }
}


```



```java

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RedisService {

  private List<String> failedMessagesQueue = new ArrayList<>();

  public void saveFailedMessage(String message) {
    failedMessagesQueue.add(message); // Redis에 저장하는 대신 리스트로 예시
  }

  public List<String> getFailedMessages() {
    return new ArrayList<>(failedMessagesQueue);
  }

  public void removeFailedMessage(String message) {
    failedMessagesQueue.remove(message);
  }
}

```


```properties
mp.messaging.incoming.my-kafka-topic.connector=smallrye-kafka
mp.messaging.incoming.my-kafka-topic.topic=my-topic
mp.messaging.incoming.my-kafka-topic.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
mp.messaging.incoming.my-kafka-topic.auto.offset.reset=earliest

```






> Producer: 고유한 메시지 ID 할당

```java
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class OrderServiceProducer {

    private KafkaProducer<String, String> producer;

    public OrderServiceProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String order) {
        String messageId = UUID.randomUUID().toString();  // 고유한 메시지 ID 생성
        String message = String.format("{\"id\": \"%s\", \"order\": \"%s\"}", messageId, order);
        producer.send(new ProducerRecord<>("order-topic", messageId, message));
        System.out.println("Sent message: " + message);
    }
    
    public static void main(String[] args) {
        OrderServiceProducer producer = new OrderServiceProducer();
        producer.sendMessage("order-123");
    }
}

```
> 2. Consumer: 메시지 ID 추적 및 중복 메시지 필터링

```java
import org.eclipse.microprofile.reactive.messaging.Incoming;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ApplicationScoped
public class FulfillmentServiceConsumer {

    @Inject
    MessageIdTrackingService messageIdTrackingService;

    @Incoming("order-topic")
    @Transactional
    public void consume(String message) {
        String messageId = extractMessageId(message);  // 메시지에서 ID 추출

        if (messageIdTrackingService.isMessageProcessed(messageId)) {
            System.out.println("중복된 메시지: " + messageId + ", 처리 스킵됨");
            return;
        }

        // 메시지 처리 로직
        processOrder(message);
        
        // 처리 완료 후 메시지 ID를 추적 테이블에 저장
        messageIdTrackingService.trackMessageId(messageId);
    }

    private String extractMessageId(String message) {
        // 메시지에서 ID 추출 (예: JSON 파싱)
        // 이 부분은 실제 메시지 형식에 따라 수정 필요
        return message.substring(message.indexOf("\"id\": \"") + 7, message.indexOf("\", \"order\": \""));
    }

    private void processOrder(String message) {
        // 실제 비즈니스 로직
        System.out.println("메시지 처리: " + message);
    }
}

```
> 3. MessageIdTrackingService: 메시지 ID 추적 서비스

```java
import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ApplicationScoped
public class MessageIdTrackingService {

    private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka_db", "user", "password");
        } catch (Exception e) {
            throw new RuntimeException("데이터베이스 연결 오류", e);
        }
    }

    // 메시지가 이미 처리되었는지 확인
    public boolean isMessageProcessed(String messageId) {
        String query = "SELECT COUNT(*) FROM message_tracking WHERE message_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, messageId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (Exception e) {
            throw new RuntimeException("메시지 ID 조회 오류", e);
        }
    }

    // 메시지 ID 저장
    public void trackMessageId(String messageId) {
        String query = "INSERT INTO message_tracking (message_id) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, messageId);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("메시지 ID 저장 오류", e);
        }
    }
}

```


```mysql
CREATE TABLE message_tracking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    message_id VARCHAR(255) NOT NULL,
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


```


```properties
mp.messaging.incoming.order-topic.connector=smallrye-kafka
mp.messaging.incoming.order-topic.topic=order-topic
mp.messaging.incoming.order-topic.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
mp.messaging.incoming.order-topic.auto.offset.reset=earliest

quarkus.datasource.db-kind=mysql
quarkus.datasource.username=user
quarkus.datasource.password=password
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/kafka_db


```