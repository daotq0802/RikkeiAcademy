package entity;

public class Department {
   private int id;
   private String name;
   private String description;
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

   @Override
   public String toString() {
      return "Tên phòng ban: " + this.name +
              "\nMô tả: " + this.description +
              "\nTrạng thái: " + (this.status ? "Làm việc" : "Nghỉ") +
              "\n----------------------------------";
   }
}
