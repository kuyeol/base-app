package org.acme.hibernate.reactive.model;

import java.util.Collection;

public class Category {

  public Long id;
  public String name;
  public Long parentid;

  public Category() {

  }
  private Collection<Category> SubCategories;

  public Collection<Category> getSubCategories() {
    return SubCategories;
  }

  public void setSubCategories(Collection<Category> subCategories) {
    SubCategories = subCategories;
  }
}
