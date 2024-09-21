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

public class GoodNames {
  public static void printDetails(List<Driver> drivers) {
  drivers.stream()
    .filter(x -> x.getAge() > 21) //Please avoid meaningless parameter names
    .filter(x -> x.isDriversLicenseValid())
    .map(x -> x.getPrimaryCar())
    .map(c -> c.getRegistration())
    .forEach(r -> System.out.println(r));
  }

  public static void printDetailsConcise(List<Driver> drivers) {
  drivers.stream()
    .filter(driver -> driver.getAge() > 21)
    .filter(driver -> driver.isDriversLicenseValid())
    .map(driver -> driver.getPrimaryCar())
    .map(car -> car.getRegistration())
    .forEach(registration -> System.out.println(registration));
  }
  
  public static void main(String[] args) {
    var drivers = List.of(new Driver(), new Driver());
    
    printDetails(drivers);
    printDetailsConcise(drivers);
  }
}