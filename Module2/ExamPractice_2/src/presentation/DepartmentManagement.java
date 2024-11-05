package presentation;

import business.DepartmentBusiness;
import entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentManagement {
   public static void getAllDepartments() {
      List<Department> list = DepartmentBusiness.getDepartment();
      if (list.isEmpty()) {
         System.err.println("Không có dữ liệu!!");
      }
      list.forEach(System.out::println);
   }

   public static void createDepartment(Scanner scanner) {
      Department department = new Department();
      System.out.print("Nhập tên phòng ban: ");
      department.setName(scanner.nextLine());
      System.out.print("Nhập mô tả phòng ban: ");
      department.setDescription(scanner.nextLine());
      if (DepartmentBusiness.save(department)) {
         System.out.println("Thêm mới thành công!");
      } else {
         System.err.println("Thêm mới thất bại!!!");
      }
   }

   public static void updateDepartment(Scanner scanner) {
      System.out.println("Chọn phòng ban cần cập nhật");
      List<Department> list = DepartmentBusiness.getDepartment();
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
      }
      while (true) {
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               Department selectedDepartment = list.get(choice - 1);
               while (true) {
                  System.out.println("Chọn mục cần cập nhật" +
                          "\n1. Cập nhật tên phòng ban" +
                          "\n2. Cập nhật mô tả phòng ban" +
                          "\n3. Thoát");
                  System.out.print("Lựa chọn của bạn: ");
                  try {
                     int option = Integer.parseInt(scanner.nextLine());
                     switch (option) {
                        case 1:
                           selectedDepartment.setName(scanner.nextLine());
                           break;
                        case 2:
                           selectedDepartment.setDescription(scanner.nextLine());
                           break;
                        case 3:
                           if (DepartmentBusiness.update(selectedDepartment)) {
                              System.out.println("Cập nhật thành công");
                           } else {
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

   public static void deleteDepartment(Scanner scanner) {
      List<Department> list = DepartmentBusiness.getDepartment();
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, list.get(i).getName());
      }
      while (true) {
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > list.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               Department selectedDepartment = list.get(choice - 1);
               System.out.println("Đồng ý xoá phòng ban - " + selectedDepartment.getName() + "?" +
                       "\n1. Đồng ý\t\t2. Huỷ");
               while (true) {
                  System.out.println("Lựa chọn của bạn: ");
                  try {
                     int option = Integer.parseInt(scanner.nextLine());
                     switch (option) {
                        case 1:
                           if(DepartmentBusiness.delete(selectedDepartment.getId())){
                              System.out.println("Xoá thành công!!!");
                           }else{
                              System.err.println("Xoá thất bại!!!");
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

   public static void findDepartmentByName(Scanner scanner) {
      System.out.print("Nhập tên phòng ban cần tìm kiếm: ");
      List<Department> listSearch = DepartmentBusiness.searchDepartmentByName(scanner.nextLine());
      if(listSearch.isEmpty()){
         System.err.println("Không tìm thấy phòng ban nào!!!");
      }else{
         listSearch.forEach(System.out::println);
         System.out.println("Tìm thấy " + listSearch.size() + " phù hợp!");
      }
   }
}
