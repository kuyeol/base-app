package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Collection;


@Entity
public class Person {

  //id는 생략 프레임워크 자동 생성

  @Id
  private Long id;
  private String username;


  public Person() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }



  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent")
  public Person parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
  private Collection<Person> subPersons;

  public java.util.Collection<Person> getSubPersons() {
    return subPersons;
  }

  @JsonbTransient
  public Person getParent() {
    return parent;
  }

  public void setParent(Person parent) {
    this.parent = parent;
  }



}

