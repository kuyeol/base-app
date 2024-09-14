package org.acme.panache.lunapractice;


import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgConnection;
import io.vertx.sqlclient.PoolOptions;
import jakarta.enterprise.context.ApplicationScoped;


public class LowleveConnect
{

  private final PgConnectOptions clientOption;

  private final PoolOptions      poolOptions;

  private final PgPool           client;


  public LowleveConnect()
  {

    this.poolOptions = new PoolOptions().setMaxSize( 100 );

    this.clientOption = new PgConnectOptions().setHost( "182.218.135.229" )
                                              .setPort( 5432 )
                                              .setDatabase( "quarkus" )
                                              .setUser( "quarkus" )
                                              .setPassword( "quarkus" );
    this.client       = PgPool.pool( clientOption, poolOptions );
  }


  public void test()
  {
    //
    client.getConnection()
          .toMulti()
          .onSubscription();
    Uni< RowSet< Row > > rowSetUni = client.query( "SELECT name FROM price" )
                                           .execute();

  }





}
