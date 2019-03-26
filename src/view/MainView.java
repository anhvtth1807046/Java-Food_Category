package view;

import controller.ApplicationController;
import controller.CategoryController;
import controller.FoodController;

import java.util.Scanner;

public class MainView {
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        ApplicationController controller = null;
        GenerateMenu generateMenu = null;
        while (true){
            System.out.println("--------------Trang quản lý nhà hàng---------------");
            System.out.println("1.Món ăn");
            System.out.println("2.Danh mục các món ăn");
            System.out.println("3.Thoát chương trình");
            System.out.println("--------------***********************---------------");
            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1 ==> 3.");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    controller = new FoodController();
                    generateMenu = new FoodView();
                    break;
                case 2:
                    controller = new CategoryController();
                    generateMenu = new CategoryView();
                    break;
                case 3:
                    System.out.println("Hẹn gặp lại bạn.....");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn sai vui lòng chọn từ 1 ==> 4");
                    break;
            }
            if (generateMenu != null){
                generateMenu.menu(controller);
            }



        }



    }
}
