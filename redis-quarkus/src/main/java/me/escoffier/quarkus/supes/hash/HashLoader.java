package me.escoffier.quarkus.supes.hash;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.hash.HashCommands;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.enterprise.context.ApplicationScoped;
import me.escoffier.quarkus.supes.user.User;

@ApplicationScoped
public class HashLoader {


  private static final String MY_KEY ="table";

  private final HashCommands<String, String, User> commands;

  public HashLoader(RedisDataSource ds) {
    commands = ds.hash(User.class);
  }

  public void set(String field, User value) {
    commands.hset(MY_KEY, field, value);
  }

  public User get(String field) {
    return commands.hget(MY_KEY, field);
  }


  public User getOrSetIfAbsent(String key, Supplier<User> computation) {
    var cached = get(key);
    if (cached != null) {
      return cached;
    } else {
      var result = computation.get();
      set(key, result);
      return result;
    }
  }


public void setUser(String field, User value) {

  Map<String, String> newuser = new HashMap<>();
 newuser.put(field, value.name);

  commands.hset(MY_KEY, "field", (User) newuser);


}





}
