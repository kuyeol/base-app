package org.acme.kafka;


import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;
import io.debezium.outbox.quarkus.ExportedEvent;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class MsgConsumer
{

  @Inject
  UserRepository                 userRepository;

  @Inject
  Event< ExportedEvent< ?, ? > > event;


  @Incoming("msg")
  @Outgoing("transaction")
  @Transactional
  public KafkaRecord< String, User > receive( Message< User > user )
  {
    //User pay = new User();
    //pay = user.getPayload();
    //pay.getVersion();
    //UUID   uuid         = UuidCreator.getNameBasedSha1( UuidNamespace.NAMESPACE_URL, "pay.getUserId()" );
    //
    //String key = String.valueOf( uuid.timestamp() );

    userRepository.persist( user.getPayload() );
  //  event.fire( new CreateEvent( Instant.now(), user.getPayload() ) );
    //String key = String.valueOf( pay.getVersion() );

    return KafkaRecord.of("3fa85f64-5717-4562-b3fc-2c963f66afa6", user.getPayload() );
  }


  @Incoming("prices")
  public CompletionStage< Void > consume( Message< Double > price )
  {
    // process your price.
    System.out.println(
        "KafkaPriceMessageConsumer: consume " + price.getPayload() );

    // Acknowledge the incoming message (commit the offset)
    return price.ack();
  }


  public UserVerionValidation find( User user )
  {

    PanacheQuery< UserVerionValidation > validate = userRepository.find( "id", user.getId() )
                                                                  .project( UserVerionValidation.class );

    return validate.stream()
                   .findFirst()
                   .orElse( null );
  }


  public User addOrder( User order )
  {

    userRepository.persist( order );

    event.fire( new CreateEvent( Instant.now(), order ) );

    return order;
  }





}
