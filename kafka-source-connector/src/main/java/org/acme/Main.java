package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;


public class Main
  {

    public Main()
      {
      }


    private static EntityManager em;


    public Main( EntityManager em )
      {
        this.em = em;
      }

public void setEm(EntityManager em){
      this.em = em;

}
    public EntityManager getEm()
      {
        return em;
      }








public static void main( String... a )
  {
    EntityManager EM;
    Main ma = new Main();
    ma.setEm(em);
    ma.getEm().find( Model.class, 1 );

    System.out.println( "asdf" );
  }





  }
