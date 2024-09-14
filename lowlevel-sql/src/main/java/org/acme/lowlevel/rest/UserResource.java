package org.acme.lowlevel.rest;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.lowlevel.model.User;


@Path("user")
//ApplicationScoped
public class UserResource
{

  @Inject
  PgPool client;


  @GET
  @Path("search/{term}")
  public Multi< User > search( @PathParam("term") String term )
  {

    return client.preparedQuery( "SELECT  name, bio,age FROM user_table WHERE name ILIKE $1 OR bio ILIKE $1 " )
                 .execute( Tuple.of( "%" + term + "%" ) )
                 .toMulti()
                 .flatMap( Multi.createFrom()::iterable )
                 .map( User::fromFilte );
  }


  @POST
  public Uni< String > create( User user )
  {

    return client.preparedQuery( "INSERT INTO user_table (id, name,age, bio) values ($1,$2,$3,$4) RETURNING name" )
                 .execute( Tuple.of( user.id, user.name, user.age, user.bio ) )
                 .onItem()
                 .transform( row -> row.iterator()
                                       .next()
                                       .getValue( "name" )
                                       .toString() );
  }


  @DELETE
  @Path("{id}")
  public Uni< Boolean > deletS( @PathParam("id")
  Long id )
  {

    return client.preparedQuery( "DELETE FROM user_table WHERE id = $1" )
                 .execute( Tuple.of( id ) )
                 .onItem()
                 .transform( pgRowSet -> pgRowSet.rowCount() == 1 );
  }





}
