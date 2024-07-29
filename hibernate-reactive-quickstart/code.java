package org.acme.hibernate.reactive;

import io.vertx.mutiny.sqlclient.Row;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Path("/CustomerOrderServicesWeb/jaxrs/Category")
@ApplicationScoped
@Produces("application/json")
public class CategoryResource {

  @Inject
  io.vertx.mutiny.pgclient.PgPool client;

  //private static int MAXIMAL_DURATION = 5000;

 // @Inject
 // private InitDatabase initDatabase;

  @PostConstruct
  public void config() {

  }

  @GET
  public CompletionStage<List<Fruit>> get() {
    System.out.println("/CustomerOrderServicesWeb/jaxrs/Category invoked in Quarkus reactive catalog service");

    String statement = "SELECT id, name, parent FROM category";
    return client.preparedQuery(statement).execute()
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 //.exceptionally(throwable -> {
                 //    System.out.println(throwable);
                 //    return null;
                 //})
                 .onItem().transform(rows -> {
          List<Fruit> categories = new ArrayList<>(rows.size());
          rows.forEach(row -> categories.add(fromRow(row)));
          return addSubCategories(categories);
        })
                 .subscribeAsCompletionStage();
  }

  private List<Fruit> addSubCategories(List<Fruit> categories) {
    List<Fruit> potentialSubCategories = categories;
    return categories.stream()
                     .filter(category -> category.parent == Long.valueOf(0))
                     .map(category -> {
                       List<Fruit> subCategories = potentialSubCategories.stream()
                                                                            .filter(subCategory -> subCategory.parent == category.id)
                                                                            .collect(Collectors.toList());
                       category.setSubCategories(subCategories);
                       return category;
                     })
                     .collect(Collectors.toList());
  }

  private static Fruit fromRow(Row row) {
    Fruit category = new Fruit();
    category.setId(row.getLong("id")) ;
    category.setName(row.getString("name"));
    category.setParent(row.getLong("parent"));
    return category;
  }
}