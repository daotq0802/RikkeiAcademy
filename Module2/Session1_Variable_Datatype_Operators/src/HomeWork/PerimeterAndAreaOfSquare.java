package HomeWork;

import java.util.Scanner;

public class PerimeterAndAreaOfSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter square edge: ");
        double edge = sc.nextDouble();
        double perimeter = 4 * edge;
        double area = edge * edge;
        System.out.println("Perimeter is: " + perimeter);
        System.out.println("Area is: " + area);
    }
}
