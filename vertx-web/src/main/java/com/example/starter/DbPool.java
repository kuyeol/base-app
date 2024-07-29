package com.example.starter;

import io.vertx.core.json.JsonObject;

public class DbPool {



public void connect() {
  final JsonObject config = new JsonObject()
      .put("jdbcUrl", "jdbc:h2:~/test")
      .put("datasourceName", "pool-name")
      .put("username", "sa")
      .put("password", "")
      .put("max_pool_size", 16);
}




  //JDBCPool pool = JDBCPool.pool(vertx, config);

}
