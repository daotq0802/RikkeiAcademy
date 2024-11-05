package entity;

import java.time.LocalDate;

public class Employee {
   private int id;
   private String name;
   private LocalDate birthday;
   private boolean sex;
   private String address;
   private String phone;
   private String email;
   private String avatar;
   private boolean status;
   private int deptId;

   public int getDeptId() {
      return deptId;
   }

   public void setDeptId(int deptId) {
      this.deptId = deptId;
   }

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

   public LocalDate getBirthday() {
      return birthday;
   }

   public void setBirthday(LocalDate birthday) {
      this.birthday = birthday;
   }

   public boolean isSex() {
      return sex;
   }

   public void setSex(boolean sex) {
      this.sex = sex;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   @Override
   public String toString() {
      return "Tên nhân viên: " + this.name + "\t-\tGiới tính: " + this.sex +
              "\nNăm sinh: " + this.birthday + "\t-\tSố điện thoại: " + this.phone +
              "\nĐịa chỉ: " + this.address +
              "\nEmail: " + this.email + "\t-\tTrạng thái: " + (this.status ? "Đi làm" : "Nghỉ làm") +
              "\n----------------------------------";
   }
}
