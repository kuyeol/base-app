package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Future;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import io.vertx.reactivex.core.Vertx;
import io.vertx.sqlclient.Pool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class PriceQuery
{

  @Inject
  PgPool client;

  Vertx vertx;

  @Inject
  Pool pool;

  private Price price;


  public Uni< Price > findById( PgPool client, Long id )
    {

      return client.preparedQuery( "SELECT id,version FROM price WHERE id = $1" )
                   .execute( Tuple.of( id ) )
                   .onItem()
                   .transform( RowSet::iterator )
                   .onItem()
                   .transform( iterator -> iterator.hasNext() ? from( iterator.next() ) : null );
    }

  //public Uni<Boolean> update(PgPool client) {
  //  return client.preparedQuery("UPDATE fruits SET name ,color = $2,$3 WHERE id = $1").execute(Tuple.of(id, name,color))
  //               .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
  //}
  //*
  //     public Uni<Integer> update(UUID id, Post data) {
  //        return this.client
  //                .preparedQuery("UPDATE posts SET title=$1, content=$2 WHERE id=$3", Tuple.of(data.getTitle(), data.getContent(), id))
  //                .map(RowSet::rowCount);

  //  return client.preparedQuery( "UPDATE price SET name=$1 , version = $2  WHERE id = $3" )
  //      .execute( Tuple.of( price.getName(), price.getVersion(), id ) )
  //.onItem()
  //               .transform( pgRow -> pgRow.rowCount() == 1 );
  //    }*


  public Uni< Boolean > updateRow( Long id, Price price )
    {

      return client.preparedQuery( "UPDATE price SET name=$1 , version = $2  WHERE id = $3" )
                   .execute( Tuple.of( price.getName(), price.getVersion(), id ) )
                   .onItem()
                   .transform( pgRow -> pgRow.rowCount() == 1 );
    }





  public static Price from( Row row )
    {
      return new Price( row.getLong( "id" ), row.getLong( "version" ) );
    }


  public static Price fromup( Row row )
    {
      return new Price( row.getLong( "id" ), row.getString( "name" ), row.getLong( "version" ) );
    }


  public static Price fromid( Row row )
    {
      return new Price( row.getLong( "id" ) );
    }


  public Uni< Long > save( PgPool client )
    {

      Price price1 = new Price();
      price1.setId( 116l );
      price1.setName( "dddd" );
      return client.preparedQuery( "INSERT INTO price (name) VALUES ($1) RETURNING id" )
                   .execute( Tuple.of( price1.name ) )
                   .onItem()
                   .transform( pgRowSet -> pgRowSet.iterator().next().getLong( "id" ) );
    }





}
