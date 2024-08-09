package com.ibm.rest.response.collections;

import com.ibm.rest.response.enity.Customer;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("customers")
public class CustomerResource {

    @GET
    public Response findAll() {
        List<Customer> customers = List.of(
                new Customer(1, "Subramaian", "Coimbatore"),
                new Customer(2, "Murugan", "Coimbatore"),
                new Customer(3, "Karthik", "Chennai")
        );




        return Response.ok().entity(customers).header("secret", "223klwewerwr").build();
    }


    @POST
    public Response create(Customer customer) {
        Customer newCustomer = new Customer();
            newCustomer.setCustomerId(customer.getCustomerId());
            newCustomer.setCustomerName(customer.getCustomerName());
            newCustomer.setCity(customer.getCity());
            return Response.ok().entity(newCustomer).build();
    }


    @DELETE
    public void delete(){
        System.out.println("Delete");
    }
}
