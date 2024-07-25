package org.acme.graphql.logic;

import java.util.List;
import org.acme.graphql.model.SocialGroup;
import org.acme.graphql.repository.SocialGroupRepository;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;



@GraphQLApi
public class SocialGroupFetcher {

  private SocialGroupRepository repository;

 SocialGroupFetcher(SocialGroupRepository repository) {
    this.repository = repository;
  }

  @Query("socialgroups")
  public List<SocialGroup> findAll() {
    return repository.findAllByCriteria();
  }

  @Query("socialgroups")
  public SocialGroup findById(@Name("id") Long id) {
    return repository.findByIdWithCriteria(id);
  }

}