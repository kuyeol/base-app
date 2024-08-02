package org.acme.extra;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FruitService {


  @Inject
  PgPool client;




  public Uni<Long> addFruit(Fruit fruit) {

return  client.preparedQuery("INSERT INTO fruits (name,color) VALUES ($1,$2) RETURNING (id)").execute(Tuple.of(fruit.name,
    fruit.color)).onItem().transform(row->row.iterator().next().getLong("id"));



}

  public  Uni<Fruit> findByName(String names) {

    return client.preparedQuery("SELECT  name, color  FROM fruits WHERE name = $1").execute(Tuple.of(names))
                 .onItem().transform(RowSet::iterator)
                 .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
  }

  public  Uni<Fruit> findByColor(String color) {

    return client.preparedQuery("SELECT  color, name , id FROM fruits WHERE color = $1").execute(Tuple.of(color))
                 .onItem().transform(RowSet::iterator)
                 .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
  }






  private static Fruit from(Row row) {
    return new Fruit( row.getString("name"),row.getString("color"));
  }


}
