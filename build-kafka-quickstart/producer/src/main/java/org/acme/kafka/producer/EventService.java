package org.acme.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import org.acme.kafka.event.EventStatus;
import org.acme.kafka.event.UserCreateEvent;
import org.acme.kafka.model.EventRecord;
import org.acme.kafka.model.User;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.smallrye.reactive.messaging.kafka.Record;


@ApplicationScoped
public class EventService
{

  @Inject
  @Channel("user-create")
  Emitter< EventRecord > userCreate;





  public void createUserEvent( UserCreateEvent userCreateEvent ) throws JsonProcessingException
    {
      EventRecord eventRecord = new EventRecord();

      eventRecord.setEventData( new ObjectMapper().writeValueAsString( userCreateEvent.getUserDetails() ) );

      eventRecord.setEventType( EventStatus.CREATED.name() );
      eventRecord.setEventId( UUID.randomUUID().getMostSignificantBits() );
      eventRecord.setEntityId( userCreateEvent.getUserDetails().getUsername() );
      eventRecord.setEventTime( LocalDateTime.now() );

      CompletionStage< Void > future = userCreate.send( eventRecord );
      future.whenComplete( ( rs, ex ) ->
        {
          if ( ex == null ) {
            System.out.println( rs );
          } else {
            System.out.print( ex.getMessage() );
          }
        } );
    }




}
