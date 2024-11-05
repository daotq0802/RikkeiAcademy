import java.io.*;
import java.util.*;

public class Store {
   public static List<Category> categories = new ArrayList<Category>();
   public static List<Product> products = new ArrayList<Product>();
   private static final Map<Integer, Integer> productsInCategory = new HashMap<Integer, Integer>();
   private static final String LINE = "--------------------------------";

   private static final File FILE_CATEGORY = new File("category.txt");
   private static final File FILE_PRODUCT = new File("product.txt");
   private static ObjectOutputStream fileOutput = null;
   private static ObjectInputStream fileInput = null;
   private static ListIterator iterator = null;

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      while (true) {
         getProductsInCategory();
         System.out.println("===== QUẢN LÝ KHO =====\n" +
                 "1. Quản lý danh mục\n" +
                 "2. Quản lý sản phẩm\n" +
                 "3. Thoát");
         try {
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
               case 1:
                  categoryMenu(scanner);
                  break;
               case 2:
                  productMenu(scanner);
                  break;
               case 3:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số");
         }
      }
   }

   // * QUẢN LÝ DANH MỤC
   private static void categoryMenu(Scanner scanner) {
      while (true) {
         System.out.println("===== QUẢN LÝ DANH MỤC =====\n" +
                 "1. Thêm mới danh mục\n" +
                 "2. Cập nhật danh mục\n" +
                 "3. Xóa danh mục\n" +
                 "4. Tìm kiếm danh mục theo tên danh mục\n" +
                 "5. Thống kê số lượng sp đang có trong danh mục\n" +
                 "6. Quay lại");
         try {
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
               case 1:
                  addCategory(scanner);
                  break;
               case 2:
                  updateCategory(scanner);
                  break;
               case 3:
                  deleteCategory(scanner);
                  break;
               case 4:
                  searchCategoryByName(scanner);
                  break;
               case 5:
                  statisticalProductsInEachCategory();
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

   // * THÊM MỚI DANH MỤC
   private static void addCategory(Scanner scanner) {
      Category category = new Category();
      category.inputData(scanner);
      categories.add(category);
      writeCategoriesData();
   }

   // * CẬP NHẬT DANH MỤC
   private static void updateCategory(Scanner scanner) {
      if (FILE_CATEGORY.isFile()) {
         readCategoriesData();
         if (categories.size() == 0) {
            System.err.println("Hiện tại không có danh mục nào!!!");
         } else {
            System.out.println("Chọn danh mục cần update");
            renderCategoryList();
            while (true) {
               try {
                  int select = Integer.parseInt(scanner.nextLine());
                  if (select <= 0 || select > categories.size()) {
                     System.err.println("Lựa chọn không hợp lệ!!!");
                  } else {
                     System.out.println("Chọn mục thông tin cần cập nhật" +
                             "\n1. Cập nhật tên" +
                             "\n2. Cập nhật mô tả" +
                             "\n3. Cập nhật trạng thái" +
                             "\n4. Quay lại");
                     while (true) {
                        try {
                           int choice = Integer.parseInt(scanner.nextLine());
                           switch (choice) {
                              case 1:
                                 System.out.println("Tên hiện tại " + categories.get(select - 1).getName());
                                 System.out.print("Nhập tên mới: ");
                                 categories.get(select - 1).setName(categories.get(select - 1).inputName(scanner));
                                 System.out.println("Cập nhật thành công");
                                 writeCategoriesData();
                                 return;
                              case 2:
                                 System.out.println("Mô tả hiện tại " + categories.get(select - 1).getDescription());
                                 System.out.print("Nhập mô tả mới: ");
                                 categories.get(select - 1).setDescription(categories.get(select - 1).inputDescription(scanner));
                                 System.out.println("Cập nhật thành công");
                                 writeCategoriesData();
                                 return;
                              case 3:
                                 if (categories.get(select - 1).status) {
                                    System.out.println("Danh mục đang ở trạng thái 'Hoạt động', chuyển sang 'Không hoạt động'?" +
                                            "\n1. Đồng ý\t\t\t2. Không");
                                    while (true) {
                                       try {
                                          int yes = Integer.parseInt(scanner.nextLine());
                                          switch (yes) {
                                             case 1:
                                                categories.get(select - 1).setStatus(false);
                                                System.out.println("Cập nhật thành công!");
                                                writeCategoriesData();
                                                return;
                                             case 2:
                                                System.out.println("Đã huỷ cập nhật");
                                                return;
                                             default:
                                                System.err.println("Lựa chọn không hợp lệ!!!");
                                          }
                                       } catch (NumberFormatException e) {
                                          System.err.println("Lựa chọn phải là số");
                                       }
                                    }
                                 } else {
                                    System.out.println("Danh mục đang ở trạng thái 'Không hoạt động', chuyển sang 'Hoạt động'?" +
                                            "\n1. Đồng ý\t\t\t2. Không");
                                    while (true) {
                                       try {
                                          int yes = Integer.parseInt(scanner.nextLine());
                                          switch (yes) {
                                             case 1:
                                                categories.get(select - 1).setStatus(true);
                                                System.out.println("Cập nhật thành công!");
                                                writeCategoriesData();
                                                return;
                                             case 2:
                                                System.out.println("Đã huỷ cập nhật");
                                                return;
                                             default:
                                                System.err.println("Lựa chọn không hợp lệ!!!");
                                          }
                                       } catch (NumberFormatException e) {
                                          System.err.println("Lựa chọn phải là số!!!");
                                       }
                                    }
                                 }
                              case 4:
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
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * XOÁ DANH MỤC
   private static void deleteCategory(Scanner scanner) {
      if (FILE_CATEGORY.isFile()) {
         readCategoriesData();
         System.out.println("Chọn danh mục cần xoá");
         renderCategoryList();
         while (true) {
            try {
               int select = Integer.parseInt(scanner.nextLine());
               if (select <= 0 || select > categories.size()) {
                  System.err.println("Lựa chọn không hợp lệ!!!");
               } else {
                  if (productsInCategory.get(categories.get(select - 1).getId()) == null || productsInCategory.get(categories.get(select - 1).getId()) == 0) {
                     System.out.println("Đồng ý xoá danh mục - " + categories.get(select - 1).getName() + "?");
                     System.out.println("1. Đồng ý\t\t\t2. Không");
                     int yes = Integer.parseInt(scanner.nextLine());
                     switch (yes) {
                        case 1:
                           categories.remove(select - 1);
                           System.out.println("Đã xoá thành công");
                           writeCategoriesData();
                           return;
                        case 2:
                           System.out.println("Đã huỷ!!!");
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } else {
                     System.err.println("Hiện tại danh mục đang có sản phẩm, không thể xoá!!!");
                  }
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            }
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * TÌM KIẾM DANH MỤC THEO TÊN
   private static void searchCategoryByName(Scanner scanner) {
      if (FILE_CATEGORY.isFile()) {
         readCategoriesData();
         iterator = categories.listIterator();
         int count = 0;
         System.out.print("Nhập tên danh mục cần tìm kiếm: ");
         String search = scanner.nextLine();
         while (iterator.hasNext()) {
            Category category = (Category) iterator.next();
            if (category.getName().toLowerCase().contains(search.toLowerCase())) {
               count++;
               System.out.println(category.outputData());
               System.out.println(LINE);
            }
         }
         if (count > 0) {
            System.out.println("Tìm thấy " + count + " kết quả!");
         } else {
            System.err.println("Không tìm thấy danh mục nào!!!");
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * THỐNG KÊ SỐ LƯỢNG SP TRONG TỪNG DANH MỤC
   private static void statisticalProductsInEachCategory() {
      if (FILE_CATEGORY.isFile() && FILE_PRODUCT.isFile()) {
         getProductsInCategory();
         int ascNumber = 0;
         for (Map.Entry<Integer, Integer> entry : productsInCategory.entrySet()) {
            ascNumber++;
            System.out.printf("%d. Danh mục %d (%s sản phẩm)\n", ascNumber, entry.getKey(), entry.getValue());
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * IN RA TOÀN BỘ DANH MỤC
   private static void renderCategoryList() {
      System.out.println(LINE);
      for (int i = 0; i < categories.size(); i++) {
         System.out.printf("%d. %d (%s)\n", i + 1, categories.get(i).id, categories.get(i).name);
      }
      System.out.println(LINE);
   }

   // * QUẢN LÝ SẢN PHẨM
   private static void productMenu(Scanner scanner) {
      while (true) {
         System.out.println("===== QUẢN LÝ SẢN PHẨM =====\n" +
                 "1. Thêm mới sản phẩm\n" +
                 "2. Cập nhật sản phẩm\n" +
                 "3. Xóa sản phẩm\n" +
                 "4. Hiển thị sản phẩm theo tên A-Z\n" +
                 "5. Hiển thị sản phẩm theo lợi nhuận từ cao-thấp\n" +
                 "6. Tìm kiếm sản phẩm\n" +
                 "7. Quay lại");
         try {
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
               case 1:
                  addProduct(scanner);
                  break;
               case 2:
                  updateProduct(scanner);
                  break;
               case 3:
                  deleteProduct(scanner);
                  break;
               case 4:
                  renderProductByName();
                  break;
               case 5:
                  renderProductByProfit();
                  break;
               case 6:
                  searchProduct(scanner);
                  break;
               case 7:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   // * THÊM MỚI SẢN PHẨM
   private static void addProduct(Scanner scanner) {
      Product product = new Product();
      product.inputData(scanner);
      products.add(product);
      writeProductsData();
   }

   // * CẬP NHẬT SẢN PHẨM
   private static void updateProduct(Scanner scanner) {
      if (FILE_PRODUCT.isFile()) {
         readProductsData();
         boolean isFound = false;
         System.out.println("Nhập mã sản phẩm cần cập nhật");
         String id = scanner.nextLine();
         iterator = products.listIterator();
         while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.id.equals(id)) {

               while (true) {
                  System.out.println("Chọn mục cần cập nhật" +
                          "\n1. Cập nhật tên" +
                          "\n2. Cập nhật giá nhập hàng" +
                          "\n3. Cập nhật giá bán" +
                          "\n4. Cập nhật mô tả" +
                          "\n5. Cập nhật trạng thái" +
                          "\n6. Đổi mã danh mục" +
                          "\n7. Quay lại");
                  try {
                     int choice = Integer.parseInt(scanner.nextLine());
                     switch (choice) {
                        case 1:
                           updateProductName(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 2:
                           updateProductImportPrice(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 3:
                           updateProductExportPrice(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 4:
                           updateProductDescription(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 5:
                           updateProductStatus(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 6:
                           updateProductCategoryID(scanner, product);
                           System.out.println("Cập nhật thành công!");
                           break;
                        case 7:
                           System.out.println("Kết thúc cập nhật");
                           writeProductsData();
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
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * CẬP NHẬT TÊN
   private static void updateProductName(Scanner scanner, Product product) {
      System.out.println("Tên hiện tại: " + product.name);
      System.out.print("Nhập tên mới: ");
      product.setName(product.inputName(scanner));
   }

   // * CẬP NHẬT GIÁ NHẬP
   private static void updateProductImportPrice(Scanner scanner, Product product) {
      System.out.println("Giá nhập hàng hiện tại: " + product.importPrice);
      System.out.print("Nhập giá mới: ");
      product.setImportPrice(product.inputImportPrice(scanner));
   }

   // * CẬP NHẬT GIÁ BÁN
   private static void updateProductExportPrice(Scanner scanner, Product product) {
      System.out.println("Giá bán hiện tại: " + product.exportPrice);
      System.out.print("Nhập giá mới: ");
      product.setExportPrice(product.inputExportPrice(scanner));
   }

   // * CẬP NHẬT MÔ TẢ
   private static void updateProductDescription(Scanner scanner, Product product) {
      System.out.println("Mô tả hiện tại: " + product.description);
      System.out.print("Nhập mô tả mới: ");
      product.setDescription(product.inputDescription(scanner));
   }

   // * CẬP NHẬT TRẠNG THÁI
   private static void updateProductStatus(Scanner scanner, Product product) {
      if (product.status) {
         System.out.println("Trạng thái sản phẩm hiện tại đang 'Còn hàng', chuyển sang trạng thái 'Ngừng kinh doanh'?" +
                 "\n1. Đồng ý\t\t\t2. Không");
         while (true) {
            try {
               int choice = Integer.parseInt(scanner.nextLine());
               switch (choice) {
                  case 1:
                     product.setStatus(false);
                     return;
                  case 2:
                     System.out.println("Đã huỷ cập nhật");
                     return;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            }
         }
      } else {
         System.out.println("Trạng thái sản phẩm hiện tại đang 'Ngừng kinh doanh', chuyển sang trạng thái 'Còn hàng'?" +
                 "\n1. Đồng ý\t\t\t2. Không");
         while (true) {
            try {
               int choice = Integer.parseInt(scanner.nextLine());
               switch (choice) {
                  case 1:
                     product.setStatus(true);
                     return;
                  case 2:
                     System.out.println("Đã huỷ cập nhật");
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

   // * CẬP NHẬT MÃ DANH MỤC
   private static void updateProductCategoryID(Scanner scanner, Product product) {
      System.out.println("Danh mục hiện tại - " + product.categoryID + " (" + product.getCategoryName(product.categoryID) + ")");
      List<Integer> categoryID = new ArrayList<>();
      for (Category category : categories) {
         categoryID.add(category.id);
      }
      int index = 0;
      for (int i = 0; i < categoryID.size(); i++) {
         if (categoryID.get(i) == product.categoryID) {
            index = i;
            break;
         }
      }
      categoryID.remove(index);
      for (int i = 0; i < categoryID.size(); i++) {
         System.out.printf("%d. Danh mục %d (%s)\n", i + 1, categoryID.get(i), product.getCategoryName(categoryID.get(i)));
      }
      while (true) {
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice <= 0 || choice >= categoryID.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               productsInCategory.put(product.categoryID, productsInCategory.get(product.categoryID) - 1);
               product.setCategoryID(categoryID.get(choice - 1));
               productsInCategory.put(product.categoryID, productsInCategory.get(product.categoryID) + 1);
               return;
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   // * XOÁ SẢN PHẨM
   private static void deleteProduct(Scanner scanner) {
      if (FILE_PRODUCT.isFile()) {
         readProductsData();
         System.out.print("Nhập mã sản phẩm muốn xoá: ");
         String id = scanner.nextLine();
         iterator = products.listIterator();
         while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.id.equals(id)) {
               System.out.println("Chắc chắn muốn xoá sản phẩm - " + product.name + " không?" +
                       "\n1. Đồng ý\t\t\t2. Không");
               while (true) {
                  try {
                     int choice = Integer.parseInt(scanner.nextLine());
                     switch (choice) {
                        case 1:
                           iterator.remove();
                           writeProductsData();
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
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * HIỂN THỊ SẢN PHẨM THEO TÊN
   private static void renderProductByName() {
      if (FILE_PRODUCT.isFile()) {
         readProductsData();
         Collections.sort(products, new Comparator<Product>() {

            @Override
            public int compare(Product o1, Product o2) {
               return o1.name.compareTo(o2.name);
            }
         });
         System.out.println(LINE);
         for (Product product : products) {
            System.out.println(product.outputData());
            System.out.println(LINE);
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * HIỂN THỊ SẢN PHẨM THEO LỢI NHUẬN
   private static void renderProductByProfit() {
      if (FILE_PRODUCT.isFile()) {
         readProductsData();
         Collections.sort(products, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
               return (int) (o2.calProfit() - o1.calProfit());
            }
         });
         System.out.println(LINE);
         for (Product product : products) {
            System.out.println(product.outputData());
            System.out.println(LINE);
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * TÌM KIẾM SẢN PHẨM
   private static void searchProduct(Scanner scanner) {
      if (FILE_PRODUCT.isFile()) {
         readProductsData();
         iterator = products.listIterator();
         System.out.print("Nhập tên mã sản phẩm cần tìm: ");
         String id = scanner.nextLine();
         int count = 0;
         System.out.println(LINE);
         while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.id.toLowerCase().equals(id.toLowerCase())) {
               count++;
               System.out.println(product.outputData());
               System.out.println(LINE);
            }
         }
         if(count>0){
            System.out.println("Tìm thấy " + count + " sản phẩm!");
         }else{
            System.err.println("Không tìm thấy sản phẩm nào!!!");
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   // * ĐỌC & VIẾT FILE
   private static void readProductsData() {
      try {
         fileInput = new ObjectInputStream(new FileInputStream(FILE_PRODUCT));
         products = (List<Product>) fileInput.readObject();
         fileInput.close();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   private static void writeProductsData() {
      try {
         fileOutput = new ObjectOutputStream(new FileOutputStream(FILE_PRODUCT));
         fileOutput.writeObject(products);
         fileOutput.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private static void readCategoriesData() {
      try {
         fileInput = new ObjectInputStream(new FileInputStream(FILE_CATEGORY));
         categories = (List<Category>) fileInput.readObject();
         fileInput.close();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   private static void writeCategoriesData() {
      try {
         fileOutput = new ObjectOutputStream(new FileOutputStream(FILE_CATEGORY));
         fileOutput.writeObject(categories);
         fileOutput.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private static void getProductsInCategory() {
      readProductsData();
      readCategoriesData();
      for (Category category : categories) {
         productsInCategory.put(category.id, 0);
      }
      for (Product product : products) {
         if (productsInCategory.containsKey(product.categoryID)) {
            productsInCategory.put(product.categoryID, productsInCategory.get(product.categoryID) + 1);
         }
      }
   }
}
