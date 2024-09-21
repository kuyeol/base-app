/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.stream.Stream;

public class FirstRepeatedLetterFunctional extends FirstRepeatedLetter {
  public char findIn(String word) {
    return Stream.of(word.split(""))
      .filter(letter -> word.lastIndexOf(letter) > word.indexOf(letter))
      .findFirst()
      .map(letter -> letter.charAt(0))
      .orElse('\0');      
  }
}
