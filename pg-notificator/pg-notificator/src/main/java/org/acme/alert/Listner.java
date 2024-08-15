package org.acme.alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;


public class Listner extends Thread
{

  private static final Instant       instant   = Instant.now();

  private              Connection    jCon;

  private              PGConnection  pgConn;

  private              EventProvider eProvider;


  
  /*
  CREATE OR REPLACE FUNCTION update_ts_column_on_update() RETURNS TRIGGER AS $$ BEGIN NEW.ts = now();

  RETURN NEW;

  END;

  language 'plpgsql';
  */

  private static final String        PL_Line_0 =
      "CREATE OR REPLACE FUNCTION update_ts_column_on_update() RETURNS TRIGGER AS $$ " + "BEGIN" + " NEW.ts = now();";

  private static final String        PL_Line_1 = "RETURN NEW;";

  private static final String        PL_Line_2 = "END;";

  private static final String        PL_Line_3 = "language 'plpgsql';";

  private              String        eventMsg;


  Listner(Connection jCon, EventProvider eProvider) throws
      SQLException
  {
    this.jCon = jCon;
    this.pgConn = jCon.unwrap(PGConnection.class);
    Statement stmt = jCon.createStatement();
    this.eProvider = eProvider;
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
            eProvider.onMessage("provider"+A.getParameter().toString());
            // System.out.println(A.getName());
            System.out.println(A.getParameter().toString());
            System.out.println("for in" + instant.toString());
          }
        }
        Thread.sleep(2000);
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
