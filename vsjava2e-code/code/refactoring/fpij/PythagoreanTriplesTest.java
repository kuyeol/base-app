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
import java.util.List;
import static fpij.Triple.triple;

public class PythagoreanTriplesTest {
  PythagoreanTriples pythagoreanTriples;
  
  @BeforeEach
  public void init() {
    pythagoreanTriples = new PythagoreanTriples();
  }
  
  @Test 
  public void compute() {
    assertAll(
      () -> assertEquals(List.of(), pythagoreanTriples.compute(0)),
      () -> assertEquals(List.of(triple(3, 4, 5)),
         pythagoreanTriples.compute(1)),
      () -> assertEquals(
        List.of(triple(3, 4, 5), triple(8, 6, 10), triple(5, 12, 13)),
         pythagoreanTriples.compute(3)),
      () -> assertEquals(
        List.of(triple(3, 4, 5), triple(8, 6, 10),
                triple(5, 12, 13), triple(15, 8, 17),
                triple(12, 16, 20)),
         pythagoreanTriples.compute(5))
    );
  }
}
