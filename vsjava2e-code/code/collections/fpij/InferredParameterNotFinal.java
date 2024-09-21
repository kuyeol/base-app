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
import java.util.Arrays;
import static fpij.Folks.friends;

public class InferredParameterNotFinal {
  public static void main(final String[] args) {
    friends.forEach(name -> {
      name = name.toUpperCase();
      System.out.println(name);
    });
  }
}
