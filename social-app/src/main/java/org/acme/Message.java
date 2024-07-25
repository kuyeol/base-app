package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedNativeQuery;
import java.util.Collection;

@Entity
@Cacheable
//@NamedNativeQuery(name = "", query = "", resultClass = Message.class
//)
public class Message extends PanacheEntity {

  public Message() {}

  public String followName;

  public String messages;


  @ManyToMany(fetch= FetchType.EAGER)
  @JoinTable(name="follow",joinColumns={@JoinColumn(name="messageid")},inverseJoinColumns={@JoinColumn(name=
      "personid")})
  public Collection<Person> persons;

  @JsonbTransient
  public Collection<Person> getPersons() {
    return persons;
  }

  @JsonbTransient
  public void serPerson(Collection<Person> persons) {
    this.persons = persons;
  }




}
