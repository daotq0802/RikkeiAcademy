import java.util.Scanner;

public class BusTicket {
    public static void main(String[] args) {
        int busFee = 7000;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your age? ");
        int age = Integer.parseInt(scanner.nextLine());
        if (age < 6 || age > 60) {
            System.out.println("Your bus fee is free");
        } else if (age < 18) {
            System.out.println("Your bus fee have 50% discount: " + busFee / 2);
        }else{
            System.out.println("Your bus fee is: " + busFee);
        }
    }
}
