

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public class ung
  {
  private final String name;
  private final Map<String, String> config;

  @JsonCreator
  public ung(@JsonProperty("name") String name, @JsonProperty("config") Map<String, String> config) {
    this.name = name;
    this.config = config;
  }

  public static void main( String[] args )
    {




      System.out.println( "hello" );
    }





  }
