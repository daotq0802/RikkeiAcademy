package ra.StorageManagement.Model;

import java.time.Year;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee {
   private int employeeID;
   private String employeeName;
   private String birthYear;
   private String phone;
   private String email;
   private String status;

   public Employee() {

   }

   public Employee(int employeeID, String employeeName, String birthYear, String phone, String email, String status) {
      this.employeeID = employeeID;
      this.employeeName = employeeName;
      this.birthYear = birthYear;
      this.phone = phone;
      this.email = email;
      this.status = status;
   }

   public int getEmployeeID() {
      return employeeID;
   }

   public void setEmployeeID(int employeeID) {
      this.employeeID = employeeID;
   }

   public String getEmployeeName() {
      return employeeName;
   }

   public void setEmployeeName(String employeeName) {
      this.employeeName = employeeName;
   }

   public String getBirthYear() {
      return birthYear;
   }

   public void setBirthYear(String birthYear) {
      this.birthYear = birthYear;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String inputName(Scanner scanner) {
      System.out.print("Họ và tên nhân viên: ");
      while (true) {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập tên của nhân viên!!!");
         } else {
            if (input.length() >= 10 && input.length() <= 50) {
               String realName = "";
               for (int i = 0; i < input.split(" ").length; i++) {
                  realName += input.split(" ")[i].substring(0, 1).toUpperCase() + input.split(" ")[i].substring(1) + " ";
               }
               return realName;
            } else {
               System.err.println("Độ dài tên nhân viên phải từ 10 - 50 ký tự, mời nhập lại!!!");
            }
         }
      }
   }

   public String inputBirthYear(Scanner scanner) {
      System.out.print("Năm sinh nhân viên: ");
      while (true) {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input >= Year.now().getValue()) {
               System.err.println("Độ tuổi không hợp lệ, mời nhập lại!!!");
            } else {
               return String.valueOf(input);
            }
         } catch (NumberFormatException e) {
            System.err.println("Năm sinh phải là số, mời nhập lại!!!");
         }
      }
   }

   public String inputPhone(Scanner scanner) {
      System.out.print("Số điện thoại nhân viên: ");
      String regex = "^(\\+84|(09[01346-8])|(08[1-689])|(07[06-9])|(03[2-9]))\\d{7}$";
      do {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập số điện thoại của nhân viên!!!");
         } else {
            if (!Pattern.matches(regex, input)) {
               System.err.println("Số điện thoại không hợp lệ!!!");
            } else {
               return input;
            }
         }
      } while (true);
   }

   public String inputEmail(Scanner scanner) {
      System.out.print("Địa chỉ Email nhân viên: ");
      String regex = "^\\w+(\\.?[\\w]+)@\\w+(?:.+\\w+)$";
      do {
         String input = scanner.nextLine();
         if (input.isEmpty() || input.trim().isEmpty()) {
            System.err.println("Hãy nhập địa chỉ Email của nhân viên!!!");
         } else {
            if (!Pattern.matches(regex, input)) {
               System.err.println("Email không đúng định dạng, mời nhập lại!!!");
            } else {
               return input;
            }
         }
      } while (true);
   }

   public String inputStatus(Scanner scanner) {
      System.out.println("Chọn trạng thái cho nhân viên" +
              "\n1. Đang làm việc\t\t2. Nghỉ việc\t\t3. Nghỉ chế độ");
      do {
         try {
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
               case 1:
                  return "Đang làm việc";
               case 2:
                  return "Nghỉ việc";
               case 3:
                  return "Nghỉ chế độ";
               default:
                  System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      } while (true);
   }

   public void inputData(Scanner scanner) {
      System.out.println("-------------------------------");
      System.out.println("Nhập thông tin của nhân viên");
      this.employeeName = inputName(scanner);
      this.birthYear = inputBirthYear(scanner);
      this.phone = inputPhone(scanner);
      this.email = inputEmail(scanner);
      this.status = inputStatus(scanner);
   }

   public String displayData() {
      return "Mã nhân viên: " + this.employeeID + "\t-\tTên nhân viên: " + this.employeeName +
              "\nNăm sinh: " + this.birthYear + "\t-\tSố điện thoại: " + this.phone + "\t-\tEmail: " + this.email +
              "\nTrạng thái: " + this.status;
   }
}
