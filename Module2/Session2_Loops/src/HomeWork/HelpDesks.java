package HomeWork;

import java.util.Scanner;

public class HelpDesks {
    public static void main(String[] args) {
        System.out.println("Welcome to the Help Desk");
        System.out.println("Our services has:\n1. Check the parity of a number\n2. Check prime number\n3. Check divisible by 3\n4. Exit");
        System.out.print("Which service do you need? ");
        Scanner sc = new Scanner(System.in);
        int service = sc.nextInt();
        switch (service) {
            case 1:
                System.out.print("Enter number: ");
                int number = sc.nextInt();
                checkParity(number);
                break;
            case 2:
                System.out.print("Enter number: ");
                int number2 = sc.nextInt();
                if(checkPrime(number2)) {
                    System.out.print("The number is prime number");
                }else{
                    System.out.print("The number is not prime number");
                }
                break;
            case 3:
                System.out.print("Enter number: ");
                int number3 = sc.nextInt();
                checkDivisibleBy3(number3);
                break;
            case 4:
                sc.close();
                break;
            default:
                System.out.println("Invalid service");
                break;
        }
    }

    public static void checkParity(int number) {
        if (number % 2 == 0) {
            System.out.println("The number is even");
        } else {
            System.out.println("The number is odd");
        }
    }

    public static void checkDivisibleBy3(int number) {
        if (number % 3 == 0) {
            System.out.println("The number is divisible by 3");
        } else {
            System.out.println("The number isn't divisible by 3");
        }
    }

    public static boolean checkPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
