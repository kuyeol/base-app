package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import org.acme.panache.model.User;


@Path( "user" )
public class UserResource

{

  private PgPool client;

  private UserQuery userQuery;
  public UserResource( PgPool client,UserQuery userQuery )
    {
      this.client     = client;
      this.userQuery = userQuery;
    }


  @POST
  public Uni< Response > create( User user)
    {
      return  userQuery.saves( client,user  )
                       .onItem()
                       .transform( id -> URI.create( "/QU/" + id ) )
                       .onItem()
                       .transform( uri -> Response.created( uri ).build() );
    }
}
