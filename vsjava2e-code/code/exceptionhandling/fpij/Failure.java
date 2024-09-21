/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

public record Failure<T>(Throwable throwable) implements Try<T> {
  @Override
  public T getResult() { throw new RuntimeException("Invalid invocation"); }

  @Override
  public Throwable getError() { return throwable; }    
}
