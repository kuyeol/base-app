package me.escoffier.quarkus.supes.hash;

import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import me.escoffier.quarkus.supes.user.Role;
import me.escoffier.quarkus.supes.user.User;


@ApplicationScoped
public class HashProvider {

  @Inject
  HashLoader cache;

  public User getTopHeroes() {
    return cache.getOrSetIfAbsent("fiel", () -> {
      // Dumb approach, don't do this
      return new User(Role.<Role>listAll().stream().sorted((o1, o2) -> Integer.compare(o2.idx, o1.idx)).peek(h -> {
        nap();
      }).collect(Collectors.toList()));
    });
  }




  private void nap() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
