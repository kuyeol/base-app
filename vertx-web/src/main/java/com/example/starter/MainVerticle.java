package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.SqlConnectOptions;

public class MainVerticle extends AbstractVerticle {

  private static final int PORT=5432;
  private static final String HOST="jdbc:postgresql://182.218.135.229";
  private static final String DB="quarkus";
  private static final String ID="quarkus";
  private static final String PW="quarkus";
  static PgConnectOptions options;

  public static void main(String[] args) {


        options = new PgConnectOptions()
        .setPort(PORT)
        .setHost(HOST)
        .setDatabase(DB)
        .setUser(ID)
        .setPassword(PW);
    // Uncomment for MySQL
    //    MySQLContainer<?> mysql = new MySQLContainer<>();
    //    mysql.start();
    //    MySQLConnectOptions options = new MySQLConnectOptions()
    //      .setPort(mysql.getMappedPort(3306))
    //      .setHost(mysql.getContainerIpAddress())
    //      .setDatabase(mysql.getDatabaseName())
    //      .setUser(mysql.getUsername())
    //      .setPassword(mysql.getPassword());
    PoolOptions poolOptions = new PoolOptions()
        .setMaxSize(5);

    SqlClient client = PgBuilder
        .client()
        .with(poolOptions)
        .connectingTo(options)
        .build();


  }







@Override
public void start() throws
    Exception {
  Pool pool = Pool.pool(vertx, options, new PoolOptions().setMaxSize(4));

  // create a test table
  pool.query("create table test(id int primary key, name varchar(255))")
      .execute()
      .compose(r ->
          // insert some test data
          pool
              .query("insert into test values (1, 'Hello'), (2, 'World')")
              .execute()
      ).compose(r ->
          // query some data
          pool
              .query("select * from test")
              .execute()
      ).onSuccess(rows -> {
        for (Row row : rows) {
          System.out.println("row = " + row.toJson());
        }
      }).onFailure(Throwable::printStackTrace);
}

//vertx.createHttpServer().requestHandler(req -> {
//  req.response()
//    .putHeader("content-type", "text/plain")
//    .end("Hello from Vert.x!");
//}).listen(8888).onComplete(http -> {
//  if (http.succeeded()) {
//    startPromise.complete();
//    System.out.println("HTTP server started on port 8888");
//  } else {
//    startPromise.fail(http.cause());
//  }
//});
  }

