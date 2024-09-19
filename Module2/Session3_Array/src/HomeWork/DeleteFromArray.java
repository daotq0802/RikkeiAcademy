package HomeWork;

import java.util.Scanner;

public class DeleteFromArray {
    public static void main(String[] args) {
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập phần tử muốn xoá: ");
        int number = Integer.parseInt(sc.nextLine());
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (number == numbers[i]) {
                index = i;
            }
        }
        System.out.print("Vị trí của " + number + " là: " + index + "\n");
        for (int i = index; i < numbers.length - 1; i++) {
            int temp = numbers[i + 1];
            numbers[i + 1] = 0;
            numbers[i] = temp;
        }
        System.out.printf("Mảng sau khi xoá phần tử %d là: \n", number);
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "\t");
        }
    }
}
