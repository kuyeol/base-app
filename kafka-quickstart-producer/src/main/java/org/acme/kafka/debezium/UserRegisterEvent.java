package org.acme.kafka.debezium;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;
import java.util.Map;
import org.acme.kafka.User;
import org.acme.kafka.constant.ServiceType;


public class UserRegisterEvent implements ExportedEvent< String, JsonNode >
{

  private static ObjectMapper mapper = new ObjectMapper();

  private final Long Id;

  private final String ENTITY = "Account";

  private final JsonNode userForm;

  private final Instant timestamp;


  public UserRegisterEvent( Long Id, JsonNode userForm )
    {
      this.Id    = Id;
      this.userForm  = userForm;
      this.timestamp = Instant.now();
    }


  public static UserRegisterEvent of( UserRegisterForm newUser )
    {

      ObjectNode formData = mapper.createObjectNode();
      formData.put( "Id", newUser.getId() );
      formData.put( "userId", newUser.getUserId() );
      formData.put( "description", newUser.getDescription() );

      return new UserRegisterEvent( newUser.getId(), formData );
    }


  @Override
  public String getAggregateId()
    {
      return String.valueOf( Id );
    }


  @Override
  public String getAggregateType()
    {
      return ENTITY;
    }


  @Override
  public String getType()
    {
      return ServiceType.REGISTER.name();
    }


  @Override
  public Instant getTimestamp()
    {
      return timestamp;
    }


  @Override
  public JsonNode getPayload()
    {
      return userForm;
    }


  @Override
  public Map< String, Object > getAdditionalFieldValues()
    {
      return ExportedEvent.super.getAdditionalFieldValues();
    }





}
