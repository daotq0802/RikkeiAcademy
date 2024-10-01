package ra.CafeShop;

import java.util.Scanner;

public class Category {
   private int categoryID;
   private String categoryName;
   private String description;
   private boolean status;
   private int productCount;

   public int getProductCount() {
      return productCount;
   }

   public void setProductCount(int productCount) {
      this.productCount = productCount;
   }

   public void setCategoryID(int categoryID) {
      this.categoryID = categoryID;
   }

   public int getCategoryID() {
      return categoryID;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
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

   public String getCategoryName() {
      return categoryName;
   }

   // * Method nhập tên Category
   public String inputCategoryName(Scanner scan, Category[] categories) {
      System.out.println("Nhập tên danh mục");
      while (true) {
         categoryName = scan.nextLine();
         if (categoryName.isEmpty() || categoryName.trim().isEmpty()) {
            System.err.println("Vui lòng nhập tên cho danh mục!!!");
         } else {
            boolean found = false;
            for (int i = 0; i < categories.length; i++) {
               if (categories[i].getCategoryName().equals(categoryName)) {
                  found = true;
               }
            }
            if (found) {
               System.err.println("Tên danh mục đã trùng lặp");
            }
            return categoryName;
         }
      }
   }

   // * Method nhập mô tả Category
   public String inputCategoryDescription(Scanner scan) {
      System.out.println("Nhập mô tả danh mục");
      while (true) {
         description = scan.nextLine();
         if (description.isEmpty() || description.trim().isEmpty()) {
            System.err.println("Vui lòng nhập mô tả cho danh mục!!!");
         } else {
            return description;
         }
      }
   }

   // * Method nhập trạng thái Category
   public boolean inputCategoryStatus(Scanner scan) {
      System.out.println("Chọn trạng thái danh mục");
      while (true) {
         String status = scan.nextLine();
         if (status.isEmpty() || status.trim().isEmpty()) {
            System.err.println("Vui lòng nhập trạng thái cho danh mục!!!");
         } else {
            if (status.equals("true") || status.equals("false")) {
               return Boolean.parseBoolean(status);
            } else {
               System.err.println("Giá trị nhập vào phải là 'true' hoặc 'false'" +
                       "\ntrue -> Hoạt động" +
                       "\nfalse -> Không hoạt động");
            }
         }
      }
   }

   public void inputData(Scanner scan, Category[] categories) {
      this.categoryName = inputCategoryName(scan, categories);
      this.description = inputCategoryDescription(scan);
      this.status = inputCategoryStatus(scan);
      this.productCount = 0;
   }

   public String displayCategory() {
      return "Mã danh mục: " + this.categoryID +
              "\nTên danh mục: " + this.categoryName +
              "\nMô tả: " + this.description +
              "\nTrạng thái: " + (this.status ? "Hoạt động" : "Không hoạt động") +
              "\nSản phẩm hiện có: " + this.productCount;
   }
}
