package me.escoffier.quarkus.supes;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HeroService {

  @Inject
  MyRedisCache cache;

  private Supplier<Ranking> rankingSupplier;



  public Ranking callset() {

    var rs = rankingSupplier.get();
    return rs;
  }

  public Ranking getTopHeroes() {
    return cache.getOrSetIfAbsent("heroTable", () -> {
      // Dumb approach, don't do this
      return new Ranking(
          Hero.<Hero>listAll().stream().sorted((o1, o2) -> Integer.compare(o2.level, o1.level)).peek(h -> {
            // do something very long...
            nap();
          }).limit(30).collect(Collectors.toList()));
    });
  }

  private void nap() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
