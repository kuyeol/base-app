package org.acme.base;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "base_domain")
public class Domain {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String name;

  private String role;


  private Instant timeStamp;

  @OneToMany(mappedBy = "domain",cascade = CascadeType.ALL)
  private Set<Domain> domains;



  public Domain() {}

  public Domain(Long id, String name, String role, Instant timeStamp,Set<Domain> domains) {

    this.id = id;
    this.name = name;
    this.role = role;
    this.timeStamp = timeStamp;
    this.domains = domains;
  }

  public Domain(String name) {

    this.id = id;
    this.name = name;
    this.role = role;
    this.timeStamp = timeStamp;
    this.domains = domains;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Instant getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp() {
    this.timeStamp = Instant.now();
  }

  public Set<Domain> getDomains() {
    return domains;
  }

  public void setDomains(Set<Domain> domains) {
    this.domains = domains;
  }


  @Override
  public String toString() {
    return "Domain{" + "id=" + id + ", name=" + name + ", role=" + role + ", timeStamp=" + timeStamp + '}';
  }

}
