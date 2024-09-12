package HomeWork;

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        final int USD_EXCHANGE_RATE = 25000;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter USD amount: ");
        double usdAmount = sc.nextDouble();
        int totalVND = (int) (usdAmount * USD_EXCHANGE_RATE);
        System.out.println("You will have " + totalVND + " after exchange");
    }
}
