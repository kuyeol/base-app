package org.acme.kafka.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;


public class User
{

  private long id;

  private String username;

  private String email;

  private ZonedDateTime ts;
  ;


  public User(){


  }

  public User( long id, String username)
    {
      this.id       = id;
      this.username = username;
      this.ts =ZonedDateTime.now();
    }

  public User( long id, String username, String email, LocalDateTime ts )
    {
      this.id       = id;
      this.username = username;
      this.email    = email;
      this.ts =ZonedDateTime.now();
    }


  public long getId()
    {
      return id;
    }


  public void setId( long id )
    {
      this.id = id;
    }


  public String getUsername()
    {
      return username;
    }


  public void setUsername( String username )
    {
      this.username = username;
    }


  public String getEmail()
    {
      return email;
    }


  public void setEmail( String email )
    {
      this.email = email;
    }


  public ZonedDateTime getTs()
    {
      return ts;
    }


  public void setTs( )
    {
      this.ts =ZonedDateTime.now();
    }


  @Override
  public String toString()
    {
      return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", ts=" + ts + '}';
    }


  @Override
  public boolean equals( Object o )
    {
      if ( this == o ) { return true; }
      if ( o == null || getClass() != o.getClass() ) { return false; }

      User user = (User) o;
      return id == user.id && Objects.equals( username, user.username ) && Objects.equals( email, user.email ) &&
             Objects.equals( ts, user.ts );
    }







}
