package org.acme.extra;

import java.time.Instant;



public class FruitOrder {

private Long orderid;
private Long fruitid;
private String username;
private Instant orderdate=Instant.now();



public FruitOrder() {}





  public FruitOrder(Long orderid, Long fruitid, String username, Instant orderdate) {
  this.orderid = orderid;
  this.fruitid = fruitid;
  this.username = username;
  this.orderdate = orderdate;

  }

  public FruitOrder(Long fruitid, String username) {
    this.fruitid = fruitid;
    this.username = username;
  }

  public Long getOrderid() {
    return orderid;
  }

  public void setOrderid(Long orderid) {
    this.orderid = orderid;
  }

  public Long getFruitid() {
    return fruitid;
  }

  public void setFruitid(Long fruitid) {
    this.fruitid = fruitid;
  }

  public Instant getOrderdate() {
    return orderdate;
  }

  public void setOrderdate(Instant orderdate) {
    this.orderdate = orderdate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "FruitOrder{" +
        "orderid=" + orderid +
        ", fruitid=" + fruitid +
        ", username='" + username + '\'' +
        ", orderdate=" + orderdate +
        '}';
  }
}
