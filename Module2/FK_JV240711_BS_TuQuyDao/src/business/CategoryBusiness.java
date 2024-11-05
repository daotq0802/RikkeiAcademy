package business;

import entity.Category;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryBusiness {
   public static List<Category> getListCategory() {
      List<Category> listCategory = new ArrayList<Category>();
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      try {
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call getAllCategories()}");
         rs = cs.executeQuery();
         while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setStatus(rs.getBoolean("category_status"));
            listCategory.add(category);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return listCategory;
   }

   public static boolean save(Category category) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try{
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call createCategory(?)}");
         cs.setString(1, category.getName());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }finally{
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static boolean update(Category category) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try{
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call updateCategory(?,?,?)}");
         cs.setInt(1, category.getId());
         cs.setString(2, category.getName());
         cs.setBoolean(3, category.isStatus());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }finally{
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static boolean delete(int id) {
      Connection con = null;
      CallableStatement cs = null;
      boolean result = false;
      try{
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call deleteCategory(?)}");
         cs.setInt(1, id);
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return result;
   }

   public static Map<String, Integer> statisticProduct(){
      Connection con = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      Map<String, Integer> map = new HashMap<>();
      try{
         con = ConnectionDB.getConnection();
         cs = con.prepareCall("{call statisticProduct()}");
         rs = cs.executeQuery();
         while (rs.next()) {
            map.put(rs.getString(1), rs.getInt(2));
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }finally {
         ConnectionDB.closeConnection(con, cs);
      }
      return map;
   }
}
