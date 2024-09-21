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
import java.util.Arrays;
import java.util.stream.Collectors;
import static fpij.Folks.friends;

public class TakeElements {
  public static void main(final String[] args) {
System.out.println("//" + "START:LIMIT_OUTPUT");
    friends.stream()
           .limit(3)
           .map(String::toUpperCase)
           .forEach(System.out::println);
System.out.println("//" + "END:LIMIT_OUTPUT");

System.out.println("//" + "START:TAKE_OUTPUT");
    friends.stream()
           .takeWhile(name -> name.length() > 4)
           .map(String::toUpperCase)
           .forEach(System.out::println);
System.out.println("//" + "END:TAKE_OUTPUT");
  }
}
