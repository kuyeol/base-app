package org.acme.hibernate.reactive.resource;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import org.acme.hibernate.reactive.model.InitDatabase;
import org.acme.hibernate.reactive.model.Product;
import org.acme.hibernate.reactive.model.ProductCategory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/CustomerOrderServicesWeb/jaxrs/Product")
@ApplicationScoped
@Produces("application/json")
public class ProductResource {

  @Inject
  io.vertx.mutiny.pgclient.PgPool client;

  @Inject
  private InitDatabase initDatabase;

  @PostConstruct
  public void config() {
    initDatabase.config();
  }

  @GET
  public CompletionStage<List<Product>> getProductsByCategory(
      @QueryParam(value = "categoryId")
      int categoryId) {
    System.out.println("/CustomerOrderServicesWeb/jaxrs/Product invoked in Quarkus reactive catalog service");

    CompletionStage<List<Product>> products = getProducts();
    CompletionStage<List<ProductCategory>> productCategories = getProductCategories();
    Long categoryIdLong = new Long(categoryId);

    return products.thenCombine(productCategories, (productsRead, productCategoriesRead) -> {
      List<Product> output = new ArrayList<Product>();
      List<ProductCategory> productCategoriesFiltered = productCategoriesRead.stream().filter(productCategoryRead -> {
        return productCategoryRead.categoryid.equals(categoryIdLong);
      }).collect(Collectors.toList());

      productCategoriesFiltered.forEach(productCategoryFiltered -> {
        productsRead.forEach(productRead -> {
          if (productRead.id.equals(productCategoryFiltered.productid)) {
            output.add(productRead);
          }
        });
      });
      return output;
    });
  }

  public CompletionStage<List<Product>> getProducts() {
    String statement = "SELECT id, price, name, description, image FROM product";
    return client.preparedQuery(statement).execute()
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 //.exceptionally(throwable -> {
                 //    System.out.println(throwable);
                 //    return null;
                 //})
                 .onItem().transform(rows -> {
          List<Product> products = new ArrayList<>(rows.size());
          rows.forEach(row -> products.add(fromRow(row)));
          return products;
        }).subscribeAsCompletionStage();
  }

  public CompletionStage<List<ProductCategory>> getProductCategories() {
    String statement = "SELECT id, productid, categoryid FROM productcategory";
    return client.preparedQuery(statement).execute()
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 //.exceptionally(throwable -> {
                 //    System.out.println(throwable);
                 //    return null;
                 //})
                 .onItem().transform(rows -> {
          List<ProductCategory> productCategories = new ArrayList<>(rows.size());
          rows.forEach(row -> productCategories.add(fromRowProductCategory(row)));
          return productCategories;
        }).subscribeAsCompletionStage();
  }

  private static ProductCategory fromRowProductCategory(Row row) {
    ProductCategory productCategory = new ProductCategory();
    productCategory.id = row.getLong("id");
    productCategory.productid = row.getLong("productid");
    productCategory.categoryid = row.getLong("categoryid");
    return productCategory;
  }

  private static Product fromRow(Row row) {
    Product product = new Product();
    product.id = row.getLong("id");
    product.price = row.getBigDecimal("price");
    product.name = row.getString("name");
    product.description = row.getString("description");
    product.image = row.getString("image");
    return product;
  }

  @PUT
  @Consumes("application/json")
  @Produces("application/json")
  @Path("{id}")
  public CompletionStage<Product> update(
      @PathParam("id")
      Long id, Product updatedProduct) {
    System.out.println("@PUT updateProduct invoked in Quarkus reactive catalog service");

    String statement = "UPDATE product SET price = $1 WHERE ID = $2";

    return client.preparedQuery(statement).execute(Tuple.of(updatedProduct.price, id))
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 //.exceptionally(throwable -> {
                 //    System.out.println(throwable);
                 //    return null;
                 //})
                 .onItem().transform(rows -> {
          sendMessageToKafka(id, updatedProduct.price);
          return updatedProduct;
        }).subscribeAsCompletionStage();
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  @Path("new/{id}")
  public CompletionStage<ProductCategory> updates(
      @PathParam("id")
      Long id, ProductCategory updatedProduct) {
    System.out.println("@PUT updateProduct invoked in Quarkus reactive catalog service");

    // TODO: refactoring
    //Product product = new Product();
    //product.price = products.price;
    //product.name = products.name;
    //product.description = products.description;
    //product.image = products.image;
    //
    //

    String statement2 = "INSERT INTO productcategory (id, productid, categoryid) VALUES ($1, $2, $3) RETURNING (id)";
    //"INSERT INTO productcategory (id, productid, categoryid) VALUES ($1, $2, $3) RETURNING (id)"

    return client.preparedQuery(statement2).execute(Tuple.of(updatedProduct.productid, id))
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 //.exceptionally(throwable -> {
                 //    System.out.println(throwable);
                 //    return null;
                 //})
                 .onItem().transform(rows -> {
          sendMessageToKafka(id, updatedProduct.productid);
          return updatedProduct;
        }).subscribeAsCompletionStage();
  }

  @ConfigProperty(name = "kafka.bootstrap.servers")
  String kafkaBootstrapServer;

  @Inject
  Vertx vertx;

  private KafkaProducer<String, String> producer;

  @PostConstruct
  void initKafkaClient() {
    Map<String, String> config = new HashMap<>();
    config.put("bootstrap.servers", kafkaBootstrapServer);
    config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    System.out.println("bootstrapping Kafka with config: " + config);

    producer = KafkaProducer.create(vertx, config);
  }

  public void sendMessageToKafka(Long productId, BigDecimal price) {
    String productIdString = productId.toString();
    String priceString = price.toString();
    try {
      KafkaProducerRecord<String, String> record =
          KafkaProducerRecord.create("product-price-updated", productIdString + "#" + priceString);
      producer.write(record, done -> System.out.println(
          "Kafka message sent: product-price-updated - " + productIdString + "#" + priceString));
    } catch (Exception e) {
    }
  }

  public void sendMessageToKafka(Long productId, Long id) {
    String productIdString = productId.toString();
    String priceString = id.toString();
    try {
      KafkaProducerRecord<String, String> record =
          KafkaProducerRecord.create("product-price-updated", productIdString + "#" + priceString);
      producer.write(record, done -> System.out.println(
          "Kafka message sent: product-price-updated - " + productIdString + "#" + priceString));
    } catch (Exception e) {
    }
  }

  @POST
  @Path("/CREATE")
  public CompletionStage<Product> addProduct(Product products) {
    Product product = new Product();
    product.price = products.price;
    product.name = products.name;
    product.description = products.description;
    product.image = products.image;

    return client.preparedQuery(
                     "INSERT INTO product (id, price, name, description, image) VALUES ($1, $2, $3, $4, " + "$5) RETURNING (id)")
                 .execute(Tuple.of(products.id, products.price, products.name, products.description, products.image))
                 // to be done: change to Mutiny
                 //.toCompletableFuture()
                 //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                 .onItem()
                 .transform(pgRowSet -> {
                   product.id = ((RowSet<Row>) pgRowSet).iterator().next().getLong("id");
                   return product;
                 })
                 .subscribeAsCompletionStage()
        // to be done: change to Mutiny
        //.exceptionally(throwable -> {
        //    System.out.println(throwable);
        //    throw new RuntimeException();
        //})
        ;
  }
}