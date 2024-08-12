package org.acme.kafka.alert;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import java.sql.Connection;
import java.sql.DriverManager;
import org.acme.kafka.Producer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Alert
{

  public static void run(@Observes Startup ev) throws
      Exception
  {


    Class.forName("org.postgresql.Driver");
    String     url          = "jdbc:postgresql://182.218.135.229:5432/postgres";
    String     user         = "quarkus";
    String     password     = "quarkus";
    Connection connListener = DriverManager.getConnection(url, user, password);
    Connection connNotifier = DriverManager.getConnection(url, user, password);

    Listner  listner  = new Listner(connListener);
    Notifier notifier = new Notifier(connNotifier);
    listner.start();
    notifier.start();
  }





}