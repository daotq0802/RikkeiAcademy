package ra.CafeShop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
   private String productID;
   private String productName;
   private double price;
   private String description;
   private String createTime;
   private int categoryID;
   private String status;

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

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getCreateTime() {
      return createTime;
   }

   public void setCreateTime(String createTime) {
      this.createTime = createTime;
   }

   public int getCategoryID() {
      return categoryID;
   }

   public void setCategoryID(int categoryID) {
      this.categoryID = categoryID;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String inputProductID(Scanner scan, Category[] categories, Product[] products) {
      while (true) {
         productID = scan.nextLine();
         if (productID.isEmpty() || productID.trim().isEmpty()) {
            System.err.println("Vui lòng nhập vào mã sản phẩm!!!");
         } else {
            if (!Pattern.matches("^[A|S|C][0-9]{3}$", productID)) {
               System.err.println("Mã sản phẩm không đúng định dạng!!!\n" +
                       "A -> Đồ ăn nhanh\n" +
                       "C -> Các đồ uống là Cafe\n" +
                       "S -> Các đồ uống là sinh tố\n" +
                       "Ví dụ: 'A001'\t'C001'\t'S001'");
            } else {
               boolean isFound = false;
               for (Product product : products) {
                  if (product.getProductID().equals(productID)) {
                     isFound = true;
                  }
               }
               if (isFound) {
                  System.err.println("Mã sản phẩm đã tồn tại, mời nhập lại!!!");
               } else {
                  System.out.println("Hãy chọn danh mục cho sản phẩm");
                  for (int i = 0; i < categories.length; i++) {
                     System.out.printf("%d. %s\n", i + 1, categories[i].getCategoryName());
                  }
                  while (true) {
                     try {
                        int categorySelected = Integer.parseInt(scan.nextLine());
                        if (categorySelected > 0 && categorySelected <= categories.length) {
                           this.categoryID = categories[categorySelected - 1].getCategoryID();
                           categories[categorySelected - 1].setProductCount(categories[categorySelected - 1].getProductCount() + 1);
                           return productID;
                        } else {
                           System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                        }
                     } catch (NumberFormatException e) {
                        System.err.println("Lựa chọn phải là số từ 1 - " + categories.length + "!!!");
                     }
                  }
               }
            }
         }
      }
   }

   public String inputProductName(Scanner scan, Product[] products) {
      while (true) {
         productName = scan.nextLine();
         if (Integer.parseInt(productName) <= 0 || Integer.parseInt(productName) > 0) {
            System.err.println("Tên sản phẩm nên là chuỗi ký tự, mời nhập lại!!!");
            inputProductName(scan, products);
         }
         if (productName.isEmpty() || productName.trim().isEmpty()) {
            System.err.println("Hãy đặt tên cho sản phẩm!!!");
         } else {
            boolean isFound = false;
            for (Product product : products) {
               if (product.getProductName().equals(productName)) {
                  isFound = true;
               }
            }
            if (isFound) {
               System.err.println("Tên sản phẩm đã tồn tại, mời nhập lại!!!");
            } else {
               return productName;
            }
         }
      }
   }

   public int inputProductPrice(Scanner scan) {
      try {
         while (true) {
            int price = Integer.parseInt(scan.nextLine());
            if (price <= 0) {
               System.err.println("Sản phẩm không có giá trị!!!");
            } else {
               return price;
            }
         }
      } catch (NumberFormatException e) {
         System.err.println("Giá sản phẩm phải là số!!!");
         return inputProductPrice(scan);
      }
   }

   public String inputProductDescription(Scanner scan) {
      while (true) {
         description = scan.nextLine();
         if (description.isEmpty() || description.trim().isEmpty()) {
            System.err.println("Hãy miêu tả cho sản phẩm!!!");
         }
         return description;
      }
   }

   public String inputCreateTime() {
      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return date.format(formatter);
   }

   public String inputStatus(Scanner scan) {
      List<String> listStatus = new ArrayList<>();
      listStatus.add("Đang bán");
      listStatus.add("Hết hàng");
      listStatus.add("Không bán");
      System.out.println("Chọn trạng thái cho sản phẩm");
      for (int i = 0; i < listStatus.size(); i++) {
         System.out.printf("%d. %s\t", i + 1, listStatus.get(i));
      }
      System.out.println();
      while (true) {
         try {
            int statusSelected = Integer.parseInt(scan.nextLine());
            if (statusSelected <= 0 || statusSelected > listStatus.size()) {
               System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
            }
            return listStatus.get(statusSelected - 1);
         } catch (NumberFormatException e) {
            System.err.println("Giá trị lựa chọn phải là số, mời chọn lại!!!");
         }
      }
   }

   public void inputData(Scanner scan, Category[] categories, Product[] products) {
      System.out.println("Nhập mã sản phẩm");
      this.productID = inputProductID(scan, categories, products);
      System.out.println("Nhập tên sản phẩm");
      this.productName = inputProductName(scan, products);
      System.out.println("Nhập giá sản phẩm");
      this.price = inputProductPrice(scan);
      System.out.println("Nhập mô tả sản phẩm");
      this.description = inputProductDescription(scan);
      this.createTime = inputCreateTime();
      this.status = inputStatus(scan);
   }

   public String displayData() {
      return "Mã sản phẩm: " + this.productID + "\t-\tTên sản phẩm: " + this.productName +
              "\nNgày tạo: " + this.createTime + "\t-\tGiá: " + this.price +
              "\nMô tả: " + this.description +
              "\nTrạng thái: " + this.status + "\t-\tDanh mục: " + this.categoryID;
   }
}
