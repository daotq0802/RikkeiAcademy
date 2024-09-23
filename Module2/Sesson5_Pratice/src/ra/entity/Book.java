package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    private String bookID;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float profit;
    private int year;

    public Book() {
    }

    public Book(String bookID, String bookName, float importPrice, float exportPrice, String author, int year) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.profit = exportPrice - importPrice;
        this.year = year;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getProfit() {
        return profit;
    }

    public float setProfit() {
        return this.profit = exportPrice - importPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputBookData(Scanner scan, Book[] books) {
        this.bookID = inputBookID(scan, books);
        this.bookName = inputBookName(scan, books);
        this.importPrice = inputImportPrice(scan);
        this.exportPrice = inputExportPrice(scan);
        this.author = inputAuthor(scan);
        this.profit = setProfit();
        this.year = inputYear(scan);
    }

    public String displayBookData() {
        return "Book ID: " + this.bookID + " - Book name: " + this.bookName
                + "\nImport price: " + this.importPrice + " - Export price: " + this.exportPrice
                + "\nAuthor: " + this.author + " - Year: " + this.year
                + "\nProfit: " + this.profit;
    }

    private String inputBookID(Scanner scan, Book[] books) {
        System.out.print("Enter the book ID: ");
        while (true) {
            String bookID = scan.nextLine();
            if (bookID.equals("") || bookID == null || bookID.trim().equals("")) {
                System.err.println("Please enter the book ID");
            } else {
                boolean found = false;
                if (Pattern.matches("B[0-9]{3}", bookID)) {
                    for (Book book : books) {
                        if (book.getBookID().equals(bookID)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        return bookID;
                    } else {
                        System.out.println("This book's ID already exists");
                    }
                } else {
                    System.err.println("Book's ID is not match");
                }
            }
        }
    }

    private String inputBookName(Scanner scan, Book[] books) {
        System.out.print("Enter the book name: ");
        while (true) {
            String bookName = scan.nextLine();
            if (bookName.equals("") || bookName == null || bookName.trim().equals("")) {
                System.err.println("Please enter the book name");
            } else {
                boolean found = false;
                for (Book book : books) {
                    if (book.getBookName().equals(bookName)) {
                        found = true;
                    }
                }
                if (!found) {
                    return bookName;
                } else {
                    System.out.println("This book's name already exists");
                }
            }
        }
    }

    private float inputImportPrice(Scanner scan) {
        System.out.print("Enter the import price: ");
        while (true) {
            float importPrice = Float.parseFloat(scan.nextLine());
            if (importPrice < 0) {
                System.err.println("Please enter a positive number");
            } else {
                return importPrice;
            }
        }
    }

    private float inputExportPrice(Scanner scan) {
        System.out.print("Enter the export price: ");
        while (true) {
            float exportPrice = Float.parseFloat(scan.nextLine());
            if (exportPrice < 0) {
                System.err.println("Please enter a positive number");
            } else if (exportPrice < importPrice * 1.3) {
                System.err.println("Export price must be bigger than 30% of import price");
            } else {
                return exportPrice;
            }
        }
    }

    private String inputAuthor(Scanner scan) {
        System.out.print("Enter the author: ");
        while (true) {
            String author = scan.nextLine();
            if (author.equals("") || author == null || author.trim().equals("")) {
                System.err.println("Please enter the book author");
            } else {
                if (author.length() >= 6 && author.length() <= 50) {
                    return author;
                } else {
                    System.err.println("Book's author must be have 6-50 characters");
                }
            }
        }
    }

    private int inputYear(Scanner scan) {
        System.out.print("Enter the year: ");
        while (true) {
            int year = Integer.parseInt(scan.nextLine());
            if (year >= 2000) {
                return year;
            } else {
                System.err.println("Book's year must be in range after 2000");
            }
        }
    }
}

