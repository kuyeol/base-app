package org.acme.mongo;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;

@ApplicationScoped
public class Register {

  @Inject
  ReactiveMongoClient mongoClient;

  private final Pattern validUsername = Pattern.compile("\\w[\\w+|-]*");
  private final Pattern validDeviceId = Pattern.compile("\\w[\\w+|-]*");

  // Email regexp from https://www.owasp.org/index.php/OWASP_Validation_Regex_Repository
  private final Pattern validEmail =
      Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

  public Uni<Void> add(UserProfile fruit) {
    Document document = new Document()
        .append("id", fruit.getId())
        .append("name", fruit.getName())
        .append("description", fruit.getDescription()
        ).append("email", fruit.getEmail());

    return getCollection().insertOne(document).onItem().ignore().andContinueWithNull();
  }

  private ReactiveMongoCollection<Document> getCollection() {
    return mongoClient.getDatabase("profile").getCollection("userprofiles");
  }

  public Uni<List<UserProfile>> list() {

    return getCollection().find()
                          .map(doc -> {
                            UserProfile userProfile = new UserProfile();
                            userProfile.setId("****");
                            userProfile.setName(doc.getString("name"));
                            userProfile.setDescription(doc.getString("description"));
                            userProfile.setEmail("******");
                            return userProfile;
                          }).collect().asList();
  }
}
