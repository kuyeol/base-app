package org.acme.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;


public class CreateEvent implements ExportedEvent< String, JsonNode >
{

  private final ObjectMapper mapper = new ObjectMapper();

  private static final String TYPE = "Order";

  private static final String EVENT_TYPE = "OrderCreated";

  private final String orderId;

  private final JsonNode jsonNode;

  private final Instant timestamp;


  public CreateEvent( Instant createdAt, User order )
    {
      this.orderId   = order.getId().toString();
      this.timestamp = createdAt;
      this.jsonNode  = convertToJson( order );
    }


  private JsonNode convertToJson( User newUser )
    {
      ObjectNode formData = mapper.createObjectNode();
      formData.put( "id", newUser.getId().toString() );
      formData.put( "userId", newUser.getUserId() );
      formData.put( "description", newUser.getDescription() );
      return formData;
    }


  @Override
  public String getAggregateId()
    {
      return orderId.toString();
    }


  @Override
  public String getAggregateType()
    {
      return TYPE;
    }


  @Override
  public JsonNode getPayload()
    {
      return jsonNode;
    }


  @Override
  public String getType()
    {
      return EVENT_TYPE;
    }


  @Override
  public Instant getTimestamp()
    {
      return timestamp;
    }


  @Override
  public Map< String, Object > getAdditionalFieldValues()
    {
      // no additional fields
      return Collections.emptyMap();
    }





}
