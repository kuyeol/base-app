/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.math.BigDecimal;
import static java.util.stream.Collectors.joining;

public class Stocks100 {
  public static void main(final String[] args) {
    final BigDecimal HUNDRED = new BigDecimal("100");
    
    System.out.println("Stocks priced over $100 are:");
    
    System.out.println( 
      Tickers.symbols
             .stream()
             .filter(
               symbol -> FinanceData.getPrice(symbol).compareTo(HUNDRED) > 0)
             .sorted()
             .collect(joining(", ")));
  }
}
