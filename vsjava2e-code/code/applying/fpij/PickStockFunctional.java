/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class PickStockFunctional {
  public static void findHighPriced(final Stream<String> symbols) {
    final StockInfo highPriced = 
      symbols.map(StockUtil::getPrice)
             .filter(StockUtil.isPriceLessThan(500))
             .reduce(StockUtil::pickHigh)
             .get();    

    System.out.println("High priced under $500 is " + highPriced);
  }

  public static void main(final String[] args) {
    findHighPriced(Tickers.symbols.stream());
  }
}
