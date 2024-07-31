package org.acme.hibernate.orm.panache.shoptest.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import org.acme.hibernate.orm.panache.shoptest.Person;
import org.acme.hibernate.orm.panache.shoptest.repo.PersonRepository;

@ApplicationScoped
@Path("/person")
@Transactional(Transactional.TxType.SUPPORTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {


  @Inject
  PersonRepository personRepository;

  @GET
  public List<Person> listAllArtists() {
    return personRepository.listAllPersonsSorted();
  }


  @POST
  @Transactional
  public Response create(Person person,@Context
  UriInfo uriInfo) {
   personRepository.persist(person);
UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(person.getPersonid()));


    return Response.created(uri.build()).build();
  }





}
