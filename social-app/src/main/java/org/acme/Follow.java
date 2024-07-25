package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class Follow extends PanacheEntity {

  public Follow() {
  }

  public int personId;
  public int followId;


}
