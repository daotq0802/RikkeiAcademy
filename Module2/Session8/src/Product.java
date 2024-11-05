import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IProduct, Serializable {
   String id;
   String name;
   double importPrice;
   double exportPrice;
   double profit;
   String description;
   boolean status;
   int categoryID;

   public Product() {
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

   public double getImportPrice() {
      return importPrice;
   }

   public void setImportPrice(double importPrice) {
      this.importPrice = importPrice;
   }

   public double getExportPrice() {
      return exportPrice;
   }

   public void setExportPrice(double exportPrice) {
      this.exportPrice = exportPrice;
   }

   public double getProfit() {
      return profit;
   }

   public void setProfit(double profit) {
      this.profit = profit;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public int getCategoryID() {
      return categoryID;
   }

   public void setCategoryID(int categoryID) {
      this.categoryID = categoryID;
   }

   public Product(String id, String name, double importPrice, double exportPrice, double profit, String description, boolean status, int categoryID) {
      this.id = id;
      this.name = name;
      this.importPrice = importPrice;
      this.exportPrice = exportPrice;
      this.profit = profit;
      this.description = description;
      this.status = status;
      this.categoryID = categoryID;
   }

   public String inputID(Scanner scanner) {
      while (true) {
         String id = scanner.nextLine();
         if (Pattern.matches("^P\\w{3}", id)) {
            boolean isFound = false;
            for (Product product : Store.products) {
               if (product.id.equals(id)) {
                  isFound = true;
                  break;
               }
            }
            if (!isFound) {
               return id;
            } else {
               System.err.println("Mã sản phẩm đã tồn tại!!!");
            }
         } else {
            System.err.println("Mã sản phẩm định dạng không hợp lệ!!!");
         }
      }
   }

   public String inputName(Scanner scanner) {
      while (true) {
         String name = scanner.nextLine();
         if (name.length() < 6 || name.length() > 30) {
            System.err.println("Tên sản phẩm phải có từ 6 - 30 ký tự!!!");
         } else {
            boolean isFound = false;
            for (Product product : Store.products) {
               if (product.name.equals(name)) {
                  isFound = true;
                  break;
               }
            }
            if (!isFound) {
               return name;
            } else {
               System.err.println("Tên sản phẩm đã tồn tại!!!");
            }
         }
      }
   }

   public double inputImportPrice(Scanner scanner) {
      while (true) {
         try {
            double importPrice = Double.parseDouble(scanner.nextLine());
            if (importPrice <= 0) {
               System.err.println("Giá nhập sản phẩm không hợp lệ!!!");
            }
            return importPrice;
         } catch (NumberFormatException e) {
            System.err.println("Giá nhập sản phẩm phải là số!!!");
         }
      }
   }

   public double inputExportPrice(Scanner scanner) {
      while (true) {
         try {
            double exportPrice = Double.parseDouble(scanner.nextLine());
            if (exportPrice <= 0 || exportPrice < this.importPrice * 1.2) {
               System.err.println("Giá bán sản phẩm phải nhiều hơn giá nhập " + MIN_INTEREST_RATE + " lần!!!");
            } else {
               return exportPrice;
            }
         } catch (NumberFormatException e) {
            System.err.println("Giá bán sản phẩm phải là số!!!");
         }
      }
   }

   public String inputDescription(Scanner scanner) {
      while (true) {
         String description = scanner.nextLine();
         if (description.isEmpty() || description.trim().isEmpty()) {
            System.err.println("Hãy nhập mô tả cho sản phầm!!!");
         }
         return description;
      }
   }

   public boolean inputStatus(Scanner scanner) {
      while (true) {
         String status = scanner.nextLine();
         if (status.toLowerCase().equals("true")) {
            return true;
         } else if (status.toLowerCase().equals("false")) {
            return false;
         } else {
            System.err.println("Trạng thái sản phẩm chỉ được là true/false!!!" +
                    "\ntrue -> Còn hàng" +
                    "\nfalse -> Ngừng kinh doanh");
         }
      }
   }

   public int inputCategoryID(Scanner scanner) {
      while (true) {
         for (int i = 0; i < Store.categories.size(); i++) {
            System.out.printf("%d. %d (%s)\n", i + 1, Store.categories.get(i).getId(), Store.categories.get(i).getName());
         }
         try {
            int categoryID = Integer.parseInt(scanner.nextLine());
            if (categoryID <= 0) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               return Store.categories.get(categoryID - 1).id;
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }


   @Override
   public void inputData(Scanner scanner) {
      System.out.println("Nhập mã sản phẩm: ");
      this.id = inputID(scanner);
      System.out.println("Nhập tên sản phẩm: ");
      this.name = inputName(scanner);
      System.out.println("Giá nhập hàng: ");
      this.importPrice = inputImportPrice(scanner);
      System.out.println("Giá bán: ");
      this.exportPrice = inputExportPrice(scanner);
      System.out.println("Mô tả sản phẩm: ");
      this.description = inputDescription(scanner);
      System.out.println("Chọn danh mục sản phẩm");
      this.categoryID = inputCategoryID(scanner);
   }

   public String getCategoryName(int id) {
      for (Category category : Store.categories) {
         if (category.id == id) {
            return category.name;
         }
      }
      return "";
   }

   @Override
   public String outputData() {
      String display = "Mã sản phẩm: %s\t-\tTên sản phẩm: %s\nGiá nhập: %f\t-\tGiá bán: %f\nMô tả: %s\nTrạng thái: %s\nLợi nhuận: %f/sản phẩm\nDanh mục: %s"
              .formatted(this.id, this.name, this.importPrice, this.exportPrice, this.description, this.status ? "Còn hàng" : "Ngừng kinh doanh", this.calProfit(), getCategoryName(this.categoryID));
      return display;
   }

   @Override
   public double calProfit() {
      return exportPrice - importPrice;
   }
}
