/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.*;
import java.util.stream.*;

public class PerfectNumberStillPoor {
  public static List<Long> perfectNumbersUpTo(long number) {
    return LongStream.range(1, number)
      .filter(i -> LongStream.range(1, i) //Not good
        .filter(j -> i % j == 0)
        .sum() == i)
      .boxed()
      .toList();
  }
  
  public static void main(String[] args) {
    System.out.println(perfectNumbersUpTo(500));
  }
}