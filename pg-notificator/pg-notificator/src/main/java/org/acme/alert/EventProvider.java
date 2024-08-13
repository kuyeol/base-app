package org.acme.alert;

public class EventProvider
{

// TODO: test seccess to private filed write
  
public EventProvider(){}

  public String onMessage(String m)
  {
    System.out.println(m);
    return m;
  }

  
}
  
