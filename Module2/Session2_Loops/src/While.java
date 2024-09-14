import java.util.Scanner;

public class While {
    public static void main(String[] args) {
        //* In ra và tính tổng tổng 20 số nguyên chẵn đầu tiên tính từ 1
        //? B1: Khai báo biến đếm số nguyên chẵn
        int count = 0;
        //? B2: Sử dụng vòng lặp while để in 20 số chẵn đầu tiên và tính tổng
        int number = 1;
        int sum = 0;
        System.out.println("20 số chẵn đầu tiên tính từ 1 là:");
        while (count < 20) {
            if (number % 2 == 0) {
                System.out.printf("%d\t", number);
                count++;
                sum += number;
            }
            number++;
        }
        //? B3: In ra tổng
        System.out.printf("\nTổng 20 số nguyên chẵn đầu tiên là: %d\n", sum);

        //* Nhập các số từ bàn phím, tính tổng các số, kết thúc khi nhập 0
        int sumNumber = 0;
        int input;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\nNhập vào 1 số: ");
            input = Integer.parseInt(scan.nextLine());
            sumNumber += input;
        } while (input != 0);
        System.out.printf("Tổng số vừa nhập là: %d", sumNumber);
    }
}
