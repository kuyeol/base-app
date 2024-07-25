package org.acme.base;

import java.time.Instant;
import java.util.Set;

public class DomainDTO {

  private Long id;

  private String name;

  private String role;

  private Instant timeStamp;

  private Set<Domain> domains;

  public DomainDTO() {
  }

  public DomainDTO(Long id, String name, String role, Instant timeStamp, Set<Domain> domains) {

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setTimeStamp(Instant timeStamp) {
    this.timeStamp = timeStamp;
  }

  @Override
  public String toString() {
    return "Domain{" + "id=" + id + ", name=" + name + ", role=" + role + ", timeStamp=" + timeStamp + '}';
  }

  public Set<Domain> getDomains() {
    return domains;
  }

  public void setDomains(Set<Domain> domains) {
    this.domains = domains;
  }
}
