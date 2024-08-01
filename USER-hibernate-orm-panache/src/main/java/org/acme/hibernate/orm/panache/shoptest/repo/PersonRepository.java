package org.acme.hibernate.orm.panache.shoptest.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.acme.hibernate.orm.panache.shoptest.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {




  public List<Person> listAllPersonsSorted() {

    return listAll(Sort.ascending("email"));

  }





}
