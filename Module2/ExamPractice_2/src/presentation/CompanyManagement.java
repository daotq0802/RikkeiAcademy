package presentation;

import java.util.Scanner;

public class CompanyManagement {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      while (true) {
         System.out.println("*********************COMPANY MANAGEMENT*****************\n" +
                 "1. Quản lý phòng ban\n" +
                 "2. Quản lý nhân viên\n" +
                 "3. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  departmentManagement(sc);
                  break;
               case 2:
                  employeeManagement(sc);
                  break;
               case 3:
                  System.exit(0);
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void departmentManagement(Scanner sc) {
      while (true) {
         System.out.println("*********************DEPARTMENT MANAGEMENT**************\n" +
                 "1. Danh sách phòng ban\n" +
                 "2. Thêm mới phòng ban\n" +
                 "3. Cập nhật phòng ban\n" +
                 "4. Xóa phòng ban (cập nhật trạng thái phòng ban là false)\n" +
                 "5. Tìm kiếm phòng ban theo tên\n" +
                 "6. Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  DepartmentManagement.getAllDepartments();
                  break;
               case 2:
                  DepartmentManagement.createDepartment(sc);
                  break;
               case 3:
                  DepartmentManagement.updateDepartment(sc);
                  break;
               case 4:
                  DepartmentManagement.deleteDepartment(sc);
                  break;
               case 5:
                  DepartmentManagement.findDepartmentByName(sc);
                  break;
               case 6:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void employeeManagement(Scanner sc) {
      while (true) {
         System.out.println("*********************EMPLOYEE MANAGEMENT*****************\n" +
                 "1. Danh sách nhân viên\n" +
                 "2. Danh sách nhân viên theo giới tính (nam/nữ)\n" +
                 "3. Danh sách nhân viên theo phòng ban (hiển thị theo tên phòng ban)\n" +
                 "4. Thêm mới nhân viên\n" +
                 "5. Cập nhật nhân viên\n" +
                 "6. Xóa nhân viên\n" +
                 "7. Thống kê nhân viên theo khoảng tuổi\n" +
                 "8. Tìm kiếm nhân viên theo tên hoặc email hoặc số điện thoại\n" +
                 "9. Sắp xếp nhân viên theo tuổi tăng dần\n" +
                 "10.Thoát");
         System.out.print("Lựa chọn của bạn: ");
         try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
               case 1:
                  break;
               case 2:
                  break;
               case 3:
                  break;
               case 4:
                  break;
               case 5:
                  break;
               case 6:
                  break;
               case 7:
                  break;
               case 8:
                  break;
               case 9:
                  break;
               case 10:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }
}
