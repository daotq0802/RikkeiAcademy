import java.io.Serializable;
import java.util.Scanner;

public class Category implements Serializable, ICategory {
   int id;
   String name;
   String description;
   boolean status;

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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Category(int id, String name, String description, boolean status) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.status = status;
   }

   public Category() {
   }

   public int inputID(Scanner scanner) {
      while (true) {
         try {
            int id = Integer.parseInt(scanner.nextLine());
            if (id <= 0) {
               System.err.println("Mã danh mục không hợp lệ!!!");
            } else {
               boolean isFound = false;
               for(Category category : Store.categories){
                  if(category.getId() == id){
                     isFound = true;
                     break;
                  }
               }
               if(!isFound){
                  return id;
               }else{
                  System.err.println("Mã danh mục đã tồn tại!!!");
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã danh mục phải là số!!!");
         }
      }
   }

   public String inputName(Scanner scanner) {
      while (true) {
         String name = scanner.nextLine();
         if (name.isEmpty() || name.trim().isEmpty()) {
            System.err.println("Hãy nhập tên danh mục!!!");
         } else {
            boolean isFound = false;
            for (Category category : Store.categories) {
               if (category.getName().equals(name)) {
                  isFound = true;
                  break;
               }
            }
            if (!isFound) {
               return name;
            } else {
               System.err.println("Tên danh mục đã tồn tại!!!");
            }
         }
      }
   }

   public String inputDescription(Scanner scanner) {
      while (true) {
         String description = scanner.nextLine();
         if (description.isEmpty() || description.trim().isEmpty()) {
            System.err.println("Hãy nhập mô tả danh mục!!!");
         } else {
            return description;
         }
      }
   }

   public boolean inputStatus(Scanner scanner) {
      while (true) {
         String status = scanner.nextLine();
         if (status.isEmpty() || status.trim().isEmpty()) {
            System.err.println("Hãy nhập trạng thái danh mục!!!");
         } else {
            if (status.equals("true")) {
               return true;
            } else if (status.equals("false")) {
               return false;
            } else {
               System.err.println("Trạng thái không hợp lệ!!!");
            }
         }
      }
   }

   @Override
   public void inputData(Scanner scanner) {
      System.out.print("Nhập mã danh mục: ");
      this.id = inputID(scanner);
      System.out.print("Nhập tên danh mục ");
      this.name = inputName(scanner);
      System.out.print("Nhập mô tả danh mục: ");
      this.description = inputDescription(scanner);
      System.out.print("Nhập trạng thái danh mục: ");
      this.status = inputStatus(scanner);
   }

   @Override
   public String outputData() {
      return "Mã danh mục: " + this.id + "\t-\tTên danh mục: " + this.name +
              "\nMô tả: " + this.description +
              "\nTrạng thái: " + (this.status?"Hoạt động" : "Không hoạt động");
   }
}
