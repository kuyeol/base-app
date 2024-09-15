package org.acme.lowlevel.inchannel;


import io.smallrye.mutiny.Multi;
import java.math.BigDecimal;
import java.util.Random;
import org.acme.lowlevel.model.User;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


public class Processor
{

  private final static BigDecimal MINIMUM_PRICE = new BigDecimal( "30" );

  private final        Random     random        = new Random();


  @Outgoing("raw-price-updates")
  public Multi< User > generate()
  {

    System.out.println("generate");
    String S="S";
    User user = new User();
    return Multi.createFrom().items(user);
  }


  @Incoming("raw-price-updates")
  @Outgoing("price-update")
  public User process( User update )
  {

    if ( update.age == update.age ) {
            update.age
          = update.age + random.nextInt( 10 );
    }
    return update;
  }





}
