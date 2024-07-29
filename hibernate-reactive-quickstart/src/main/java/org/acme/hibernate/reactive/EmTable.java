package org.acme.hibernate.reactive;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmTable {

  public String getEmname() {
    return emname;
  }

  public void setEmname(String emname) {
    this.emname = emname;
  }

  private String emname;







}
