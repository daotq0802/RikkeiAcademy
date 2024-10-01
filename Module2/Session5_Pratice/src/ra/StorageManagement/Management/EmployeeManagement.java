package ra.StorageManagement.Management;

import ra.StorageManagement.Model.Employee;
import ra.StorageManagement.StoreSystem;

import java.util.Scanner;

public class EmployeeManagement {

   public static int employeeManagementMenu(Scanner sc) {
      System.out.println("======================EMPLOYEE MANAGEMENT==================");
      System.out.println("1. Nhập thông tin nhân viên" +
              "\n2. Hiển thị thông tin nhân viên" +
              "\n3. Cập nhật thông tin nhân viên" +
              "\n4. Tìm nhân viên theo tên nhân viên" +
              "\n5. Cập nhật trạng thái nhân viên" +
              "\n6. Quay lại");
      do {
         try {
            return Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            return 0;
         }
      } while (true);
   }

   public static void addEmployee(Scanner sc) {
      Employee emp = new Employee();
      emp.setEmployeeID(StoreSystem.employees.get(StoreSystem.employees.size() - 1).getEmployeeID() + 1);
      emp.inputData(sc);
      StoreSystem.employees.add(emp);
   }

   public static void displayEmployee() {
      if (StoreSystem.employees.isEmpty()) {
         System.err.println("Không có nhân viên nào!!!");
      } else {
         System.out.println("Hiện tại có " + StoreSystem.employees.size() + " nhân viên");
         System.out.println(StoreSystem.LINE);
         for (Employee emp : StoreSystem.employees) {
            System.out.println(emp.displayData());
            System.out.println(StoreSystem.LINE);
         }
      }
   }

   public static void updateEmployee(Scanner sc) {
      System.out.print("Nhập mã nhân viên cần cập nhật: ");
      while (true) {
         try {
            int index = getEmployee(Integer.parseInt(sc.nextLine()));
            if (index == -1) {
               System.err.println("Không tìm thấy nhân viên");
            } else {
               System.out.println("Chọn mục cần cập nhật cho nhân viên");
               System.out.println("1. Tên nhân viên\n2. Năm sinh nhân viên\n3. Số điện thoại\n4. Địa chỉ Email\n5. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(sc.nextLine());
                     switch (choice) {
                        case 1:
                           System.out.println("Tên hiện tại: " + StoreSystem.employees.get(index).getEmployeeName());
                           String newName = StoreSystem.employees.get(index).inputName(sc);
                           StoreSystem.employees.get(index).setEmployeeName(newName);
                           System.out.println("Cập nhật thành công!");
                           return;
                        case 2:
                           System.out.println("Năm sinh hiện tại: " + StoreSystem.employees.get(index).getBirthYear());
                           String newBirthYear = StoreSystem.employees.get(index).inputBirthYear(sc);
                           StoreSystem.employees.get(index).setBirthYear(newBirthYear);
                           System.out.println("Cập nhật thành công!");
                           return;
                        case 3:
                           System.out.println("Số điện thoại hiện tại: " + StoreSystem.employees.get(index).getPhone());
                           String newPhone = StoreSystem.employees.get(index).inputPhone(sc);
                           StoreSystem.employees.get(index).setPhone(newPhone);
                           System.out.println("Cập nhật thành công!");
                           return;
                        case 4:
                           System.out.println("Địa chỉ Email hiện tại: " + StoreSystem.employees.get(index).getEmail());
                           String newEmail = StoreSystem.employees.get(index).inputEmail(sc);
                           StoreSystem.employees.get(index).setEmail(newEmail);
                           System.out.println("Cập nhật thành công!");
                           return;
                        case 5:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên không hợp lệ!!!");
         }
      }
   }

   public static void searchEmployee(Scanner sc) {
      System.out.print("Nhập tên nhân viên cần tìm: ");
      while (true) {
         String input = sc.nextLine();
         boolean isFound = false;
         for (Employee emp : StoreSystem.employees) {
            if (emp.getEmployeeName().toLowerCase().equals(input.toLowerCase())) {
               System.out.println(emp.displayData());
               return;
            }
         }
         if (!isFound) {
            System.err.println("Không tìm thấy nhân viên - " + input);
         }
      }
   }

   public static void updateStatus(Scanner sc) {
      System.out.println("Nhập mã nhân viên cần cập nhật trạng thái: ");
      while (true) {
         try {
            int index = getEmployee(Integer.parseInt(sc.nextLine()));
            if (index == -1) {
               System.err.println("Mã nhân viên không tồn tại!!!");
            }
            if (StoreSystem.employees.get(index).getStatus().equals("Đang làm việc")) {
               System.out.println("Nhân viên này hiện tại đang làm việc, chọn trạng thái cần thay đổi");
               System.out.println("1. Nghỉ việc\t\t2. Nghỉ chế độ\t\t3. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(sc.nextLine());
                     switch (choice) {
                        case 1:
                           StoreSystem.employees.get(index).setStatus("Nghỉ việc");
                           return;
                        case 2:
                           StoreSystem.employees.get(index).setStatus("Nghỉ chế độ");
                           return;
                        case 3:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại");
                  }
               }
            } else if (StoreSystem.employees.get(index).getStatus().equals("Nghỉ việc")) {
               System.out.println("Nhân viên này hiện tại đang nghỉ việc, chọn trạng thái cần thay đổi");
               System.out.println("1. Đang làm việc\t\t2. Nghỉ chế độ\t\t3. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(sc.nextLine());
                     switch (choice) {
                        case 1:
                           StoreSystem.employees.get(index).setStatus("Đang làm việc");
                           return;
                        case 2:
                           StoreSystem.employees.get(index).setStatus("Nghỉ chế độ");
                           return;
                        case 3:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại");
                  }
               }
            }else{
               System.out.println("Nhân viên này hiện tại đang nghỉ chế độ, chọn trạng thái cần thay đổi");
               System.out.println("1. Nghỉ việc\t\t2. Đang làm việc\t\t3. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(sc.nextLine());
                     switch (choice) {
                        case 1:
                           StoreSystem.employees.get(index).setStatus("Nghỉ việc");
                           return;
                        case 2:
                           StoreSystem.employees.get(index).setStatus("Đang làm việc");
                           return;
                        case 3:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên không hợp lệ!!!");
         }
      }
   }

   public static int getEmployee(int employeeID) {
      int index = -1;
      for (int i = 0; i < StoreSystem.employees.size(); i++) {
         if (StoreSystem.employees.get(i).getEmployeeID() == employeeID) {
            index = i;
         }
      }
      return index;
   }

   public static void employeeMenuSelect(Scanner sc) {
      while (true) {
         int input = employeeManagementMenu(sc);
         switch (input) {
            case 1:
               addEmployee(sc);
               break;
            case 2:
               displayEmployee();
               break;
            case 3:
               updateEmployee(sc);
               break;
            case 4:
               searchEmployee(sc);
               break;
            case 5:
               updateStatus(sc);
               break;
            case 6:
               break;
            default:
               System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
         }
      }
   }
}
