package HomeWork;

import java.util.Scanner;

public class Circle {
    private double radius;
    private String color;

    public Circle() {
        System.out.println("This is a circle");
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public void inputData(Scanner scanner) {
        this.radius = Double.parseDouble(scanner.nextLine());
        this.color = scanner.nextLine();
    }

    public void displayData() {
        System.out.println("Radius: " + radius + "\nColor: " + color);
    }
}
