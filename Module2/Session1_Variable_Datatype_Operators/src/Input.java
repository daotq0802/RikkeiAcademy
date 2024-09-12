import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        //1. Nhập dữ liệu mã sinh viên, tên sinh viên, tuổi
        //B1: Khởi tạo đối tượng Scanner
        Scanner scanner = new Scanner(System.in);
        //B2: Thông báo nhập
        System.out.println("Nhập vào mã sinh viên:");
        //B3: Lấy giá trị nhận từ bàn phím và lưu vào biến
        String studentId = scanner.nextLine();

        System.out.println("Nhập vào tên sinh viên:");
        String studentName = scanner.nextLine();

        System.out.println("Nhập vào số tuổi sinh viên:");
//        int studentAge = scanner.nextInt();

        //C1: Xoá phím enter trong bộ nhớ đệm
//        scanner.nextLine();
        //C2: Lấy dữ liệu vào là String --> convert sang kiểu dữ liệu mong muốn
        /*
        * String --> int: Integer.parseInt("String")
        * String --> float: Float.parseFloat("String")
        * String --> double: Double.parseDouble("String")
        * String --> boolean: Boolean.parseBoolean("String")
        * */
        int studentAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào địa chỉ sinh viên");
        String studentAddress = scanner.nextLine();
        System.out.println("Nhập vào giới tính sinh viên");
        boolean studentGender = Boolean.parseBoolean(scanner.nextLine());

        //In thông tin sinh vin
        System.out.printf("Mã sinhviên là: %s - Tên sinh viên: %s - Tuổi: %d\n", studentId, studentName, studentAge);
        System.out.printf("Địa chỉ: %s - Giới tính: %s\n", studentAddress, studentGender?"Name":"Nữ");
    }
}
