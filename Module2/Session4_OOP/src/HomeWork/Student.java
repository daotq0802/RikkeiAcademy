package HomeWork;

import java.util.Scanner;

public class Student {
    private int age;
    private String studentID, studentName, className, address;
    private boolean sex;


    public Student() {
    }

    public Student(int age, String studentID, String studentName, String className, String address, boolean sex) {
        this.age = age;
        this.studentID = studentID;
        this.studentName = studentName;
        this.className = className;
        this.address = address;
        this.sex = sex;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean inputData(Student[] students, Scanner scanner) {
        System.out.print("Nhập mã sinh viên: ");
        setStudentID(scanner.nextLine());

        for (int i = 0; i < students.length; i++) {
            if (students[i].getStudentID().equals(getStudentID())) {
                System.err.println("Mã sinh viên đã tồn tại!!");
                return false;
            }
        }
        System.out.print("Nhập tên sinh viên: ");
        setStudentName(scanner.nextLine());
        System.out.print("Nhập giới tính sinh viên: ");
        setSex(Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Nhập tuổi sinh viên: ");
        setAge(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nhập địa chỉ sinh viên: ");
        setAddress(scanner.nextLine());
        System.out.print("Nhập tên lớp sinh viên: ");
        setClassName(scanner.nextLine());
        return true;
    }


    @Override
    public String toString() {
        return "Mã sinh viên - " + studentID +
                "\nTên sinh viên - " + studentName +
                "\nTuổi - " + age +
                "\nGiới tính - " + (sex ? "Nam" : "Nữ") +
                "\nĐịa chỉ - " + address +
                "\nTên lớp - " + className;
    }
}
