/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

public class Person {
  private final String name;
  private final int age;
  
  public Person(final String theName, final int theAge) {
    name = theName;
    age = theAge;
  } 
  
  public String getName() { return name; }
  public int getAge() { return age; }
  
  public int ageDifference(final Person other) {
    return age - other.age;
  }
  
  public String toString() {
    return String.format("%s - %d", name, age);
  }
}
