package org.acme.panache;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import io.smallrye.reactive.messaging.kafka.transactions.KafkaTransactions;
import io.vertx.mutiny.core.Context;
import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.Properties;
import java.util.concurrent.CompletionStage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.hibernate.reactive.mutiny.Mutiny;


@Path("KAFKA")
public class KafkaEndpoint
{

  //
  //@Channel("acc")
  //Emitter< Price > emitter;
  //
  @Channel("acc")
  Emitter< Record< Long, Price > > recordEmitter;

  @Channel("kafka")
  KafkaTransactions< Price > kafkaTx;

  //
  //@POST
  //@Produces(MediaType.APPLICATION_JSON)
  //public CompletionStage< Void > post( Price price )
  //  {
  //    return emitter.send( price );
  //  }


  @POST
  @Path("recod")
  @Produces(MediaType.APPLICATION_JSON)
  public CompletionStage< Void > send( Price payload )
    {
      Long id = payload.getId();
      return recordEmitter.send( Record.of( id, payload ) );
    }


  @Inject
  Mutiny.SessionFactory sf;


  @POST
  @Path("/fruits")
  @Consumes(MediaType.APPLICATION_JSON)
  @Bulkhead(1)
  public Uni< Void > post( Price fruit )
    {
      Context context = Vertx.currentContext();
      Properties props = new Properties();
      props.setProperty( "bootstrap.servers", "localhost:9092" );
      props.setProperty( "acks", "all" );
      props.setProperty( "retries", "0" );`
      props.setProperty( "batch.size", "16384" );
      props.setProperty( "linger.ms", 1 );
      props.setProperty( "buffer.memory", "33554432" );
      props.setProperty( "key.serializer", "org.apache.kafka.common.serialization.StringSerializer" );
      props.setProperty( "value.serializer", "org.apache.kafka.common.serialization.StringSerializer" );

      ConsumerRecord< ?, ? > metadatas;
      IncomingKafkaRecordMetadata< String, Double > metadata;

      if ( metadata != null ) {
        // The topic
        String topic = metadata.getTopic();

        // The key
        String key = metadata.getKey();

        // The timestamp
        Instant timestamp = metadata.getTimestamp();

        // The underlying record
        ConsumerRecord< String, Double > record = metadata.getRecord();

        // ...
      }
      return sf.withTransaction( session -> kafkaTx.withTransaction( emitter -> session.persist( fruit )
                                                                                       .invoke( () -> emitter.send( fruit ) ) )
                                                   .emitOn( context::runOnContext ) );
    }





}
