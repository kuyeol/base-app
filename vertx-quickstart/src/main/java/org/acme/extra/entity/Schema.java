package org.acme.extra.entity;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class Schema
{

  private final PgPool client;

  private final boolean schemaCreate;


  public Schema( PgPool client,
      @ConfigProperty(name = "myapp.schema.create", defaultValue = "true") boolean schemaCreate ){
    this.client       = client;
    this.schemaCreate = schemaCreate;
  }


  void onStart(@Observes
  StartupEvent ev) {
    if (schemaCreate) {
      initdb();
    }
  }

  private void initdb() {
    // TODO
  }


}
