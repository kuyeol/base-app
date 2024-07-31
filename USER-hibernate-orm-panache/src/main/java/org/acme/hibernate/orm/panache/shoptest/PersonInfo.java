package org.acme.hibernate.orm.panache.shoptest;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "person_info")
public class PersonInfo extends PanacheEntity {


  public String nickname;
  public String bio;
  public LocalDate birthday;

  public String tierinfo;

  public PersonInfo() {}


  //@ManyToOne(cascade = CascadeType.PERSIST)
  //@JoinColumn(name = "person_fk")
  //public Person person;

  @Override
  public String toString() {
    return "PersonInfo{" +
        "nickname='" + nickname + '\'' +
        ", bio='" + bio + '\'' +
        ", birthday=" + birthday +
        //", person=" + person +
        ", tier=" + tierinfo +

        '}';
  }
}
