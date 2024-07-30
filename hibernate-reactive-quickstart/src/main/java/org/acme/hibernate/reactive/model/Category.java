package org.acme.hibernate.reactive.model;

import java.util.Collection;

public class Category {

  public Long id;
  public String name;
  public Long parent;

  public Category() {

  }
  private Collection<Category> subCategories;

  public Collection<Category> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories(Collection<Category> subCategories) {
    subCategories = subCategories;
  }
}
