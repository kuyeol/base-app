package org.acme.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import lombok.experimental.WithBy;
import org.acme.kafka.event.UserCreateEvent;
import org.acme.kafka.model.User;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("kafka-send")
public class EventResource
{

  @Inject
  EventService eventService;


  @Inject
  @Channel( "record" )
  Emitter< Record<String, User > > userRecord;

  @POST
  @Path( "rec" )
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<Void> send(User user) {
    return userRecord.send(Record.of("my-key", user));
  }




  @POST
  public Uni< Response > createUser( User user ) throws JsonProcessingException
    {

      UserCreateEvent event =  UserCreateEvent.builder().userDetails( user ).build();

      try {
        eventService.createUserEvent( event );
        return Uni.createFrom().item( Response.ok().status( 200 ).entity( eventService ).build() );
      } catch ( JsonProcessingException e ) {
        throw new RuntimeException( e );
      }

    }





}
