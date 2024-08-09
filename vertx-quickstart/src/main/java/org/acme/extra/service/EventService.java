package org.acme.extra.service;

import io.debezium.outbox.quarkus.ExportedEvent;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.extra.entity.Fruit;
import org.acme.extra.event.EventExport;

@ApplicationScoped
public class EventService {
  private final PgPool client;
  private final FruitService fruitService;

  public EventService(FruitService fruitService, PgPool client) {
    this.fruitService = fruitService;
    this.client = client;
  }

  @Inject
  Event<ExportedEvent<?, ?>> event;

  @Transactional
  public Fruit insertToEvent(Fruit fruit) {

    fruitService.addFruit(fruit);
    event.fire(new EventExport(fruit));

    return fruit;
  }

  //  public Uni<Response> create(Fruit fruit) {
  //    return fruit.save(client)
  //                .onItem()
  //                .transform(id -> URI.create("/fruits/" + id))
  //                .onItem()
  //                .transform(uri -> Response.created(uri).build());
  //
  //}
}