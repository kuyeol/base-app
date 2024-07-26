package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
public class MyEntityResource {

  private Person person;

  //
  @Inject
  EntityManager em;


  @POST
  @Path("/create")
  @Transactional
  public Response create(String name) {
name="asdfasd";
    Person ps = new Person();
    ps.setUsername(name);
    ps.setParent(person);
    em.persist(ps);
    return Response.ok(ps).build();
  }

  @GET
  public String findPerson() {

    return "";
  }
}