package org.acme.kafka;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
public class HelloResource {


  @GET
  public String hello() {
    return "Hello World!";
  }

}
