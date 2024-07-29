package org.acme.hibernate.reactive;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;
import java.util.List;

@Entity
@Table(name = "known_fruits")
@NamedQuery(name = "Fruits.findAll", query = "SELECT f FROM Fruit f ORDER BY f.name")
public class Fruit {

  @Id
  @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1, initialValue = 10)
  @GeneratedValue(generator = "fruitsSequence")
  private Integer id;

  @Column(length = 40, unique = true)
  private String name;
  @Embedded
  private EmTable emTable;

  public Fruit() {
  }

  public Fruit(String name, EmTable emTable) {
    this.name = name;
    this.emTable = emTable;

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EmTable getEmTable() {
    return emTable;
  }

  public void setEmTable(EmTable emTable) {
    this.emTable = emTable;
  }

  @Override
  public String toString() {
    return "Fruit{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", emTable=" + emTable +
        '}';
  }
}
