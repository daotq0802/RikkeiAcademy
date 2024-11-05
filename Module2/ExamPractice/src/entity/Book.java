package entity;

public class Book {
   private int bookId;
   private String bookName;
   private String author;
   private String publisher;
   private String title;
   private int totalPages;
   private String content;
   private double price;
   private int typeId;
   private boolean isDeleted;

   public Book() {
   }

   public Book(int bookId, String bookName, String author, String publisher, String title, int totalPages, String content, double price, int typeId, boolean isDeleted) {
      this.bookId = bookId;
      this.bookName = bookName;
      this.author = author;
      this.publisher = publisher;
      this.title = title;
      this.totalPages = totalPages;
      this.content = content;
      this.price = price;
      this.typeId = typeId;
      this.isDeleted = isDeleted;
   }

   public int getBookId() {
      return bookId;
   }

   public void setBookId(int bookId) {
      this.bookId = bookId;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getTotalPages() {
      return totalPages;
   }

   public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public int getTypeId() {
      return typeId;
   }

   public void setTypeId(int typeId) {
      this.typeId = typeId;
   }

   public boolean isDeleted() {
      return isDeleted;
   }

   public void setDeleted(boolean deleted) {
      isDeleted = deleted;
   }

   @Override
   public String toString() {
      return "Tên sách: " + this.bookName + "\t-\tTiêu đề: " + this.title +
              "\nTác giả: " + this.author + "\t-\tSố trang: " + this.totalPages +
              "\nNội dung: " + this.content +
              "\nGiá sách: " + this.price + "\t-\tNhà xuất bản: " + this.publisher +
              "\n--------------------------------";
   }
}
