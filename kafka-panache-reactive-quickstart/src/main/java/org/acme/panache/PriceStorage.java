package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.panache.model.User;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class PriceStorage
{

  @Inject
  UserQuery userQuery;

  @Inject
  PgPool pool;



  @ActivateRequestContext
  @Transactional
  Uni< Void > store( User user )
    {
      User user1 = new User();
      user1.setId( user.getId() );

      System.out.println( "sss" );
      return userQuery.saves(pool ,user).replaceWithVoid();
    }





}
