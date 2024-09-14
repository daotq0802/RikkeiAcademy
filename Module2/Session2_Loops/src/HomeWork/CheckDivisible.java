package HomeWork;

import java.util.Scanner;

public class CheckDivisible {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int num = sc.nextInt();
        if (num % 3 == 0 && num % 5 == 0) {
            System.out.println("Number " + num + " is Divisible 3 and 5.");
        } else {
            System.out.println("Number " + num + " is not Divisible 3 and 5.");
        }
    }
}
