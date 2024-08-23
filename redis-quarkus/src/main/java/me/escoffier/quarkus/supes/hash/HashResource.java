package me.escoffier.quarkus.supes.hash;

import java.util.Map;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.escoffier.quarkus.supes.Hero;
import me.escoffier.quarkus.supes.HeroService;
import me.escoffier.quarkus.supes.Ranking;
import me.escoffier.quarkus.supes.user.Role;
import me.escoffier.quarkus.supes.user.User;

@Path("/hash")
public class HashResource {


  private final HashProvider provider;
  private final HashLoader loader;

  public HashResource(HashLoader loader , HashProvider provider) {
    this.loader = loader;
    this.provider = provider;
  }



  @GET
  public User getTopHeroes() {
    return this.provider.getTopHeroes();
  }

  @POST
  @Path("/A")
 // @Produces(MediaType.APPLICATION_JSON)
  public void set() {

    String field;
    User value;
    field="asdfsd";
    value = new User();
    value.name="adsf";
    this.loader.setUser(field, value);
  }



}
