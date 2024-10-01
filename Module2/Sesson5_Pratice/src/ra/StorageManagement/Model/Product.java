package ra.StorageManagement.Model;

import ra.StorageManagement.StoreSystem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
   private String productID, productName, manufacturer, productStatus;


   public Product() {

   }

   public Product(String productID, String productName, String manufacturer, String productStatus) {
      this.productID = productID;
      this.productName = productName;
      this.manufacturer = manufacturer;
      this.productStatus = productStatus;
   }

   public String getProductID() {
      return productID;
   }

   public void setProductID(String productID) {
      this.productID = productID;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public String getManufacturer() {
      return manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public String getProductStatus() {
      return productStatus;
   }

   public void setProductStatus(String productStatus) {
      this.productStatus = productStatus;
   }

   public String inputID(Scanner scanner) {
      System.out.print("Tạo ID cho sản phẩm: ");
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy đặt ID cho sản phẩm!!!");
         } else {
            if (Pattern.matches("^[A-Z][a-z0-9]{3}$", input)) {
               boolean isFound = false;
               for(Product product : StoreSystem.products){
                  if (product.getProductID().equals(input)) {
                     isFound = true;
                  }
               }
               if (!isFound) {
                  return input;
               }else{
                  System.err.println("Mã sản phẩm đã tồn tại!!!");
               }
            } else {
               System.err.println("ID sản phẩm phải bắt đầu bằng chữ in hoa\nVD: A001\tB001\tC001v.v...!!!");
            }
         }
      }
   }

   public String inputName(Scanner scanner) {
      System.out.print("Nhập tên cho sản phẩm: ");
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy đặt tên cho sản phẩm");
         } else {
            return input;
         }
      }
   }

   public String inputManufacturer(Scanner scanner) {
      System.out.print("Nhập tên nhà sản xuất: ");
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy điền nhà sản xuất của sản phẩm!!!");
         } else {
            return input;
         }
      }
   }

   public String inputStatus(Scanner scanner) {
      System.out.println("Chọn trạng thái cho sản phẩm\n1. Hoạt động\t\t2. Không hoạt động");
      while (true) {
         try {
            int selection = Integer.parseInt(scanner.nextLine());
            switch (selection) {
               case 1:
                  return "Hoạt động";
               case 2:
                  return "Không hoạt động";
               default:
                  System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
         }
      }
   }

   public void inputData(Scanner scanner) {
      this.productID = inputID(scanner);
      this.productName = inputName(scanner);
      this.manufacturer = inputManufacturer(scanner);
      this.productStatus = inputStatus(scanner);
   }

   public String displayData() {
      return "Mã sản phẩm: " + this.productID +
              "\nTên sản phẩm: " + this.productName +
              "\nNhà sản xuất: " + this.manufacturer +
              "\nTrạng thái: " + this.productStatus;
   }
}
