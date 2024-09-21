/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

public sealed interface DataOrException<T> {
  public default boolean isDataPresent() { return true; }

  public default T getDataOrThrow() {
    throw new RuntimeException("No data"); 
  }

  public default Exception getExceptionOrThrow() { 
    throw new RuntimeException("No Exception"); 
  }

  public static <T> DataOrException<T> of(T data) {
    return new Data<T>(data);
  }

  public static <T> DataOrException<T> of(Exception exception) {
    return new TheException<T>(exception);
  }
}

record Data<T>(T data) implements DataOrException<T> {
  public T getDataOrThrow() { return data; }
}

record TheException<T>(Exception exception) implements DataOrException<T> {
  public boolean isDataPresent() { return false; }

  public Exception getExceptionOrThrow() { return exception; }      
}
