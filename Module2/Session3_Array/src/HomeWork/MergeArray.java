package HomeWork;

import java.util.Arrays;
import java.util.Scanner;

public class MergeArray {
    public static void main(String[] args) {
        int[] array1 = new int[5];
        int[] array2 = new int[5];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array1.length; i++) {
            System.out.printf("Nhập phần tử thứ %d của mảng 'Array 1': ", i + 1);
            array1[i] = sc.nextInt();
        }
        System.out.println("Mảng Array 1 gồm các phần tử");
        for (int element : array1) {
            System.out.print(element + "\t");
        }
        System.out.println();
        for (int i = 0; i < array2.length; i++) {
            System.out.printf("Nhập phần tử thứ %d của mảng 'Array 2': ", i + 1);
            array2[i] = sc.nextInt();
        }
        System.out.println("Mảng Array 2 gồm các phần tử");
        for (int element : array2) {
            System.out.print(element + "\t");
        }
        System.out.println("\nTiến hành gộp 2 mảng");
        setTimeout(() -> mergeArray(array1, array2), 500);

    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    System.out.print("\rLoading......" + i + "%");
                    Thread.sleep(20);
                }
                System.out.println();
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void mergeArray(int[] array1, int[] array2) {
        int length = array1.length + array2.length;
        int index = 0;
        int[] result = new int[length];
        for (int element : array1) {
            result[index] = element;
            index++;
        }

        for (int element : array2) {
            result[index] = element;
            index++;
        }

        for (int element : result) {
            System.out.print(element + "\t");
        }
    }
}
