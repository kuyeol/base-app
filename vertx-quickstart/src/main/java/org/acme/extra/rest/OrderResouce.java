package org.acme.extra.rest;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.acme.extra.service.OrderService;

@Path("/CustomerOrderServicesWeb/jaxrs/Category")
@ApplicationScoped
@Produces("application/json")

public class OrderResouce {

@GET
public String hi(){
  return "hi";
}




  @Inject
  private OrderService orderService;

  @PostConstruct
  public void config() {
    orderService.config();
  }



}
