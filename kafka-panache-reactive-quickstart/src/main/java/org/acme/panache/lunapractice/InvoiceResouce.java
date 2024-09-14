package org.acme.panache.lunapractice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.acme.panache.Price;
import org.acme.panache.PriceRepository;



@Path("invoice")
@ApplicationScoped
public class InvoiceResouce
{


@Inject
  LowleveConnect lowleveConnect;


  @GET
  @Path( "low" )
  public void lowquery(){
lowleveConnect.test();
  }



  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Invoice getInvoice()
  {

    String    id    = "customer";
    int       price = 1000;
    LocalDate ts    = LocalDate.now();

    return new Invoice( id, price, ts );
  }


  @Inject
  ObjectMapper mapper;


  @GET
  @Path("LIST")
  public ObjectNode InvoiceList() throws InterruptedException
  {

    String     id    = "customer";
    int        price = 1000;
    LocalDate  ts    = LocalDate.now();
    ObjectNode list  = mapper.createObjectNode();

    list.put( id, price );
    list.put( id + "1", price + 2 );
    list.put( id + "2", price + 1 );

    mapper.createObjectNode()
          .pojoNode( new Invoice( id, price, ts ) );
    list.pojoNode( new Invoice( id, price, ts ) );

    String thread = Thread.currentThread()
                          .getName();
    Thread.sleep( 1000 );
    String thread1 = Thread.currentThread()
                           .getName();
    Thread.sleep( 1000 );
    list.put( thread, price + 1 );
    list.put( thread1, price + 1 );

    System.out.println( thread );
    System.out.println( thread1 );

    return list;
  }


  @GET
  @Path("CompletableFuture")
  @Produces(MediaType.APPLICATION_JSON)
  public CompletableFuture< Invoice > csI() throws InterruptedException
  {

    return CompletableFuture.completedFuture( getInvoice() );

  }


  @GET
  @Path("block")
  @Blocking
  public String block()
  throws InterruptedException
  {
    // String th = Thread.currentThread().getName();
    Thread.sleep( 1000 );
    return Thread.currentThread()
                 .getName();

  }


  @GET
  @Path("non-block")
  public Uni< Invoice > nonBlock()
  {

    return Uni.createFrom()
              .item( getInvoice() )
              .onItem()
              .delayIt()
              .by( Duration.ofMillis( 100 ) )
              .onItem()
              .transform( i ->
                            {
                              String A = i.id;
                              System.out.println( " -> [ " + A + " ] <- " );
                              return getInvoice();
                            } );
  }


  @Route(methods = Route.HttpMethod.GET)
  public String hellos( RoutingContext rct )
  {

    return rct.response()
              .end( "hellodddddddddddd" )
              .toString();
  }


  @Route(path = "hellos")
  public Uni< String > uniUri( RoutingContext rct )
  {

    return Uni.createFrom()
              .item( hellos( rct ) );
  }


  @Route(produces = "application/json")
  public Invoice createIn( @Body Invoice invoice, @Param("price") Optional< String > parameter )
  {

    invoice = new Invoice( invoice.id, parameter.map( Integer::valueOf )
                                                .orElse( 123 ), LocalDate.now() );

    return invoice;
  }


  @Inject
  PriceRepository priceRepository;


  @GET
  @Path("findid/{id}")
  public Uni< Price > findID( @PathParam("id") String id )
  {

    return (Uni< Price >) priceRepository.find( id );
  }


  @GET
  @Path("findAll")
  public Multi<Price> findStream(){
    return (Multi< Price >) priceRepository.findAll();
  }
}
