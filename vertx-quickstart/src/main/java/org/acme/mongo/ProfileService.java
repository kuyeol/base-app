package org.acme.mongo;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.Collection;
import java.util.List;

@Path("/mongo")
public class ProfileService {


@Inject
  Register register;

@GET
public Uni<List<UserProfile>> getProfiles() {
register.list().map(f->f.get(1));
  return register.list();
}



@POST
  public Uni<List<UserProfile>> addUser(UserProfile profile){

return register.add(profile).onItem().ignore().andSwitchTo(this::getProfiles);


}
}
