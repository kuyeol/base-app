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
import java.util.function.Predicate;
import java.util.function.Function;
import static fpij.Folks.friends;

public class PickDifferentNames {
  public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
  }
  
  public static void main(final String[] args) {
{
    final Predicate<String> startsWithN = name -> name.startsWith("N");
    final Predicate<String> startsWithB = name -> name.startsWith("B");
    
    final long countFriendsStartN = 
      friends.stream()
             .filter(startsWithN)
             .count();         
    final long countFriendsStartB = 
      friends.stream()
             .filter(startsWithB)
             .count();


    System.out.println(countFriendsStartN);
    System.out.println(countFriendsStartB);
}

{
    final long countFriendsStartN =
      friends.stream()
             .filter(checkIfStartsWith("N"))
             .count();
    final long countFriendsStartB =
      friends.stream()
             .filter(checkIfStartsWith("B"))
             .count();


    System.out.println(countFriendsStartN);
    System.out.println(countFriendsStartB);
}

{
    final Function<String, Predicate<String>> startsWithLetter = 
      (String letter) -> {
        Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
        return checkStarts;
    };

    final long countFriendsStartN =
      friends.stream()
             .filter(startsWithLetter.apply("N"))
             .count();
       
    final long countFriendsStartB =
      friends.stream()
             .filter(startsWithLetter.apply("B"))
             .count();

    System.out.println(countFriendsStartN);
    System.out.println(countFriendsStartB);
}

{
    final Function<String, Predicate<String>> startsWithLetter = 
      (String letter) -> (String name) -> name.startsWith(letter);

    final long countFriendsStartN =
      friends.stream()
             .filter(startsWithLetter.apply("N"))
             .count();
       
    final long countFriendsStartB =
      friends.stream()
             .filter(startsWithLetter.apply("B"))
             .count();

    System.out.println(countFriendsStartN);
    System.out.println(countFriendsStartB);
}

{
    final Function<String, Predicate<String>> startsWithLetter = 
      letter -> name -> name.startsWith(letter);

    final long countFriendsStartN =
      friends.stream()
             .filter(startsWithLetter.apply("N"))
             .count();
    final long countFriendsStartB =
      friends.stream()
             .filter(startsWithLetter.apply("B"))
             .count();


    System.out.println(countFriendsStartN);
    System.out.println(countFriendsStartB);
}

  }
}
