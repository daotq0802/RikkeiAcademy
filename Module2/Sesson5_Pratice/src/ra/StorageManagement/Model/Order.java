package ra.StorageManagement.Model;

import ra.StorageManagement.StoreSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Order {
   private String orderID;
   private int quantity;
   private String productID;
   private String created;
   private String updated;
   private int createdUserID;
   private int updatedUserID;
   private boolean orderType;
   private double price;
   private String status;

   public Order() {
   }

   public Order(String orderID, int quantity, String productID, String created, String updated, int createdUserID, int updatedUserID, boolean orderType, double price, String status) {
      this.orderID = orderID;
      this.quantity = quantity;
      this.productID = productID;
      this.created = created;
      this.updated = updated;
      this.createdUserID = createdUserID;
      this.updatedUserID = updatedUserID;
      this.orderType = orderType;
      this.price = price;
      this.status = status;
   }

   public String getOrderID() {
      return orderID;
   }

   public void setOrderID(String orderID) {
      this.orderID = orderID;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getProductID() {
      return productID;
   }

   public void setProductID(String productID) {
      this.productID = productID;
   }

   public String getCreated() {
      return created;
   }

   public void setCreated(String created) {
      this.created = created;
   }

   public String getUpdated() {
      return updated;
   }

   public void setUpdated(String updated) {
      this.updated = updated;
   }

   public int getCreatedUserID() {
      return createdUserID;
   }

   public void setCreatedUserID(int createdUserID) {
      this.createdUserID = createdUserID;
   }

   public int getUpdatedUserID() {
      return updatedUserID;
   }

   public void setUpdatedUserID(int updatedUserID) {
      this.updatedUserID = updatedUserID;
   }

   public boolean isOrderType() {
      return orderType;
   }

   public void setOrderType(boolean orderType) {
      this.orderType = orderType;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int inputQuantity(Scanner scanner) {
      System.out.print("Nhập số lượng: ");
      do {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input <= 0) {
               System.err.println("Số lượng không hợp lệ!!!");
            } else {
               return input;
            }
         } catch (NumberFormatException e) {
            System.err.println("Số lượng phải là số, mời nhập lại!!!");
         }
      } while (true);
   }

   public String inputProductID(Scanner scanner) {
      System.out.println("Chọn sản phẩm");
      System.out.println(StoreSystem.LINE);
      for (int i = 0; i < StoreSystem.products.size(); i++) {
         System.out.printf("%d. %s", i + 1, StoreSystem.products.get(i).getProductName());
      }
      do {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input <= 0 || input > StoreSystem.products.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               return StoreSystem.products.get(input - 1).getProductID();
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      } while (true);
   }

   public static String inputTimeOfAction() {
      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return date.format(formatter);
   }

   public int inputPrice(Scanner scanner) {
      System.out.print("Nhập giá tiền: ");
      do {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input <= 0) {
               System.err.println("Giá tiền không hợp lệ!!!");
            } else {
               return input;
            }
         } catch (NumberFormatException e) {
            System.err.println("Giá tiền phải là số, mời nhập lại!!!");
         }
      } while (true);
   }

   public String importOrderID() {
      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return "PN-" + date.format(formatter).split("/")[2].substring(2)
              + date.format(formatter).split("/")[1] + "-" + (Integer.parseInt(StoreSystem.ordersImport.get(StoreSystem.ordersImport.size() - 1).getOrderID().split("-")[2]) + 1);
   }

   public String exportOrderID() {
      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return "PX-" + date.format(formatter).split("/")[2].substring(2)
              + date.format(formatter).split("/")[1] + "-" + (Integer.parseInt(StoreSystem.ordersImport.get(StoreSystem.ordersImport.size() - 1).getOrderID().split("-")[2]) + 1);
   }

   public String inputStatus(Scanner scanner) {
      System.out.println("Chọn trạng thái của đơn hàng");
      System.out.println("1. Hoạt động\t\t2. Bị huỷ");
      do {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
               case 1:
                  return "Hoạt động";
               case 2:
                  return "Bị huỷ";
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      } while (true);
   }

   public double getTotalPrice() {
      return price * quantity;
   }

   public void importOrderInformation(Scanner scanner) {
      this.orderID = importOrderID();
      this.productID = inputProductID(scanner);
      this.quantity = inputQuantity(scanner);
      this.created = inputTimeOfAction();
      this.updated = "Chưa có cập nhật!!";
      this.createdUserID = StoreSystem.loginID;
      this.updatedUserID = StoreSystem.loginID;
      this.price = inputPrice(scanner);
      this.status = inputStatus(scanner);
      this.orderType = true;
   }

   public void exportOrderInformation(Scanner scanner) {
      this.orderID = exportOrderID();
      this.productID = inputProductID(scanner);
      this.quantity = inputQuantity(scanner);
      this.created = inputTimeOfAction();
      this.updated = "Chưa có cập nhật!!";
      this.createdUserID = StoreSystem.loginID;
      this.updatedUserID = StoreSystem.loginID;
      this.price = inputPrice(scanner);
      this.status = inputStatus(scanner);
      this.orderType = false;
   }

   public String displayOrder() {
      return "Mã đơn hàng: " + this.orderID + "\t-\tMã sản phẩm: " + this.productID
              + "\nSố lượng: " + this.quantity + "\t-\tĐơn giá: " + this.price
              + "\nLoại đơn: " + (this.orderType ? "Nhập hàng" : "Xuất hàng") + "\t-\tTrạng thái: " + this.status
              + "\nNgười tạo: " + this.createdUserID + "\t-\tNgười cập nhật: " + this.updatedUserID
              + "\nNgày tạo: " + this.created + "\t-\tNgày cập nhật cuối: " + this.updated
              + "\nThành tiền: " + this.getTotalPrice();
   }
}
