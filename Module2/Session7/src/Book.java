import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book implements IEntity, Serializable {
   String id;
   String title;
   String author;
   String publisher;
   int year;
   String description;
   int categoryID;

   public Book() {
   }

   public Book(String id, String title, String author, String publisher, int year, String description, int categoryID) {

   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getCategoryID() {
      return categoryID;
   }

   public void setCategoryID(int categoryID) {
      this.categoryID = categoryID;
   }

   public String inputID(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập mã sách!!!");
         } else {
            if (Pattern.matches("^B\\w{3}$", input)) {
               boolean found = false;
               for (Book book : Library.books) {
                  if (book.title.equals(input)) {
                     found = true;
                     break;
                  }
               }
               if (!found) {
                  return input;
               } else {
                  System.err.println("Mã sách đã tồn tại!!!");
               }
            } else {
               System.err.println("Mã sách phải bắt đầu bằng 'B' và dài 4 ký tự");
            }
         }
      }
   }

   public String inputTitle(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập tiêu đề sách!!!");
         }
         if (input.length() >= 6 && input.length() <= 50) {
            boolean found = false;
            for (Book book : Library.books) {
               if (book.title.equals(input)) {
                  found = true;
                  break;
               }
            }
            if (!found) {
               return input;
            } else {
               System.err.println("Sách đã tồn tại!!!");
            }
         } else {
            System.err.println("Tiêu đề phải có độ dài từ 6 - 50 ký tự!!!");
         }
      }
   }

   public String inputAuthor(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập tên tác giả");
         }
         return input;
      }
   }

   public String inputPublisher(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập tên nhà sản xuất!!!");
         }
         return input;
      }
   }

   public String inputDescription(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập mô tả sách!!!!");
         }
         return input;
      }
   }

   public int inputYear(Scanner scanner) {
      while (true) {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input < 1970 || input > LocalDate.now().getYear()) {
               System.err.println("Năm sản xuất phải từ năm 1970 đến nay!!!");
            }
            return input;
         } catch (NumberFormatException e) {
            System.err.println("Năm sản xuất phải là số");
         }
      }
   }

   public int inputCategoryID(Scanner scanner) {
      System.out.println("Danh sách thể loại");
      for (int i = 0; i < Library.categories.size(); i++) {
         System.out.printf("%d. %d (%s)\n", i + 1, Library.categories.get(i).id, Library.categories.get(i).name);
      }
      while (true) {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input <= 0 || input > Library.categories.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            }
            return Library.categories.get(input - 1).getId();
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   @Override
   public void input(Scanner scanner) {
      System.out.print("Nhập mã sách: ");
      this.id = inputID(scanner);
      System.out.print("Nhập tiêu đề: ");
      this.title = inputTitle(scanner);
      System.out.print("Nhập tác giả: ");
      this.author = inputAuthor(scanner);
      System.out.print("Nhập nhà sản xuất: ");
      this.publisher = inputPublisher(scanner);
      System.out.print("Nhập năm sản xuất: ");
      this.year = inputYear(scanner);
      System.out.print("Nhập mô tả sách: ");
      this.description = inputDescription(scanner);
      System.out.print("Chọn thể loại sách - ");
      this.categoryID = inputCategoryID(scanner);
   }

   @Override
   public String output() {
      return "Mã sách: " + this.id + "\t - \tTiêu đề: " + this.title +
              "\nTác giả: " + this.author + "\t - \tNhà sản xuất: " + this.publisher +
              "\nMô tả: " + this.description +
              "\nNăm sản xuất: " + this.year + "\t - \tThể loại sách: " + this.categoryID;
   }
}
