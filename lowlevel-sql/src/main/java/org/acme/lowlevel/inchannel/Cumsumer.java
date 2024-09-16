package org.acme.lowlevel.inchannel;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.lowlevel.model.User;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.resteasy.reactive.RestStreamElementType;


@Path("inbound")

@ApplicationScoped
public class Cumsumer
{

  @Channel("price-updates")
  Multi< User > priceUpdates;


  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @RestStreamElementType(MediaType.APPLICATION_JSON)
  public Multi< User > prices()
  {

    return priceUpdates;
  }


  @Inject
  Processor processor;


  @GET
  @Path("G")
  public void restGen1()
  {

    processor.generate();

  }



  @Incoming("usertable")
  @Outgoing("price-updates")
  public Uni< Void > restGen( Message< User > m )
  {

    System.out.println( "\n\n adsfsdfsdf" + m + "asdfsdfasdf  \n\n\n" );

    return Uni.createFrom()
              .voidItem();
  }





}
