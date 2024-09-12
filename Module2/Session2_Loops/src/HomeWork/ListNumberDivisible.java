package HomeWork;

public class ListNumberDivisible {
    public static void main(String[] args) {
        System.out.println("Numbers divisible by 3 and 5 are:");
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i);
            }
        }
    }
}
