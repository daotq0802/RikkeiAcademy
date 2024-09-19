package HomeWork;

public class MinInArray {
    public static void main(String[] args) {
        int[] numbers = {3, 4, 2, 6, 4, 1, 4, 5, 6, 7, 8, 0, 3, 2};
        int min = 100;
        for (int element : numbers) {
            if (element < min) {
                min = element;
            }
        }
        System.out.printf("Phần tử nhỏ nhất trong mảng là %d", min);
    }
}
