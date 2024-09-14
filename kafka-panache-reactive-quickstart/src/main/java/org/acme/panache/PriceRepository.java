package org.acme.panache;


import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class PriceRepository implements PanacheRepository< Price >
{

  public Price findid( String name )
  {

    return (Price) find( "name" , name ).firstResult();
  }





}
