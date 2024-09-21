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

public class FirstRepeatedLetter {
  public char findIn(String word) {
    char[] letters = word.toCharArray();
    
    for(char candidate: letters) {
      int count = 0;
      
      for(char letter: letters) {
        if(candidate == letter) {
          count++;
        }
      }
      
      if(count > 1) {
        return candidate;
      }
    }
    
    return '\0';
  }
}
