package org.acme.panache;

import jakarta.ws.rs.PathParam;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.smallrye.mutiny.Uni;

@Path("/prices")
public class PriceResource {

    @GET
    public Uni<List<Price>> getAllPrices() {

        return null;
    }


    @GET
    @Path( "findId/{id}" )
    public Uni<List<Price>> getSinge(@PathParam( "id" ) int id){

      return null;
    }


}
