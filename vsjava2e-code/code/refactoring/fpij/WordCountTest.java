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

public class WordCountTest {
  WordCount wordCount;
  
  @BeforeEach
  public void init() {
    wordCount = new WordCount();
  }
  
  @Test 
  public void count() {
    assertAll(
      () -> assertEquals(2,
        wordCount.countInFile("public", "fpij/WordCount.java")),
      () -> assertEquals(1,
        wordCount.countInFile("package", "fpij/WordCount.java"))        
          
    );
  }
}
