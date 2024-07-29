package org.acme.hibernate.reactive;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "order_fruits")
public class OrderFruits {

  @Id
  private Long order_id;

  private String name;

}