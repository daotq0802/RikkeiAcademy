package HomeWork;

import java.util.Scanner;

public class Employee {
    private int employeeId;
    private int age;
    private float rate, salary;
    private String employeeName;
    private boolean gender;

    public Employee() {
    }

    public Employee(int employeeId, int age, int rate, String employeeName, boolean gender) {
        this.employeeId = employeeId;
        this.age = age;
        this.rate = rate;
        this.employeeName = employeeName;
        this.gender = gender;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void calculateSalary() {
        this.salary = rate * 1300000;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        employeeId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Employee Name: ");
        employeeName = scanner.nextLine();
        System.out.print("Enter Age: ");
        age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Gender: ");
        gender = scanner.nextBoolean();
        System.out.print("Enter Salary: ");
        rate = Float.parseFloat(scanner.nextLine());
    }

    public void displayData() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Gender: " + (gender ? "Male" : "Female"));
        System.out.println("Age: " + age);
        System.out.println("Rate: " + rate);
        System.out.println("Salary: " + salary);
    }

}
