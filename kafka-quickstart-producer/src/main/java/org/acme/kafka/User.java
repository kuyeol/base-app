package org.acme.kafka;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "users")
public class User extends PanacheEntityBase
{

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  public String id;


  @Column(name = "account_id")
  public String accountId;

  @Column
  public String description;

  @Column
  @Enumerated(EnumType.STRING)
  public EventType status;

  @Column
  public double value;


  @Override
  public String toString()
    {
      return "User{" + "id='" + id + '\'' + ", accountId='" + accountId + '\'' + ", description='" + description +
             '\'' + ", type=" +status + ", value=" + value + '}';
    }





}
