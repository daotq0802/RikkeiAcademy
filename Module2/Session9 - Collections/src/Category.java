import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Category implements IShop, Serializable {
   private int Id;
   private String Name;
   private String Description;
   private boolean status;

   public int getId() {
      return Id;
   }

   public void setId(int id) {
      Id = id;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }

   public String getDescription() {
      return Description;
   }

   public void setDescription(String description) {
      Description = description;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Category(int id, String name, String description, boolean status) {
      Id = id;
      Name = name;
      Description = description;
      this.status = status;
   }

   public Category() {
   }

   public int inputId(){
      Date date = new Date();
      return (int) date.getTime();
   }

   public String inputName(Scanner scanner){
      while(true){
         String name = scanner.nextLine();
         if(name.isEmpty() || name.trim().isEmpty()){
            System.err.println("Hãy nhập tên danh mục!!!");
         }else{
            boolean found = false;
            for(Category category : Main.categories){
               if(name.equals(category.getName())){
                  found = true;
                  break;
               }
            }
            if(!found){
               return name;
            }else{
               System.out.println("Tên danh mục đã tồn tại!!!");
            }
         }
      }
   }

   public String inputDescription(Scanner scanner){
      while(true){
         String description = scanner.nextLine();
         if(description.isEmpty() || description.trim().isEmpty()){
            System.err.println("Hãy nhập mô tả danh mục!!!");
         }else{
            return description;
         }
      }
   }

   public boolean inputStatus(Scanner scanner){
      while(true){
         String status = scanner.nextLine();
         if(status.isEmpty() || status.trim().isEmpty()){
            System.err.println("Hãy nhập trạng thái danh mục!!!");
         }else{
            if(status.equals("true")){
               return true;
            }else if(status.equals("false")){
               return false;
            }else{
               System.err.println("Trạng thái danh mục phải là true/false" +
                       "\ntrue -> Hoạt động" +
                       "\nfalse -> Không hoạt động");
            }
         }
      }
   }

   @Override
   public void inputData(Scanner scanner) {
      this.Id = inputId();
      System.out.print("Nhập tên danh mục: ");
      this.Name = inputName(scanner);
      System.out.print("Nhập mô tả danh mục: ");
      this.Description = inputDescription(scanner);
      System.out.print("Nhập trạng thái danh mục: ");
      this.status = inputStatus(scanner);
   }

   @Override
   public String displayData() {
      return "Tên danh mục: " + this.Name +
              "\nMô tả: " + this.Description +
              "\nStatus : " + this.status;
   }
}
