package HomeWork;

public class First20PrimeNumbers {
    public static void main(String[] args) {
        int count = 0, start = 0, end = 20, i = 1, j = 1;
        System.out.println("First 20 prime numbers: ");
        while (start < end) {
            count = 0;
            j = 1;
            while (j <= i) {
                if (i % j == 0) {
                    count++;
                }
                j++;
            }
            if (count == 2) {
                System.out.print(i + " ");
                start++;
            }
            i++;
        }
    }
}
