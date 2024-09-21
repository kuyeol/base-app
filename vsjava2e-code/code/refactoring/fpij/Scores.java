/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Scores {
  public Map<Integer, Set<String>> namesForScores(
    Map<String, Integer> scores) {
      
    Map<Integer, Set<String>> namesForScores = new HashMap<>();
    
    for(String name : scores.keySet()) {
      int score = scores.get(name);

      Set<String> names = new HashSet<>();
      if(namesForScores.containsKey(score)) {
        names = namesForScores.get(score);          
      }

      names.add(name);
      namesForScores.put(score, names);
    }                            

    return namesForScores;
  } 
}
