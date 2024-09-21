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
import java.io.IOException;

public class AirportNamesFunctionalNope {
  public static List<String> getNamesOfAirports(List<String> iataCodes) {
    return iataCodes.stream()
      .map(iataCode -> { //Bad idea
        try {
          return AirportInfo.getNameOfAirport(iataCode);
        } catch(Exception ex) {
          throw new RuntimeException(ex);
        }})
      .map(String::toUpperCase)
      .toList();
  }

  public static void main(String[] args) {
    var iataCodes = List.of("AUS", "DFW", "HOU", "IHA", "SAT");
    
    for(var name: getNamesOfAirports(iataCodes)) {
      System.out.println(name);
    }
  }
}
