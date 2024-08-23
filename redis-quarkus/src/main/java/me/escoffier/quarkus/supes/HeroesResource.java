package me.escoffier.quarkus.supes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/heroes")
public class HeroesResource {

  private final HeroService service;

  public HeroesResource(HeroService service) {
    this.service = service;
  }

  @GET
  public Ranking getTopHeroes() {
    return this.service.getTopHeroes();
  }

  @GET
  @Path("/call")
  public Ranking getTopHeroesCall() {
    return this.service.callset();
  }
}
