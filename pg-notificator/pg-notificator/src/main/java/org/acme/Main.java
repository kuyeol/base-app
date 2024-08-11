package org.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.postgresql.PGStatement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) throws Exception {

    final String DB_HOST = "182.218.135.229";

    Connection connection = DriverManager.getConnection(DB_HOST);
    PreparedStatement jstmt = connection.prepareStatement("select * from notificator");

    PGStatement pgstmt = jstmt.unwrap(PGStatement.class);
    pgstmt.setPrepareThreshold(3);

    for (int i = 1; i < 5; i++) {
      jstmt.setInt(1, i);
      boolean using_server_stmt = pgstmt.isUseServerPrepare();
      ResultSet rs = jstmt.executeQuery();
      rs.next();
      System.out.println("The " + i + "st Loop" + "Sever side : " + using_server_stmt + " Result : " + rs.getInt(1));

      rs.close();
    }


  }
}