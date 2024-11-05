package presentation;

import java.util.Scanner;

public class StoreManagement {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      while(true) {
         System.out.println("******************STORE-MANAGEMENT******************\n" +
                 "1. Quản lý danh mục\n" +
                 "2. Quản lý sản phẩm\n" +
                 "3. Thoát\n");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  categoryManagement(sc);
                  break;
               case 2:
                  productManagement(sc);
                  break;
               case 3:
                  System.exit(0);
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!");
         }
      }
   }

   private static void categoryManagement(Scanner sc) {
      while (true) {
         System.out.println("**********************CATEGORY-MENU********************\n" +
                 "1. Danh sách danh mục\n" +
                 "2. Tạo mới danh mục\n" +
                 "3. Cập nhật danh mục\n" +
                 "4. Xóa danh mục\n" +
                 "5. Thống kê số lượng sản phẩm theo danh mục\n" +
                 "6. Thoát\n");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  CategoryManagement.getList();
                  break;
               case 2:
                  CategoryManagement.addCategory(sc);
                  break;
               case 3:
                  CategoryManagement.editCategory(sc);
                  break;
               case 4:
                  CategoryManagement.deleteCategory(sc);
                  break;
               case 5:
                  CategoryManagement.statisticProduct();
                  break;
               case 6:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!");
         }
      }
   }

   private static void productManagement(Scanner sc) {
      while(true){
         System.out.println("************************PRODUCT-MENU********************\n" +
                 "1. Danh sách sản phẩm\n" +
                 "2. Tạo mới sản phẩm\n" +
                 "3. Cập nhật sản phẩm\n" +
                 "4. Xóa sản phẩm\n" +
                 "5. Hiển thị danh sách sản phẩm theo ngày tạo giảm dần\n" +
                 "6. Tìm kiếm sản phẩm theo khoản giá bán.\n" +
                 "7. Hiển thị top 3 sản phẩm có lợi nhuận cao nhất (lợi nhuận = giá bán - giá nhập)\n" +
                 "8. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  ProductManagement.getListProduct();
                  break;
               case 2:
                  ProductManagement.addProduct(sc);
                  break;
               case 3:
                  ProductManagement.updateProduct(sc);
                  break;
               case 4:
                  ProductManagement.deleteProduct(sc);
                  break;
               case 5:
                  ProductManagement.listCreatedAtDESC();
                  break;
               case 6:
                  ProductManagement.searchProductBySellingRange(sc);
                  break;
               case 7:
                  ProductManagement.getTopThree();
                  break;
               case 8:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!");
         }
      }
   }
}
