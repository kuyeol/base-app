package org.acme.lowlevel.notify;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.pgclient.PgConnection;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.pgclient.PgNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.acme.lowlevel.model.User;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.resteasy.reactive.RestStreamElementType;


@Path("channel")
@ApplicationScoped
public class ListenNotifyResource
{

  @Inject
  PgPool          client;

  @Channel("usertable")
  Emitter< String > emitter;

  PgConnection connection;


  @GET
  @Path("{channel}")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @RestStreamElementType(MediaType.APPLICATION_JSON)
  public Multi< JsonObject > listenNotify( @PathParam("channel") String channel )
  {

    return client.getConnection()
                 .map( PgConnection::cast )
                 .toMulti()
                 .flatMap( pgConnection ->
                               pgConnection.query( "LISTEN " + channel )
                                           .execute()
                                           .toMulti()
                                           .flatMap( __ ->

                                                         streamNotifications( pgConnection )

                                           ) )
                 .map( PgNotification::toJson )
                 .toHotStream();

  }


  //@Outgoing("raw-price-updates")
  @POST
  @Path("Dfdsfd")
  public Uni< Void > ss( Message< String > S )
  {

    Multi< JsonObject > d = listenNotify( "usertable" );
    System.out.println( "\n\n\n" + d );
    return Uni.createFrom()
              .voidItem();
  }


  @Path("{channel}")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.WILDCARD)
  public Uni< String > notify( @PathParam("channel") String channel, User user )
  {

    return client.preparedQuery( "NOTIFY " + channel + ", $$" + user + "$$" )
                 .execute()
                 .onItem()
                 .transform( cha -> emitter.send( user.getName() ) )
                 .map( rs -> "Posted to " + channel + " channel" );
  }


  // Use PgConnection::notificationHandler to register a handler that emits PgNotification values on a Multi stream
  private Multi< PgNotification > streamNotifications( PgConnection connection )
  {

    // To be implemented
    return Multi.createFrom()
                .emitter( multiEmitter ->
                            {
                              connection.notificationHandler( multiEmitter::emit );

                            }
                );
  }




  public KafkaRecord< String, User > DDD( Message< User > message )
  {

    connection.noticeHandler( notification ->
                                {
                                  System.out.println( notification.getSeverity() );
                                } );

    connection
        .query( "LISTEN usertable" )
        .execute()
        .toMulti();

 connection.notificationHandler( notification ->
                                      {
                                        System.out.println( "Received " + notification.getPayload() + " on channel " +
                                                            notification.getChannel() );
                                        KafkaRecord.of("msg",notification.getPayload());
                                        emitter.send( notification.getPayload() );
                                      } );


    return KafkaRecord.of( "msg", message.getPayload() );
  }





}
