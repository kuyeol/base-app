/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.time.Year;
import java.util.stream.IntStream;

public class LeapYearsFunctional extends LeapYears {
  public int countFrom1900(int upTo) {
    return (int) IntStream.iterate(
        1900, year -> year <= upTo, year -> year + 4)
      .filter(Year::isLeap)
      .count();
  }
}
