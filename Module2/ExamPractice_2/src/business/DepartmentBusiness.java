package business;

import entity.Department;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBusiness {
   public static List<Department> getDepartment() {
      Connection conn = null;
      CallableStatement cs = null;
      List<Department> list = new ArrayList<Department>();
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call getAllDepartment()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            Department d = new Department();
            d.setId(rs.getInt("dept_id"));
            d.setName(rs.getString("dept_name"));
            d.setDescription(rs.getString("dept_description"));
            d.setStatus(rs.getBoolean("dept_status"));
            list.add(d);
         }
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return list;
   }

   public static boolean save(Department department) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call createDepartment(?,?)}");
         cs.setString(1, department.getName());
         cs.setString(2, department.getDescription());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static boolean update(Department department) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call updateDepartment(?,?,?,?)}");
         cs.setInt(1, department.getId());
         cs.setString(2, department.getName());
         cs.setString(3, department.getDescription());
         cs.setBoolean(4, department.isStatus());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static boolean delete(int id) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call deleteDepartment(?)}");
         cs.setInt(1, id);
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static List<Department> searchDepartmentByName(String name) {
      Connection conn = null;
      CallableStatement cs = null;
      List<Department> listSearch = new ArrayList<>();
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call findDepartmentByName(?)}");
         cs.setString(1, name);
         cs.executeQuery();
         ResultSet rs = cs.getResultSet();
         while (rs.next()) {
            Department d = new Department();
            d.setId(rs.getInt("dept_id"));
            d.setName(rs.getString("dept_name"));
            d.setDescription(rs.getString("dept_description"));
            d.setStatus(rs.getBoolean("dept_status"));
            listSearch.add(d);
         }
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return listSearch;
   }
}
