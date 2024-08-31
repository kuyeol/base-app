package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.net.URI;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@Path("QU")

public class QueryResource
{

  private PgPool client;

  private PriceQuery priceQuery;


  public QueryResource( PgPool client, PriceQuery priceQuery )
    {
      this.client     = client;
      this.priceQuery = priceQuery;
    }


  @GET
  @Path("{id}")
  public Uni< Response > findSinge( Long id )
    {

      return priceQuery.findById( client, id )
                       .onItem()
                       .transform( price -> price != null
                                            ? Response.ok( price ).status( Response.Status.OK )
                                            : Response.status( Response.Status.NOT_FOUND ) )
                       .onItem()
                       .transform( ResponseBuilder::build );
    }


  @POST

  public Uni< Response > create( Price price )
    {
      return priceQuery.save( client )
                       .onItem()
                       .transform( id -> URI.create( "/QU/" + id ) )
                       .onItem()
                       .transform( uri -> Response.created( uri ).build() );
    }


  @PUT
  @Path("update/{id}")
  public Uni< Response > updatemutiny( Long id, Price price )
    {

      return priceQuery.updateRow( id, price )
                       .onItem()
                       .transform( updated -> updated ? Response.Status.OK : Response.Status.NOT_FOUND )
                       .onItem()
                       .transform( s -> Response.status( s ).build() );
    }





}