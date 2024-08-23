package me.escoffier.quarkus.supes.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Role  extends PanacheEntity {


  public int idx;
  public String name;


}
