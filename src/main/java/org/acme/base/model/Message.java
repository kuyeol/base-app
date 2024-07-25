package org.acme.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Message{
  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
    private Integer id;


  private String message;

  private String status;


  @ManyToOne(fetch = FetchType.LAZY)
  private MessegeBox messegeBox;

  @ManyToOne(fetch = FetchType.LAZY)
  private SocialUser socialuser;





  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
