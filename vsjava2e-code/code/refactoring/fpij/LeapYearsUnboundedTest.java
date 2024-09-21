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

public class LeapYearsUnboundedTest {
  LeapYearsUnbounded leapYearsUnbounded;
  
  @BeforeEach
  public void init() {
    leapYearsUnbounded = new LeapYearsUnbounded();
  }
  
  @Test 
  public void count() {
    assertAll(
      () -> assertEquals(25,
        leapYearsUnbounded.countFrom1900(year -> year <= 2000)),
      () -> assertEquals(27,
        leapYearsUnbounded.countFrom1900(year -> year <= 2010)),
      () -> assertEquals(31,
        leapYearsUnbounded.countFrom1900(year -> year <= 2025)),
      () -> assertEquals(49,
        leapYearsUnbounded.countFrom1900(year -> year <= 2100)),
      () -> assertEquals(0,
        leapYearsUnbounded.countFrom1900(year -> year <= 1800))
    );
  }
}
