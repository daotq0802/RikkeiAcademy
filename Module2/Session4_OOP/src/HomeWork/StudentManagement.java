package HomeWork;

import java.util.Scanner;

public class StudentManagement {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Student[] students = new Student[0];
        boolean isFinished = false;
        while (!isFinished) {
            showMenu();
            int selection = Integer.parseInt(sc.nextLine());
            switch (selection) {
                case 1:
                    renderAllStudent(students);
                    break;
                case 2:
                    students = addNewStudent(students, sc);
                    break;
                case 3:
                    students = updateStudent(students, sc);
                    break;
                case 4:
                    students = deleteStudent(students, sc);
                    break;
                case 5:

                    isFinished = true;
                    break;
                default:
                    System.err.println("Không hợp lệ, hãy chọn lại!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("==== Quản lý sinh viên ====" +
                "\n1. Hiển thị danh sách sinh viên" +
                "\n2. Thêm mới sinh viên" +
                "\n3. Cập nhật thông tin sinh viên theo ID" +
                "\n4. Xoá sinh viên theo ID" +
                "\n5. Thoát");
    }

    public static void renderAllStudent(Student[] students) {
        if (students.length == 0) {
            System.out.println("Không có sinh viên nào!!");
        } else {
            setTimeout(() -> {
                for (int i = 0; i < students.length; i++) {
                    System.out.printf("--- Sinh viên %d ---\n", i + 1);
                    System.out.println(students[i].toString());
                }
            }, students.length * 20);

        }
    }

    public static Student[] addNewStudent(Student[] students, Scanner sc) {
        Student[] addArray = new Student[students.length + 1];
        Student newStudent = new Student();
        if (students.length == 0) {
            System.out.print("Nhập mã sinh viên: ");
            String studentID = sc.nextLine();
            System.out.print("Nhập tên sinh viên: ");
            String studentName = sc.nextLine();
            System.out.print("Nhập giới tính sinh viên: ");
            boolean studentGender = Boolean.parseBoolean(sc.nextLine());
            System.out.print("Nhập tuổi sinh viên: ");
            int studentAge = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập địa chỉ sinh viên: ");
            String studentAddress = sc.nextLine();
            System.out.print("Nhập tên lớp sinh viên: ");
            String studentClassName = sc.nextLine();
            Student student = new Student(studentAge, studentID, studentName, studentClassName, studentAddress, studentGender);
            addArray[0] = student;
            students = addArray;
        } else {
            if (newStudent.inputData(students, sc)) {
                for (int i = 0; i < students.length; i++) {
                    addArray[i] = students[i];
                }
                addArray[students.length] = newStudent;
                students = addArray;
            }
        }
        return students;
    }

    public static Student[] updateStudent(Student[] students, Scanner sc) {
        if (students.length == 0) {
            System.err.println("Hiện tại danh sách không có sinh viên nào");
        } else {
            int index = -1;
            System.out.print("Nhập mã sinh viên cần sửa thông tin: ");
            String studentID = sc.nextLine();
            for (int i = 0; i < students.length; i++) {
                if (students[i].getStudentID().equals(studentID)) {
                    index = i;
                }
            }
            if (index != -1) {
                System.out.println("Đã tìm thấy sinh viên");
                System.out.printf("=== Cập nhật thông tin của sinh viên - %s ===\n", students[index].getStudentID());
                System.out.println("Chọn thông tin cần sửa");
                System.out.println("1. Sửa tên sinh viên");
                System.out.println("2. Sửa tuổi sinh viên");
                System.out.println("3. Sửa địa chỉ sinh viên");
                System.out.println("4. Sửa tên lớp sinh viên");
                int selection = Integer.parseInt(sc.nextLine());
                switch (selection) {
                    case 1:
                        System.out.println("Tên cũ: " + students[index].getStudentName());
                        System.out.print("Nhập tên mới của sinh viên: ");
                        String studentName = sc.nextLine();
                        students[index].setStudentName(studentName);
                        break;
                    case 2:
                        System.out.println("Tuổi cũ: " + students[index].getAge());
                        System.out.print("Nhập tuổi mới của sinh viên: ");
                        int studentAge = Integer.parseInt(sc.nextLine());
                        students[index].setAge(studentAge);
                        break;
                    case 3:
                        System.out.println("Địa chỉ cũ: " + students[index].getAddress());
                        System.out.print("Nhập địa chỉ mới của sinh viên: ");
                        String studentAddress = sc.nextLine();
                        students[index].setStudentName(studentAddress);
                        break;
                    case 4:
                        System.out.println("Tên lớp cũ: " + students[index].getClassName());
                        System.out.print("Nhập tên lớp mới của sinh viên: ");
                        String studentClassName = sc.nextLine();
                        students[index].setStudentName(studentClassName);
                        break;
                    default:
                        System.err.println("Mời chọn lại");
                }
            }else{
                System.out.println("Không tồn tại sinh viên này");
            }
        }
        return students;
    }

    public static Student[] deleteStudent(Student[] students, Scanner sc) {
        if (students.length == 0) {
            System.err.println("Hiện tại danh sách không có sinh viên nào");
        } else {
            int index = -1;
            System.out.println("Nhập mã sinh viên cần xoá: ");
            String studentID = sc.nextLine();
            for (int i = 0; i < students.length; i++) {
                if (students[i].getStudentID().equals(studentID)) {
                    index = i;
                }
            }
            if (index != -1) {
                System.out.println("Đã tìm thấy sinh viên");
                System.out.println("Có đồng ý xoá sinh viên??");
                System.out.println("1. Có");
                System.out.println("2. Không");
                Student[] deleteArray = new Student[students.length - 1];
                int selection = Integer.parseInt(sc.nextLine());
                switch (selection) {
                    case 1:
                        for (int i = 0; i < deleteArray.length; i++) {
                            if (i < index) {
                                deleteArray[i] = students[i];
                            } else {
                                deleteArray[i] = students[i + 1];
                            }
                        }
                        System.out.println("Đã xoá sinh viên");
                        students = deleteArray;
                        break;
                    case 2:
                        break;
                    default:
                        System.err.println("Mời chọn lại");
                }
            } else {
                System.out.println("Không tồn tại sinh viên");
            }
        }
        return students;
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

}
