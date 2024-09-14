package org.acme.kafka.streams.aggregator.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path( "hello" )
public class Hello
{

  @GET
  public Response hello()
    {
      return Response.ok( "Hello World!" ).build();
    }



}
