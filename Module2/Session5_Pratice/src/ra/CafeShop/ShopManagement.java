package ra.CafeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShopManagement {
   private static Product[] products = new Product[0];
   private static Category[] categories = new Category[0];
   private static final String LINE = "-----------------------------------";

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      while (true) {
         int selected = showMainMenu(sc);
         if (selected <= 0 || selected > 3) {
            System.err.println("Lựa chọn phải là số từ 1 - 3, mời chọn lại!!!");
         } else {
            if (selected == 1) {
               boolean isFinished = false;
               while (!isFinished) {
                  int selectedCategory = showCategoriesMenu(sc);
                  if (selectedCategory <= 0 || selectedCategory > 6) {
                     System.err.println("Lựa chọn phải là số từ 1 - 6, mời chọn lại!!!");
                  }
                  switch (selectedCategory) {
                     case 1:
                        addCategories(sc);
                        break;
                     case 2:
                        showCategories();
                        break;
                     case 3:
                        updateCategories(sc);
                        break;
                     case 4:
                        deleteCategories(sc);
                        break;
                     case 5:
                        updateStatusCategories(sc);
                        break;
                     case 6:
                        isFinished = true;
                  }
               }
            } else if (selected == 2) {
               boolean isFinished = false;
               while (!isFinished) {
                  int selectedProduct = showProductsMenu(sc);
                  if (selectedProduct <= 0 || selectedProduct > 8) {
                     System.err.println("Lựa chọn phải là số từ 1 - 8, mời chọn lại!!!");
                  }
                  switch (selectedProduct) {
                     case 1:
                        addProducts(sc);
                        break;
                     case 2:
                        showProducts(products);
                        break;
                     case 3:
                        sortProductsByPrice();
                        break;
                     case 4:
                        updateProductsByID(sc);
                        break;
                     case 5:
                        deleteProductsByID(sc);
                        break;
                     case 6:
                        searchProductsByName(sc);
                        break;
                     case 7:
                        searchProductsFromPriceToPrice(sc);
                        break;
                     case 8:
                        isFinished = true;
                  }
               }
            } else {
               System.out.println("Tạm biệt...!!!");
               System.exit(0);
               break;
            }
         }
      }
   }

   public static int showMainMenu(Scanner scanner) {
      List<String> menuMain = new ArrayList<String>();
      menuMain.add("1. Quản lý danh mục sản phẩm");
      menuMain.add("2. Quản lý sản phẩm");
      menuMain.add("3. Thoát");
      System.out.println("================MENU================");
      for (String item : menuMain) {
         System.out.println(item);
      }
      try {
         return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
         return -1;
      }
   }

   //* CÁC PHƯƠNG THỨC CỦA CATEGORIES
   public static int showCategoriesMenu(Scanner scanner) {
      List<String> menuCategory = new ArrayList<String>();
      menuCategory.add("1. Nhập thông tin các danh mục");
      menuCategory.add("2. Hiển thị thông tin các danh mục");
      menuCategory.add("3. Cập nhật thông tin danh mục");
      menuCategory.add("4. Xoá danh mục");
      menuCategory.add("5. Cập nhật trạng thái danh mục");
      menuCategory.add("6. Thoát");
      System.out.println("================CATEGORIES MENU================");
      for (String item : menuCategory) {
         System.out.println(item);
      }
      try {
         return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
         return -1;
      }
   }

   public static void addCategories(Scanner sc) {
      System.out.println("Mời nhập số danh mục muốn thêm: ");
      while (true) {
         try {
            int number = Integer.parseInt(sc.nextLine());
            Category[] cloneCategory = new Category[categories.length + number];
            for (int i = 0; i < cloneCategory.length; i++) {
               if (i < categories.length) {
                  cloneCategory[i] = categories[i];
                  cloneCategory[i].setCategoryID(i + 1);
               } else {
                  Category category = new Category();
                  category.inputData(sc, categories);
                  category.setCategoryID(i + 1);
                  cloneCategory[i] = category;
                  System.out.println("-----------------------------------");
               }
            }
            categories = cloneCategory;
            break;
         } catch (NumberFormatException | NegativeArraySizeException e) {
            System.err.println("Số lượng cần thêm phải là số lớn hơn 0");
         }
      }
   }

   public static void showCategories() {
      for (Category category : categories) {
         System.out.println(category.displayCategory());
         System.out.println(LINE);
      }
   }

   public static void updateCategories(Scanner sc) {
      while (true) {
         System.out.println("Chọn danh mục cần cập nhật ở dưới");
         for (Category category : categories) {
            System.out.println("Danh mục " + category.getCategoryID());
         }
         try {
            int selected = Integer.parseInt(sc.nextLine());
            int index = indexCategory(selected);
            if (index != -1) {
               System.out.println("Chọn thông tin cần cập nhật");
               System.out.println("1. Cập nhật tên danh mục");
               System.out.println("2. Cập nhật mô tả danh mục");
               System.out.println("3. Cập nhật trạng thái danh mục");
               System.out.println("4. Quay lại");
               while (true) {
                  try {
                     int updateSelected = Integer.parseInt(sc.nextLine());
                     switch (updateSelected) {
                        case 1:
                           System.out.println("Tên danh mục cũ: " + categories[index].getCategoryName());
                           System.out.println("Nhập tên mới cho danh mục");
                           String newName = sc.nextLine();
                           boolean isFount = false;
                           for (Category category : categories) {
                              if (category.getCategoryName().equals(newName)) {
                                 isFount = true;
                              }
                           }
                           if (!isFount) {
                              categories[index].setCategoryName(newName);
                              System.out.println("Đã đổi tên danh mục sang '" + newName + "'");
                              return;
                           } else {
                              System.err.println("Tên danh mục đã tồn tại!!!");
                           }
                        case 2:
                           System.out.println("Mô tả danh mục cũ: " + categories[index].getDescription());
                           System.out.println("Nhập mô tả mới cho danh mục");
                           String newDescription = categories[index].inputCategoryDescription(sc);
                           categories[index].setDescription(newDescription);
                           System.out.println("Đã cập nhật mô tả thành công");
                           return;
                        case 3:
                           System.out.print("Trạng thái hiện tại: " + (categories[index].isStatus() ? "Hoạt động\n" : "Không hoạt động\n"));
                           if (categories[index].isStatus()) {
                              System.out.println("Danh mục đang 'Hoạt động', đồng ý đổi sang 'Không hoạt động'?");
                              System.out.println("1. Có\t\t\t2. Không");
                              try {
                                 int yes = Integer.parseInt(sc.nextLine());
                                 if (yes <= 0 || yes > 2) {
                                    System.err.println("Ngoài sự lựa chọn, mời chọn lại!!!");
                                 }
                                 if (yes == 1) {
                                    categories[index].setStatus(false);
                                    System.out.println("Đã cập nhật trạng thái thành công");
                                    return;
                                 } else {
                                    return;
                                 }
                              } catch (NumberFormatException e) {
                                 System.err.println("Lựa chọn phải là số, mời nhập lại");
                              }
                           } else {
                              System.out.println("Danh mục đang 'Không hoạt động', đồng ý đổi sang 'Hoạt động'?");
                              System.out.println("1. Có\t\t2. Không");
                              try {
                                 int yes = Integer.parseInt(sc.nextLine());
                                 if (yes <= 0 || yes > 2) {
                                    System.err.println("Ngoài sự lựa chọn, mời chọn lại!!!");
                                 }
                                 if (yes == 1) {
                                    categories[index].setStatus(true);
                                    System.out.println("Đã cập nhật trạng thái thành công");
                                    return;
                                 } else {
                                    return;
                                 }
                              } catch (NumberFormatException e) {
                                 System.err.println("Lựa chọn phải là số, mời nhập lại");
                              }
                           }
                           return;
                        case 4:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            } else {
               System.err.println("Mã danh mục không tồn tại, mời chọn lại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã danh mục là số, mời chọn lại!!!");
         }
      }
   }

   public static void deleteCategories(Scanner sc) {
      while (true) {
         System.out.println("Chọn danh mục muốn xoá bên dưới");
         for (Category category : categories) {
            System.out.println("Danh mục " + category.getCategoryID());
         }
         try {
            int selected = Integer.parseInt(sc.nextLine());
            int index = indexCategory(selected);
            if (index != -1) {
               if (categories[index].getProductCount() > 0) {
                  System.err.println("Danh mục này hiện đang có sản phẩm, không thể xoá!!!");
                  return;
               }
               System.out.println("Bạn chắc chắn muốn xoá danh mục này???");
               System.out.println("1. Đồng ý \t\t\t 2. Không");
               while (true) {
                  try {
                     int deleteSelected = Integer.parseInt(sc.nextLine());
                     if (deleteSelected <= 0 || deleteSelected > 2) {
                        System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                     }
                     if (deleteSelected == 1) {
                        Category[] deleteCategories = categories;
                        Category[] afterDeleteCategories = new Category[deleteCategories.length - 1];

                        deleteCategories[index] = null;
                        for (int i = selected - 1; i < deleteCategories.length - 1; i++) {
                           Category temp = deleteCategories[i + 1];
                           deleteCategories[i] = temp;
                        }

                        for (int i = 0; i < afterDeleteCategories.length; i++) {
                           afterDeleteCategories[i] = deleteCategories[i];
                        }
                        categories = afterDeleteCategories;
                        return;
                     } else if (deleteSelected == 2) {
                        return;
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời chọn lại!!!");
                  }
               }
            } else {
               System.err.println("Mã danh mục không tồn tại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn theo mã danh mục, mời nhập số!!!");
         }
      }
   }

   public static void updateStatusCategories(Scanner sc) {
      while (true) {
         System.out.println("Chọn mã danh mục cần cập nhật trạng thái");
         for (Category category : categories) {
            System.out.println("Danh mục " + category.getCategoryID());
         }
         try {
            int selected = Integer.parseInt(sc.nextLine());
            if (selected <= 0) {
               System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
            }
            int index = indexCategory(selected);
            if (index != -1) {
               System.out.print("Trạng thái hiện tại: " + (categories[index].isStatus() ? "Hoạt động\n" : "Không hoạt động\n"));
               if (categories[index].isStatus()) {
                  System.out.println("Danh mục đang 'Hoạt động', đồng ý đổi sang 'Không hoạt động'?");
                  System.out.println("1. Có\t\t\t2. Không");
                  try {
                     int yes = Integer.parseInt(sc.nextLine());
                     if (yes <= 0 || yes > 2) {
                        System.err.println("Ngoài sự lựa chọn, mời chọn lại!!!");
                     }
                     if (yes == 1) {
                        categories[index].setStatus(false);
                        System.out.println("Đã cập nhật trạng thái thành công");
                        return;
                     } else {
                        return;
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời nhập lại");
                  }
               } else {
                  System.out.println("Danh mục đang 'Không hoạt động', đồng ý đổi sang 'Hoạt động'?");
                  System.out.println("1. Có\t\t\t2. Không");
                  try {
                     int yes = Integer.parseInt(sc.nextLine());
                     if (yes <= 0 || yes > 2) {
                        System.err.println("Ngoài sự lựa chọn, mời chọn lại!!!");
                     }
                     if (yes == 1) {
                        categories[index].setStatus(true);
                        System.out.println("Đã cập nhật trạng thái thành công");
                        return;
                     } else {
                        return;
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số, mời nhập lại");
                  }
               }
            } else {
               System.err.println("Mã danh mục không tồn tại");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      }
   }

   public static int indexCategory(int categoryID) {
      int index = -1;
      for (int i = 0; i < categories.length; i++) {
         if (categories[i].getCategoryID() == categoryID) {
            index = i;
         }
      }
      return index;
   }

   //* CÁC PHƯƠNG THỨC CỦA PRODUCTS
   public static int showProductsMenu(Scanner scanner) {
      List<String> menuProduct = new ArrayList<String>();
      menuProduct.add("1. Nhập thông tin các sản phẩm");
      menuProduct.add("2. Hiển thị thông tin các sản phẩm");
      menuProduct.add("3. Sắp xếp các sản phẩm theo giá");
      menuProduct.add("4. Cập nhật thông tin theo mã sản phẩm");
      menuProduct.add("5. Xoá sản phẩm theo mã sản phẩm");
      menuProduct.add("6. Tìm kiếm sản phẩm theo tên sản phẩm");
      menuProduct.add("7. Tìm kiếm sản phẩm trong khoảng giá a - b");
      menuProduct.add("8. Thoát");
      System.out.println("================PRODUCTS MANAGEMENT================");
      for (String item : menuProduct) {
         System.out.println(item);
      }
      try {
         return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
         return -1;
      }
   }

   public static void addProducts(Scanner sc) {
      while (true) {
         System.out.print("Nhập số lượng sản phầm cần thêm: ");
         try {
            int number = Integer.parseInt(sc.nextLine());
            if (number <= 0) {
               System.err.println("Số lượng phải lớn hơn 0, mời nhập lại!!!");
            }
            Product[] newProducts = new Product[number + products.length];
            for (int i = 0; i < newProducts.length; i++) {
               if (i < products.length) {
                  newProducts[i] = products[i];
               } else {
                  Product product = new Product();
                  product.inputData(sc, categories, products);
                  newProducts[i] = product;
               }
            }
            products = newProducts;
            break;
         } catch (NumberFormatException e) {
            System.err.println("Số lượng cần thêm phải là số, mời nhập lại!!!");
         }
      }
   }

   public static void showProducts(Product[] products) {
      for (Product product : products) {
         System.out.println(product.displayData());
         System.out.println(LINE);
      }
   }

   public static void sortProductsByPrice() {
      Product[] sortArray = products.clone();
      for (int i = 0; i < sortArray.length; i++) {
         for (int j = i + 1; j < sortArray.length; j++) {
            if (sortArray[i].getPrice() > sortArray[j].getPrice()) {
               Product temp = sortArray[i];
               sortArray[i] = sortArray[j];
               sortArray[j] = temp;
            }
         }
      }
      showProducts(sortArray);
   }

   public static void updateProductsByID(Scanner sc) {
      while (true) {
         System.out.print("Nhập mã sản phẩm cần cập nhật: ");
         int index = indexProduct(sc.nextLine());
         if (index != -1) {
            while (true) {
               System.out.println("Chọn mục thông tin cần cập nhật");
               System.out.println("1. Tên sản phẩm");
               System.out.println("2. Giá sản phẩm");
               System.out.println("3. Mô tả của sản phẩm");
               System.out.println("4. Trạng thái sản phẩm");
               System.out.println("5. Mã danh mục");
               System.out.println("6. Thoát");
               try {
                  int selection = Integer.parseInt(sc.nextLine());
                  if (selection <= 0 || selection > 6) {
                     System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                  }
                  switch (selection) {
                     case 1:
                        System.out.println("Tên hiện tại: " + products[index].getProductName());
                        String newName = products[index].inputProductName(sc, products);
                        products[index].setProductName(newName);
                        System.out.println("Đã cập nhật thành công!");
                        return;
                     case 2:
                        System.out.println("Giá bán hiện tại: " + products[index].getPrice());
                        int newPrice = products[index].inputProductPrice(sc);
                        products[index].setPrice(newPrice);
                        System.out.println("Đã cập nhật thành công!");
                        return;
                     case 3:
                        System.out.println("Mô tả hiện tại: " + products[index].getDescription());
                        String newDescription = products[index].inputProductDescription(sc);
                        products[index].setDescription(newDescription);
                        System.out.println("Đã cập nhật thành công!");
                        return;
                     case 4:
                        System.out.println("Trạng thái hiện tại: " + products[index].getStatus());
                        String currentStatus = products[index].getStatus();
                        if (currentStatus.equalsIgnoreCase("đang bán")) {
                           System.out.println("Chọn trạng thái muốn thay đổi");
                           System.out.println("1. Hết hàng\t\t\t2. Không bán\t\t\t3. Thoát");
                           while (true) {
                              try {
                                 int change = Integer.parseInt(sc.nextLine());
                                 if (change <= 0 || change > 3) {
                                    System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                                 }
                                 switch (change) {
                                    case 1:
                                       products[index].setStatus("Hết hàng");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 2:
                                       products[index].setStatus("Không bán");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 3:
                                       return;
                                 }
                              } catch (NumberFormatException e) {
                                 System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
                              }
                           }
                        }
                        if (currentStatus.equalsIgnoreCase("hết hàng")) {
                           System.out.println("Chọn trạng thái muốn thay đổi");
                           System.out.println("1. Đang bán\t\t\t2. Không bán\t\t\t3. Thoát");
                           while (true) {
                              try {
                                 int change = Integer.parseInt(sc.nextLine());
                                 if (change <= 0 || change > 3) {
                                    System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                                 }
                                 switch (change) {
                                    case 1:
                                       products[index].setStatus("Đang bán");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 2:
                                       products[index].setStatus("Không bán");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 3:
                                       return;
                                 }
                              } catch (NumberFormatException e) {
                                 System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
                              }
                           }
                        }
                        if (currentStatus.equalsIgnoreCase("không bán")) {
                           System.out.println("Chọn trạng thái muốn thay đổi");
                           System.out.println("1. Đang bán\t\t\t2. Hết hàng\t\t\t3. Thoát");
                           while (true) {
                              try {
                                 int change = Integer.parseInt(sc.nextLine());
                                 if (change <= 0 || change > 3) {
                                    System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                                 }
                                 switch (change) {
                                    case 1:
                                       products[index].setStatus("Đang bán");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 2:
                                       products[index].setStatus("Hết hàng");
                                       System.out.println("Cập nhật thành công");
                                       return;
                                    case 3:
                                       return;
                                 }
                              } catch (NumberFormatException e) {
                                 System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
                              }
                           }
                        }
                     case 5:
                        System.out.println("Mã danh mục hiện tại: " + products[index].getCategoryID());
                        while (true) {
                           try {
                              List<Integer> arrayID = new ArrayList<>();
                              for (Category category : categories) {
                                 arrayID.add(category.getCategoryID());
                              }
                              arrayID.remove(products[index].getCategoryID() - 1);
                              for (int i = 0; i < arrayID.size(); i++) {
                                 System.out.printf("%d. Danh mục %d\n", i + 1, arrayID.get(i));
                              }
                              int categorySelect = Integer.parseInt(sc.nextLine());
                              if (categorySelect <= 0 || categorySelect > categories.length - 1) {
                                 System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
                              }
                              int minusIndex = indexCategory(products[index].getCategoryID());
                              categories[minusIndex].setProductCount(categories[minusIndex].getProductCount() - 1);
                              products[index].setCategoryID(arrayID.get(categorySelect - 1));
                              int plusIndex = indexCategory(products[index].getCategoryID());
                              categories[plusIndex].setProductCount(categories[plusIndex].getProductCount() + 1);
                              break;
                           } catch (NumberFormatException ex) {
                              System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
                           }
                        }
                     case 6:
                        return;
                  }
               } catch (NumberFormatException e) {
                  System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
               }
            }
         } else {
            System.err.println("Không tìm thấy sản phẩm, mời nhập lại!!!");
         }
      }
   }

   public static void deleteProductsByID(Scanner sc) {
      while (true) {
         System.out.println("Chọn sản phẩm cần xoá");
         for (int i = 0; i < products.length; i++) {
            System.out.printf("%d. %s\n", i + 1, products[i].getProductID());
         }
         try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice <= 0 || choice > products.length) {
               System.err.println("Lựa chọn không hợp lệ, mời chọn lại!!!");
            }
            Product[] deleteProducts = products;
            Product[] afterDelete = new Product[products.length - 1];
            int indexCategory = indexCategory(deleteProducts[choice - 1].getCategoryID());
            deleteProducts[choice - 1] = null;
            for (int i = choice - 1; i < products.length - 1; i++) {
               Product temp = deleteProducts[i + 1];
               deleteProducts[i] = temp;
            }
            for (int i = 0; i < afterDelete.length; i++) {
               afterDelete[i] = deleteProducts[i];
            }
            products = afterDelete;
            categories[indexCategory].setProductCount(categories[indexCategory].getProductCount() - 1);
            break;
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số, mời nhập lại!!!");
         }
      }
   }

   public static void searchProductsByName(Scanner sc) {
      System.out.println("Nhập tên sản phẩm muốn tìm");
      while (true) {
         String searchID = sc.nextLine();
         int count = 0;
         for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(searchID.toLowerCase())) {
               System.out.println(product.displayData());
               System.out.println(LINE);
               count++;
            }
         }
         if (count == 0) {
            System.err.println("Không tìm thấy sản phẩm nào!!!");
         } else {
            System.out.println("Đã tìm thấy " + count + " sản phẩm");
            break;
         }
      }
   }

   public static void searchProductsFromPriceToPrice(Scanner sc) {
      System.out.println("Nhập khoảng giá muốn tìm (XXXXXX - XXXXXX");
      while (true) {
         String searchPrice = sc.nextLine();
         if (Pattern.matches("^[0-9]{3,10} - [0-9]{3,10}$", searchPrice)) {
            int start = Integer.parseInt(searchPrice.split(" - ")[0]);
            int end = Integer.parseInt(searchPrice.split(" - ")[1]);
            if (start >= end) {
               System.err.println("Giá trị đầu phải nhỏ hơn giá trị sau, mời nhập lại!!!");
            }
            for (Product product : products) {
               if (start <= product.getPrice() && product.getPrice() <= end) {
                  System.out.println(product.displayData());
                  System.out.println(LINE);
               }
            }
            break;
         } else {
            System.err.println("Giá trị tìm kiếm không hợp lệ, mời nhập lại!!!");
         }
      }
   }

   public static int indexProduct(String productID) {
      int index = -1;
      for (int i = 0; i < products.length; i++) {
         if (products[i].getProductID().toLowerCase().equals(productID.toLowerCase())) {
            index = i;
         }
      }
      return index;
   }
}
