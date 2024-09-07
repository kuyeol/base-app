package org.acme.kafka.debezium;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.acme.kafka.EventType;
import org.acme.kafka.User;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class RegisterUser
{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "register_user_ids")
  @SequenceGenerator(name = "register_user_ids", sequenceName = "seq_register_user", allocationSize = 50)
  private Long id;

  private long userid;

  @Enumerated(EnumType.STRING)
  private EventType status;

  private String description;

  private LocalDateTime createdDate;


  public double value;


  RegisterUser() {
  }


  public RegisterUser(long customerId, LocalDateTime orderDate) {
    this.userid = customerId;
    this.createdDate = orderDate;
    this.status=EventType.INCOME;

  }


  public Long getId()
    {
      return id;
    }


  public void setId( Long id )
    {
      this.id = id;
    }


  public long getUserid()
    {
      return userid;
    }


  public void setUserid( long userid )
    {
      this.userid = userid;
    }


  public EventType getStatus()
    {
      return status;
    }


  public void setStatus( EventType status )
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






}
