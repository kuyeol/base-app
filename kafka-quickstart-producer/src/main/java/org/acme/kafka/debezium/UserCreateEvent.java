package org.acme.kafka.debezium;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;
import java.util.Map;
import org.acme.kafka.User;


public class UserCreateEvent implements ExportedEvent<String, JsonNode >
{
private static ObjectMapper mapper = new ObjectMapper();

private final long userId;
private final JsonNode userInfo;
private final Instant timestamp;


  public UserCreateEvent( long userId, JsonNode userInfo )
    {
      this.userId    = userId;
      this.userInfo  = userInfo;
      this.timestamp = Instant.now();
    }

public UserCreateEvent of( RegisterUser registerUser ){

  ObjectNode node = mapper.createObjectNode();
  node.put( "userId", registerUser.getId() );
  node.put( "userInfo", userInfo );



    return new UserCreateEvent(userId, node);
}






  @Override
  public String getAggregateId()
    {
      return "";
    }


  @Override
  public String getAggregateType()
    {
      return "";
    }


  @Override
  public String getType()
    {
      return "";
    }


  @Override
  public Instant getTimestamp()
    {
      return null;
    }


  @Override
  public JsonNode getPayload()
    {
      return null;
    }


  @Override
  public Map< String, Object > getAdditionalFieldValues()
    {
      return ExportedEvent.super.getAdditionalFieldValues();
    }





}
