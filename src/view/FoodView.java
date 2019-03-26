package view;

import controller.ApplicationController;

import java.util.Scanner;

public class FoodView implements GenerateMenu {
    @Override
    public void menu(ApplicationController controller) {
        boolean isLoop = false;
        Scanner scanner = new Scanner(System.in);
        while (!isLoop) {
            System.out.println("----------Quản lý món ăn---------");
            System.out.println("1.Thêm món ăn");
            System.out.println("2.Danh sách món ăn");
            System.out.println("3.Sửa món ăn");
            System.out.println("4.Xoá món ăn");
            System.out.println("5.Tìm kiếm món ăn");
            System.out.println("6.Quay lại");
            System.out.println("-----------**************---------");
            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1 đến 6.");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    controller.create();
                    break;
                case 2:
                    controller.readList();
                    break;
                case 3:
                    controller.update();
                    break;
                case 4:
                    controller.delete();
                    break;
                case 5:
                    controller.find();
                    break;
                case 6:
                    isLoop = true;
                    break;
                default:
                    System.out.println("Lựa chọn sai,vui lòng chọn lại từ 1 đến 6.");
                    break;
            }
        }

    }
}
