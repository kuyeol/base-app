package org.acme.kafka.model;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;


public class UserDeserializer extends ObjectMapperDeserializer<User> {
    public UserDeserializer() {
        super(User.class);
    }
}
