package presentation;

import business.BookBusiness;
import business.BookTypeBusiness;
import entity.Book;
import entity.BookType;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookManagement {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      while (true) {
         System.out.println("******************BOOK-MANAGEMENT******************\n" +
                 "1. Quản lý loại sách\n" +
                 "2. Quản lý sách\n" +
                 "3. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  bookTypeManagement(sc);
                  break;
               case 2:
                  bookManagement(sc);
                  break;
               case 3:
                  System.exit(0);
               default:
                  System.err.println("Lựa chọn không hợp lệ!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void bookTypeManagement(Scanner sc) {
      while (true) {
         System.out.println("**********************BOOKTYPE-MENU********************\n" +
                 "1. Danh sách loại sách\n" +
                 "2. Tạo mới loại sách\n" +
                 "3. Cập nhật thông tin loại sách\n" +
                 "4. Xóa loại sách\n" +
                 "5. Thống kê số lượng sách theo mã loại sách\n" +
                 "6. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  getAllBookType();
                  break;
               case 2:
                  createBookType(sc);
                  break;
               case 3:
                  updateBookType(sc);
                  break;
               case 4:
                  deleteBookType(sc);
                  break;
               case 5:
                  countBookInType();
                  break;
               case 6:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void getAllBookType() {
      List<BookType> list = BookTypeBusiness.getList();
      System.out.println("Danh sách thể loại sách" +
              "\n--------------------------------");
      list.stream().forEach(System.out::println);
   }

   private static void createBookType(Scanner sc) {
      BookType bookType = new BookType();
      System.out.print("Nhập tên thể loại sách: ");
      bookType.setTypeName(sc.nextLine());
      System.out.print("Nhập mô tả sách: ");
      bookType.setDescription(sc.nextLine());
      boolean result = BookTypeBusiness.save(bookType);
      if (result) {
         System.out.println("Thêm mới thành công!!");
      } else {
         System.err.println("Thêm mới thất bại");
      }
   }

   private static void updateBookType(Scanner sc) {
      List<BookType> list = BookTypeBusiness.getList();
      System.out.println("Chọn thể loại sách cần cập nhật: ");
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, list.get(i).getTypeName());
      }
      System.out.print("Lựa chọn của bạn: ");
      try {
         int choice = Integer.parseInt(sc.nextLine());
         if (choice <= 0 || choice > list.size()) {
            System.err.println("Lựa chọn không hợp lệ!!!");
         } else {
            BookType bookType = list.get(choice - 1);
            while (true) {
               System.out.println("1. Cập nhật tên thể loại" +
                       "\n2. Cập nhật mô tả" +
                       "\n3. Cập nhật trạng thái" +
                       "\n4. Thoát");
               System.out.print("Lựa chọn của bạn: ");
               try {
                  int selection = Integer.parseInt(sc.nextLine());
                  switch (selection) {
                     case 1:
                        bookType.setTypeName(sc.nextLine());
                        break;
                     case 2:
                        bookType.setDescription(sc.nextLine());
                        break;
                     case 3:
                        bookType.setDeleted(Boolean.parseBoolean(sc.nextLine()));
                        break;
                     case 4:
                        if (BookTypeBusiness.update(bookType)) {
                           System.out.println("Cập nhật thành công!!");
                        } else {
                           System.out.println("Cập nhật thất bại");
                        }
                        return;
                     default:
                        System.err.println("Lựa chọn không hợp lệ!!!");
                  }
               } catch (NumberFormatException e) {
                  System.err.println("Lựa chọn phải là số!!!");
               }
            }
         }
      } catch (NumberFormatException e) {
         System.err.println("Lựa chọn phải là số!!!");
      }
   }

   private static void deleteBookType(Scanner sc) {
      List<BookType> list = BookTypeBusiness.getList();
      System.out.println("Chọn thể loại sách cần xoá: ");
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, list.get(i).getTypeName());
      }
      System.out.print("Lựa chọn của bạn: ");
      try {
         int choice = Integer.parseInt(sc.nextLine());
         if (choice <= 0 || choice > list.size()) {
            System.err.println("Lựa chọn không hợp lệ!!!");
         } else {
            BookType bookType = list.get(choice - 1);
            System.out.println("Đồng ý xoá thể loại - " + bookType.getTypeName() + "?" +
                    "\n1. Đồng ý\t\t2. Huỷ bỏ");
            System.out.print("Lựa chọn của bạn: ");
            try {
               int selection = Integer.parseInt(sc.nextLine());
               switch (selection) {
                  case 1:
                     if (BookTypeBusiness.delete(bookType)) {
                        System.out.println("Xoá thành công!!");
                     } else {
                        System.err.println("Xoá thất bại!!");
                     }
                     break;
                  case 2:
                     break;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            }
         }
      } catch (NumberFormatException e) {
         System.err.println("Lựa chọn phải là số");
      }
   }

   private static void countBookInType() {
      Map<String, Integer> mapCount = BookTypeBusiness.statisticBook();
      System.out.println("Thống kê sách");
      for (Map.Entry<String, Integer> entry : mapCount.entrySet()) {
         System.out.println(entry.getKey() + ": " + entry.getValue());
      }
   }

   public static void bookManagement(Scanner sc) {
      while (true) {
         System.out.println("***********************BOOK-MENU***********************\n" +
                 "1. Danh sách sách\n" +
                 "2. Tạo mới sách\n" +
                 "3. Cập nhật thông tin sách\n" +
                 "4. Xóa sách\n" +
                 "5. Hiển thị danh sách các cuốn sách theo giá giảm dần\n" +
                 "6. Tìm kiếm sách theo tên hoặc nội dung\n" +
                 "7. Thống kê số lượng sách theo nhóm\n" +
                 "8. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  getAllBook();
                  break;
               case 2:
                  createNewBook(sc);
                  break;
               case 3:
                  updateBook(sc);
                  break;
               case 4:
                  deleteBook(sc);
                  break;
               case 5:
                  sortBookByPriceDESC();
                  break;
               case 6:
                  searchBook(sc);
                  break;
               case 7:
                  break;
               case 8:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void getAllBook() {
      List<Book> books = BookBusiness.getBooks();
      books.stream().forEach(System.out::println);
   }

   private static void createNewBook(Scanner sc) {
      while (true) {
         try {
            Book newBook = new Book();
            System.out.print("Nhập tên sách: ");
            newBook.setBookName(sc.nextLine());
            System.out.print("Nhập tiêu đề: ");
            newBook.setTitle(sc.nextLine());
            System.out.print("Nhập tác giả: ");
            newBook.setAuthor(sc.nextLine());
            System.out.print("Nhập số trang: ");
            newBook.setTotalPages(Integer.parseInt(sc.nextLine()));
            System.out.print("Nhập nội dung: ");
            newBook.setContent(sc.nextLine());
            System.out.print("Nhập nhà xuất bản: ");
            newBook.setPublisher(sc.nextLine());
            System.out.print("Nhập giá sách: ");
            newBook.setPrice(Integer.parseInt(sc.nextLine()));
            System.out.print("Nhập thể loại sách: ");
            newBook.setTypeId(Integer.parseInt(sc.nextLine()));
            if (BookBusiness.save(newBook)) {
               System.out.println("Thêm mới thành công!!!");
               return;
            } else {
               System.err.println("Thêm mới thất bại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Hãy nhập số!!!");
         }
      }
   }

   private static void updateBook(Scanner sc) {
      System.out.println("Chọn sách cần cập nhật");
      List<Book> books = BookBusiness.getBooks();
      for (int i = 0; i < books.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, books.get(i).getBookName());
      }
      try {
         int choice = Integer.parseInt(sc.nextLine());
         if (choice <= 0 || choice > books.size()) {
            System.err.println("Lựa chọn không hợp lệ!!!");
         } else {
            Book book = books.get(choice - 1);
            while (true) {
               System.out.println("Chọn mục cần cập nhật" +
                       "\n1. Cập nhật tên sách" +
                       "\n2. Cập nhật tiêu đề sách" +
                       "\n3. Cập nhật tác giả" +
                       "\n4. Cập nhật số trang sách" +
                       "\n5. Cập nhật nội dung" +
                       "\n6. Cập nhật nhà xuất bản" +
                       "\n7. Cập nhật giá sách" +
                       "\n8. Cập nhật thể loại" +
                       "\n9. Cập nhật trạng thái" +
                       "\n10. Thoát");
               try {
                  int selection = Integer.parseInt(sc.nextLine());
                  switch (selection) {
                     case 1:
                        book.setBookName(sc.nextLine());
                        break;
                     case 2:
                        book.setTitle(sc.nextLine());
                        break;
                     case 3:
                        book.setAuthor(sc.nextLine());
                        break;
                     case 4:
                        book.setTotalPages(Integer.parseInt(sc.nextLine()));
                        break;
                     case 5:
                        book.setContent(sc.nextLine());
                        break;
                     case 6:
                        book.setPublisher(sc.nextLine());
                        break;
                     case 7:
                        book.setPrice(Integer.parseInt(sc.nextLine()));
                        break;
                     case 8:
                        book.setTypeId(Integer.parseInt(sc.nextLine()));
                        break;
                     case 9:
                        book.setDeleted(Boolean.parseBoolean(sc.nextLine()));
                        break;
                     case 10:
                        if (BookBusiness.update(book)) {
                           System.out.println("Cập nhật thành công!!!");
                        } else {
                           System.err.println("Cập nhật thất bại!!!");
                        }
                        return;
                     default:
                        System.err.println("lựa chọn không hợp lệ!!!");
                  }
               } catch (NumberFormatException e) {
                  System.err.println("Lựa chọn phải là số");
               }
            }
         }
      } catch (NumberFormatException e) {
         System.err.println("Lựa chọn phải là số!!!");
      }
   }

   private static void deleteBook(Scanner sc) {
      System.out.println("Chọn sách cần xoá");
      List<Book> books = BookBusiness.getBooks();
      for (int i = 0; i < books.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, books.get(i).getBookName());
      }
      try {
         int choice = Integer.parseInt(sc.nextLine());
         if (choice <= 0 || choice > books.size()) {
            System.err.println("Lựa chọn không hợp lệ!!!");
         } else {
            Book book = books.get(choice - 1);
            while (true) {
               System.out.println("Đồng ý xoá sách - " + book.getBookName() + "?" +
                       "\n1. Đồng ý\t\t2. Huỷ bỏ");
               System.out.print("Lựa chọn của bạn: ");
               int selection = Integer.parseInt(sc.nextLine());
               switch (selection) {
                  case 1:
                     if(BookBusiness.delete(book.getBookId())){
                        System.out.println("Xoá thành công!!!");
                     }else{
                        System.err.println("Xoá thất bại!!!");
                     }
                     return;
                  case 2:
                     return;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            }
         }
      } catch (NumberFormatException e) {
         System.err.println("Lựa chọn phải là số");
      }
   }

   private static void sortBookByPriceDESC(){
      List<Book> sortList = BookBusiness.sortList();
      sortList.stream().forEach(System.out::println);
   }

   private static void searchBook(Scanner sc){
      System.out.print("Nhập tên sách hoặc nội dung sách bạn muốn tìm: ");
      List<Book> books = BookBusiness.searchBook(sc.nextLine());
      if(books.isEmpty()){
         System.err.println("Không tìm thấy sách nào phù hợp!!!");
      }else{
         System.out.println("Đã tìm thấy " + books.size() + " kết quả");
         for(int i = 0; i < books.size(); i++){
            System.out.printf("%d. %s\n", i + 1, books.get(i).getBookName());
         }
      }
   }
}
