package ra.StorageManagement.Management;

import ra.StorageManagement.Model.Product;
import ra.StorageManagement.StoreSystem;

import java.util.Scanner;

public class ProductManagement {
   public static int productManagementMenu(Scanner sc) {
      System.out.println("======================PRODUCT MANAGEMENT====================");
      System.out.println("1. Nhập thông tin các sản phẩm" + "\n2. Hiển thị thông tin các sản phẩm" + "\n3. Cập nhật thông tin sản phẩm" + "\n4. Tìm kiếm sản phẩm theo tên" + "\n5. Cập nhật trạng thái sản phẩm" + "\n6. Quay lại");
      do {
         try {
            return Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            return 0;
         }
      } while (true);
   }

   public static void addProduct(Scanner sc) {
      System.out.print("Nhập số lượng sản phẩm muốn thêm mới: ");
      while (true) {
         try {
            int input = Integer.parseInt(sc.nextLine());
            if (input <= 0) {
               System.err.println("Số lượng không hợp lệ!!!");
            }
            for (int i = 0; i < input; i++) {
               Product product = new Product();
               product.inputData(sc);
               StoreSystem.products.add(product);
            }
            break;
         } catch (NumberFormatException e) {
            System.err.println("Số lượng phải là số, mời nhập lại!!!");
         }
      }

   }

   public static void displayProduct() {
      if (StoreSystem.products.isEmpty()) {
         System.err.println("Không có sản phẩm nào");
      } else {
         System.out.println("Hiện tại có " + StoreSystem.products.size() + " sản phẩm");
         System.out.println(StoreSystem.LINE);
         for (Product product : StoreSystem.products) {
            System.out.println(product.displayData());
            System.out.println(StoreSystem.LINE);
         }
      }
   }

   public static void updateProduct(Scanner sc) {
      System.out.print("Nhập mã sản phẩm cần cập nhật: ");
      while (true) {
         int index = getProduct(sc.nextLine());
         if (index == -1) {
            System.err.println("Không tìm thấy sản phẩm, hãy nhập lại!!!");
         } else {
            System.out.println("Chọn mục cần cập nhật");
            System.out.println("1. Cập nhật tên sản phẩm\n2. Cập nhật nhà sản xuất\n3. Quay lại");
            while (true) {
               try {
                  int input = Integer.parseInt(sc.nextLine());
                  switch (input) {
                     case 1:
                        System.out.println("Tên hiện tại: " + StoreSystem.products.get(index).getProductName());
                        System.out.print("Tên sản phẩm mới: ");
                        String newName = sc.nextLine();
                        StoreSystem.products.get(index).setProductName(newName);
                        System.out.println("Cập nhật thành công!!");
                        return;
                     case 2:
                        System.out.println("Nhà sản xuất hiện tại: " + StoreSystem.products.get(index).getManufacturer());
                        System.out.print("Nhà sản xuất mới: ");
                        String newManufacturer = sc.nextLine();
                        StoreSystem.products.get(index).setManufacturer(newManufacturer);
                        System.out.println("Cập nhật thành công!!");
                        return;
                     case 3:
                        return;
                     default:
                        System.err.println("Lựa chọn không hợp lệ!!!");

                  }
               } catch (NumberFormatException e) {
                  System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
               }
            }
         }
      }
   }

   public static void searchProduct(Scanner sc) {
      System.out.print("Nhập tên sản phẩm cần tìm: ");
      while (true) {
         int input = getProduct(sc.nextLine());
         if (input == -1) {
            System.err.println("Không tìm thấy sản phẩm nào!!!");
         } else {
            System.out.println(StoreSystem.products.get(input).displayData());
            System.out.println(StoreSystem.LINE);
            break;
         }
      }
   }

   public static void updateStatus(Scanner sc) {
      System.out.print("Nhập mã sản phẩm: ");
      while (true) {
         int index = getProduct(sc.nextLine());
         if (index == -1) {
            System.err.println("Không tìm thấy sản phầm");
         } else {
            if (StoreSystem.products.get(index).getProductStatus().equals("Hoạt động")) {
               System.out.println("Sản phẩm đang hoạt động, đồng ý chuyển sang 'Không hoạt động'?" + "\n1. Có\t\t\t2. Không");
               while (true) {
                  try {
                     int input = Integer.parseInt(sc.nextLine());
                     switch (input) {
                        case 1:
                           StoreSystem.products.get(index).setProductStatus("Không hoạt động");
                           return;
                        case 2:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            } else {
               System.out.println("Sản phẩm đang không hoạt động, đồng ý chuyển sang 'Hoạt động'?" + "\n1. Có\t\t\t2. Không");
               while (true) {
                  try {
                     int input = Integer.parseInt(sc.nextLine());
                     switch (input) {
                        case 1:
                           StoreSystem.products.get(index).setProductStatus("Hoạt động");
                           return;
                        case 2:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            }
         }
      }
   }

   public static int getProduct(String productID) {
      int index = -1;
      for (int i = 0; i < StoreSystem.products.size(); i++) {
         if (StoreSystem.products.get(i).getProductID().equals(productID)) {
            index = i;
         }
      }
      return index;
   }

   public static void productMenuSelect(Scanner sc) {
      boolean isFinished = false;
      while (!isFinished) {
         int select = productManagementMenu(sc);
         switch (select) {
            case 1:
               addProduct(sc);
               break;
            case 2:
               displayProduct();
               break;
            case 3:
               updateProduct(sc);
               break;
            case 4:
               searchProduct(sc);
               break;
            case 5:
               updateStatus(sc);
               break;
            case 6:
               isFinished = true;
               break;
            default:
               System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
         }
      }
   }
}
