package ra.run;

import ra.entity.Book;

import java.util.*;

public class BookImp {
    static Book[] books = new Book[0];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookImp book = new BookImp();

        do {
            System.out.println("==============MENU==============");
            System.out.println("1. Add Book");
            System.out.println("2. Profit All Books");
            System.out.println("3. Display All Books");
            System.out.println("4. Sort Books by Sold Price");
            System.out.println("5. Sort Books by Profit");
            System.out.println("6. Search Book by Name");
            System.out.println("7. Statistics Book by Year");
            System.out.println("8. Statistics Book by Author");
            System.out.println("9. Exit");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        book.addBook(sc);
                        break;
                    case 2:
                        book.calculateProfit();
                        break;
                    case 3:
                        book.displayAllBooks(books);
                        break;
                    case 4:
                        book.sortBooksBySoldPrice();
                        break;
                    case 5:
                        book.sortBooksByProfit();
                        break;
                    case 6:
                        book.searchBookByName(sc);
                        break;
                    case 7:
                        book.statisticsBooksByYear(sc);
                        break;
                    case 8:
                        book.statisticsBooksByAuthor(sc);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Invalid choice, please try again");
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter a valid number");
            }
        } while (true);
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter the number of books you want to add: ");
        int n = Integer.parseInt(scanner.nextLine());
        Book[] newBooks = new Book[books.length + n];
        for (int i = 0; i < newBooks.length; i++) {
            if (i < books.length) {
                newBooks[i] = books[i];
            } else {
                System.out.println("Enter new book's information");
                Book book = new Book();
                book.inputBookData(scanner, books);
                newBooks[i] = book;
                System.out.println("----------------------------------------");
            }
        }
        books = newBooks;
    }

    public void calculateProfit() {
        System.out.println("Profit All Books");
        for (int i = 0; i < books.length; i++) {
            System.out.printf("%d. Profit of book %s is %f\n", i + 1, books[i].getBookName(), books[i].getProfit());
        }
    }

    public void displayAllBooks(Book[] books) {
        if (books.length > 0) {
            System.out.printf("Has %d books in store.\n", books.length);
            System.out.println("----------------------------------------");
            for (Book book : books) {
                System.out.println(book.displayBookData());
                System.out.println("----------------------------------------");
            }
        } else {
            System.err.println("No books in store.");
        }
    }

    public void sortBooksBySoldPrice() {
        Book[] sortBooks = books;
        for (int i = 0; i < sortBooks.length - 1; i++) {
            for (int j = i + 1; j < sortBooks.length; j++) {
                if (sortBooks[i].getExportPrice() > sortBooks[j].getExportPrice()) {
                    Book temp = sortBooks[i];
                    sortBooks[i] = sortBooks[j];
                    sortBooks[j] = temp;
                }
            }
        }
        displayAllBooks(sortBooks);
    }

    public void sortBooksByProfit() {
        Book[] sortBooks = books;
        for (int i = 0; i < sortBooks.length - 1; i++) {
            for (int j = i + 1; j < sortBooks.length; j++) {
                if (sortBooks[i].getProfit() > sortBooks[j].getProfit()) {
                    Book temp = sortBooks[i];
                    sortBooks[i] = sortBooks[j];
                    sortBooks[j] = temp;
                }
            }
        }
        displayAllBooks(sortBooks);
    }

    public void searchBookByName(Scanner scanner) {
        System.out.print("Enter the book name you want to search: ");
        String bookName = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getBookName().toLowerCase().contains(bookName.toLowerCase())) {
                System.out.println(count + 1 + ". " + books[i].getBookName());
                count++;
            }
        }
        if (count > 0) {
            System.out.println("Found " + count + " book(s)");
        } else {
            System.err.println("Don't found any book");
        }
    }

    public void statisticsBooksByYear(Scanner scanner) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < books.length; j++) {
            if (map.containsKey(books[j].getYear())) {
                map.put(books[j].getYear(), map.get(books[j].getYear()) + 1);
            } else {
                map.put(books[j].getYear(), 1);
            }
        }
        System.out.println("Quantity of Year published books");
        int index = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer year = entry.getKey();
            Integer count = entry.getValue();
            System.out.printf("%d. %d - %d\n", index, year, count);
            index++;
        }
        System.out.printf("%d. Exit\n", map.size() + 1);

        while (true) {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= map.size()) {
                int year = 0;
                List<Integer> list = new ArrayList<>(map.keySet());
                for (int i = 0; i < list.size(); i++) {
                    if (choice - 1 == i) {
                        year = list.get(i);
                    }
                }
                for (Book book : books) {
                    if (book.getYear() == year) {
                        System.out.println(book.displayBookData());
                        System.out.println("-------------------------------------");
                    }
                }
                break;
            } else if (choice == map.size() + 1) {
                break;
            } else {
                System.err.println("Invalid choice, please try again");
            }
        }
    }

    public void statisticsBooksByAuthor(Scanner scanner) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < books.length; i++) {
            if (map.containsKey(books[i].getAuthor())) {
                map.put(books[i].getAuthor(), map.get(books[i].getAuthor()) + 1);
            } else {
                map.put(books[i].getAuthor(), 1);
            }
        }
        System.out.println("Quantity of Author books");
        int index = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String author = entry.getKey();
            Integer count = entry.getValue();
            System.out.printf("%d. %s - %d\n", index, author, count);
            index++;
        }
        System.out.printf("%d. Exit\n", map.size() + 1);

        while (true) {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= map.size()) {
                String author = "";
                List<String> list = new ArrayList<>(map.keySet());
                for (int i = 0; i < list.size(); i++) {
                    if (choice - 1 == i) {
                        author = list.get(i);
                    }
                }
                for (Book book : books) {
                    if (book.getAuthor() == author) {
                        System.out.println(book.displayBookData());
                        System.out.println("-------------------------------------");
                    }
                }
                break;
            } else if (choice == map.size() + 1) {
                break;
            } else {
                System.err.println("Invalid choice, please try again");
            }
        }
    }
}
