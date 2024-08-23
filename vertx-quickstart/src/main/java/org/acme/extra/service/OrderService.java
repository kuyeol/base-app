package org.acme.extra.service;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.extra.entity.FruitOrder;


@ApplicationScoped
public class OrderService
  {

  @Inject
  PgPool client;


  @PostConstruct
  public void config()
    {
      initDb();
    }


public OrderService plrun()
    {
      client.preparedQuery( "LISTEN table_changes" ).execute().flatMap( r ->
        {
          System.out.println( "Listen Method" );
          return client.preparedQuery( "LISTEN table_changes"

          ).execute();
        } ).await().indefinitely();
      return null;
    }


  private void initDb()
    {
      System.out.println( "Quarkus reactive - initDB" );

      client.query( "DROP TABLE IF EXISTS fruit_order" ).execute().flatMap( r ->
        {
          System.out.println( "Niklas 1" );
          return client.query(
              "CREATE TABLE fruit_order (orderid SERIAL PRIMARY KEY, fruitid bigint NOT NULL," +
              "username varchar(255) NOT NULL,orderdate TIMESTAMP " + ")"
          ).execute();
        } ).await().indefinitely();
    }


  public Uni< Long > add( FruitOrder orderP )
    {
      FruitOrder order = new FruitOrder();
      order.setOrderid( orderP.getOrderid() );
      order.setUsername( orderP.getUsername() );

      return client.preparedQuery( "INSERT INTO fruit_order(fruitid,username) VALUES ($1,$2) returning (id)" )
                   .execute( Tuple.of( order.getFruitid(), order.getFruitid() ) )
                   .onItem()
                   .transform( row -> row.iterator().next().getLong( "id" ) );
    }


  private static FruitOrder from( Row row )
    {

      return new FruitOrder( row.getLong( "fruitid" ), row.getString( "username" ) );
    }





  }
