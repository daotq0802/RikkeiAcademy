import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Library {
   public static List<Category> categories = new ArrayList<>();
   public static List<Book> books = new ArrayList<>();
   public static Map<Integer, Integer> counts = new HashMap<>();

   private static final File FILE_CATEGORY = new File("category.txt");
   private static final File FILE_BOOK = new File("book.txt");
   private static ObjectOutputStream output = null;
   private static ObjectInputStream input = null;
   private static ListIterator iterator = null;
   private static final String LINE = "-----------------------------------";

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      while (true) {
         getData();
         System.out.println("===== QUẢN LÝ THƯ VIỆN =====\n" +
                 "1. Quản lý Thể loại\n" +
                 "2. Quản lý Sách\n" +
                 "3. Thoát");
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
               case 1:
                  categoryMenu(scanner);
                  break;
               case 2:
                  bookMenu(scanner);
                  break;
               case 3:
                  System.exit(0);
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   // * QUẢN LÝ THỂ LOẠI
   public static void categoryMenu(Scanner scanner) {
      boolean isExit = false;
      while (!isExit) {
         getData();
         System.out.println("===== QUẢN LÝ THỂ LOẠI =====\n" +
                 "1. Thêm mới thể loại\n" +
                 "2. Hiển thị danh sách theo tên A – Z\n" +
                 "3. Thống kê thể loại và số sách có trong mỗi thể loại\n" +
                 "4. Cập nhật thể loại\n" +
                 "5. Xóa thể loại\n" +
                 "6. Quay lại");
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
               case 1:
                  addCategory(scanner);
                  break;
               case 2:
                  sortCategoryByName();
                  break;
               case 3:
                  countBooksInEachCategory();
                  break;
               case 4:
                  updateCategory(scanner);
                  break;
               case 5:
                  deleteCategory(scanner);
                  break;
               case 6:
                  isExit = true;
                  break;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void addCategory(Scanner scanner) {
      Category category = new Category();
      category.input(scanner);
      categories.add(category);
      writeCategoryFile();
   }

   public static void sortCategoryByName() {
      if (FILE_CATEGORY.isFile()) {
         readCategoryFile();

         Collections.sort(categories, new Comparator<Category>() {
            public int compare(Category o1, Category o2) {
               return o1.getName().compareTo(o2.getName());
            }
         });
         System.out.println(LINE);
         for (Category category : categories) {
            System.out.println(category.output());
            System.out.println(LINE);
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   public static void countBooksInEachCategory() {
      if (FILE_CATEGORY.isFile() || FILE_BOOK.isFile()) {
         System.out.println(categories.size());
         for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            System.out.println("Thể loại " + entry.getKey() + " - " + entry.getValue() + " Sách");
         }
      }
   }

   public static void updateCategory(Scanner scanner) {
      if (FILE_CATEGORY.isFile()) {
         readCategoryFile();
         boolean found = false;
         System.out.print("Nhập mã thể loại cần cập nhật: ");
         while (!found) {
            try {
               int id = Integer.parseInt(scanner.nextLine());
               iterator = categories.listIterator();
               System.out.println(LINE);
               while (iterator.hasNext()) {
                  Category category = (Category) iterator.next();
                  if (category.getId() == id) {
                     System.out.println("Tên cũ: " + category.getName());
                     System.out.print("Tên mới: ");
                     String name = category.inputName(scanner);
                     System.out.print("Trạng thái mới: ");
                     boolean status = category.inputStatus(scanner);
                     iterator.set(new Category(id, name, status));
                     found = true;
                  }
               }
               if (found) {
                  writeCategoryFile();
               } else {
                  System.err.println("Thể loại không tồn tại!!!");
               }
            } catch (NumberFormatException e) {
               System.err.println("Mã thể loại phải là số!!!");
            }
         }
      }
   }

   public static void deleteCategory(Scanner scanner) {
      if (FILE_CATEGORY.isFile()) {
         readCategoryFile();
         boolean found = false;
         System.out.print("Nhập mã thể loại cần xoá: ");
         try {
            int input = Integer.parseInt(scanner.nextLine());
            iterator = categories.listIterator();
            while (iterator.hasNext()) {
               Category category = (Category) iterator.next();
               if (category.getId() == input) {
                  iterator.remove();
                  found = true;
               }
            }
            if (found) {
               writeCategoryFile();
            } else {
               System.err.println("Thể loại không tồn tại!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Mã thể loại phải là số!!!");
         }
      }
   }

   //* QUẢN LÝ SÁCH
   public static void bookMenu(Scanner scanner) {
      boolean isExit = false;
      while (!isExit) {
         getData();
         System.out.println("===== QUẢN LÝ SÁCH =====\n" +
                 "1. Thêm mới sách\n" +
                 "2. Cập nhật thông tin sách\n" +
                 "3. Xóa sách\n" +
                 "4. Tìm kiếm sách\n" +
                 "5. Hiển thị danh sách sách theo nhóm thể loại\n" +
                 "6. Quay lại");
         try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
               case 1:
                  addBook(scanner);
                  break;
               case 2:
                  updateBook(scanner);
                  break;
               case 3:
                  deleteBook(scanner);
                  break;
               case 4:
                  searchBook(scanner);
                  break;
               case 5:
                  displayBookByCategory();
                  break;
               case 6:
                  isExit = true;
                  break;
               default:
                  System.err.println("Lựa chọn không hợp lệ!!!");
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         }
      }
   }

   public static void addBook(Scanner scanner) {
      readCategoryFile();
      Book book = new Book();
      book.input(scanner);
      books.add(book);
      writeBookFile();
   }

   public static void updateBook(Scanner scanner) {
      if (FILE_BOOK.isFile()) {
         readBookFile();
         boolean found = false;
         System.out.print("Nhập mã sách cần xoá: ");
         String id = scanner.nextLine();
         iterator = books.listIterator();
         while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            if (book.getId().equals(id)) {
               System.out.println("Chọn thông tin cần cập nhật" +
                       "\n1. Tên tiêu đề" +
                       "\n2. Tên tác giả" +
                       "\n3. Tên nhà sản xuất" +
                       "\n4. Năm sản xuất" +
                       "\n5. Thể loại sách" +
                       "\n6. Quay lại");
               while (true) {
                  try {
                     int choice = Integer.parseInt(scanner.nextLine());
                     switch (choice) {
                        case 1:
                           System.out.println("Nhập tên mới: ");
                           book.setTitle(book.inputTitle(scanner));
                           writeBookFile();
                           return;
                        case 2:
                           System.out.println("Nhập tên tác giả mới: ");
                           book.setAuthor(book.inputAuthor(scanner));
                           writeBookFile();
                           return;
                        case 3:
                           System.out.println("Nhập tên nhà sản xuất mới: ");
                           book.setPublisher(book.inputPublisher(scanner));
                           writeBookFile();
                           return;
                        case 4:
                           System.out.println("Năm sản xuất mới: ");
                           book.setYear(book.inputYear(scanner));
                           writeBookFile();
                           return;
                        case 5:
                           System.out.println("Chọn thể loại mới: ");
                           book.setCategoryID(book.inputCategoryID(scanner));
                           writeBookFile();
                           return;
                        case 6:
                           return;
                        default:
                           System.err.println("Lựa chọn không hợp lệ!!!");
                     }
                  } catch (NumberFormatException e) {
                     System.err.println("Lựa chọn phải là số");
                  }
               }
            }
         }
      } else {
         System.err.println("File không tồn tại!!!");
      }
   }

   public static void deleteBook(Scanner scanner) {
      if (FILE_BOOK.isFile()) {
         readBookFile();
         boolean found = false;
         System.out.print("Nhập mã sách cần xoá: ");
         String id = scanner.nextLine();
         iterator = books.listIterator();
         while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            if (book.getId().equals(id)) {
               found = true;
               iterator.remove();
            }
         }
         if (found) {
            writeBookFile();
         } else {
            System.err.println("Sách không tồn tại!!!");
         }
      }
   }

   public static void searchBook(Scanner scanner) {
      if (FILE_BOOK.isFile()) {
         readBookFile();
         int count = 0;
         System.out.print("Nhập tên sách cần tìm: ");
         String id = scanner.nextLine();
         iterator = books.listIterator();
         while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            if (book.getId().contains(id)) {
               count++;
               System.out.println(book.output());
               System.out.println(LINE);
            }
         }
         if (count > 0) {
            System.out.println("Tìm thấy " + count + " sách");
         } else {
            System.err.println("Không tìm thấy sách nào!!!");
         }
      }
   }

   public static void displayBookByCategory() {
      if (FILE_BOOK.isFile() || FILE_CATEGORY.isFile()) {
         readCategoryFile();
         readBookFile();
         for (int key : counts.keySet()) {
            System.out.println("Thể loại " + key + " (" + getNameCategory(key) + ")");
            int count = 0;
            for (int i = 0; i < books.size(); i++) {
               if (books.get(i).getCategoryID() == key) {
                  count++;
                  System.out.printf("\t%d. %s\n", count, books.get(i).getTitle());
               }
            }
            System.out.println();
         }
      }
   }

   public static String getNameCategory(int id) {
      for (Category category : categories) {
         if (category.getId() == id) {
            return category.getName();
         }
      }
      return "";
   }

   public static void getData() {
      readCategoryFile();
      readBookFile();
      for (Category category : categories) {
         counts.put(category.getId(), 0);
      }
      for (Book book : books) {
         if (counts.containsKey(book.getCategoryID())) {
            counts.put(book.getCategoryID(), counts.get(book.getCategoryID()) + 1);
         }
      }
   }

   public static void readCategoryFile() {
      try {
         input = new ObjectInputStream(new FileInputStream(FILE_CATEGORY));
         categories = (List<Category>) input.readObject();
         input.close();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public static void writeCategoryFile() {
      try {
         output = new ObjectOutputStream(new FileOutputStream(FILE_CATEGORY));
         output.writeObject(categories);
         output.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static void readBookFile() {
      try {
         input = new ObjectInputStream(new FileInputStream(FILE_BOOK));
         books = (List<Book>) input.readObject();
         input.close();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public static void writeBookFile() {
      try {
         output = new ObjectOutputStream(new FileOutputStream(FILE_BOOK));
         output.writeObject(books);
         output.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
