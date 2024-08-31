package org.acme.panache.sedes;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.panache.Price;


public class PriceDeserializer extends ObjectMapperDeserializer< Price >
{

  public PriceDeserializer()
    {
      super( Price.class );
    }





}
