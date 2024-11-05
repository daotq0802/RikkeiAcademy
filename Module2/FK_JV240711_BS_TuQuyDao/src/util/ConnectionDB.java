package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
   private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private static final String DB_URL = "jdbc:mysql://localhost:2810/db";
   private static final String USER = "root";
   private static final String PASS = "Sieunhan@12";

   public static Connection getConnection() {
      Connection conn = null;
      try {
         Class.forName(DRIVER);
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
      } catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
      return conn;
   }

   public static void closeConnection(Connection conn, CallableStatement cs) {
      if (cs != null) {
         try {
            cs.close();
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }

      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
   }
}
