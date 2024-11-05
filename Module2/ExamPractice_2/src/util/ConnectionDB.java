package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
   private static final String URL = "jdbc:mysql://localhost:2810/employeeManagement";
   private static final String USER = "root";
   private static final String PASSWORD = "Sieunhan@12";
   private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

   public static Connection getConnection() {
      Connection con = null;
      try {
         Class.forName(DRIVER);
         con = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (ClassNotFoundException | SQLException e) {
         System.err.println("Lỗi kết nối tới Database!!!");
      }
      return con;
   }

   public static void closeConnection(Connection con, CallableStatement cs) {
      if (con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            System.err.println("Lỗi đóng dữ liệu!!!");
         }
      }
      if (cs != null) {
         try {
            cs.close();
         } catch (SQLException e) {
            System.err.println("Lỗi đóng truy vấn!!!");
         }
      }
   }
}
