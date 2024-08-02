package org.acme.mongo;

import java.util.Objects;

public class UserProfile {

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String id;
  private String name;
  private String description;
  private String email;

  public UserProfile() {
  }

  public UserProfile(String id,String name, String description, String email) {
this.id = id;
    this.name = name;
    this.description = description;
    this.email = email;
  }


@Override
  public boolean equals(Object obj) {
    if(!(obj instanceof UserProfile)) {
      return false;
    }
    UserProfile other = (UserProfile) obj;
    return Objects.equals(other.name,this.name);
}


@Override
  public int hashCode() {
    return Objects.hash(name);
}



}
