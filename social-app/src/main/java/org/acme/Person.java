package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Collection;

/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 * <p>
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 * <p>
 * Usage (more example on the documentation)
 * <p>
 * {@code
 * public void doSomething() {
 * Person entity1 = new Person();
 * entity1.field = "field-1";
 * entity1.persist();
 * <p>
 * List<Person> entities = Person.listAll();
 * }
 * }
 */
@Entity
public class Person extends PanacheEntity {

  //id는 생략 프레임워크 자동 생성

  public Person() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String username;

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

