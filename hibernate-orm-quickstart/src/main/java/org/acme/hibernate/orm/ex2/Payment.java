package org.acme.hibernate.orm.ex2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Payment {
  @Id
  @GeneratedValue
  private int patmentId;


  @ManyToOne
  @JoinColumn(name="productId")
  private Product product;

  @ManyToOne
  @JoinColumn(name="optionId")
  private Option option;

  public int getPatmentId() {
    return patmentId;
  }

  public void setPatmentId(int patmentId) {
    this.patmentId = patmentId;
  }

  public Product getProduct() {

    return product;
  }

  public void setProduct(Product product) {
    this.product =product;

  }

  public Option getOption() {
    return option;
  }

  public void setOption(Option option) {
    this.option = option;
  }
}
