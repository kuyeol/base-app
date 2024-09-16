package org.acme.neo4j;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.neo4j.driver.Driver;
import org.neo4j.driver.reactive.ReactiveResult;
import org.neo4j.driver.reactive.ReactiveSession;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("reactivefruits")
public class ReactiveFruitResource {

    @Inject
    Driver driver;



  static Uni<Void> sessionFinalizer(ReactiveSession session) {
    return Uni.createFrom().publisher(session.close());
  }

  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public Multi<String> get() {
    // Create a stream from a resource we can close in a finalizer...
    return Multi.createFrom().resource(() -> driver.session(ReactiveSession.class),
                                       session -> session.executeRead(tx -> {
                                         var result = tx.run("MATCH (f:Fruit) RETURN f.name as name ORDER BY f.name");
                                         return Multi.createFrom().publisher(result).flatMap(ReactiveResult::records);
                                       }))
                .withFinalizer(ReactiveFruitResource::sessionFinalizer)
                .map(record -> record.get("name").asString());
  }







}
