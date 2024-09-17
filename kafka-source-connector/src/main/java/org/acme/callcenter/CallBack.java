package org.acme.callcenter;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class CallBack {
  private final Producer<String, String> producer;
  final String outTopic;

  //Producer Instance transfer to Constructor Parameter
  public CallBack(final Producer<String, String> producer, final String topic) {
    this.producer = producer;
    outTopic = topic;
  }

  //this example is method in dependency injection

  public void produceeMassage(final String message) {

    //message array process
    final String[] parts = message.split("-");
    final String key, value;

    if (parts.length > 1) {
      key = parts[0];
      value = parts[1];
    } else {
      key = "Key is Null";
      value = parts[0];
    }

    // record created
    final ProducerRecord<String, String> record = new ProducerRecord<>(outTopic, key, value);

    //brocker send record for callback function with lambda select to Instance
    producer.send(record, (metadata, exception) -> {
      //NO EXCEOPION PRINT TIMESTAMP
      if (exception == null) {
        System.out.println("Record wrritten to offset " + metadata.offset() + "TimeStamp " + metadata.timestamp());
        //EXCEPTION occured print stack err
      } else {
        System.out.println("An ERROR occurred");
        exception.printStackTrace(System.err);
      }
    });
  }

public void close() {
    producer.close();
}






}
