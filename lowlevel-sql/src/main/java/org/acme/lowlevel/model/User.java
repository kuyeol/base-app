package org.acme.lowlevel.model;


import io.vertx.mutiny.sqlclient.Row;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "user_table")
public class User
{

  @Id
  public Long   id;

  @NotNull
  @Length(min = 3)
  public String name;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = true)
  @Digits(integer = 10, fraction = 2)
  public int    age;

  @NotNull
  @Length(min = 10)
  public String bio;


  public User()
  {

  }


  public User( Long id, String name, String description, int price )
  {

    this.id   = id;
    this.name = name;
    this.bio  = description;
    this.age  = price;
  }


  public User( String name, String description )
  {

    this.name = name;
    this.bio  = description;

  }


  public User( String name, String description, int price )
  {

    this.name = name;
    this.bio  = description;
    this.age  = price;

  }


  public static User from( Row row )
  {

    return new User(
        row.getLong( "id" ),
        row.getString( "name" ),
        row.getString( "bio" ),
        row.getInteger( "age" ) );
  }


  public static User fromFilte( Row row )
  {

    return new User(
        row.getString( "name" ),
        row.getString( "bio" ),
        row.getInteger( "age" ) );
  }

  //public static Uni< Long > deleteById( Long id )
  //{
  //
  //  return delete( "id", id );
  //}


  public @NotNull @Length(min = 3) String getName()
  {

    return name;
  }


  public void setName(
      @NotNull @Length(min = 3) String name )
  {

    this.name = name;
  }


  @NotNull
  @DecimalMin(value = "0.0", inclusive = true)
  @Digits(integer = 10, fraction = 2)
  public int getAge()
  {

    return age;
  }


  public void setAge(
      @NotNull @DecimalMin(value = "0.0", inclusive = true) @Digits(integer = 10, fraction = 2) int age )
  {

    this.age = age;
  }


  public @NotNull @Length(min = 10) String getBio()
  {

    return bio;
  }


  public void setBio(
      @NotNull @Length(min = 10) String bio )
  {

    this.bio = bio;
  }





}
