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

public class Statistics {
  public static void main(String[] args) {
    var statistics = SAMPLE_DATA.stream()
      .collect(
        summarizingDouble(person -> person.emailAddresses().size()));
    
    System.out.println("Number of people: " + statistics.getCount());
    System.out.println(
      "Number of email addresses: " + statistics.getSum());
    System.out.println(
      "Average number of email addresses: " + statistics.getAverage());
    System.out.println(
      "Max number of email addresses: " + statistics.getMax());
    System.out.println(
      "Min number of email addresses: " + statistics.getMin());
  }
}
