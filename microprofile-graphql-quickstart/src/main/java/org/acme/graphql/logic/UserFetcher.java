package org.acme.graphql.logic;

import java.util.List;
import org.acme.graphql.model.User;
import org.acme.graphql.repository.UserRepository;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class UserFetcher {

  private UserRepository repository;

  UserFetcher(UserRepository repository) {
    this.repository = repository;
  }

  @Query("user")
  public List<User> findAll() {
    return (List<User>) repository.listAll();
  }

  @Query("organization")
  public User findById(@Name("id") Long id) {
    return (User) repository.findById(id);
  }


  //

}