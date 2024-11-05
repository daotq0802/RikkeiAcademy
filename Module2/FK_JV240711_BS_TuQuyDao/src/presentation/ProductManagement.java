package presentation;

import business.CategoryBusiness;
import business.ProductsBusiness;
import entity.Category;
import entity.Products;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductManagement {
   public static void getListProduct() {
      List<Products> list = ProductsBusiness.getListProduct();
      list.forEach(System.out::println);
   }

   public static void addProduct(Scanner sc) {
      while (true) {
         Products product = new Products();
         System.out.println("Nhập tên sản phẩm: ");
         product.setName(sc.nextLine());
         System.out.println("Nhập số lượng sản phẩm: ");
         product.setStock(Integer.parseInt(sc.nextLine()));
         System.out.println("Nhập giá nhập hàng: ");
         product.setCost_price(Double.parseDouble(sc.nextLine()));
         System.out.println("Nhập giá bán: ");
         product.setSelling_price(Double.parseDouble(sc.nextLine()));
         System.out.println("Chọn danh mục sản phẩm");
         List<Category> list = CategoryBusiness.getListCategory();
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               product.setCategory_id(list.get(choice - 1).getId());
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số");
         }
         if (ProductsBusiness.addProduct(product)) {
            System.out.println("Thêm mới sản phẩm thành công!");
         } else {
            System.err.println("Thêm mới thất bại!!!");
         }
         return;
      }
   }

   public static void updateProduct(Scanner sc) {
      System.out.println("Chọn sản phẩm cần cập nhật");
      List<Products> list = ProductsBusiness.getListProduct();

      while (true) {
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!");
            } else {
               Products product = list.get(choice - 1);
               while(true) {
                  System.out.println("Chọn mục cần cập nhật" +
                          "\n1. Cập nhật tên sản phẩm" +
                          "\n2. Cập nhật số lượng sản phẩm" +
                          "\n3. Cập nhật giá nhập hàng" +
                          "\n4. Cập nhật giá bán" +
                          "\n5. Cập nhật ngày tạo" +
                          "\n6. Cập nhật danh mục sản phẩm" +
                          "\n7. Thoát");
                  System.out.print("Lựa chọn của bạn: ");
                  try {
                     int option = Integer.parseInt(sc.nextLine());
                     switch (option) {
                        case 1:
                           System.out.print("Nhập tên sản phẩm mới: ");
                           product.setName(sc.nextLine());
                           break;
                        case 2:
                           System.out.print("Nhập số lượng mới: ");
                           product.setStock(Integer.parseInt(sc.nextLine()));
                           break;
                        case 3:
                           System.out.print("Nhập giá nhập mới: ");
                           product.setCost_price(Double.parseDouble(sc.nextLine()));
                           break;
                        case 4:
                           System.out.print("Nhập giá bán mới: ");
                           product.setSelling_price(Double.parseDouble(sc.nextLine()));
                           break;
                        case 5:
                           System.out.print("Nhập ngày tạo mới: ");
                           product.setCreated_at(LocalDate.parse(sc.nextLine()));
                           break;
                        case 6:
                           System.out.print("Chọn danh mục mới");
                           List<Category> listCate = CategoryBusiness.getListCategory();
                           for (int i = 0; i < listCate.size(); i++) {
                              System.out.printf("%d. %s\n", i + 1, listCate.get(i).getName());
                           }
                           try {
                              int choiceCate = Integer.parseInt(sc.nextLine());
                              if (choiceCate < 1 || choiceCate > listCate.size()) {
                                 System.err.println("Lựa chọn không hợp lệ!!!");
                              } else {
                                 product.setCategory_id(listCate.get(choiceCate - 1).getId());
                              }
                           } catch (NumberFormatException e) {
                              System.err.println("Lựa chọn phải là số");
                           }
                           break;
                        case 7:
                           if(ProductsBusiness.updateProduct(product)) {
                              System.out.println("Cập nhật thành công!");
                           }else{
                              System.err.println("Cập nhật thất bại");
                           }
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số!!!");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void deleteProduct(Scanner sc) {
      System.out.println("Chọn sản phẩm cần xoá");
      List<Products> list = ProductsBusiness.getListProduct();
      while (true) {
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               Products product = list.get(choice - 1);
               System.out.println("Đồng ý xoá sản phẩm - " + product.getName() + "?" +
                       "\n1. Đồng ý\t\t2. Không");
               try {
                  int option = Integer.parseInt(sc.nextLine());
                  switch (option) {
                     case 1:
                        if (ProductsBusiness.deleteProduct(product.getId())) {
                           System.out.println("Xoá sản phẩm thành công!!!");
                        } else {
                           System.err.println("Xoá sản phẩm thất bại!!!");
                        }
                        return;
                     case 2:
                        return;
                  }
               } catch (NumberFormatException e) {
                  System.err.println("Lựa chọn phải là số!!!");
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void listCreatedAtDESC() {
      System.out.println("Danh sách sản phẩm theo ngày tạo giảm dần");
      List<Products> products = ProductsBusiness.listCreatedAtDESC();
      products.forEach(System.out::println);
   }

   public static void searchProductBySellingRange(Scanner sc) {
      System.out.print("Nhập giá bán nhỏ nhất: ");
      double minPrice = Double.parseDouble(sc.nextLine());
      System.out.print("Nhập giá bán lớn nhất: ");
      double maxPrice = Double.parseDouble(sc.nextLine());
      Map<String, Double> list = ProductsBusiness.listBySellingRange(minPrice, maxPrice);
      if (list.isEmpty()) {
         System.err.println("Không có sản phẩm nào");
      } else {
         for (Map.Entry<String, Double> entry : list.entrySet()) {
            System.out.printf("%s: %f\n", entry.getKey(), entry.getValue());
            System.out.println("------------------------------");
         }
         System.out.println("Tìm thấy: " + list.size() + " kết quả!");
      }
   }

   public static void getTopThree() {
      System.out.println("Top 3 sản phẩm có lợi nhuận cao nhất");
      Map<String, Double> list = ProductsBusiness.getTopThree();
      for (Map.Entry<String, Double> entry : list.entrySet()) {
         System.out.printf("%s: %f\n", entry.getKey(), entry.getValue());
         System.out.println("------------------------------");
      }
   }
}
