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

public class PerfectNumberBetter {
  public static long sumOfFactors(long number) {
    return LongStream.range(1, number)
      .filter(i -> number % i == 0)
      .sum();
  }
  public static List<Long> perfectNumbersUpTo(long number) {
    return LongStream.rangeClosed(1, number)
      .filter(i -> sumOfFactors(i) == i)
      .boxed()
      .toList();
  }
  
  public static void main(String[] args) {
    System.out.println(perfectNumbersUpTo(500));
  }
}