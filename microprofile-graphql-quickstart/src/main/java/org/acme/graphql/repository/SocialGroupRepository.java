package org.acme.graphql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import io.smallrye.graphql.api.Context;
import java.util.List;
import org.acme.graphql.model.SocialGroup;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;

import jakarta.persistence.criteria.*;


@ApplicationScoped
public class SocialGroupRepository implements PanacheRepository<SocialGroup> {

  private EntityManager em;
  private Context context;

  public SocialGroupRepository(Context context, EntityManager em) {

    this.em = em;
    this.context = context;
  }

  public List<SocialGroup> findAllByCriteria() {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<SocialGroup> criteriaQuery = builder.createQuery(SocialGroup.class);

    Root<SocialGroup> root = criteriaQuery.from(SocialGroup.class);

    DataFetchingEnvironment dfe = context.unwrap(DataFetchingEnvironment.class);

    DataFetchingFieldSelectionSet selectionSet = dfe.getSelectionSet();


    if (selectionSet.contains("user")) {
      root.fetch("user", JoinType.LEFT);
    }
    criteriaQuery.select(root).distinct(true);
    return em.createQuery(criteriaQuery).getResultList();
  }

  public SocialGroup findByIdWithCriteria(Long id) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<SocialGroup> criteriaQuery = builder.createQuery(SocialGroup.class);
    Root<SocialGroup> root = criteriaQuery.from(SocialGroup.class);
    DataFetchingEnvironment dfe = context.unwrap(DataFetchingEnvironment.class);
    DataFetchingFieldSelectionSet selectionSet = dfe.getSelectionSet();

    if (selectionSet.contains("user")) {
      root.fetch("user", JoinType.LEFT);
    }
    criteriaQuery.where(builder.equal(root.get("id"), id));
    return em.createQuery(criteriaQuery).getSingleResult();
  }











}
