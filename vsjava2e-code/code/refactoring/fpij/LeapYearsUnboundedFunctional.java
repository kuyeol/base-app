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
import java.util.function.Predicate;

public class LeapYearsUnboundedFunctional extends LeapYearsUnbounded {
  public int countFrom1900(Continue shouldContinue) {
    Predicate<Integer> check = year -> shouldContinue.check(year);
    
    return countFrom1900(check);
  }
  
  public int countFrom1900(Predicate<Integer> shouldContinue) {
    return (int) IntStream.iterate(1900, year -> year + 4)
      .takeWhile(shouldContinue::test)
      .filter(Year::isLeap)
      .count();
  }
}
