package org.acme.kafka;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import org.acme.kafka.constant.ServiceType;


@Entity
@Table(name = "users")
public class User
{

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "version", updatable = true, nullable = false)
  private UUID version;

  @Column(name = "userid")
  private String userId;

  @Column
  private String description;

  @Column
  @Enumerated(EnumType.STRING)
  private ServiceType status;

  @Column
  private double value;

  @Column(name = "ts")
  private LocalDateTime ts;


  public LocalDateTime getTs()
    {
      return ts;
    }


  public void setTs( )
    {
      this.ts = LocalDateTime.now();
    }


  public User( UUID userId, User user,UUID version )
    {
      this.id          = userId;
      this.userId      = user.getUserId();
      this.description = user.getDescription();
      this.status      = user.getStatus();
      this.value       = user.getValue();
      this.version     = version;
      this.ts          = ts;
    }


  public User()
    {

    }


  public UUID getId()
    {
      return id;
    }


  public void setId( UUID id )
    {
      this.id = id;
    }


  public String getUserId()
    {
      return userId;
    }


  public void setUserId( String userId )
    {
      this.userId = userId;
    }


  public String getDescription()
    {
      return description;
    }


  public void setDescription( String description )
    {
      this.description = description;
    }


  public ServiceType getStatus()
    {
      return status;
    }


  public void setStatus( ServiceType status )
    {
      this.status = status;
    }


  public double getValue()
    {
      return value;
    }


  public void setValue( double value )
    {
      this.value = value;
    }


  public UUID getVersion()
    {
      return version;
    }


  public void setVersion( UUID version )
    {
      this.version = UUID.randomUUID();
    }


  @Override
  public String toString()
    {
      return "User{" + "id=" + id + ", version=" + version + ", userId='" + userId + '\'' + ", description='" +
             description + '\'' + ", status=" + status + ", value=" + value + '}';
    }





}
