package HomeWork;

import java.util.Scanner;

public class AverageScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Math score: ");
        double mathScore = sc.nextDouble();
        System.out.print("Literature score: ");
        double literatureScore = sc.nextDouble();
        System.out.print("English score: ");
        double englishScore = sc.nextDouble();
        System.out.println("Average score: " + (mathScore + literatureScore + englishScore)/3);
    }
}
