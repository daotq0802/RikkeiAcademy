package HomeWork;

import java.util.Arrays;

public class FindMaxIn3DArray {
    public static void main(String[] args) {
        double[][] numbers = {{2.1, 10.6, 4.3}, {1.3, 3.3, 10.3}, {7.2, 1.1, 9.6}};
        int maxCol = 0;
        int maxRow = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] > numbers[maxRow][maxCol]) {
                    maxRow = i;
                    maxCol = j;
                    System.out.printf("Số lớn nhất trong mảng là %f\nỞ vị trí:\ndòng thứ %d\ncột thứ %d", numbers[i][j], maxRow, maxCol);
                }
            }
        }
    }
}
