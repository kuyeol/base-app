/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.Map;
import java.util.List;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import static fpij.Person.SAMPLE_DATA;

public class Teeing {
  public static void main(String[] args) {
    record MinMax(String least, String most) {}
						
    var leastAndMostEmailAddressPerson =
      SAMPLE_DATA.stream()
        .collect(
          teeing(
            minBy(comparing(person -> person.emailAddresses().size())),
            maxBy(comparing(person -> person.emailAddresses().size())),
            (min, max) -> 
              new MinMax(min.map(Person::fullName).orElse(""),
                max.map(Person::fullName).orElse(""))));
      
    System.out.println(leastAndMostEmailAddressPerson);
  }
}
