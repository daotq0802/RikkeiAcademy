package HomeWork;

import java.util.Scanner;

public class AddToArray {
    public static void main(String[] args) {
        int[] numbers = new int[10];
        Scanner sc = new Scanner(System.in);

        boolean isAllSet = false;


        System.out.print("Nhập vị trí của mảng cần thêm phần tử ");

        int index = Integer.parseInt(sc.nextLine());
        if (index >= numbers.length) {
            System.err.println("Mảng không tồn tại vị trí này");
        } else {

            try {
                System.out.print("\nNhập giá trị của phần tử: ");
                int number = Integer.parseInt(sc.nextLine());
                for (int i = numbers.length - 1; i > index; i--) {
                    numbers[i] = numbers[i - 1];
                }
                numbers[index] = number;

            } catch (Exception e) {
                System.err.println("Không hợp lệ");
            }
            for (int element : numbers) {
                System.out.print(element + "\t");
            }
        }

    }
}
