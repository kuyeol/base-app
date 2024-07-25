package org.acme.graphql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.graphql.model.User;



@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


}
