package org.acme.hibernate.orm.example;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OrderBy;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "fruit_list")
@NamedQuery(name = "Fruit_list.findAll", query = "SELECT f FROM Fruit f ORDER BY f.name", hints = @QueryHint(name =
  "org.hibernate.cacheable", value = "true"))
@Cacheable
@Access(AccessType.FIELD)
public class Fruit {
  static AtomicInteger atomicInteger = new AtomicInteger();
  static AtomicInteger atomicInteger1 = new AtomicInteger();
  static AtomicInteger atomicInteger2 = new AtomicInteger();
  static AtomicInteger atomicInteger3 = new AtomicInteger();


  @Id
  @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1,
    initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;


  @Column(length = 40, unique = true, name = "name")
  private String name;


  @Transient
  private Integer items;

  public Fruit(String name, List<Fruit> person) {

    AtomicInteger atomicInteger = new AtomicInteger();
    System.out.println("NEW FOR ARG ID" + atomicInteger1.getAndIncrement());
    this.name = name;
  }

  public Fruit() {
  }

  public Integer getId() {
    AtomicInteger atomicInteger = new AtomicInteger();
    System.out.println("getID" + atomicInteger2.getAndIncrement());
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    System.out.println("getNAME" + atomicInteger3.getAndIncrement());
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getItems() {
    return items;
  }

  public void setItems(Integer items) {
    this.items = items;
  }


}
