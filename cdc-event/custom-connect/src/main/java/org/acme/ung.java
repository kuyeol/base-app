package org.acme;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import org.jboss.logging.Logger;
import io.debezium.outbox.quarkus.ExportedEvent;
import jakarta.enterprise.event.Event;

public class ung
  {

  private final String name;

  private final Map< String, String > config;


  @JsonProperty
  public String name()
    {
      return name;
    }


  @JsonProperty
  public Map< String, String > config()
    {
      return config;
    }


  @Override
  public boolean equals( Object o )
    {
      if ( this == o ) return true;
      if ( o == null || getClass() != o.getClass() ) return false;
      ung that = (ung) o;
      return Objects.equals( name, that.name ) && Objects.equals( config, that.config );
    }


  @Override
  public int hashCode()
    {
      return Objects.hash( name, config );
    }


  @JsonCreator
  public ung(
      @JsonProperty("name")
      String name,
      @JsonProperty("config")
      Map< String, String > config )
    {
      this.name   = name;
      this.config = config;
    }
public static final Logger LOG = Logger.getLogger(ung.class);

  public static List< WeatherStation > stations = List.of( new WeatherStation( 1, "Hamburg", 13 ),
      new WeatherStation( 2, "Snowdonia", 5 ), new WeatherStation( 3, "Boston", 11 ), new WeatherStation( 4, "Tokio", 16 ), new WeatherStation( 5, "Cusco", 12 ), new WeatherStation( 6, "Svalbard", - 7 ), new WeatherStation( 7, "Porthsmouth", 11 ), new WeatherStation( 8, "Oslo", 7 ), new WeatherStation( 9, "Marrakesh", 20 ) );





  public static class WeatherStation
    {

      int id;

      String name;

      int temp;


      public WeatherStation( int id, String name, int temp )
        {
          this.id   = id;
          this.name = name;
          this.temp = temp;
        }





    }
  private static Random random = new Random();

  public static Multi< Record< Integer, String > > generate(String st)
    {
    return Multi.createFrom().ticks().every( Duration.ofMillis( 1 ) ).onOverflow().drop().map( tick->{
      WeatherStation station = stations.get(random.nextInt(stations.size()));
      double temp = BigDecimal.valueOf( random.nextGaussian() * 15 + station.temp)
                                     .setScale(1, RoundingMode.HALF_UP)
                                     .doubleValue();
LOG.infof( "station: {0}, temp: {1}", station.name, temp );

      return Record.of( station.id,st+ Instant.now() + ";" + temp);
    } ) ;
    }


  public static Multi< Record< Integer, String > > ws()
    {
      return Multi.createFrom().items( stations.stream().map( s -> Record.of( s.id, "{ \"id\" : " + s.id + ", \"name\" : \"" + s.name + "\" }" ) ) );
    }


  public static void main( String[] args )
    {

      generate("aaaa");
      ws();

      System.out.println(generate("adsfas"));
      System.out.println(ws());
    }





  }
