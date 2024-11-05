package business;

import entity.BookType;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookTypeBusiness {
   public static List<BookType> getList() {
      Connection conn = null;
      CallableStatement cs = null;
      List<BookType> list = new ArrayList<BookType>();
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call findAllBookType()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            BookType bookType = new BookType();
            bookType.setTypeId(rs.getInt("typeID"));
            bookType.setTypeName(rs.getString("typeName"));
            bookType.setDescription(rs.getString("description"));
            bookType.setDeleted(rs.getBoolean("isDeleted"));
            list.add(bookType);
         }
      } catch (SQLException e) {
         System.err.println("Xảy ra lỗi truy vấn!!!");
      }
      return list;
   }

   public static boolean save(BookType bookType) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call createNewBookType(?,?)}");
         cs.setString(1, bookType.getTypeName());
         cs.setString(2, bookType.getDescription());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static boolean update(BookType bookType) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try{
         conn = ConnectionDB.getConnection();
         cs=conn.prepareCall("{call updateBookType(?,?,?,?)}");
         cs.setInt(1, bookType.getTypeId());
         cs.setString(2, bookType.getTypeName());
         cs.setString(3, bookType.getDescription());
         cs.setBoolean(4, bookType.isDeleted());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!");
      }finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static boolean delete(BookType bookType) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try{
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call deleteBookType(?)}");
         cs.setInt(1, bookType.getTypeId());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!1");
      }finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static Map<String,Integer> statisticBook(){
      Map<String,Integer> mapStatistic = new HashMap<String,Integer>();
      Connection conn = null;
      CallableStatement cs = null;
      try{
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call countBookInType()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            mapStatistic.put(rs.getString(1), rs.getInt(2));
         }
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      }finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return mapStatistic;
   }
}
