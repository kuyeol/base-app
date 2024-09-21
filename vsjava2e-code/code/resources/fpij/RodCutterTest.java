/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Arrays;
import java.util.List;

public class RodCutterTest {
  private RodCutter rodCutter;
  private List<Integer> prices;
  
  protected RodCutter createCutter() {
    return new RodCutter(false);
  }
  
  @BeforeEach
  public void initialize() {
    rodCutter = createCutter();
    prices = Arrays.asList(1, 1, 2, 2, 3, 4, 5);
  }

  @Test public void verboseExceptionTest() {
    rodCutter.setPrices(prices);

    try {
      rodCutter.maxProfit(0);
      fail("Expected exception for zero length");
    } catch(RodCutterException ex) {
      assertTrue(true);
    }
  }

/*
  @Test(expected = RodCutterException.class) //JUnit 4 feature
  public void TerseExceptionTest() {
    rodCutter.setPrices(prices);
    rodCutter.maxProfit(0);
  }
*/

  @Test 
  public void ConciseExceptionTest() {
    rodCutter.setPrices(prices);
    
    Exception ex = 
      assertThrows(RodCutterException.class, () -> rodCutter.maxProfit(0));
    
    assertEquals("length should be greater than zero", ex.getMessage());
  }
}
