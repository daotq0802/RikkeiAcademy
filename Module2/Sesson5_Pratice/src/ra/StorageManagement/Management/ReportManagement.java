package ra.StorageManagement.Management;

import ra.StorageManagement.Model.Order;
import ra.StorageManagement.StoreSystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReportManagement {
   public static int reportManagementMenu(Scanner sc) {
      System.out.println("======================REPORT MANAGEMENT======================");
      System.out.println("1. Thống kê các sản phẩm nhập từ ngày đến ngày\n" +
              "2. Thống kê các sản phẩm xuất từ ngày đến ngày\n" +
              "3. Thống kê doanh thu từ ngày đến ngày (theo các phiếu xuất)\n" +
              "4. Thống kê chi phí từ ngày đến ngày (theo các phiếu nhập)\n" +
              "5. Thống kê phiếu thu theo các tháng\n" +
              "6. Thống kê chi phí theo các tháng\n" +
              "7. Quay lại");
      do {
         try {
            return Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            return 0;
         }
      } while (true);
   }

   public static void reportManagementSelection(Scanner sc) {
      while (true) {
         try {
            int input = reportManagementMenu(sc);
            switch (input) {
               case 1:
                  reportImportProductsByDate(sc);
                  break;
               case 2:
                  reportExportProductsByDate(sc);
                  break;
               case 3:
                  reportSalesByDate(sc);
                  break;
               case 4:
                  reportCostByDate(sc);
                  break;
               case 5:
                  reportSalesByMonth(sc);
                  break;
               case 6:
                  reportCostByMonth(sc);
                  break;
               case 7:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      }
   }

   public static void reportImportProductsByDate(Scanner sc) {
      System.out.println("Nhập khoảng thời gian muốn tìm (VD: 01/01/2024 - 01/05/2024): ");
      String date = sc.nextLine();
      String startString = date.split(" - ")[0];
      String endString = date.split(" - ")[1];
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      df.setLenient(false);
      try {
         Date dateStart = df.parse(startString);
         Date dateEnd = df.parse(endString);
         int count = 0;
         for (Order order : StoreSystem.ordersImport) {
            if (dateStart.before(df.parse(order.getCreated())) && dateEnd.after(df.parse(order.getCreated()))) {
               System.out.println(order.displayOrder());
               System.out.println(StoreSystem.LINE);
               count++;
            }
         }
         if (count > 0) {
            System.out.println("Đã tìm thấy " + count + " hoá đơn hợp lệ");
         } else {
            System.err.println("Không tìm thấy hoá đơn nào!!!");
         }
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static void reportExportProductsByDate(Scanner sc) {
      System.out.println("Nhập khoảng thời gian muốn tìm (VD: 01/01/2024 - 01/05/2024): ");
      String date = sc.nextLine();
      String startString = date.split(" - ")[0];
      String endString = date.split(" - ")[1];
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      df.setLenient(false);
      try {
         Date dateStart = df.parse(startString);
         Date dateEnd = df.parse(endString);
         int count = 0;
         for (Order order : StoreSystem.ordersExport) {
            if (dateStart.before(df.parse(order.getCreated())) && dateEnd.after(df.parse(order.getCreated()))) {
               System.out.println(order.displayOrder());
               System.out.println(StoreSystem.LINE);
               count++;
            }
         }
         if (count > 0) {
            System.out.println("Đã tìm thấy " + count + " hoá đơn hợp lệ");
         } else {
            System.err.println("Không tìm thấy hoá đơn nào!!!");
         }
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static void reportSalesByDate(Scanner sc) {
      System.out.println("Nhập khoảng thời gian muốn tìm (VD: 01/01/2024 - 01/05/2024): ");
      String date = sc.nextLine();
      String startString = date.split(" - ")[0];
      String endString = date.split(" - ")[1];
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      df.setLenient(false);
      try {
         Date dateStart = df.parse(startString);
         Date dateEnd = df.parse(endString);
         double sum = 0;
         for (Order order : StoreSystem.ordersImport) {
            if (dateStart.before(df.parse(order.getCreated())) && dateEnd.after(df.parse(order.getCreated()))) {
               System.out.println(order.displayOrder());
               System.out.println(StoreSystem.LINE);
               sum += order.getTotalPrice();
            }
         }
         System.out.println("Tổng doanh thu: " + sum);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static void reportCostByDate(Scanner sc) {
      System.out.println("Nhập khoảng thời gian muốn tìm (VD: 01/01/2024 - 01/05/2024): ");
      String date = sc.nextLine();
      String startString = date.split(" - ")[0];
      String endString = date.split(" - ")[1];
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      df.setLenient(false);
      try {
         Date dateStart = df.parse(startString);
         Date dateEnd = df.parse(endString);
         double sum = 0;
         for (Order order : StoreSystem.ordersExport) {
            if (dateStart.before(df.parse(order.getCreated())) && dateEnd.after(df.parse(order.getCreated()))) {
               System.out.println(order.displayOrder());
               System.out.println(StoreSystem.LINE);
               sum += order.getTotalPrice();
            }
         }
         System.out.println("Tổng doanh thu: " + sum);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static void reportSalesByMonth(Scanner sc) {
      System.out.println("Chọn tháng cần thống kê");
      for (int i = 1; i <= 12; i++) {
         System.out.printf("%d. Tháng %d\t\t", i, i);
         if (i==3 ||i == 6 || i == 9) {
            System.out.println();
         }
         if(i == 1 || i == 2 || i ==4 || i ==5 || i==7 || i == 8){
            System.out.print("    ");
         }
      }
      System.out.println();
      while (true) {
         try {
            int month = Integer.parseInt(sc.nextLine());
            double sum = 0;
            for (Order order : StoreSystem.ordersImport) {
               if (Integer.parseInt(order.getCreated().split("/")[1]) == month) {
                  System.out.println(order.displayOrder());
                  System.out.println(StoreSystem.LINE);
                  sum += order.getTotalPrice();
               }
            }
            if (sum > 0) {
               System.out.println("Tổng doanh thu của tháng " + month + ": " + sum);
               return;
            } else {
               System.err.println("Không có danh thu nào trong tháng " + month);
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
         }
      }
   }

   public static void reportCostByMonth(Scanner sc) {
      System.out.println("Chọn tháng cần thống kê");
      for (int i = 1; i <= 12; i++) {
         System.out.printf("%d. Tháng %d\t", i, i);
      }
      System.out.println();
      while (true) {
         try {
            int month = Integer.parseInt(sc.nextLine());
            double sum = 0;
            for (Order order : StoreSystem.ordersExport) {
               if (Integer.parseInt(order.getCreated().split("/")[1]) == month) {
                  System.out.println(order.displayOrder());
                  System.out.println(StoreSystem.LINE);
                  sum += order.getTotalPrice();
               }
            }
            if (sum > 0) {
               System.out.println("Tổng chi của tháng " + month + ": " + sum);
               return;
            } else {
               System.err.println("Không có khoản chi nào trong tháng " + month);
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
         }
      }
   }
}
