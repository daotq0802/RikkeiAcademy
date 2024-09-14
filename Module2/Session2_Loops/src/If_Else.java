import java.util.Scanner;

public class If_Else {
    public static void main(String[] args) {
        //* 1. Nhập vào 1 số, kiểm tra số vừa nhập có phải là số chẵn hay không, nếu là chẵn thì in ra là số chẵn
        /*
         * B1: Đọc và hiểu yêu cầu
         * B2: Phân tích: Đầu vào, đầu ra
         * B3: Các giải pháp và lựa chọn giải pháp phù hợp
         * B4: Các bước giải quyết vấn đề
         * B5: Thực hiện code theo các bước đã thiết kế
         * */

        // ? B1: Nhập 1 số nguyên từ bàn phím
        // ? 1-1: Khởi tạo đối tượng Scanner
        Scanner sc = new Scanner(System.in);
        // ? 1-2: Thông báo nhập
        System.out.println("Nhập vào một số nguyên: ");
        // ? 1-3: Nhập số nguyên và lưu vào 1 biến number
        int number = Integer.parseInt(sc.nextLine());
        // ? B2: Kiểm tra số nguyên có phải là số chẵn không --> In ra thông báo
        // ? 2-1: Sử dụng câu lệnh điều kiện để kiểm tra số chẵn
        if (number % 2 == 0) {
            // ? 2-2: Nếu là số chẵn thông báo ra
            System.out.printf("%d là số chẵn\n", number);
        }else{
            System.out.printf("%d là số lẻ\n", number);
        }

        // * 2. In ra số dư trong phép chia 3, sử dụng câu lệnh điều kiện if
        if(number % 3 == 1){
            System.out.printf("%d chia 3 dư 1\n", number);
        }else if(number % 3 == 2){
            System.out.printf("%d chia 3 dư 2\n", number);
        }else {
            System.out.printf("%d chia hết cho 3\n", number);
        }

        // * 3. Nhập vào 1 số nguyên từ bàn phím, in ra số dư trong phép chia 5
        if(number % 5 == 1){
            System.out.printf("%d chia 5 dư 1\n", number);
        }else if(number % 5 == 2){
            System.out.printf("%d chia 5 dư 2\n", number);
        }else if(number % 5 == 3){
            System.out.printf("%d chia 5 dư 3\n", number);
        }else if(number % 5 == 4){
            System.out.printf("%d chia 5 dư 4\n", number);
        }else{
            System.out.printf("%d chia hết cho 5 \n", number);
        }
    }
}
