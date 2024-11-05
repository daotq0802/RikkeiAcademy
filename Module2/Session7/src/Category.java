import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Category implements IEntity, Serializable {
   int id;
   String name;
   boolean status;

   public Category() {
   }

   public Category(int id, String name, boolean status) {
      this.id = id;
      this.name = name;
      this.status = status;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public int inputID(Scanner scanner) {
      while (true) {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input <= 0) {
               System.err.println("Mã thể loại phải lớn hơn 0!!!");
            }
            boolean found = false;
            for (Category category : Library.categories) {
               if (category.getId() == input) {
                  found = true;
                  break;
               }
            }
            if (!found) {
               return input;
            }
            System.err.println("Mã thể loại đã tồn tại!!!");
         } catch (NumberFormatException e) {
            System.err.println("Mã thể loại phải là số!!!");
         }
      }
   }

   public String inputName(Scanner scanner) {
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập tên cho thể loại!!!");
         }
         if (input.length()>=6 && input.length()<=30) {
            boolean found = false;
            for (Category category : Library.categories) {
               if (category.getName().equals(input)) {
                  found = true;
                  break;
               }
            }
            if (!found) {
               return input;
            }
            System.err.println("Tên thể loại đã tồn tại!!!");
         }else{
            System.err.println("Tên thể loại phải từ 6 - 30 ký tự!!!");
         }
      }
   }

   public boolean inputStatus(Scanner scanner) {
      System.out.println("1. Hoạt động\t\t2. Không hoạt động");
      while (true) {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
               case 1:
                  return true;
               case 2:
                  return false;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   @Override
   public void input(Scanner scanner) {
      System.out.print("Nhập mã thể loại: ");
      this.id = inputID(scanner);
      System.out.print("Nhập tên thể loại: ");
      this.name = inputName(scanner);
      System.out.print("Chọn trạng thái: ");
      this.status = inputStatus(scanner);
   }

   @Override
   public String output() {
      return "Mã thể loại: " + this.id +
              "\nTên thể loại: " + this.name +
              "\nTrạng thái: " + (this.status ? "Hoạt động" : "Không hoạt động");
   }
}
