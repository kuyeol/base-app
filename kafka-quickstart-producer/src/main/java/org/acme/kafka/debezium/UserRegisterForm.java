package org.acme.kafka.debezium;

import java.time.LocalDateTime;
import org.acme.kafka.constant.ServiceType;


public class UserRegisterForm
{

  private Long id;

  private String userid;

  private ServiceType status;

  private String description;

  private LocalDateTime createdDate;

  private double value;


  public UserRegisterForm()
    {
    }


  public UserRegisterForm( String userid, LocalDateTime orderDate )
    {
      this.userid      = userid;
      this.createdDate = orderDate;

    }


  public UserRegisterForm( Long id, String userid, ServiceType status, String description, LocalDateTime createdDate,
      double value )
    {
      this.id          = id;
      this.userid      = userid;
      this.status      = ServiceType.REGISTER;
      this.description = description;
      this.createdDate = createdDate;
      this.value       = value;
    }


  public UserRegisterForm( ServiceType status, String description, LocalDateTime createdDate, double value )
    {
      this.status      = ServiceType.UPDATE;
      this.description = description;
      this.createdDate = createdDate;
      this.value       = value;
    }


  public Long getId()
    {
      return id;
    }


  public void setId( Long id )
    {
      this.id = id;
    }


  public String getUserid()
    {
      return userid;
    }


  public void setUserid( String userid )
    {
      this.userid = userid;
    }


  public ServiceType getStatus()
    {
      return status;
    }


  public void setStatus(ServiceType status )
    {
      this.status = status;
    }


  public String getDescription()
    {
      return description;
    }


  public void setDescription( String description )
    {
      this.description = description;
    }


  public LocalDateTime getCreatedDate()
    {
      return createdDate;
    }


  public void setCreatedDate( LocalDateTime createdDate )
    {
      this.createdDate = createdDate;
    }


  public double getValue()
    {
      return value;
    }


  public void setValue( double value )
    {
      this.value = value;
    }


  public String getUserId(){
    return userid;
  }





}
