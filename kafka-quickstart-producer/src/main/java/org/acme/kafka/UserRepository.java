package org.acme.kafka;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class UserRepository implements PanacheRepository< User >
{

  public Uni< User > findByName( String name )
    {
      return find( "name", name ).firstResult();
    }

  //
  //public Uni< List< User > > versionValidation()
  //  {
  //    return list( "status" );
  //  }


  //public Uni< Long > deleteStefs()
  //  {
  //    return delete( "name", "Stef" );
  //  }
  //




}
