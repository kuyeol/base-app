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

public class AirportNames {
  public static List<String> getNamesOfAirports(List<String> iataCodes) {
    return iataCodes.stream()
      .map(iataCode -> AirportInfo.getNameOfAirport(iataCode))
      .map(String::toUpperCase)
      .toList();
    //ERROR: This code will not compile
  }
}
