package org.acme;

public class User {
  private String name;
  private int age;


  public User(){
    System.out.println("USER CONSTRUCTED");
  }

  public String getName() {
    System.out.println("USER getName");
    return name;
  }

  public void setName(String name) {
    System.out.println("USER setName");
    this.name = name;
  }

  public int getAge() {
    System.out.println("UUSER getAge");
    return age;
  }

  public void setAge(int age) {
    System.out.println("USER setAge");
    this.age = age;
  }
}
