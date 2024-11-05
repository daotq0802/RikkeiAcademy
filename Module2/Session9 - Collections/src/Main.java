import java.io.*;
import java.util.*;

public class Main {
   public static List<Category> categories = new ArrayList<>();
   public static List<Product> products = new ArrayList<>();
   private static Map<Integer, Integer> countProduct = new HashMap<>();
   private static final File categoryFile = new File("Category.txt");
   private static final File productFile = new File("Product.txt");
   private static ObjectOutputStream oos = null;
   private static ObjectInputStream ois = null;
   private static final String LINE = "=============================================";
   private static ListIterator iterator = null;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      if (categoryFile.isFile() && productFile.isFile()) {
         getData();
      }
      while (true) {
         System.out.println("********************SHOP MENU*********************\n" +
                 "1. Quản lý danh mục sản phẩm\n" +
                 "2. Quản lý sản phẩm\n" +
                 "3. Thoát");
         try {
            int select = Integer.parseInt(sc.nextLine());
            switch (select) {
               case 1:
                  categoryMenu(sc);
                  break;
               case 2:
                  productMenu(sc);
                  break;
               case 3:
                  break;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void categoryMenu(Scanner sc) {
      while (true) {
         readCategory();
         System.out.println("********************CATEGORIES MENU********************\n" +
                 "1. Danh sách danh mục\n" +
                 "2. Thêm mới danh mục\n" +
                 "3. Cập nhật thông tin danh mục (Cập nhật theo mã)\n" +
                 "4. Xóa danh mục (Chỉ cho phép xóa danh mục chưa có sản phẩm)\n" +
                 "5. Quay lại");
         try {
            int select = Integer.parseInt(sc.nextLine());
            switch (select) {
               case 1:
                  displayListCategory();
                  break;
               case 2:
                  addNewCategory(sc);
                  break;
               case 3:
                  updateCategory(sc);
                  break;
               case 4:
                  deleteCategory(sc);
                  break;
               case 5:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số");
         }
      }
   }

   private static void displayListCategory() {
      if (categoryFile.isFile()) {
         System.out.println("Hiện có " + categories.size() + " danh mục");
         System.out.println(LINE);
         categories.forEach(category ->
                 System.out.println(category.displayData()
                         + "\n" + LINE));
      } else {
         System.err.println("Không tìm thấy dữ liệu Category");
      }
   }

   private static void addNewCategory(Scanner sc) {
      Category category = new Category();
      category.inputData(sc);
      categories.add(category);
      writeData(categories, categoryFile);
   }

   private static void updateCategory(Scanner sc) {
      if (categoryFile.isFile()) {
         System.out.println("Chọn danh mục cần cập nhật");
         int count = 1;
         for (Category category : categories) {
            System.out.printf("%d. %s (%d)\n", count, category.getName(), category.getId());
            count++;
         }
         try {
            int select = Integer.parseInt(sc.nextLine());
            if (select <= 0 || select > categories.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               boolean Finish = false;
               while (!Finish) {
                  System.out.println(LINE);
                  System.out.println("Chọn thông tin cần cập nhật\n" +
                          "1. Cập nhật tên danh mục\n" +
                          "2. Cập nhập mô tả\n" +
                          "3. Cập nhật trạng thái\n" +
                          "4. Quay lại");
                  try {
                     int updateSelect = Integer.parseInt(sc.nextLine());
                     switch (updateSelect) {
                        case 1:
                           updateNameCategory(sc, select - 1);
                           break;
                        case 2:
                           updateDescriptionCategory(sc, select - 1);
                           break;
                        case 3:
                           updateStatusCategory(sc, select - 1);
                           break;
                        case 4:
                           writeData(categories, categoryFile);
                           Finish = true;
                           break;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số!!!");
                  } catch (Exception e) {
                     System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
         }
      } else {
         System.err.println("Không tìm thấy dữ liệu Category!!!");
      }
   }

   private static void updateNameCategory(Scanner sc, int index) {
      System.out.println("Tên danh mục hiện tại: " + categories.get(index).getName());
      System.out.println("Nhập tên mới: ");
      categories.get(index).setName(categories.get(index).inputName(sc));
   }

   private static void updateDescriptionCategory(Scanner sc, int index) {
      System.out.println("Mô tả danh mục hiện tại: " + categories.get(index).getDescription());
      System.out.println("Nhập mô tả mới: ");
      categories.get(index).setDescription(categories.get(index).inputDescription(sc));
   }

   private static void updateStatusCategory(Scanner sc, int index) {
      if (categories.get(index).isStatus()) {
         System.out.println("Trạng thái hiện tại của danh mục 'Hoạt động', thay đổi sang 'Không hoạt đông' ?\n" +
                 "1. Đồng ý\t\t\t\t2. Không");
         while (true) {
            try {
               int yes = Integer.parseInt(sc.nextLine());
               switch (yes) {
                  case 1:
                     categories.get(index).setStatus(false);
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
      } else {
         System.out.println("Trạng thái hiện tại của danh mục 'Không hoạt động', thay đổi sang 'Hoạt đông' ?\n" +
                 "1. Đồng ý\t\t\t\t2. Không");
         while (true) {
            try {
               int yes = Integer.parseInt(sc.nextLine());
               switch (yes) {
                  case 1:
                     categories.get(index).setStatus(true);
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

   private static void deleteCategory(Scanner sc) {
      if (categoryFile.isFile()) {
         System.out.println("Chọn danh mục cần cập nhật");
         int count = 1;
         for (Category category : categories) {
            System.out.printf("%d. %s (%d)\n", count, category.getName(), category.getId());
            count++;
         }
         while (true) {
            try {
               int select = Integer.parseInt(sc.nextLine());
               if (select <= 0 || select > categories.size()) {
                  System.err.println("Lựa chọn không hợp lệ!!!");
               } else {
                  System.out.println("Chắc chắn xoá danh mục - " + categories.get(select - 1).getName() + "?\n" +
                          "1. Đồng ý\t\t\t\t2. Không");
                  while (true) {
                     try {
                        int yes = Integer.parseInt(sc.nextLine());
                        switch (yes) {
                           case 1:
                              categories.remove(select - 1);
                              writeData(categories, categoryFile);
                              return;
                           case 2:
                              return;
                           default:
                              System.err.println("Lựa chọn không hợp lệ!!!");
                        }
                     } catch (NumberFormatException e) {
                        System.err.println("Lựa chọn phải là số!!!");
                     } catch (Exception e) {
                        System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
                     }
                  }
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            } catch (Exception e) {
               System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
            }
         }
      } else {
         System.err.println("Không tìm thấy dữ liệu Category!!!");
      }
   }

   private static void productMenu(Scanner sc) {
      while (true) {
         readProduct();
         System.out.println("********************CATEGORIES MENU********************\n" +
                 "1. Danh sách sản phẩm\n" +
                 "2. Thêm mới sản phẩm (Khi thêm mới cho phép chọn danh mục)\n" +
                 "3. Cập nhật thông tin sản phẩm (Cập nhật theo mã)\n" +
                 "4. Xóa sản phẩm\n" +
                 "5. Sắp xếp sản phẩm theo giá bán tăng dần\n" +
                 "6. Sắp xếp sản phẩm theo giá nhập giảm dần\n" +
                 "7. Tìm kiếm sản phẩm theo tên sản phẩm\n" +
                 "8. Quay lại");
         try {
            int select = Integer.parseInt(sc.nextLine());
            switch (select) {
               case 1:
                  displayListProduct();
                  break;
               case 2:
                  addNewProduct(sc);
                  break;
               case 3:
                  updateProduct(sc);
                  break;
               case 4:
                  deleteProduct(sc);
                  break;
               case 5:
                  sortProductByExportPriceASC();
                  break;
               case 6:
                  sortProductByImportPriceDSC();
                  break;
               case 7:
                  searchProductByName(sc);
                  break;
               case 8:
                  return;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
         }
      }
   }

   private static void displayListProduct() {
      if (productFile.isFile()) {
         System.out.println("Hiện có " + products.size() + " danh mục");
         System.out.println(LINE);
         for (Product p : products) {
            System.out.println(p.displayData());
         }
      } else {
         System.err.println("Hiện không có sản phẩm nào!!!");
      }
   }

   private static void addNewProduct(Scanner sc) {
      if (categories.size() > 0) {
         Product product = new Product();
         product.inputData(sc);
         products.add(product);
         writeData(products, productFile);
      } else {
         System.err.println("Không có danh mục nào, hãy thêm danh mục trước!!!");
      }

   }

   private static void updateProduct(Scanner sc) {
      System.out.print("Nhập mã sản phẩm cần cập nhật: ");
      while (true) {
         String id = sc.nextLine();
         if (id.isEmpty() || id.trim().isEmpty()) {
            System.err.println("Hãy nhập mã sản phẩm để tiến hành cập nhật!!!");
         } else {
            iterator = products.listIterator();
            while (iterator.hasNext()) {
               Product product = (Product) iterator.next();
               if (product.getId().equals(id)) {
                  System.out.println("Chọn thông tin cần cập nhật\n" +
                          "1. Cập nhật tên\n" +
                          "2. Cập nhật danh mục\n" +
                          "3. Cập nhật giá nhập hàng\n" +
                          "4. Cập nhật trạng thái\n" +
                          "5. Quay lại");
                  boolean finish = false;
                  while (!finish) {
                     try {
                        int select = Integer.parseInt(sc.nextLine());
                        switch (select) {
                           case 1:
                              updateNameProduct(sc, product);
                              writeData(products, productFile);

                              return;
                           case 2:
                              updateCategoryIdProduct(sc, product);
                              writeData(products, productFile);

                              return;
                           case 3:
                              updateImportPriceProduct(sc, product);
                              writeData(products, productFile);

                              return;
                           case 4:
                              updateStatusProduct(sc, product);
                              writeData(products, productFile);
                              return;
                           case 5:
                              finish = true;
                              break;
                           default:
                              System.err.println("Lựa chọn không hợp lệ!!!");
                        }
                     } catch (NumberFormatException e) {
                        System.err.println("Lựa chọn phải là số!!!");
                     } catch (Exception e) {
                        System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
                     }
                  }
               } else {
                  System.err.println("Không tìm thấy sản phẩm!!!");
               }
            }
         }
      }
   }

   private static void updateNameProduct(Scanner sc, Product product) {
      System.out.println("Tên sản phẩm hiện tại: " + product.getName());
      System.out.println("Nhập tên mới: ");
      product.setName(product.inputName(sc));
   }

   private static void updateCategoryIdProduct(Scanner sc, Product product) {
      System.out.println("Danh mục sản phẩm hiện tại: " + product.getNameCategory(product.getCatalogId()));
      System.out.println("Chọn danh mục cần thay đổi");
      List<Category> changeCategory = new ArrayList<>();
      for (Category category : categories) {
         if (category.getId() != product.getCatalogId()) {
            changeCategory.add(category);
         }
      }
      for (int i = 0; i < changeCategory.size(); i++) {
         System.out.printf("%d. %s (%d)", i + 1, changeCategory.get(i).getName(), changeCategory.get(i).getId());
      }
      while (true) {
         try {
            int select = Integer.parseInt(sc.nextLine());
            if (select <= 0 || select > changeCategory.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               countProduct.put(product.getCatalogId(), countProduct.get(product.getCatalogId()) - 1);
               product.setCatalogId(changeCategory.get(select - 1).getId());
               countProduct.put(product.getCatalogId(), countProduct.get(product.getCatalogId()) + 1);
               System.out.println("Đã đổi sang danh mục " + changeCategory.get(select - 1).getName());
               return;
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   private static void updateStatusProduct(Scanner sc, Product product) {
      if (product.isStatus()) {
         System.out.println("Trạng thái hiện tại: Hoạt động, đổi sang trạng thái 'Không hoạt động'?" +
                 "1. Đồng ý\t\t\t\t2. Không");
         while (true) {
            try {
               int select = Integer.parseInt(sc.nextLine());
               switch (select) {
                  case 1:
                     product.setStatus(false);
                     return;
                  case 2:
                     return;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            } catch (Exception e) {
               System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
            }
         }
      } else {
         System.out.println("Trạng thái hiện tại: Không hoạt động, đổi sang trạng thái 'Hoạt động'?" +
                 "1. Đồng ý\t\t\t\t2. Không");
         while (true) {
            try {
               int select = Integer.parseInt(sc.nextLine());
               switch (select) {
                  case 1:
                     product.setStatus(true);
                     return;
                  case 2:
                     return;
                  default:
                     System.err.println("Lựa chọn không hợp lệ!!!");
               }
            } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số!!!");
            } catch (Exception e) {
               System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
            }
         }
      }
   }

   private static void updateImportPriceProduct(Scanner sc, Product product) {
      System.out.println("Giá nhập hàng hiện tại: " + product.getImportPrice());
      System.out.print("Giá nhập hàng mới: ");
      product.setImportPrice(product.inputImportPrice(sc));
   }

   private static void deleteProduct(Scanner sc) {
      System.out.println("Chọn sản phẩm cần xoá");
      for (int i = 0; i < products.size(); i++) {
         System.out.printf("%d. %s\n", i + 1, products.get(i).getName());
      }
      while (true) {
         try {
            int select = Integer.parseInt(sc.nextLine());
            if (select <= 0 || select > products.size()) {
               System.err.println("Lựa chọn không hợp lệ!!!");
            } else {
               System.out.println("Chắc chắn muốn xoá sản phẩm - " + products.get(select - 1).getName());
               System.out.println("1. Đồng ý\t\t\t\t2. Không");
               while (true) {
                  try {
                     int yes = Integer.parseInt(sc.nextLine());
                     switch (yes) {
                        case 1:
                           products.remove(select - 1);
                           writeData(products, productFile);
                           return;
                        case 2:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số!!!");
                  } catch (Exception e) {
                     System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
                  }
               }
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi, hãy thử lại!!!");
         }
      }
   }

   private static void sortProductByExportPriceASC() {
      products.sort((a, b) -> {
         return (int) (a.calExportPrice() - b.calExportPrice());
      });
      displayListProduct();
   }

   private static void sortProductByImportPriceDSC() {
      products.stream().sorted(Comparator.comparing(Product::getImportPrice).reversed()).forEach(System.out::println);
   }

   private static void searchProductByName(Scanner scanner) {
      System.out.print("Nhập tên sản phẩm cần tìm: ");
      String productName = scanner.nextLine();
      System.out.println(LINE);
      for (Product product : products) {
         if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
            System.out.println(product.displayData() + "\n" + LINE);
         }
      }
   }

   private static void getData() {
      readCategory();
      readProduct();
      for (Product product : products) {
         if (countProduct.containsKey(product.getCatalogId())) {
            countProduct.put(product.getCatalogId(), countProduct.get(product.getCatalogId()) + 1);
         } else {
            countProduct.put(product.getCatalogId(), 1);
         }
      }
   }

   private static void readCategory() {
      try {
         ois = new ObjectInputStream(new FileInputStream(categoryFile));
         categories = (List<Category>) ois.readObject();
         ois.close();
      } catch (FileNotFoundException e) {
         System.err.println("Không thể đọc File '" + categoryFile.getAbsolutePath() + "'");
      } catch (IOException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      } catch (ClassNotFoundException e) {
         System.err.println("Không tìm thấy đối tượng Category!!!");
      }
   }

   private static void readProduct() {
      try {
         ois = new ObjectInputStream(new FileInputStream(productFile));
         products = (List<Product>) ois.readObject();
         ois.close();
      } catch (FileNotFoundException e) {
         System.err.println("Không thể đọc File '" + productFile.getAbsolutePath() + "'");
      } catch (IOException e) {
         System.err.println("Đã xảy ra lỗi!!!");
      } catch (ClassNotFoundException e) {
         System.err.println("Không tìm thấy đối tượng Product!!!");
      }
   }

   private static void writeData(List<?> data, File file) {
      try {
         oos = new ObjectOutputStream(new FileOutputStream(file));
         oos.writeObject(data);
         oos.close();
      } catch (FileNotFoundException e) {
         System.err.println("Không thể đọc File '" + file.getAbsolutePath() + "'");
      } catch (IOException e) {
         System.err.println("Đã xảy ra lỗi!!!" + Arrays.toString(e.getStackTrace()));
      }
   }
}
