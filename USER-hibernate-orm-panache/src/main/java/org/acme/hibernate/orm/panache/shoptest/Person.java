package org.acme.hibernate.orm.panache.shoptest;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "person")
public class Person extends PanacheEntity {




  public Person() {
  }

  public Long getPersonid() {
    return personid;
  }

  public void setPersonid(Long id) {
    this.personid = id;
  }

  private Long personid;
  public String email;
  public Instant createdAt = Instant.now();

  public static List<Person> findContainEmail(String email) {
    return Person.list("email like ?1", "%" + email + "%");
  }

  public static Optional<Person> findByEmail(String email) {
    return Person.find("email", email).firstResultOptional();
  }

  @Override
  public String toString() {
    return "Person{" +
        "email='" + email + '\'' +
        ", createdAt=" + createdAt +
        ", id=" + id +
        '}';
  }
}
