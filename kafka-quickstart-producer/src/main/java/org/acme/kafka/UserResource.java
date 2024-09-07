package org.acme.kafka;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("user")
public class UserResource
{

  private final Emitter< User > emitter;


  @Inject
  public UserResource( @Channel("msg") Emitter< User > emitter )
    {
      this.emitter = emitter;
    }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response createUser( @Valid User user )
    {
      user.persist();
      emitter.send( user );
      return Response.created( URI.create( "user" + user.id ) ).build();
    }

@GET
  public String hl(){
    return emitter.toString();
}



}
