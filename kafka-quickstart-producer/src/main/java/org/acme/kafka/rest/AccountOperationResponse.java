package org.acme.kafka.rest;

import java.time.LocalDateTime;
import org.acme.kafka.User;
import org.acme.kafka.debezium.UserRegisterForm;


public class AccountOperationResponse
{

  private final long id;

  private final String userId;

  private final UserRegisterForm form;

  private final LocalDateTime createdDate;


  public AccountOperationResponse( long id, String userId, LocalDateTime createdDate, UserRegisterForm form )
    {
      this.id          = id;
      this.userId      = userId;
      this.form        = form;
      this.createdDate = createdDate;
    }


  public AccountOperationResponse( Long id, LocalDateTime createdDate, UserRegisterForm form )
    {
      this.id     = id;
      this.userId = form.getUserId();
      this.form   = form;
      this.createdDate = createdDate;
    }


  public static AccountOperationResponse from( UserRegisterForm form )
    {

      return new AccountOperationResponse( form.getId(), form.getUserId(), LocalDateTime.now(), form );
    }


  public long getId()
    {
      return id;
    }


  public String getUserId()
    {
      return userId;
    }


  public UserRegisterForm getForm()
    {
      return form;
    }


  public LocalDateTime getCreatedDate()
    {
      return createdDate;
    }





}
