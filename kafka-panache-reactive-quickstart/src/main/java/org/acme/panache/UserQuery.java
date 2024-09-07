package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.concurrent.CompletionStage;
import org.acme.panache.model.User;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;


@ApplicationScoped
public class UserQuery
{

  @Inject
  PgPool client;


  @Incoming("create")
  public CompletionStage< Void > consume( Message< User > user )
    {
      // process your price.

      // Acknowledge the incoming message (commit the offset)
      User user1 = new User();
      user1.setId( user.getPayload().getId() );
      user1.setUsername( user.getPayload().getUsername() );

      System.out.println(user.getPayload().getUsername());
      saves( client, user1 );

      return user.ack();
    }


  public Uni< Long > saves( PgPool client, User user )
    {

      user.setUsername( "ddddd" );
      return client.preparedQuery( "INSERT INTO public.user (username) VALUES ($1) RETURNING id" )
                   .execute( Tuple.of( user.getUsername() ) )
                   .onItem()
                   .transform( pgRowSet -> pgRowSet.iterator().next().getLong( "id" ) );
    }


  public static User from( Row row )
    {
      return new User( row.getLong( "id" ), row.getString( "username" ) );
    }





}
