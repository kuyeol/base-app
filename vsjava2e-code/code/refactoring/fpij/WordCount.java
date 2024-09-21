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

public class WordCount {
  public long countInFile(
    String searchWord, String filePath) throws IOException {
      
    long count = 0;
  
    BufferedReader bufferedReader =
      new BufferedReader(new FileReader(filePath));
    
    String line = null;
    
    while((line = bufferedReader.readLine()) != null) {
      String[] words = line.split(" ");
      
      for(String word: words) {
        if(word.equals(searchWord)) {
          count++;
        }
      }
    }
    
    return count;
  }
}
