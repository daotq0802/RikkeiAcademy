package entity;

import java.time.LocalDate;

public class Products {
   private int id;
   private String name;
   private int stock;
   private double cost_price;
   private double selling_price;
   private LocalDate created_at;
   private int category_id;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getStock() {
      return stock;
   }

   public void setStock(int stock) {
      this.stock = stock;
   }

   public double getCost_price() {
      return cost_price;
   }

   public void setCost_price(double cost_price) {
      this.cost_price = cost_price;
   }

   public double getSelling_price() {
      return selling_price;
   }

   public void setSelling_price(double selling_price) {
      this.selling_price = selling_price;
   }

   public LocalDate getCreated_at() {
      return created_at;
   }

   public void setCreated_at(LocalDate created_at) {
      this.created_at = created_at;
   }

   public int getCategory_id() {
      return category_id;
   }

   public void setCategory_id(int category_id) {
      this.category_id = category_id;
   }

   @Override
   public String toString() {
      return "Tên sản phẩm: " + this.name + "\t-\tSố lượng: " + this.stock
              + "\nGía nhập: " + this.cost_price + "\t-\tGiá bán: " + this.selling_price
              + "\nNgày tạo: " + this.created_at
              + "\n-----------------------------";
   }
}
