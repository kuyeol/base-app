/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.List;

public record Person(
  String firstName, String lastName, List<String> emailAddresses) {
    
  public String fullName() { return firstName + " " + lastName; }

  public static final List<Person> SAMPLE_DATA = List.of(
    new Person("John", "Doe", List.of()),
    new Person("Sara", "Walker", List.of("sara@example.com")),
    new Person("Mike", "Baker",
      List.of("mike@example.com", "baker@example.com")),
    new Person("Dev", "Shah",
        List.of("dev@example.com", "shah@example.com")),
    new Person("Sara", "Lee",
            List.of("slee@example.org", "lee@example.com")),
    new Person("Nancy", "Xie",
      List.of("nancy@example.com", "xie@example.com", "nx@example.com")),
    new Person("Jill", "Smith", List.of("jill@example.com")));
}
