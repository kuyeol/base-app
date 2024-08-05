package org.acme.extra;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("fruits")
public class FruitResource {






  private final PgPool client;
  private final FruitService fruitService;
  private final boolean schemaCreate;

  public FruitResource(PgPool client, FruitService fruitService,
      @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
      boolean schemaCreate) {
    this.client = client;
    this.schemaCreate = schemaCreate;
    this.fruitService = fruitService;

  }

  //void initdb(
  //    @Observes
  //    StartupEvent ev) {
  //  if (schemaCreate) {
  //    client.query("DROP TABLE IF EXISTS fruits")
  //          .execute()
  //          .flatMap(r -> client.query(
  //              "CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL," + "color varchar(255))").execute())
  //          .flatMap(r -> client.query("INSERT INTO fruits (name,color) VALUES ('Orange','adsf')").execute())
  //          .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Pear')").execute())
  //          .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Apple')").execute())
  //          .await()
  //          .indefinitely();
  //  }
  //}

  @GET
  public Uni<Response> get() {
    return Fruit.findAll(client).onItem().transform(Response::ok).onItem().transform(ResponseBuilder::build);
  }

  @GET
  @Path("{id}")
  public Uni<Response> getSingle(Long id) {
    return Fruit.findById(client, id)
                .onItem()
                .transform(fruit -> fruit != null ? Response.ok(fruit) : Response.status(Status.NOT_FOUND))
                .onItem()
                .transform(ResponseBuilder::build);
  }

  @POST
  public Uni<Response> create(Fruit fruit) {
    return fruit.save(client)
                .onItem()
                .transform(id -> URI.create("/fruits/" + id))
                .onItem()
                .transform(uri -> Response.created(uri).build());
  }

  @PUT
  @Path("{id}")
  public Uni<Response> update(Long id, Fruit fruit) {
    return fruit.update(client)
                .onItem()
                .transform(updated -> updated ? Status.OK : Status.NOT_FOUND)
                .onItem()
                .transform(status -> Response.status(status).build());
  }

  @DELETE
  @Path("{id}")
  public Uni<Response> delete(Long id) {
    return Fruit.delete(client, id)
                .onItem()
                .transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
                .onItem()
                .transform(status -> Response.status(status).build());
  }

  @POST
  @Path("newadd")
  public Uni<Long> add(Fruit fruit) {

    return fruitService.addFruit(fruit);
  }

  @POST
  @Path("newquery")
  public  Uni<Fruit> search(String name) {

    return fruitService.findByName(name);
  }

  @POST
  @Path("newQc")
  public  Uni<Fruit> searchC(String name) {

    return fruitService.findByColor(name);
  }



}
