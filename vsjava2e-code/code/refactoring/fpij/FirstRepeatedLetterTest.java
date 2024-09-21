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

public class FirstRepeatedLetterTest {
  FirstRepeatedLetter firstRepeatedLetter;
  
  @BeforeEach
  public void init() {
    firstRepeatedLetter = new FirstRepeatedLetter();
  }
  
  @Test 
  public void findFirstRepeating() {
    assertAll(
      () -> assertEquals('l', firstRepeatedLetter.findIn("hello")),
      () -> assertEquals('h', firstRepeatedLetter.findIn("hellothere")),
      () -> assertEquals('a', firstRepeatedLetter.findIn("magicalguru")),
      () -> assertEquals('\0', firstRepeatedLetter.findIn("once")),
      () -> assertEquals('\0', firstRepeatedLetter.findIn(""))
    );
  }
}
