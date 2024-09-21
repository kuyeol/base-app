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
  public static List<DataOrException<String>> getNamesOfAirports(
    List<String> iataCodes) {

    List<DataOrException<String>> result = new ArrayList<>();
    
    for(var iataCode: iataCodes) {
      try {
        result.add(DataOrException.of(
          AirportInfo.getNameOfAirport(iataCode).toUpperCase()));
      } catch(IOException | AirportInfoException ex) {
          result.add(DataOrException.of(ex));
      }
    }

    return result;
  }
  
  public static void main(String[] args) {
    var iataCodes = List.of("AUS", "DFW", "HOU", "IHA", "SAT");

    for(var result: getNamesOfAirports(iataCodes)) {
      if(result.isDataPresent()) {
        System.out.println(result.getDataOrThrow());				
      } else {
        System.out.println("Error: " + 
	  result.getExceptionOrThrow().getMessage());
      }
  }
}
}
