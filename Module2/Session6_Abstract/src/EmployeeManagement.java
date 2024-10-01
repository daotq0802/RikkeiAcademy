import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
   public static List<Employee> listEmployee = new ArrayList<Employee>();
   public static final String LINE = "-----------------------------------";

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      while (true) {
         int select = showMenu(sc);
         switch (select) {
            case 1:
               addEmployee(sc);
               break;
            case 2:
               displayEmployee();
               break;
            case 3:
               calculateSalaryAllEmployee();
               break;
            case 4:
               searchEmployeeByName(sc);
               break;
            case 5:
               updateEmployee(sc);
               break;
            case 6:
               deleteEmployeeById(sc);
               break;
            case 7:
               sortEmployeeBySalary();
               break;
            case 8:
               return;
            default:
               System.err.println("Lựa chọn không hợp lệ!!!");
         }
      }
   }

   public static int showMenu(Scanner sc) {
      System.out.println("********************MENU*********************\n" +
              "1. Nhập thông tin cho n nhân viên\n" +
              "2. Hiển thị thông tin nhân viên\n" +
              "3. Tính lương cho các nhân viên\n" +
              "4. Tìm kiếm nhân viên theo tên nhân viên\n" +
              "5. Cập nhật thông tin nhân viên\n" +
              "6. Xóa nhân viên theo mã nhân viên\n" +
              "7. Sắp xếp nhân viên theo lương tăng dần\n" +
              "8. Thoát");
      while (true) {
         try {
            return Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            return 0;
         }
      }
   }

   public static void addEmployee(Scanner sc) {
      System.out.print("Nhập số lượng nhân viên cần thêm: ");
      while (true) {
         try {
            int number = Integer.parseInt(sc.nextLine());
            if (number <= 0) {
               System.err.println("Số lượng cần thêm phải lớn hơn 0!!!");
            } else {
               for (int i = 0; i < number; i++) {
                  Employee newEmployee = new Employee();
                  newEmployee.inputData(sc);
                  listEmployee.add(newEmployee);
               }
               return;
            }
         } catch (NumberFormatException e) {
            System.err.println("Số lượng không hợp lệ!!!");
         }
      }
   }

   public static void displayEmployee() {
      if (listEmployee.size() == 0) {
         System.err.println("Hiện tại không có nhân viên nào!!!");
      } else {
         System.out.println("Công ty hiện có " + listEmployee.size() + " nhân viên");
         for (Employee employee : listEmployee) {
            System.out.println(employee.displayData());
            System.out.println(LINE);
         }
      }
   }

   public static void calculateSalaryAllEmployee() {
      if (listEmployee.size() == 0) {
         System.err.println("Hiện tại không có nhân viên nào!!!");
      } else {
         System.out.println("Lương hàng tháng của tất cả nhân viên Công ty");
         for (Employee employee : listEmployee) {
            System.out.println("Tên nhân viên: " + employee.getName() + "\t-\tLương hàng tháng: " + employee.calSalary());
            System.out.println(LINE);
         }
      }
   }

   public static void searchEmployeeByName(Scanner sc) {
      System.out.println("Nhập tên nhân viên cần tìm kiếm: ");
      while (true) {
         String searchName = sc.nextLine();
         if (searchName.isEmpty() || searchName.trim().isEmpty()) {
            System.err.println("Hãy nhập tên nhân viên cần tìm!!!");
         } else {
            int count = 0;
            for (Employee employee : listEmployee) {
               if (employee.getName().contains(searchName)) {
                  count++;
                  System.out.println(employee.displayData());
                  System.out.println(LINE);
               }
            }
            if (count == 0) {
               System.err.println("Không tìm thấy nhân viên nào!!!");
            } else {
               System.out.println("Tìm thấy " + count + " nhân viên phù hợp");
               return;
            }
         }
      }
   }

   public static void updateEmployee(Scanner sc) {
      System.out.println("Chọn nhân viên cần cập nhật");
      showListEmployee();
      while (true) {
         try {
            int employee = Integer.parseInt(sc.nextLine());
            if (employee <= 0) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               System.out.println("Chọn mục thông tin cần cập nhật\n" +
                       "1. Cập nhật Họ và tên\n" +
                       "2. Cập nhật năm sinh\n" +
                       "3. Cập nhật hệ số lương\n" +
                       "4. Cập nhật tiền hoa hồng hàng tháng\n" +
                       "5. Cập nhật trạng thái\n" +
                       "6. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(sc.nextLine());
                     switch (choice) {
                        case 1:
                           System.out.println("Tên nhân viên hiện tại: " + listEmployee.get(employee - 1).getName());
                           listEmployee.get(employee - 1).setName(listEmployee.get(employee - 1).inputName(sc));
                           System.out.println("Cập nhật thành công");
                           return;
                        case 2:
                           System.out.println("Năm sinh hiện tại: " + listEmployee.get(employee - 1).getYear());
                           listEmployee.get(employee - 1).setYear(listEmployee.get(employee - 1).inputYear(sc));
                           System.out.println("Cập nhật thành công");
                           return;
                        case 3:
                           System.out.println("Hệ số lương hiện tại: " + listEmployee.get(employee - 1).getRate());
                           listEmployee.get(employee - 1).setRate(listEmployee.get(employee - 1).inputRate(sc));
                           System.out.println("Cập nhật thành công");
                           return;
                        case 4:
                           System.out.println("Tiền hoa hồng hiện tại: " + listEmployee.get(employee - 1).getCommission());
                           listEmployee.get(employee - 1).setCommission(listEmployee.get(employee - 1).inputCommission(sc));
                           System.out.println("Cập nhật thành công");
                           return;
                        case 5:
                           System.out.println("Trạng thái hiện tại: " + listEmployee.get(employee - 1).isStatus());
                           listEmployee.get(employee - 1).setStatus(listEmployee.get(employee - 1).inputStatus(sc));
                           System.out.println("Cập nhật thành công");
                           return;
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
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void deleteEmployeeById(Scanner sc) {
      System.out.println("Chọn nhân viên cần xoá");
      showListEmployee();
      while (true) {
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice <= 0 || choice > listEmployee.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               System.out.println("Có chắc chắn đồng ý xoá nhân viên - " + listEmployee.get(choice - 1).getName() + " không?\n" +
                       "1. Đồng ý\t\t\t2. Không");
               int isYes = Integer.parseInt(sc.nextLine());
               switch (isYes) {
                  case 1:
                     listEmployee.remove(choice - 1);
                     System.out.println("Đã xoá thành công");
                     return;
                  case 2:
                     return;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void sortEmployeeBySalary() {
      List<Employee> sortSalary = new ArrayList<>(listEmployee);
      System.out.println("Sặp xếp nhân viên theo lương tăng dần");
      System.out.println(LINE);
      sortSalary.sort((a, b) -> {
         return (int) (a.calSalary() - b.calSalary());
      });
      for (Employee employee : sortSalary) {
         System.out.println(employee.displayData());
         System.out.println(LINE);
      }
   }

   public static void showListEmployee() {
      for (int i = 0; i < listEmployee.size(); i++) {
         System.out.printf("%d. Nhân viên %s\n", i + 1, listEmployee.get(i).getId());
      }
   }
}
