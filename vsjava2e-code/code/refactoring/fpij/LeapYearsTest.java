/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeapYearsTest {
  LeapYears leapYears;
  
  @BeforeEach
  public void init() {
    leapYears = new LeapYears();
  }
  
  @Test 
  public void countFrom1900() {
    assertAll(
      () -> assertEquals(25, leapYears.countFrom1900(2000)),
      () -> assertEquals(27, leapYears.countFrom1900(2010)),
      () -> assertEquals(31, leapYears.countFrom1900(2025)),
      () -> assertEquals(49, leapYears.countFrom1900(2100)),
      () -> assertEquals(0, leapYears.countFrom1900(1800))
    );
  }
}
