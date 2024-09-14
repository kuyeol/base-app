package org.acme.panache.lunapractice;


import java.time.LocalDate;


public class Invoice
{

  public final String    id;

  public final int       price;

  public       LocalDate created;


  public Invoice( String id, int price, LocalDate ts )
  {

    this.id      = id;
    this.price   = price;
    this.created = LocalDate.now();

  }





}
