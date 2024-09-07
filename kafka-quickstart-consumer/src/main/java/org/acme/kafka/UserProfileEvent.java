package org.acme.kafka;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserProfileEvent implements Profile, PanacheMongoRepository<UserProfile> {


  @Override
  public UserProfile saveOrUpdate(UserProfile userProfile) {
    return null;
  }

  @Override
  public UserProfile findByName(String name) {
    return find("name", name).firstResult();
  }
}
