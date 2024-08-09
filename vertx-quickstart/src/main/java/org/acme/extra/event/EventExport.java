package org.acme.extra.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;
import org.acme.extra.entity.Fruit;

public class EventExport implements ExportedEvent<String, JsonNode> {

  private static ObjectMapper mapper = new ObjectMapper();
  private static final String TYPE = "Entity";
  private static final String EVENT_TYPE = "Created_Entity_Event";

  private final Long eventId;
  private final JsonNode payload;
  private final Instant timestamp;

  public EventExport(Fruit fruit) {
    this.eventId = fruit.id;
    this.payload = convertToJson(fruit);
    this.timestamp = Instant.now();
  }

  private JsonNode convertToJson(Fruit fruit) {
    ObjectNode asJson = mapper.createObjectNode();
    asJson.put("id", fruit.id);
    asJson.put("name", fruit.name);
    asJson.put("color", fruit.color);

    return asJson;
  }






  @Override
  public String getAggregateId() {
    return String.valueOf(eventId);
  }

  @Override
  public String getAggregateType() {
    return TYPE;
  }

  @Override
  public String getType() {
    return EVENT_TYPE;
  }

  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public JsonNode getPayload() {
    return payload;
  }
}
