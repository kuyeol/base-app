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
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class ScoresFunctional extends Scores {
  public Map<Integer, Set<String>> namesForScores(
    Map<String, Integer> scores) {
      
    return scores.keySet()
      .stream()
      .collect(groupingBy(scores::get, toSet()));
  } 
}
