import java.util.Scanner;

public class Array {
    public static void main(StringDemo[] args) {
        int[] numbers = new int[10];
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("numbers[" + i + "] = " );
            numbers[i] = Integer.parseInt(sc.nextLine());
        }
        System.out.println("Các giá trị phần tử chẵn trong mảng là:");

//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] % 2 == 0) {
//                sum += numbers[i];
//                System.out.print(numbers[i] +"\t");
//            }
//        }

        for(int element : numbers){
            if(element%2==0){
                sum += element;
                System.out.print(element + "\t\n");
            }
        }
        System.out.println("Tổng các phần tử đó là: " + sum);

    }
}
