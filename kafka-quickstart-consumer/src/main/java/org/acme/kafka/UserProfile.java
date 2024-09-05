package org.acme.kafka;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "userprofile")
public class UserProfile {

public ObjectId id;
public String name;
public String email;
public String phone;
public String state;



}
