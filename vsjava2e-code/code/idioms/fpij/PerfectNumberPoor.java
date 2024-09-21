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

public class PerfectNumberPoor {
  public static List<Long> perfectNumbersUpTo(long number) {
    return LongStream.rangeClosed(1, number)
      .filter(i -> { //Bad Code, don't do this
        long factor = 0;

        for(int j = 1; j < i; j++) {
          if(i % j == 0) {
            factor += j;
          }
        }

        return factor == i;
      })
      .boxed()
      .toList();
  }
  
  public static void main(String[] args) {
    System.out.println(perfectNumbersUpTo(500));
  }
}