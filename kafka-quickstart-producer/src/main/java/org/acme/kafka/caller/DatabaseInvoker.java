package org.acme.kafka.caller;


import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.virtual.threads.VirtualThreads;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.pgclient.pubsub.PgSubscriber;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;


@ApplicationScoped
public class DatabaseInvoker
{

 // private final PgPool client;

  @Inject
  @ReactiveDataSource("additional1")
  PgPool client1;

  public DatabaseInvoker( PgPool client1 )
  {

    this.client1 = client1;

  }



  void onStart( @Observes StartupEvent ev )
  {

    call();
  }

  public void call()
  {

    PgSubscriber subscriber;
    subscriber.


    client1.query( "LISTEN testlisten" )
          .execute()
          .invoke( a ->
                     {
                       System.out.println( a );
                     } );
  }


  private void initdb()
  {
    // TODO
  }





}
