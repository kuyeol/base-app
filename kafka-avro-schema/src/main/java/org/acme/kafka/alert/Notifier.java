package org.acme.kafka.alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Notifier extends Thread
{

  private Connection jCon;


  public Notifier(Connection jCon)
  {
    this.jCon = jCon;
  }


  public void run()
  {
    while (true) {
      try {
        Statement stmt = jCon.createStatement();
        stmt.execute("NOTIFY table_changes");
        stmt.close();
        Thread.sleep(2000);
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }





}
