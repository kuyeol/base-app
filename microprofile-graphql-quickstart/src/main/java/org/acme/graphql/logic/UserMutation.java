package org.acme.graphql.logic;

import org.acme.graphql.model.User;
import org.acme.graphql.model.UserInput;
import org.acme.graphql.repository.UserRepository;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;

@GraphQLApi
public class UserMutation {



  UserRepository repository;

UserMutation(UserRepository repository) {
    this.repository = repository;
  }

  @Mutation("newOrganization")
  public User newOrganization(@Name("input")
  UserInput userInput) {
    User user = (new User(null, userInput.getName(), null));
    repository.persist(user);
    return user;
  }

}
