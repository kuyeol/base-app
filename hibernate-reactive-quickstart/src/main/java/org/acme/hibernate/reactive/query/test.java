package org.acme.hibernate.reactive.query;

import io.quarkus.runtime.Startup;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.postgresql.PGConnection;
import org.postgresql.PGProperty;
import org.postgresql.replication.PGReplicationStream;


@ApplicationScoped
public class test
  {

  public test() throws SQLException
    {
    }


  public void method()
    {
    }





  public void ss(@Observes Startup startup ) throws SQLException, ClassNotFoundException, InterruptedException
    {
      String url = "jdbc:postgresql://182.218.135.229:5432/quarkus";

      String user = "quarkus";

      String password = "quarkus";

      Properties props = new Properties();

      Class.forName( "org.postgresql.Driver" );

      PGProperty.USER.set( props, user );
      PGProperty.PASSWORD.set( props, password );
      PGProperty.ASSUME_MIN_SERVER_VERSION.set( props, "14" );
      PGProperty.REPLICATION.set( props, "database" );
      PGProperty.PREFER_QUERY_MODE.set( props, "simple" );

      Connection con = DriverManager.getConnection( url, props );
      PGConnection replication = con.unwrap( PGConnection.class );
      PGReplicationStream pgStream = replication.getReplicationAPI()
                                                .replicationStream()
                                                .logical()
                                                .withSlotName( "demo_slot" )
                                                .withSlotOption( "include-xids", false )
                                                .withSlotOption( "skip-empty-xacts", true )
                                                .withStatusInterval( 1, TimeUnit.SECONDS )
                                                .start();

      con.setAutoCommit( true );
      Statement stmt = con.createStatement();
      stmt.execute( "insert into data(data) values ('message commit')" );
      stmt.execute( "insert into ung(name) values ('message commit')" );
      stmt.close();
      while ( true ) {
        ByteBuffer msg = pgStream.readPending();
        if ( msg == null ) {
          TimeUnit.MILLISECONDS.sleep( 10L );
          continue;
        }

        int offset = msg.arrayOffset();
        byte[] source = msg.array();
        int length = source.length - offset;
        System.out.println( "\t" + new String( "offset : " + offset ) );
        System.out.println( "\t" + source.toString() + new String( "source : " + source.length ) );
        System.out.println( "\t" + new String( "length : " + length ) );

        System.out.println( "print -> \t" + new String( source, offset, length ) + " <- \n" );
        String str = new String( source, offset, length );
        Random rand = new Random();
        rand.ints( 111 );
        int id = rand.nextInt();
      }
    }





  }
