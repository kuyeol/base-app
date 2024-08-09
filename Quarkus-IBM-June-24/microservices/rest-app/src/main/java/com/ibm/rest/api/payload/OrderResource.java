package com.ibm.rest.api.payload;

import com.ibm.rest.api.payload.entity.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("orders")
public class OrderResource {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Order save(Order order) {
    System.out.println(order);
    Order order2 = new Order();
    order2.setOrderId(order.getOrderId());
    order2.setOrderValue(order.getOrderValue());

    return order2;
  }

  @Path("vaild/{id}/{pass}")
  @GET
  public String auth(
      @PathParam("id")
      String id,
      @PathParam("pass")
      String pass) {

    String identity = "admin";

    ArrayList<String> admin = new ArrayList<>();
    admin.add("pass");

    System.out.println(admin);

    if(id.equals(identity)) {
      try {

        if (pass.equals(admin.get(0))) {
          return "correct";
        } else {

          return "NOT CORRECT";
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return "false";
  }
}
