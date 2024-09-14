package org.acme.lowlevel.rest;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.lowlevel.model.User;
import org.acme.lowlevel.model.UserWithPanache;

import static jakarta.ws.rs.core.Response.Status.CREATED;


@Path("Panache")
@ApplicationScoped
public class PancheResource
{

  @GET
  @WithTransaction
  public Uni< List< UserWithPanache > > products()
  {

    return UserWithPanache.listAll();
  }

  @POST
  public Uni< Response > create(UserWithPanache user) {
    if (user == null || user.id != null) {
      throw new WebApplicationException( "Id was invalidly set on request.", 422);
    }

    return Panache.withTransaction( user::persist)
                  .replaceWith(Response.ok(user).status(CREATED)::build);
  }



  @PUT
  @Path("{userId}")
  @WithTransaction
  public Uni< UserWithPanache > upate(
      @PathParam("userId")
      Long id, @Valid User user )
  {

    return UserWithPanache.< UserWithPanache > findById( id )
                          .flatMap( u ->
                                      {

                                        if ( u == null ) {
                                          return Uni.createFrom()
                                                    .failure( new NotFoundException( "id is null" ) );
                                        } else {

                                          u.name = user.name;
                                          u.bio  = user.bio;
                                          u.age  = user.age;
                                          return u.persistAndFlush()
                                                  .map( __ -> u );

                                        }

                                      } );

  }





}
