```java

public class PostgreSQLListener {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String USER = "your_user";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("LISTEN table_changes");

            while (true) {
                conn.createStatement().execute("LISTEN table_changes");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    String payload = rs.getString("payload");
                    // Kafka로 전송
                    sendToKafka(payload);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sendToKafka(String message) {
        // Kafka Producer를 사용하여 메시지를 전송
        // KafkaProducer<String, String> producer = ...
        // producer.send(new ProducerRecord<>("your_topic", message));
    }
}
```

```postgresql

CREATE OR REPLACE FUNCTION notify_change()
RETURNS trigger AS $$
BEGIN
    PERFORM pg_notify('table_changes', row_to_json(NEW)::text);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```


```postgresql
CREATE TRIGGER example_table_trigger
AFTER INSERT OR UPDATE OR DELETE ON example_table
FOR EACH ROW
EXECUTE FUNCTION notify_change();

```


