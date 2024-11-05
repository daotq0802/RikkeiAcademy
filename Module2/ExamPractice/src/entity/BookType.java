package entity;

import java.io.Serializable;
import java.util.Scanner;

public class BookType {
   private int typeId;
   private String typeName;
   private String description;
   private boolean isDeleted;

   public BookType() {

   }

   public BookType(int typeId, String typeName, String description, boolean isDeleted) {
      this.typeId = typeId;
      this.typeName = typeName;
      this.description = description;
      this.isDeleted = isDeleted;
   }

   public int getTypeId() {
      return typeId;
   }

   public void setTypeId(int typeId) {
      this.typeId = typeId;
   }

   public String getTypeName() {
      return typeName;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isDeleted() {
      return isDeleted;
   }

   public void setDeleted(boolean deleted) {
      isDeleted = deleted;
   }

   @Override
   public String toString() {
      return "Tên thể loại: " + this.typeName +
              "\nMô tả: " + this.description +
              "\n--------------------------------";
   }
}
