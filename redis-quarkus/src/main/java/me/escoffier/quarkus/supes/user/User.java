package me.escoffier.quarkus.supes.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Transient;
import me.escoffier.quarkus.supes.Hero;

public class User {
  

  public User(){}


  public String name;


  public String email;

  public String password;
  @Transient
  public String passwordConfirm;

  public List<Role> roleList;

  public Set<Role> roles = new HashSet<Role>();

  public User(List<Role> rolesS) {
    this.roleList=rolesS;
  }


  public void addRole(Role role) {
    roles.add(role);
  }


}
