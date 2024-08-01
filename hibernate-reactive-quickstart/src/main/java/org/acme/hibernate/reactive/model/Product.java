package org.acme.hibernate.reactive.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embedded;
import java.math.BigDecimal;

public class Product {

  public Product() {
  }



  public Long id;

  public BigDecimal price;

  public String name;

  public String description;

  public String image;
}
