package org.acme.kafka;


import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.smallrye.reactive.messaging.kafka.transactions.KafkaTransactions;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;
import org.acme.kafka.caller.DatabaseInvoker;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;


@Path("user")
public class UserResource
{

  @Inject
  UserRepository userRepository;

  private final Emitter< User >              emitter;

  private final KafkaTransactions< Integer > txProducer;

  @Inject
  MsgConsumer msgConsumer;


  @Inject
  public UserResource( @Channel("msg") Emitter< User > emitter,
      @Channel("prices-out") @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 500) KafkaTransactions< Integer > txProducer )
  {

    this.emitter = emitter; this.txProducer = txProducer;
  }


  @Incoming("prices-in")
  public Uni< Void > emitInTransaction( Message< ConsumerRecords< String, Integer > > batch )
  {

    return txProducer.withTransactionAndAck( batch, emitter ->
      {

        for ( ConsumerRecord< String, Integer > record : batch.getPayload() ) {
          emitter.send( KafkaRecord.of( record.key(), record.value() + 1 ) );
        } return Uni.createFrom()
                    .voidItem();
      } );
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response createUser( @Valid User user )
  {

    UUID   uuid         = UuidCreator.getNameBasedSha1( UuidNamespace.NAMESPACE_URL, user.getUserId() );
    String uuidToString = uuid.toString();

    user.setVersion( UUID.randomUUID() ); user.setId( uuid );

    Message< User > sender = Message.of( user );
    sender.ack(); user.setTs();
    emitter.send( sender );

    return Response.created( URI.create( "user" + user.getUserId() ) )
                   .build();
  }


  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  @Path("PUT")
  public Response upUser( @Valid User user )
  {

    String a            = String.valueOf(  user.getId() );
    UUID   uuid         = UuidCreator.getNameBasedSha1( UuidNamespace.NAMESPACE_URL, a );
    String uuidAsString = uuid.toString();

    user.setId( uuid );
    user.setVersion( user.getVersion() );
    Message< User > sender = Message.of( user );
    sender.ack();
    userRepository.persist( user ); emitter.send( user );
    msgConsumer.addOrder( user );
    msgConsumer.receive( sender );

    return Response.created( URI.create( "user" + user.getUserId() ) )
                   .build();
  }


  @Inject
  DatabaseInvoker databaseInvoker;


@GET
  public void call(){
  databaseInvoker.call();
}


}
