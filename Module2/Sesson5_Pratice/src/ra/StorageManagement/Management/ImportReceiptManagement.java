package ra.StorageManagement.Management;

import ra.StorageManagement.Model.Order;
import ra.StorageManagement.StoreSystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ImportReceiptManagement {
   public static int receiptManagementMenu(Scanner sc) {
      System.out.println("======================RECEIPT MANAGEMENT===============");
      System.out.println("1. Nhập thông tin phiếu nhập\n" +
              "2. Hiển thị thông tin phiếu nhập\n" +
              "3. Cập nhật thông tin phiếu nhập\n" +
              "4. Tìm phiếu nhập từ ngày đến ngày\n" +
              "5. Tìm phiếu nhập theo người tạo\n" +
              "6. Tìm phiếu nhập theo người cập nhật\n" +
              "7. Quay lại");
      do {
         try {
            return Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            return 0;
         }
      } while (true);
   }

   public static int getReceipt(String receiptID) {
      int index = -1;
      for (int i = 0; i < StoreSystem.ordersImport.size(); i++) {
         if (StoreSystem.ordersImport.get(i).equals(receiptID)) {
            index = i;
         }
      }
      return index;
   }

   public static void addReceipt(Scanner sc) {
      Order[] addArray = new Order[StoreSystem.ordersImport.size() + 1];
      for (int i = 0; i < addArray.length; i++) {
         if (i < StoreSystem.ordersImport.size()) {
            addArray[i] = StoreSystem.ordersImport.get(i);
         } else {
            Order newOrder = new Order();
            newOrder.setOrderID(newOrder.importOrderID());
            newOrder.importOrderInformation(sc);
            addArray[i] = newOrder;
         }
      }
      StoreSystem.ordersImport.add(addArray[addArray.length - 1]);
   }

   public static void displayReceipt() {
      if (StoreSystem.ordersImport.size() > 0) {
         for (Order order : StoreSystem.ordersImport) {
            System.out.println(order.displayOrder());
            System.out.println(StoreSystem.LINE);
         }
      } else {
         System.err.println("Hiện tại không có hoá đơn nào!!!");
      }
   }

   public static void updateReceipt(Scanner sc) {
      System.out.println("Chọn hoá đơn cần cập nhật");
      System.out.println(StoreSystem.LINE);
      for (int i = 0; i < StoreSystem.ordersImport.size(); i++) {
         System.out.printf("%d. Hoá đơn %s\n", i + 1, StoreSystem.ordersImport.get(i).getOrderID());
      }
      while (true) {
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice <= 0) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               System.out.println("Chọn mục cần cập nhật");
               System.out.println("1. Cập nhật số lượng\n2. Cập nhật đơn giá\n3. Cập nhật trạng thái\n4. Quay lại");
               while (true) {
                  try {
                     int selection = Integer.parseInt(sc.nextLine());
                     switch (selection) {
                        case 1:
                           System.out.println("Số lượng hiện tại: " + StoreSystem.ordersImport.get(choice - 1).getQuantity());
                           System.out.print("Nhập số lượng mới: ");
                           try {
                              int quantity = Integer.parseInt(sc.nextLine());
                              if (quantity <= 0) {
                                 System.err.println("Số lượng phải có giá trị lớn hơn 0!!!");
                              } else {
                                 StoreSystem.ordersImport.get(choice - 1).setQuantity(quantity);
                                 System.out.println("Cập nhật thành công!");
                                 StoreSystem.ordersImport.get(choice - 1).setUpdatedUserID(StoreSystem.loginID);
                                 StoreSystem.ordersImport.get(choice - 1).setUpdated(Order.inputTimeOfAction());
                                 return;
                              }
                           } catch (NumberFormatException e) {
                              System.err.println("Số lượng phải là số, mời nhập lại!!!");
                           }
                        case 2:
                           System.out.println("Đơn giá hiện tại: " + StoreSystem.ordersImport.get(choice - 1).getPrice());
                           System.out.println("Nhập đơn giá mới: ");
                           try {
                              int price = Integer.parseInt(sc.nextLine());
                              if (price <= 0) {
                                 System.err.println("Đơn giá phải có giá trị lớn hơn 0!!!");
                              } else {
                                 StoreSystem.ordersImport.get(choice - 1).setPrice(price);
                                 System.out.println("Cập nhật thành công!");
                                 StoreSystem.ordersImport.get(choice - 1).setUpdatedUserID(StoreSystem.loginID);
                                 StoreSystem.ordersImport.get(choice - 1).setUpdated(Order.inputTimeOfAction());
                                 return;
                              }
                           } catch (NumberFormatException e) {
                              System.err.println("Đơn giá phải là số, mời nhập lại!!!");
                           }
                        case 3:
                           if (StoreSystem.ordersImport.get(choice - 1).getStatus().equals("Hoạt động")) {
                              System.out.println("Trạng thái hiện tại là đang 'Hoạt động', đổi sang trạng thái 'Bị huỷ'?");
                              System.out.println("1. Đồng ý\t\t2. Không");
                              while (true) {
                                 try {
                                    int yes = Integer.parseInt(sc.nextLine());
                                    if (yes <= 0 || yes > 2) {
                                       System.err.println("Lựa chọn không hợp lệ");
                                    } else {
                                       switch (yes) {
                                          case 1:
                                             StoreSystem.ordersImport.get(choice - 1).setStatus("Bị huỷ");
                                             System.out.println("Cập nhật thành công!");
                                             StoreSystem.ordersImport.get(choice - 1).setUpdatedUserID(StoreSystem.loginID);
                                             StoreSystem.ordersImport.get(choice - 1).setUpdated(Order.inputTimeOfAction());
                                             return;
                                          case 2:
                                             return;
                                       }
                                    }
                                 } catch (NumberFormatException e) {
                                    System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                                 }
                              }
                           } else {
                              System.out.println("Trạng thái hiện tại là đang 'Bị huỷ', đổi sang trạng thái 'Hoạt động'?");
                              System.out.println("1. Đồng ý\t\t2. Không");
                              while (true) {
                                 try {
                                    int yes = Integer.parseInt(sc.nextLine());
                                    if (yes <= 0 || yes > 2) {
                                       System.err.println("Lựa chọn không hợp lệ");
                                    } else {
                                       switch (yes) {
                                          case 1:
                                             StoreSystem.ordersImport.get(choice - 1).setStatus("Hoạt động");
                                             System.out.println("Cập nhật thành công!");
                                             StoreSystem.ordersImport.get(choice - 1).setUpdatedUserID(StoreSystem.loginID);
                                             StoreSystem.ordersImport.get(choice - 1).setUpdated(Order.inputTimeOfAction());
                                             return;
                                          case 2:
                                             return;
                                       }
                                    }
                                 } catch (NumberFormatException e) {
                                    System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                                 }
                              }
                           }
                        case 4:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
         }
      }
   }

   public static void searchReceiptByCreatedUser(Scanner sc) {
      System.out.print("Nhập mã nhân viên cần tìm: ");
      while (true) {
         try {
            int count = 0;
            int userID = Integer.parseInt(sc.nextLine());
            for (Order order : StoreSystem.ordersImport) {
               if (order.getCreatedUserID() == userID) {
                  System.out.println(order.displayOrder());
                  System.out.println(StoreSystem.LINE);
                  count++;
               }
            }
            if (count > 0) {
               System.out.println("Tìm thấy " + count + " hoá đơn được tạo của nhân viên này!!");
               return;
            } else {
               System.err.println("Không có hoá đơn nào được tạo bởi nhân viên này!!!");
               return;
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên không hợp lệ");
         }
      }
   }

   public static void searchReceiptByUpdatedUser(Scanner sc) {
      System.out.print("Nhập mã nhân viên cần tìm: ");
      while (true) {
         try {
            int count = 0;
            int userID = Integer.parseInt(sc.nextLine());
            for (Order order : StoreSystem.ordersImport) {
               if (order.getUpdatedUserID() == userID) {
                  System.out.println(order.displayOrder());
                  System.out.println(StoreSystem.LINE);
                  count++;
               }
            }
            if (count > 0) {
               System.out.println("Tìm thấy " + count + " hoá đơn được cập nhật của nhân viên này!!");
               return;
            } else {
               System.err.println("Không có hoá đơn nào được cập nhật bởi nhân viên này!!!");
               return;
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên không hợp lệ");
         }
      }
   }

   public static void searchReceiptByDate(Scanner sc) {
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


   public static void receiptImportMenuSelect(Scanner sc) {
      while (true) {
         int input = receiptManagementMenu(sc);
         switch (input) {
            case 1:
               addReceipt(sc);
               break;
            case 2:
               displayReceipt();
               break;
            case 3:
               updateReceipt(sc);
               break;
            case 4:
               searchReceiptByDate(sc);
               break;
            case 5:
               searchReceiptByCreatedUser(sc);
               break;
            case 6:
               searchReceiptByUpdatedUser(sc);
               break;
            case 7:
               return;
            default:
               System.err.println("Lựa chọn không hợp lệ!!!");
         }
      }
   }
}
