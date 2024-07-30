package org.acme.hibernate.orm.panache.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "person_info")
public class PersonInfo extends PanacheEntity {

  public String nickname;
  public String bio;
  public LocalDate birthday;


@ManyToOne(cascade= CascadeType.PERSIST)
@JoinColumn(name = "person_fk")
  public Person person;

  @Override
  public String toString() {
    return "PersonInfo{" +
        "nickname='" + nickname + '\'' +
        ", bio='" + bio + '\'' +
        ", birthday=" + birthday +
        ", person=" + person +
        ", id=" + id +
        '}';
  }
}
