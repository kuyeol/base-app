package org.acme.panache.lunapractice;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;


public class ReactiveStreams
{

  //


  public static void main( String[] a )
  {

    Multi< String > greeter = Multi.createFrom()
                                   .items( "Hello", "world" );

    Uni< List< String > > out = greeter.collect()
                                       .asList();

    List< String > results = out.subscribe()
                                .asCompletionStage()
                                .join();

    System.out.println( greeter.collect().asList().toString() );
    System.out.println( results );
    System.out.println( out.toString() );

  }





}
