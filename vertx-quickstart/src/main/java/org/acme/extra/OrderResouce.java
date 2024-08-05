package org.acme.extra;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/CustomerOrderServicesWeb/jaxrs/Category")
@ApplicationScoped
@Produces("application/json")

public class OrderResouce {

@GET
public String hi(){
  return "hi";
}



@Autowired
  @Inject
  private  OrderService orderService;

  @PostConstruct
  public void config() {
    orderService.config();
  }



}
