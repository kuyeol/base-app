/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.time.Year;

interface Continue {
  boolean check(int year);
}

public class LeapYearsUnbounded {
  public int countFrom1900(Continue shouldContinue) {
    int numberOfLeapYears = 0;
    
    for(int i = 1900;; i += 4) {
      if(!shouldContinue.check(i)) {
        break;
      }
      
      if(Year.isLeap(i)) {
        numberOfLeapYears++;
      }
    }
    
    return numberOfLeapYears;
  }
}
