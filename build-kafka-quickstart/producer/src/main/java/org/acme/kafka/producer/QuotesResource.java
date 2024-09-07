package org.acme.kafka.producer;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.UUID;
import org.acme.kafka.model.Quote;
import org.acme.kafka.model.User;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("/quotes")
public class QuotesResource
{

  @Channel("quote-requests")
  Emitter< String > quoteRequestEmitter;


  /**
   * Endpoint to generate a new quote request id and send it to "quote-requests" Kafka topic using the emitter.
   */
  @POST
  @Path("/request")
  @Produces(MediaType.APPLICATION_JSON)
  public String createRequest()
    {
      UUID uuid = UUID.randomUUID();

      quoteRequestEmitter.send( uuid.toString() );
      return uuid.toString();
    }


  @Channel("user-requests")
  Emitter< String > userRequestEmitter;


  @POST
  @Path("/user")
  @Produces(MediaType.APPLICATION_JSON)
  public User userRequest( User user )
    {
      UUID uuid = UUID.randomUUID();

      userRequestEmitter.send( String.valueOf( user ) );

      return user;
    }


  @Channel("quotes")
  Multi< Quote > quotes;


  /**
   * Endpoint retrieving the "quotes" Kafka topic and sending the items to a server sent event.
   */
  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS) // denotes that server side events (SSE) will be produced
  public Multi< Quote > stream()
    {
      return quotes.log();
    }





}
