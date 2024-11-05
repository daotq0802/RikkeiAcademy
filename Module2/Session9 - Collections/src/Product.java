import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop, Serializable {
   private String id;
   private String name;
   private int catalogId;
   private float importPrice;
   private float exportPrice;
   private boolean status;

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

   public int getCatalogId() {
      return catalogId;
   }

   public void setCatalogId(int catalogId) {
      this.catalogId = catalogId;
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

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Product(String id, String name, int catalogId, float importPrice, float exportPrice, boolean status) {
      this.id = id;
      this.name = name;
      this.catalogId = catalogId;
      this.importPrice = importPrice;
      this.exportPrice = exportPrice;
      this.status = status;
   }

   public Product() {
   }

   public String inputId(Scanner scanner) {
      while(true){
         String id = scanner.nextLine();
         if(id.isEmpty()||id.trim().isEmpty()){
            System.err.println("Hãy nhập mã sản phẩm!!!");
         }else{
            if(Pattern.matches("^P\\w{3}$",id)){
               boolean found = false;
               for(Product product : Main.products){
                  if(product.id.equals(id)){
                     found = true;
                     break;
                  }
               }
               if(!found){
                  return id;
               }else{
                  System.err.println("Mã sản phẩm đã tồn tại!!!");
               }
            }else{
               System.err.println("Mã sản phẩm phải bắt đầu bằng 'P' và 3 ký tự bất kỳ!!!");
            }
         }
      }
   }

   public String inputName(Scanner scanner) {
      while(true){
         String name = scanner.nextLine();
         if(name.isEmpty()||name.trim().isEmpty()){
            System.err.println("Hãy nhập tên sản phẩm!!!");
         }else{
            boolean found = false;
            for(Product product : Main.products){
               if(product.name.equals(name)){
                  found = true;
                  break;
               }
            }
            if(!found){
               return name;
            }else{
               System.err.println("Tên sản phẩm đã tồn tại!!!");
            }
         }
      }
   }

   public int inputCatalogId(Scanner scanner) {
      for(int i=0;i<Main.categories.size();i++){
         System.out.printf("%d. %s\n", i+1, Main.categories.get(i).getName());
      }
      while(true){
         try{
            int select = Integer.parseInt(scanner.nextLine());
            if(select<=0 || select > Main.categories.size()){
               System.err.println("Lựa chọn không hợp lệ!!!");
            }else{
               return Main.categories.get(select-1).getId();
            }
         } catch (NumberFormatException e) {
            System.err.println("Lựa chọn phải là số!!!");
         } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi, thử lại!!!");
         }
      }
   }

   public float inputImportPrice(Scanner scanner) {
      while(true){
         try{
            float price = Float.parseFloat(scanner.nextLine());
            if(price<=0){
               System.err.println("Giá nhập hàng phải lớn hơn 0!!!");
            }else{
               return price;
            }
         } catch (NumberFormatException e) {
            System.err.println("Giá nhập hàng phải là số!!!");
         } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi, thử lại!!!");
         }
      }
   }

   public boolean inputStatus(Scanner scanner) {
      while(true){
         String status = scanner.nextLine();
         if(status.isEmpty()||status.trim().isEmpty()){
            System.err.println("Hãy nhập trạng thái sản phẩm!!!");
         }else{
            if(status.equals("true")){
               return true;
            }else if(status.equals("false")){
               return false;
            }else{
               System.err.println("Trạng thái không hợp lệ!!!" +
                       "\ntrue -> Hoạt động" +
                       "\nfalse -> Không hoạt động");
            }
         }
      }
   }

   public float calExportPrice(){
      return this.importPrice * RATE;
   }

   public String getNameCategory(long id){
      for(Category category : Main.categories){
         if(category.getId() == id){
            return category.getName();
         }
      }
      return "";
   }
   @Override
   public void inputData(Scanner scanner) {
      System.out.println("Nhập mã sản phẩm: ");
      this.id = inputId(scanner);
      System.out.println("Nhập tên sản phẩm: ");
      this.name = inputName(scanner);
      System.out.println("Chọn danh mục cho sản phẩm");
      this.catalogId = inputCatalogId(scanner);
      System.out.println("Giá nhập hàng: ");
      this.importPrice = inputImportPrice(scanner);
      System.out.println("Nhập trạng thái sản phẩm: ");
      this.status = inputStatus(scanner);
      this.exportPrice = calExportPrice();
   }

   @Override
   public String toString() {
      return "Mã sản phẩm: " + this.id +"\t-\tTên sản phẩm: " +this.name+
              "\nDanh mục: " + getNameCategory(this.catalogId) +
              "\t-\tTrạng thái: " + (this.status?"Hoạt động" : "Không hoạt động")+
              "\nGía nhập hàng: " + this.importPrice +"\t-\tGiá bán hàng: " + calExportPrice();
   }

   @Override
   public String displayData() {
      return "Mã sản phẩm: " + this.id +"\t-\tTên sản phẩm: " +this.name+
              "\nDanh mục: " + getNameCategory(this.catalogId) +
              "\t-\tTrạng thái: " + (this.status?"Hoạt động" : "Không hoạt động")+
              "\nGía nhập hàng: " + this.importPrice +"\t-\tGiá bán hàng: " + calExportPrice();
   }
}
