package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
public class Hello {

    @GET
    public String hello() {
        return "hello";
    }

}
