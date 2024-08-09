package org.acme.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.acme.extra.Fruit;

public class FruitCache {

  private List<Fruit> fruits;



  public FruitCache() { }

  public FruitCache(List<Fruit> fruits) {
    this.fruits = fruits;
  }

  public void setFruits(List<Fruit> fruits) {
    this.fruits =fruits;
  }

  public List<Fruit> getFruits() {
    return fruits;
  }







}
