public class ForEach {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 7};
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.printf("Tổng các giá trị trong mảng là: %d", sum);
    }
}
