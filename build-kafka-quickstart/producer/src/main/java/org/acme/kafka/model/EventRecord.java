package org.acme.kafka.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class EventRecord
{

  private long eventId;

  private String eventType;

  private String entityId;

  private String eventData;

  private LocalDateTime eventTime;


  public EventRecord(){ }


  public EventRecord( long eventId, String eventType, String entityId, String eventData, LocalDateTime eventTime )
    {
      this.eventId   = eventId;
      this.eventType = eventType;
      this.entityId  = entityId;
      this.eventData = eventData;
      this.eventTime = eventTime;
    }


  public long getEventId()
    {
      return eventId;
    }


  public void setEventId( long eventId )
    {
      this.eventId = eventId;
    }


  public String getEventType()
    {
      return eventType;
    }


  public void setEventType( String eventType )
    {
      this.eventType = eventType;
    }


  public String getEntityId()
    {
      return entityId;
    }


  public void setEntityId( String entityId )
    {
      this.entityId = entityId;
    }


  public String getEventData()
    {
      return eventData;
    }


  public void setEventData( String eventData )
    {
      this.eventData = eventData;
    }


  public LocalDateTime getEventTime()
    {
      return eventTime;
    }


  public void setEventTime( LocalDateTime eventTime )
    {
      this.eventTime = eventTime;
    }


  @Override
  public String toString()
    {
      return "EventRecord{" + "eventId=" + eventId + ", eventType='" + eventType + '\'' + ", entityId='" + entityId +
             '\'' + ", eventData='" + eventData + '\'' + ", eventTime=" + eventTime + '}';
    }


  @Override
  public boolean equals( Object o )
    {
      if ( this == o ) { return true; }
      if ( o == null || getClass() != o.getClass() ) { return false; }

      EventRecord that = (EventRecord) o;
      return eventId == that.eventId && Objects.equals( eventType, that.eventType ) &&
             Objects.equals( entityId, that.entityId ) && Objects.equals( eventData, that.eventData ) &&
             Objects.equals( eventTime, that.eventTime );
    }


  @Override
  public int hashCode()
    {
      int result = Long.hashCode( eventId );
      result = 31 * result + Objects.hashCode( eventType );
      result = 31 * result + Objects.hashCode( entityId );
      result = 31 * result + Objects.hashCode( eventData );
      result = 31 * result + Objects.hashCode( eventTime );
      return result;
    }





}
