package org.acme.lowlevel.rest;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class testcall
{

  public static void main( String[] args )
  {


    Map<String, Integer> concurrentHashMap = Stream.of( new Object[][]{
        {"one", 1},
        {"two", 2},
        {"three", 3},
    }).collect( Collectors.toMap( data -> (String) data[0], data -> (Integer) data[1], (v1, v2) -> v2, ConcurrentHashMap::new));

    System.out.println(concurrentHashMap);

  }





}
