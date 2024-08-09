package org.acme.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.string.StringCommands;
import  io.quarkus.redis.datasource.value.SetArgs;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.function.Supplier;

@ApplicationScoped
public class RedisController {

  private final StringCommands<String, FruitCache> fruitCommands;


  public RedisController(RedisDataSource rds) {
    this.fruitCommands = rds.string(FruitCache.class);
  }

  public FruitCache getFruitKey(String key) {

    return fruitCommands.get(key);
  }

  public void set(String key, FruitCache result) {
    fruitCommands.set(key, result,
        (io.quarkus.redis.datasource.string.SetArgs) new SetArgs().ex(Duration.ofSeconds(10)));
  }

  public void delKey(String key) {
    fruitCommands.getdel(key);
  }

  public FruitCache getOrSetIfAbsent(String key, Supplier<FruitCache> supplier) {

    var cashed = getFruitKey(key);
    if (cashed != null) {
      return cashed;
    } else {
      var rs = supplier.get();
      set(key, rs);
      return rs;
    }
  }
}
