package HomeWork;

import java.util.Scanner;

public class IsTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the side a: ");
        int a = sc.nextInt();
        System.out.print("Enter the side b: ");
        int b = sc.nextInt();
        System.out.print("Enter the side c: ");
        int c = sc.nextInt();
        System.out.println("Checking can make a triangle or not?");
        if (a + b > c && a + c > b && b + c > a) {
            setTimeout(() -> System.out.println("Can make triangle"), 500);
        } else {
            setTimeout(() -> System.out.println("Can't make triangle"), 500);
        }
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    System.out.print("\r" + "-".repeat(i) + i + "%");
                    Thread.sleep(20); // 50 millisecond
                }
                System.out.println();
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
