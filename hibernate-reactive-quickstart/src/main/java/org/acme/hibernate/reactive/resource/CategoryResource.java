package org.acme.hibernate.reactive.resource;




import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.annotation.PostConstruct;


import io.vertx.mutiny.sqlclient.Row;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;
//import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.acme.hibernate.reactive.model.Category;
import org.acme.hibernate.reactive.model.InitDatabase;

@Path("/CustomerOrderServicesWeb/jaxrs/Category")
@ApplicationScoped
@Produces("application/json")
public class CategoryResource {

  @Inject
  io.vertx.mutiny.pgclient.PgPool client;

  //private static int MAXIMAL_DURATION = 5000;

  @Inject
  private InitDatabase initDatabase;

  @PostConstruct
  public void config() {
    initDatabase.config();
  }

  @GET
  public CompletionStage<List<Category>> getCategories() {
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
          List<Category> categories = new ArrayList<>(rows.size());
          rows.forEach(row -> categories.add(fromRow(row)));
          return addSubCategories(categories);
        })
                 .subscribeAsCompletionStage();
  }

  private List<Category> addSubCategories(List<Category> categories) {
    List<Category> potentialSubCategories = categories;
    return categories.stream()
                     .filter(category -> category.parent == Long.valueOf(0))
                     .map(category -> {
                       List<Category> subCategories = potentialSubCategories.stream()
                                                                            .filter(subCategory -> subCategory.parent
                                                                                == category.id)
                                                                            .collect(Collectors.toList());
                       category.setSubCategories(subCategories);
                       return category;
                     })
                     .collect(Collectors.toList());
  }





  @GET
  @Path("/sub")
  public CompletionStage<List<Category>> getSubCategories() {
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
          List<Category> categories = new ArrayList<>(rows.size());
          rows.forEach(row -> categories.add(fromRow(row)));
          return getSubCategories(categories);
        })
                 .subscribeAsCompletionStage();
  }


  private List<Category> getSubCategories(List<Category> categories) {
    List<Category> potentialSubCategories = categories;
    return categories.stream()
                     .filter(category -> category.parent != Long.valueOf(0))
                     .map(category -> {
                       List<Category> subCategories = potentialSubCategories.stream()
                                                                            .filter(subCategory -> subCategory.parent
                                                                                == category.id)
                                                                            .collect(Collectors.toList());
                       category.setSubCategories(subCategories);
                       return category;
                     })
                     .collect(Collectors.toList());
  }









  private static Category fromRow(Row row) {
    Category category = new Category();
    category.id = row.getLong("id");
    category.name = row.getString("name");
    category.parent = row.getLong("parent");
    return category;
  }
}