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
import java.math.BigInteger;

public class FactorialTest {
  Factorial factorial;
  
  @BeforeEach
  public void init() {
    factorial = new Factorial();
  }
  
  @Test 
  public void computeFactorial() {
    assertAll(
      () -> assertEquals(BigInteger.ONE, factorial.compute(1)),
      () -> assertEquals(BigInteger.TWO, factorial.compute(2)),
      () -> assertEquals(BigInteger.valueOf(6), factorial.compute(3)),
      () -> assertEquals(BigInteger.valueOf(120), factorial.compute(5))
    );
  }
}
