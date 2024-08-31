package org.acme.panache;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;

@ApplicationScoped
public class PriceStorage {

  @Inject
  PriceQuery priceQuery;


  @Inject
  PgPool pool;

  @Incoming("prices")
  @ActivateRequestContext
  Uni< Void > store( int priceInUsd )
    {
      Price price = new Price();
      price.setCount( priceInUsd );
      System.out.println("sss");
      return priceQuery.save( pool ).replaceWithVoid();
    }

}
