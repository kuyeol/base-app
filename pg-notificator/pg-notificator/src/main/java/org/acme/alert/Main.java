package org.acme.alert;

import java.sql.Connection;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.





public class Main
{

  public static void main(String[] args) throws
      Exception
  {

    Class.forName("org.postgresql.Driver");
    String        url          = "jdbc:postgresql://182.218.135.229:5432/postgres";
    String        user         = "quarkus";
    String        password     = "quarkus";
    Connection    connListener = DriverManager.getConnection(url, user, password);
    Connection    connNotifier = DriverManager.getConnection(url, user, password);
    EventProvider eProvider=new EventProvider();
    eProvider.onMessage("");

    Listner  listner  = new Listner(connListener, eProvider);
    Notifier notifier = new Notifier(connNotifier);
    listner.start();
    notifier.start();
  }





}
