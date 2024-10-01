package ra.StorageManagement;

import ra.StorageManagement.Management.*;
import ra.StorageManagement.Model.Employee;
import ra.StorageManagement.Model.Order;
import ra.StorageManagement.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreSystem {
   public static List<Product> products = new ArrayList<Product>();
   public static List<Employee> employees = new ArrayList<>();
   public static List<Order> ordersImport = new ArrayList<>();
   public static List<Order> ordersExport = new ArrayList<>();
   public static int loginID = -1;
   public final static String LINE = "--------------------------------------";
   private static final int ADMIN = 0;

   public static void main(String[] args) {
      employees.add(new Employee(1, "Từ Quý Đạo", "1997", "07063613383", "tq.dao0802@gmail.com", "Đang làm việc"));
      products.add(new Product("A001", "Trà sữa", "Gongcha", "Hoạt động"));
      ordersImport.add(new Order("PN-2409-1", 20, "A001", "27/08/2024", "Chưa có cập nhật!!", 1, 1, true, 10000, "Hoạt động"));
      ordersExport.add(new Order("PX-2409-1", 10, "A001", "27/08/2024", "Chưa có cập nhật!!", 1, 1, false, 10000, "Hoạt động"));

      Scanner sc = new Scanner(System.in);
      do {
         System.out.print("Đăng nhập bằng mã nhân viên: ");
         loginID = checkEmployeeID(sc);
         int selection;
         // * Menu admin
         if (loginID == ADMIN) {
            do {
               selection = adminMenu(sc);
               int choice;
               switch (selection) {
                  case 1:
                     ProductManagement.productMenuSelect(sc);
                     break;
                  case 2:
                     EmployeeManagement.employeeMenuSelect(sc);
                     break;
                  case 3:
                     ImportReceiptManagement.receiptImportMenuSelect(sc);
                     break;
                  case 4:
                     ExportReceiptManagement.receiptExportMenuSelect(sc);
                     break;
                  case 5:
                     ReportManagement.reportManagementSelection(sc);
                     break;
                  case 6:
                     loginID = -1;
                     break;
                  default:
                     System.err.println("Lựa chọn phải là số từ 1 - 6, mời chọn lại!!!");
               }
            } while (loginID >= 0);
         } else
         //* Menu nhân viên
         {
            while (loginID >= 0) {
               selection = employeeMenu(sc);
               switch (selection) {
                  case 1:
                     ProductManagement.productMenuSelect(sc);
                     break;
                  case 2:
                     ImportReceiptManagement.receiptImportMenuSelect(sc);
                     break;
                  case 3:
                     ExportReceiptManagement.receiptExportMenuSelect(sc);
                     break;
                  case 4:
                     loginID = -1;
                     break;
                  default:
                     System.err.println("Lựa chọn phải là số từ 1 - 4, mời chọn lại!!!");
               }
            }
         }
      } while (true);
   }

   // * Các danh mục MENU
   public static int checkEmployeeID(Scanner sc) {
      do {
         try {
            int input = Integer.parseInt(sc.nextLine());
            if (input < 0) {
               System.err.println("Mã nhân viên không hợp lệ!!!");
            } else {
               if (input == ADMIN) {
                  return 0;
               } else {
                  boolean isFound = false;
                  for (Employee e : employees) {
                     if (e.getEmployeeID() == input) {
                        isFound = true;
                     }
                  }
                  if (!isFound) {
                     System.err.println("Không có nhân viên này!!!");
                  } else {
                     return input;
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên phải là số!!!");
         }
      } while (true);
   }

   public static int adminMenu(Scanner sc) {
      System.out.println("======================STORE MANAGEMENT======================");
      System.out.println("1. Quản lý sản phẩm\n2. Quản lý nhân viên\n3. Quản lý phiếu nhập" +
              "\n4. Quản lý phiếu xuất\n5. Báo cáo thống kê\n6. Quay lại");
      try {
         return Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
         return 0;
      }
   }

   public static int employeeMenu(Scanner sc) {
      System.out.println("======================STORE MANAGEMENT=====================");
      System.out.println("1. Quản lý sản phẩm\n2. Quản lý phiếu nhập\n3. Quản lý phiếu xuất\n4. Quay lại");
      try {
         return Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
         return 0;
      }
   }




}
