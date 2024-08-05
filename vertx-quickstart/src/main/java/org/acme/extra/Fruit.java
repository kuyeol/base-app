package org.acme.extra;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import java.util.ArrayList;
import java.util.List;

public class Fruit {

  public Long id;

  public String name;

  public String color;

public Fruit() {}


  public Fruit(String name,String color) {
    this.name = name;
    this.color = color;
  }

  public Fruit(String name) {
    this.name = name;
  }

  public Fruit(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  public Fruit(Long id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

public static Uni<List<Fruit>> findAll(PgPool client) {
    return client.query("SELECT id, name ,color FROM fruits ORDER BY name ASC").execute()
                 .onItem().transform(pgRowSet -> {
          List<Fruit> list = new ArrayList<>(pgRowSet.size());
          for (Row row : pgRowSet) {
            list.add(from(row));
          }
          return list;
        });
  }

  public static Uni<Fruit> findById(PgPool client, Long id) {


    return client.preparedQuery("SELECT id, name ,color FROM fruits WHERE id = $1").execute(Tuple.of(id))
                 .onItem().transform(RowSet::iterator)
                 .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
  }

  public Uni<Long> save(PgPool client) {
    return client.preparedQuery("INSERT INTO fruits (name,color) VALUES ($1,$2) RETURNING (id)").execute(Tuple.of(name,color))
                 .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
  }

  public Uni<Boolean> update(PgPool client) {
    return client.preparedQuery("UPDATE fruits SET name ,color = $2,$3 WHERE id = $1").execute(Tuple.of(id, name,color))
                 .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
  }

  public static Uni<Boolean> delete(PgPool client, Long id) {
    return client.preparedQuery("DELETE FROM fruits WHERE id = $1").execute(Tuple.of(id))
                 .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
  }

  private static Fruit from(Row row) {
    return new Fruit(row.getLong("id"), row.getString("name"),row.getString("color"));
  }




}
