package business;

import entity.Book;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness {
   public static List<Book> getBooks() {
      List<Book> books = new ArrayList<Book>();
      Connection conn = null;
      CallableStatement cs = null;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call findAllBook()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setDeleted(rs.getBoolean("isDeleted"));
            book.setPrice(rs.getDouble("price"));
            book.setContent(rs.getString("content"));
            book.setTypeId(rs.getInt("typeId"));
            book.setTotalPages(rs.getInt("totalPages"));
            books.add(book);
         }
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      }
      return books;
   }

   public static boolean save(Book book) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call createNewBook(?,?,?,?,?,?,?,?)}");
         cs.setString(1, book.getBookName());
         cs.setString(2, book.getTitle());
         cs.setString(3, book.getAuthor());
         cs.setInt(4, book.getTotalPages());
         cs.setString(5, book.getContent());
         cs.setString(6, book.getPublisher());
         cs.setDouble(7, book.getPrice());
         cs.setInt(8, book.getTypeId());
         cs.executeUpdate();
         result = true;
      } catch (SQLIntegrityConstraintViolationException e) {
         System.err.println("Lỗi khoá phụ!!");
      } catch (SQLException e) {
         System.err.println("Lỗi truy vấn!!");
      } finally {
         ConnectionDB.closeConnection(conn, cs);
      }
      return result;
   }

   public static boolean update(Book book) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call updateBook(?,?,?,?,?,?,?,?,?,?)}");
         cs.setInt(1, book.getBookId());
         cs.setString(2, book.getBookName());
         cs.setString(3, book.getTitle());
         cs.setString(4, book.getAuthor());
         cs.setInt(5, book.getTotalPages());
         cs.setString(6, book.getContent());
         cs.setString(7, book.getPublisher());
         cs.setDouble(8, book.getPrice());
         cs.setInt(9, book.getTypeId());
         cs.setBoolean(10, book.isDeleted());
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

   public static boolean delete(int bookId) {
      Connection conn = null;
      CallableStatement cs = null;
      boolean result = false;
      try {
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call deleteBook(?)}");
         cs.setInt(1, bookId);
         cs.executeUpdate();
         result = true;
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      }
      return result;
   }

   public static List<Book> sortList(){
      List<Book> sort = new ArrayList<>();
      Connection conn = null;
      CallableStatement cs = null;
      try{
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call sortBookByPriceDESC()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setDeleted(rs.getBoolean("isDeleted"));
            book.setPrice(rs.getDouble("price"));
            book.setContent(rs.getString("content"));
            book.setTypeId(rs.getInt("typeId"));
            book.setTotalPages(rs.getInt("totalPages"));
            sort.add(book);
         }
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      }
      return sort;
   }

   public static List<Book> searchBook(String search){
      Connection conn = null;
      CallableStatement cs = null;
      List<Book> searchBook = new ArrayList<>();
      try{
         conn = ConnectionDB.getConnection();
         cs = conn.prepareCall("{call searchBookByNameOrContent(?)}");
         cs.setString(1, search);
         ResultSet rs = cs.executeQuery();
         while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setDeleted(rs.getBoolean("isDeleted"));
            book.setPrice(rs.getDouble("price"));
            book.setContent(rs.getString("content"));
            book.setTypeId(rs.getInt("typeId"));
            book.setTotalPages(rs.getInt("totalPages"));
            searchBook.add(book);
         }
      } catch (SQLException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      }
      return searchBook;
   }
}
