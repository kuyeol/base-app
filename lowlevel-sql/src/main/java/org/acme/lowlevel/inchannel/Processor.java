package org.acme.lowlevel.inchannel;


import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Random;
import org.acme.lowlevel.model.User;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class Processor
{

  private final static BigDecimal MINIMUM_PRICE = new BigDecimal( "30" );

  private final        Random     random        = new Random();


  public Multi< User > generate()
  {

    System.out.println( "generate" );
    String S    = "S";
    User   user = new User();
    return Multi.createFrom()
                .items( user );
  }








}
