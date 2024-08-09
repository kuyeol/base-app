package org.acme.redis;

import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.stream.Collectors;
import org.acme.extra.Fruit;
import org.acme.extra.FruitResource;

@ApplicationScoped
public class RedisService {

  @Inject
  PgPool client;

  @Inject
  RedisController controller;
  @Inject
  Fruit fruit;
  @Inject
  FruitResource fruitResource;

  private void nap() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}