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

public class Impure {
  private int factor = 2;
  
  public void run() {
    var numbers = List.of(1, 2, 3);
    
    var stream = numbers.stream()
      .map(number -> number * factor);
    
    factor = 0;
    
    stream.forEach(System.out::println);
  }
  
  public static void main(String[] args) {
    new Impure().run();
  }
}
