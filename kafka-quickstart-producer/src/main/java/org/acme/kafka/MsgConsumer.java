package org.acme.kafka;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class MsgConsumer
{

  @Incoming("msg")
  @Outgoing("transactions")
  public KafkaRecord< String, User > receive( User user )
    {

      System.out.println( user );
      return KafkaRecord.of( UUID.randomUUID().toString(), user );
    }





}
