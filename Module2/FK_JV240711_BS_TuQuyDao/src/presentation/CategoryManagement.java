package presentation;

import business.CategoryBusiness;
import com.sun.security.jgss.GSSUtil;
import entity.Category;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryManagement {
   public static void getList() {
      List<Category> list = CategoryBusiness.getListCategory();
      list.forEach(System.out::println);
   }

   public static void addCategory(Scanner sc) {
      Category category = new Category();
      System.out.print("Nhập tên danh mục: ");
      category.setName(sc.nextLine());
      if (CategoryBusiness.save(category)) {
         System.out.println("Thêm danh mục thành công!");
      } else {
         System.err.println("Thêm danh mục thất bại!!!");
      }
   }

   public static void editCategory(Scanner sc) {
      System.out.println("Chọn danh mục cần cập nhật");
      List<Category> list = CategoryBusiness.getListCategory();
      while (true) {
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               Category category = list.get(choice - 1);
               while (true) {
                  System.out.println("Chọn thông tin cần cập nhật" +
                          "\n1. Cập nhật tên danh mục" +
                          "\n2. Cập nhật trạng thái" +
                          "\n3. Thoát");
                  System.out.print("Lựa chọn của bạn: ");
                  try {
                     int option = Integer.parseInt(sc.nextLine());
                     switch (option) {
                        case 1:
                           System.out.print("Nhập tên mới cho danh mục: ");
                           category.setName(sc.nextLine());
                           break;
                        case 2:
                           if (category.isStatus()) {
                              while (true) {
                                 System.out.println("Đổi sang trạng thái 'Không hoạt động'?" +
                                         "\n1. Đồng ý\t\t2. Không");
                                 try {
                                    int yesNo = Integer.parseInt(sc.nextLine());
                                    switch (yesNo) {
                                       case 1:
                                          category.setStatus(false);
                                          break;
                                       case 2:
                                          break;
                                       default:
                                          System.err.println("Lựa chọn không hợp lệ!!!");
                                    }
                                 } catch (NumberFormatException e) {
                                    System.err.println("Lựa chọn phải là số!!!");
                                 }
                              }
                           } else {
                              while (true) {
                                 System.out.println("Đổi sang trạng thái 'Hoạt động'?" +
                                         "\n1. Đồng ý\t\t2. Không");
                                 try {
                                    int yesNo = Integer.parseInt(sc.nextLine());
                                    switch (yesNo) {
                                       case 1:
                                          category.setStatus(true);
                                          break;
                                       case 2:
                                          break;
                                       default:
                                          System.err.println("Lựa chọn không hợp lệ!!!");
                                    }
                                 } catch (NumberFormatException e) {
                                    System.err.println("Lựa chọn phải là số!!!");
                                 }
                              }
                           }
                        case 3:
                           if (CategoryBusiness.update(category)) {
                              System.out.println("Cập nhật thành công!");
                           } else {
                              System.err.println("Cập nhật thất bại!!!");
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

   public static void deleteCategory(Scanner sc) {
      System.out.println("Chọn danh mục cần xoá");
      List<Category> list = CategoryBusiness.getListCategory();
      while (true) {
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               Category category = list.get(choice - 1);
               while (true) {
                  System.out.println("Đồng ý xoá danh mục - " + category.getName() + "?" +
                          "\n1. Đồng ý\t\t2. Không");
                  try {
                     int yesNo = Integer.parseInt(sc.nextLine());
                     switch (yesNo) {
                        case 1:
                           if (CategoryBusiness.delete(category.getId())) {
                              System.out.println("Xoá thành công!");
                           } else {
                              System.err.println("Xoá thất bại!");
                           }
                           return;
                        case 2:
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

   public static void statisticProduct() {
      Map<String, Integer> statistic = CategoryBusiness.statisticProduct();
      for (Map.Entry<String, Integer> entry : statistic.entrySet()) {
         System.out.printf("%s: %d Sản phẩm\n", entry.getKey(), entry.getValue());
      }
   }
}
