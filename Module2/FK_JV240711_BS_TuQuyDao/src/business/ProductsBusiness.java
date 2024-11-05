package business;

import entity.Products;
import util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsBusiness {
   public static List<Products> getListProduct() {
      List<Products> list = new ArrayList<Products>();
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procGetProduct()}");
         rs = cs.executeQuery();
         while (rs.next()) {
            Products p = new Products();
            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setStock(rs.getInt("stock"));
            p.setCost_price(rs.getDouble("cost_price"));
            p.setSelling_price(rs.getDouble("selling_price"));
            p.setCategory_id(rs.getInt("category_id"));
            p.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
            list.add(p);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return list;
   }

   public static boolean addProduct(Products p) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procCreateProduct(?,?,?,?,?)}");
         cs.setInt(1, p.getCategory_id());
         cs.setString(2, p.getName());
         cs.setInt(3, p.getStock());
         cs.setDouble(4, p.getCost_price());
         cs.setDouble(5, p.getSelling_price());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static boolean updateProduct(Products p) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procUpdateProduct(?,?,?,?,?,?,?)}");
         cs.setInt(1, p.getId());
         cs.setInt(2, p.getCategory_id());
         cs.setString(3, p.getName());
         cs.setInt(4, p.getStock());
         cs.setDouble(5, p.getCost_price());
         cs.setDouble(6, p.getSelling_price());
         LocalDateTime localDateTime = p.getCreated_at().atStartOfDay();
         cs.setTimestamp(7, Timestamp.valueOf(localDateTime));
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static boolean deleteProduct(int id) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procDeleteProduct(?)}");
         cs.setInt(1, id);
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static List<Products> listCreatedAtDESC() {
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      List<Products> list = new ArrayList<>();
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procCreatedDateDESC()}");
         rs = cs.executeQuery();
         while (rs.next()) {
            Products p = new Products();
            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setStock(rs.getInt("stock"));
            p.setCost_price(rs.getDouble("cost_price"));
            p.setSelling_price(rs.getDouble("selling_price"));
            p.setCategory_id(rs.getInt("category_id"));
            p.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
            list.add(p);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return list;
   }

   public static Map<String, Double> listBySellingRange(double min, double max) {
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      Map<String, Double> list = new HashMap<>();
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procSearchBySellingPrice(?,?)}");
         cs.setDouble(1, min);
         cs.setDouble(2, max);
         rs = cs.executeQuery();
         while (rs.next()) {
            list.put(rs.getString("product_name"), rs.getDouble("selling_price"));
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return list;
   }

   public static Map<String,Double> getTopThree() {
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      Map<String,Double> list = new HashMap<>();
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call procGetTop3Product()}");
         rs = cs.executeQuery();
         while (rs.next()) {
            list.put(rs.getString("product_name"), rs.getDouble("profit"));
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return list;
   }
}
