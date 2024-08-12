package org.acme.kafka.alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;


public class Listner extends Thread
{

  private static final Instant      instant = Instant.now();

  private              Connection   jCon;

  private              PGConnection pgConn;


  Listner(Connection jCon) throws
      SQLException
  {

    this.jCon = jCon;
    this.pgConn = jCon.unwrap(PGConnection.class);
    Statement stmt = jCon.createStatement();
    stmt.execute("LISTEN  table_changes");
    stmt.close();
  }


  public void run()
  {
    //try entry
    try {
      while (true) {

        PGNotification[] notification = pgConn.getNotifications();

        if (notification != null) {
          for (int i = 0; i < notification.length; i++) {
            PGNotification A = notification[i];
            System.out.println(A.getParameter().toString());
            System.out.println("for in" + instant.toString());
          }
        }
        Thread.sleep(500);
        System.out.println("while in" + instant.toString());
      } // while end
    } //try end
    catch (SQLException e) {
      e.printStackTrace();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    // run method end
  }





}
