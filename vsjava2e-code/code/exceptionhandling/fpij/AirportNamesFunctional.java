/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.*;

public class AirportNamesFunctional {
  public static List<Try<String>> getNamesOfAirports(List<String> iataCodes) {
    return iataCodes.stream()
      .map(iataCode -> Try.of(() -> AirportInfo.getNameOfAirport(iataCode)))
      .map(name -> name.map(String::toUpperCase))
      .toList();
  }

  public static void main(String[] args) {
    var iataCodes = List.of("AUS", "DFW", "HOU", "IHA", "SAT");
    
    getNamesOfAirports(iataCodes).stream()
      .map(name -> switch(name) {
        case Success(String result) -> result;
        case Failure(Throwable throwable) -> "Error: " + throwable.getMessage();
      })
      .forEach(System.out::println);
  }
}
