package org.acme.alert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.postgresql.PGConnection;
import org.postgresql.PGProperty;
import org.postgresql.replication.PGReplicationStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.





public class Main
  {

  public static void main( String[] args ) throws Exception
    {

      Class.forName( "org.postgresql.Driver" );
      String url = "jdbc:postgresql://182.218.135.229:5432/quarkus";
      String user = "quarkus";
      String password = "quarkus";

      Properties props = new Properties();

      PGProperty.USER.set( props, user );
      PGProperty.PASSWORD.set( props, password );
      PGProperty.ASSUME_MIN_SERVER_VERSION.set( props, "14" );
      PGProperty.REPLICATION.set( props, "database" );
      PGProperty.PREFER_QUERY_MODE.set( props, "simple" );

      Connection connListener = DriverManager.getConnection( url, user, password );
      Connection connNotifier = DriverManager.getConnection( url, user, password );
      //

      Connection con = DriverManager.getConnection( url, props );

      PGConnection replication = con.unwrap( PGConnection.class );
      //replication.getReplicationAPI()
      //           .createReplicationSlot()
      //           .logical()
      //           .withSlotName( "demo_slot12" )
      //           .withOutputPlugin( "test_decoding" )
      //           .make();


      // CallableStatement sqlConn = con.prepareCall( "{call alert(?)}" );
     // Listner listner = new Listner( connListener );
     // Notifier notifier = new Notifier( connNotifier );
     // listner.start();
     // notifier.start();

      con.setAutoCommit( true );
      Statement stmt = con.createStatement();
     // stmt.execute( "create schema topic" );
      //stmt.execute( "create table data(id,data) values (1,'message commit')" );
      //stmt.execute( "insert into data(data) values ('message commit')" );
      stmt.close();

     // stmt = con.createStatement();
      //stmt.execute( "update  data set name = 'second tx change' where pk = 1" );
     // stmt.close();

      PGReplicationStream pgStream = replication.getReplicationAPI()
                                                .replicationStream()
                                                .logical()
                                                .withSlotName( "demo_slot" )
                                                .withSlotOption( "include-xids", false )
                                                .withSlotOption( "skip-empty-xacts", true )
                                                .withStatusInterval( 1, TimeUnit.SECONDS )
                                                .start();

      while ( true ) {
        ByteBuffer msg = pgStream.readPending();
        if ( msg == null ) {
          TimeUnit.MILLISECONDS.sleep( 10L );
          continue;
        }

        int offset = msg.arrayOffset();
        byte[] source = msg.array();
        int length = source.length - offset;
        System.out.println("\t"+new String("offset : "+offset));
        System.out.println("\t"+source.toString()+new String("source : "+source.length));
        System.out.println("\t"+new String("length : "+length));

        System.out.println("print -> \t"+ new String( source, offset, length )+" <- \n" );
        String str = new String( source, offset, length );
        Random rand = new Random();
        rand.ints(111);
        int id=rand.nextInt();
        Event newEvent = new Event(id,str);
        System.out.println(newEvent);

        //JsonParser parser=null;
        //JsonNode node = parser.getCodec().readTree( parser );
        //node.get( "dd" ).asText().getBytes( StandardCharsets.UTF_8 );



        //feedback
      //  pgStream.setAppliedLSN( pgStream.getLastReceiveLSN() );
      //  pgStream.setFlushedLSN( pgStream.getLastReceiveLSN() );
      }
    }





  }
