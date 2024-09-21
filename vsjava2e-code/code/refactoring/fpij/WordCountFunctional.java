/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class WordCountFunctional extends WordCount {
  public long countInFile(
    String searchWord, String filePath) throws IOException {
      
    return Files.lines(Paths.get(filePath))
      .flatMap(line -> Stream.of(line.split(" ")))
      .filter(word -> word.equals(searchWord))
      .count();
  }
}
