package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
   private static final String URL = "jdbc:mysql://localhost:2810/bookManagement";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "Sieunhan@12";
   private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

   public static Connection getConnection() {
      Connection conn = null;
      try {
         Class.forName(DRIVER);
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      } catch (ClassNotFoundException | SQLException e) {
         System.err.println("Xảy ra lỗi khi kết nối tới MySQL!!!");
      }
      return conn;
   }

   public static void closeConnection(Connection conn, CallableStatement callSt) {
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi!!!");
         }
      }
      if (callSt != null) {
         try {
            callSt.close();
         } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi!!!");
         }
      }
   }
}
