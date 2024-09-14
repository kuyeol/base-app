package org.acme.panache;

import java.util.UUID;


public class Price
{

//  public UUID eventId=UUID.randomUUID();

  public Long id;

  public String name;

  public Long version;

  public int counts;


  public Price(){ }
  public Price(Long id){
    this.id = id;
  }
  public Price(int count){
    this.counts = count;
  }


  public Price( Long id, String name, Long version )
    {
      this.id      = id;
      this.name    = name;
      this.version = version;
    }


  public Price( Long id, Long version )
    {
      this.id      = id;
      this.version = version;
    }


  //public UUID getEventId()
  //  {
  //    return eventId;
  //  }

  //
  //public void setEventId( UUID eventId )
  //  {
  //    this.eventId = eventId;
  //  }


  public Long getId()
    {
      return id;
    }


  public void setId( Long id )
    {
      this.id = id;
    }


  public String getName()
    {
      return name;
    }


  public void setName( String name )
    {
      this.name = name;
    }


  public Long getVersion()
    {
      return version;
    }


  public void setVersion( Long version )
    {
      this.version = version;
    }


  public int getCount()
    {
      return counts;
    }


  public void setCount( int count )
    {
      this.counts = count;
    }





}
