package org.acme.kafka.rest;

import java.time.LocalDateTime;
import org.acme.kafka.debezium.UserRegisterForm;


public class RegisterRequest
{


  private String userId;
  private LocalDateTime createdDate;


  public UserRegisterForm getUser()
    {
      return user;
    }


  public void setUser( UserRegisterForm user )
    {
      this.user = user;
    }


  private UserRegisterForm user;

  public RegisterRequest() {
    this.user = new UserRegisterForm();
  }



  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate ) {
    this.createdDate = createdDate;
  }


  public UserRegisterForm toRegister()
    {
    user = new UserRegisterForm();
    user.setUserid( userId );
    createdDate = LocalDateTime.now();


      return new UserRegisterForm(user.getUserid() ,createdDate );
    }


  public String getUserId()
    {
      return userId;
    }


  public void setUserId( String userId )
    {
      this.userId = userId;
    }





}
