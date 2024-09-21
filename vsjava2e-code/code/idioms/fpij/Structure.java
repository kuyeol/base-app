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

public class Structure {
  public static void printDetailsPoor(List<String> names) {
    names.stream().filter(name -> name.length() == 4).map(String::toUpperCase).forEach(System.out::println);
    /*
    //Please don't do this
    names.stream().filter(name -> name.length() == 4).map(String::toUpperCase)...
    */
  }

  public static void printDetailsFluent(List<String> names) {
    names.stream()
      .filter(name -> name.length() == 4)            
      .map(String::toUpperCase)
      .forEach(System.out::println);    
  }
  
  public static void main(String[] args) {
    var names = List.of("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
    
    printDetailsPoor(names);
    printDetailsFluent(names);
  }
}