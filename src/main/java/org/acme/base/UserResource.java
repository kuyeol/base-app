package org.acme.base;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/users")
public class UserResource {

  @Inject
  AccountService service;

  @GET
  @RolesAllowed("user")
  @Path("/me")
  @Produces(MediaType.TEXT_PLAIN)
  public String me(
      @Context
      SecurityContext securityContext) {
    return securityContext.getUserPrincipal().getName();
  }








  @POST
  @Transactional
  @PermitAll
  public Uni<Response> createInService(Domain domain) {

    return service.create(domain);
  }

}
