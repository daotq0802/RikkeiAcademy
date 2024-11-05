package entity;

public class Category {
   private int id;
   private String name;
   private boolean status;

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

   @Override
   public String toString() {
      return "Tên danh mục: " + this.name +
              "\nTrạng thái: " + (this.status ? "Hoạt động" : "Không hoạt động") +
              "\n-----------------------------";
   }
}
