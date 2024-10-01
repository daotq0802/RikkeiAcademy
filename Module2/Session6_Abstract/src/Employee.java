import java.util.Scanner;

public class Employee implements IEmployee {
   String id;
   String name;
   int year;
   float rate;
   float commission;
   float salary;
   boolean status;

   public Employee() {
   }

   public Employee(String id, String name, int year, float rate, float commission, float salary, boolean status) {
      this.id = id;
      this.name = name;
      this.year = year;
      this.rate = rate;
      this.commission = commission;
      this.salary = salary;
      this.status = status;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public float getRate() {
      return rate;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }

   public float getCommission() {
      return commission;
   }

   public void setCommission(float commission) {
      this.commission = commission;
   }

   public float getSalary() {
      return salary;
   }

   public void setSalary(float salary) {
      this.salary = salary;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public String inputID(Scanner scanner) {
      System.out.print("Nhập mã nhân viên: ");
      while (true) {
         String id = scanner.nextLine();
         if (id.isEmpty() || id.trim().isEmpty()) {
            System.err.println("Hãy nhập mã nhân viên!!!");
         } else {
            boolean isFound = false;
            for (Employee employee : EmployeeManagement.listEmployee) {
               if (employee.id.equals(id)) {
                  isFound = true;
                  break;
               }
            }
            if (isFound) {
               System.err.println("Mã nhân viên đã tồn tại!!!");
            } else {
               return id;
            }
         }
      }
   }

   public String inputName(Scanner scanner) {
      System.out.print("Nhập tên nhân viên: ");
      while (true) {
         String name = scanner.nextLine();
         if (name.isEmpty() || name.trim().isEmpty()) {
            System.err.println("Hãy nhập tên nhân viên!!!");
         } else {
            if (name.length() < 6 || name.length() > 50) {
               System.err.println("Tên nhân viên có độ dài từ 6 - 50 ký tự!!!");
            } else {
               return name;
            }
         }
      }
   }

   public int inputYear(Scanner scanner) {
      System.out.print("Nhập năm sinh nhân viên: ");
      while (true) {
         try {
            int year = Integer.parseInt(scanner.nextLine());
            if (year <= 0 || year >= 2002) {
               System.err.println("Năm sinh nhân phải lớn hơn 0 và nhỏ hơn 2002!!!");
            } else {
               return year;
            }
         } catch (NumberFormatException e) {
            System.err.println("Năm sinh của nhân viên phải là số!!!");
         }
      }
   }

   public float inputRate(Scanner scanner) {
      System.out.print("Nhập hệ số lương của nhân viên: ");
      while (true) {
         try {
            float rate = Float.parseFloat(scanner.nextLine());
            if (rate <= 0) {
               System.err.println("Hệ số lương không hợp lệ!!!");
            } else {
               return rate;
            }
         } catch (NumberFormatException e) {
            System.err.println("Hệ số lương phải là số!!!");
         }
      }
   }

   public float inputCommission(Scanner scanner) {
      System.out.print("Nhập hoa hồng hàng tháng: ");
      while (true) {
         try {
            float commission = Float.parseFloat(scanner.nextLine());
            if (commission < 0) {
               System.err.println("Hoa hồng không hợp lệ!!!");
            } else {
               return commission;
            }
         } catch (NumberFormatException e) {
            System.err.println("Hoa hồng hàng tháng phải là số!!!");
         }
      }
   }

   public boolean inputStatus(Scanner scanner) {
      System.out.print("Nhập trạng thái nhân viên (true/false): ");
      while (true) {
         String status = scanner.nextLine();
         if (status.equals("true")) {
            return true;
         } else if (status.equals("false")) {
            return false;
         } else {
            System.err.println("Trạng thái nhân viên không hợp lệ!!!");
         }
      }
   }

   public float calSalary() {
      return rate * salary + commission;
   }

   @Override
   public void inputData(Scanner scanner) {
      this.id = inputID(scanner);
      this.name = inputName(scanner);
      this.year = inputYear(scanner);
      this.rate = inputRate(scanner);
      this.commission = inputCommission(scanner);
      this.salary = BASIC_SALARY;
      this.status = inputStatus(scanner);
   }

   @Override
   public String displayData() {
      return "Mã nhân viên: " + this.id + "\t-\tTên nhân viên: " + this.name +
              "\nNăm sinh: " + this.year + "\t-\tHệ số lương: " + this.rate +
              "\nTiền hoa hồng: " + this.commission + "\t-\tLương cơ bản: " + this.salary +
              "\nTrạng thái: " + (this.status ? "Đang làm việc" : "Nghỉ việc") +
              "\nLương hàng tháng: " + this.calSalary();
   }
}
