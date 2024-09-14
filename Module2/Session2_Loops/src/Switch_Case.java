import java.util.Scanner;

public class Switch_Case {
    public static void main(String[] args) {
        // * Nhập vào 1 số nguyên từ 0 - 9, phiên âm số đó
        // ? B1: Nhập vào 1 số nguyên từ bàn phím
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào số nguyên");
        int number = Integer.parseInt(sc.nextLine());
        // ? B2: Phiên âm số đó
        switch (number) {
            case 0:
                System.out.println("Không");
                break;
            case 1:
                System.out.println("Một");
                break;
            case 2:
                System.out.println("Hai");
                break;
            case 3:
                System.out.println("Ba");
                break;
            case 4:
                System.out.println("Bốn");
                break;
            case 5:
                System.out.println("Năm");
                break;
            case 6:
                System.out.println("Sáu");
                break;
            case 7:
                System.out.println("Bảy");
                break;
            case 8:
                System.out.println("Tám");
                break;
            case 9:
                System.out.println("Chín");
                break;
            default:
                System.out.println("Không hợp lệ");
        }
    }
}
