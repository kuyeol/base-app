/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import static java.util.stream.Collectors.*;
import static fpij.Person.SAMPLE_DATA;
import java.util.List;

public class AverageNumberOfEmailAddresses2 {
  public static void main(String[] args) {
    System.out.println("Average number of email addresses: " +
      SAMPLE_DATA.stream()
        .map(Person::emailAddresses)
        .mapToDouble(List::size)
        .average()
        .orElse(0));
  }
}
