package org.acme.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MessegeBox {
  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
  private Integer id;
  private String boxname;
  
  public MessegeBox() {}

  @OneToMany(mappedBy = "messegebox")
  private Set<Message> messages;
  @ManyToOne(fetch = FetchType.LAZY)
  private SocialUser socialUser;

  public String getBoxname() {
    return boxname;
  }

  public void setBoxname(String boxname) {
    this.boxname = boxname;
  }
}
