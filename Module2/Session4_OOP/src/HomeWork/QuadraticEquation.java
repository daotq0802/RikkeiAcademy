package HomeWork;

import java.util.Scanner;

public class QuadraticEquation {
    private double a, b, c;

    public QuadraticEquation() {
    }

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void getRoot() {
        double delta = Math.pow(b, 2) - 4 * a * c;
        double root1 = (-b + Math.sqrt(delta)) / (2 * a);
        double root2 = (-b - Math.sqrt(delta)) / (2 * a);
        if (delta < 0) {
            System.out.println("The equation has no roots");
        } else if (delta == 0) {
            System.out.println("The equation has one root -1");
        } else {
            System.out.println("The equation has two roots " + root1 + " and " + root2);
        }
    }

    public void inputData(Scanner scan) {
        System.out.print("Enter A : ");
        setA(scan.nextDouble());
        System.out.print("Enter B : ");
        setB(scan.nextDouble());
        System.out.print("Enter C : ");
        setC(scan.nextDouble());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        QuadraticEquation quadraticEquation = new QuadraticEquation();
        quadraticEquation.inputData(scan);
        quadraticEquation.getRoot();
    }

}
