package HomeWork;

import java.util.Scanner;

public class CircumferenceAndAreaOfCircle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the radius of the circle: ");
        double radius = sc.nextDouble();
        double area = Math.PI * radius * radius;
        double circumference = 2 * Math.PI * radius;
        System.out.println("The area of the circle is " + area);
        System.out.println("The circumference of the circle is " + circumference);
    }
}
