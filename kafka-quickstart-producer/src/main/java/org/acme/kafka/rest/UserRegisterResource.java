package org.acme.kafka.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.kafka.debezium.EventService;
import org.acme.kafka.debezium.UserRegisterForm;


@Path("account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRegisterResource
{

  //@Inject
  //EventService eventService;


  @GET
  public String hello()
    {
      return "Hello World!";
    }

  //
  //@POST
  //@Transactional
  //public AccountOperationResponse addOrder( RegisterRequest registerRequest )
  //  {
  //    UserRegisterForm user = registerRequest.toRegister();
  //    user = eventService.registerUser( user );
  //    return AccountOperationResponse.from( user );
  //  }
  //




}
