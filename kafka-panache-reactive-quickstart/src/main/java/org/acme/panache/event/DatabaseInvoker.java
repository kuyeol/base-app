package org.acme.panache.event;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.pgclient.PgConnection;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.SqlConnection;
import io.vertx.pgclient.PgNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestSseElementType;


@Path("TEST")
public class DatabaseInvoker
{

  @Inject
  PgPool client;


  @GET
  @Path("{channel}")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @RestSseElementType(MediaType.APPLICATION_JSON)
  public Multi< JsonObject > listner( @PathParam("channel") String channel )
  {

    String channels = "testlisten";
    return client.getConnection()
                 .toMulti()
                 .flatMap( connection ->
                             {
                               Multi< PgNotification > notifications = Multi.createFrom()
                                                                            .emitter( c -> toPgConnection(
                                                                                connection ).notificationHandler(
                                                                                c::emit ) );

                               return connection.query( "LISTEN " + channel )
                                                .execute()
                                                .toMulti()
                                                .flatMap( __ -> notifications );
                             } )
                 .map( PgNotification::toJson );
  }


  @Path("{channel}")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.WILDCARD)
  public Uni< String > notif( @PathParam("channel") String channel, String stuff )
  {

    return client.preparedQuery( "NOTIFY " + channel + ", $$" + stuff + "$$" )
                 .execute()
                 .map( rs -> "Posted to " + channel + " channel" );
  }


  PgConnection toPgConnection( SqlConnection sqlConnection )
  {

    return new PgConnection( (io.vertx.pgclient.PgConnection) sqlConnection.getDelegate() );
  }





}
