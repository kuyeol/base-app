package org.acme.lowlevel.notify;


import io.smallrye.mutiny.Multi;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.pgclient.PgConnection;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.pgclient.PgNotification;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.resteasy.reactive.RestStreamElementType;


@Path("channel")
public class ListenNotifyResource
{

  @Inject
  PgPool            client;

  @Channel("usertable")
  Emitter< String > emitter;


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
                                                         streamNotifications( pgConnection ) ) )
                 .map( PgNotification::toJson );
  }

  //// @Incoming("usertable")
  // public Uni<Void> ss( Message<String> S )
  // {
  //
  //  // System.out.println(S);
  //  // emitter.send( S );
  //   return Uni.createFrom().voidItem();
  // }

  //@Path("{channel}")
  //@POST
  //@Produces(MediaType.TEXT_PLAIN)
  //@Consumes(MediaType.WILDCARD)
  //public Uni< String > notify( @PathParam("channel") String channel,User user )
  //{
  //
  //  return client.preparedQuery( "NOTIFY " + channel + ", $$" + user + "$$" )
  //               .execute()
  //               .onItem()
  //               .transform( cha -> emitter.send( user ) )
  //               .map( rs -> "Posted to " + channel + " channel" );
  //}


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





}
