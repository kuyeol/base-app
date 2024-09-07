package org.acme.panache.sedes;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.panache.model.User;


public class UserDeserializer extends ObjectMapperDeserializer< User > {
    public UserDeserializer() {
        super(User.class);
    }
}
