package org.acme.extra;

import jakarta.ws.rs.Consumes;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.core.Vertx;
import org.acme.extra.service.OrderService;


@Path("/hello")
public class StreamingResource {

    @Inject
    Vertx vertx;


    @Inject
    OrderService orderService;


@GET
@Produces(MediaType.SERVER_SENT_EVENTS)
@Consumes(MediaType.TEXT_PLAIN)
@Path("{name}/streaming")
public Multi< String > greeting( String name )
    {
        //orderService.plrun();
        return vertx.periodicStream( 2000 )
                    .toMulti()
                    .map( l -> String.format( "Hello %s! (%s)%n", orderService.plrun(), new Date() ) );
    }
}
