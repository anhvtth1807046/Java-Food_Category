package controller;

import entity.Category;
import entity.Food;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryController implements ApplicationController {

    public static ArrayList<Category> categoryArrayList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void create() {
        boolean toString = false;
        int cont = 0;
        while (!toString) {
            System.out.printf("Danh mục thứ: %d \n", cont + 1);
            System.out.println("--------------***-------------");
            System.out.println("Nhập mã danh mục: ");
            String code = scanner.nextLine();
            System.out.println("Nhập tên danh mục: ");
            String name = scanner.nextLine();
            System.out.println("Nhập thông tin danh mục: ");
            String description = scanner.nextLine();
            System.out.println("--------------***--------------");

            Category category = new Category(code, name, description);
            categoryArrayList.add(category);

            System.out.println("Thêm danh mục thành công!\n");
            System.out.println("Ấn enter để tiếp tục. Nhập back để trở ra màn hình danh mục.");
            String choice = scanner.nextLine();

            if (choice.equals("back")) {
                toString = true;
            } else {
                System.out.println("Tiếp tục thêm danh mục! \n");
            }
            cont++;

        }
    }

    @Override
    public void readList() {
        if (!categoryArrayList.isEmpty()){

            System.out.println("Tổng số danh mục: " + categoryArrayList.size() + "\t \t \t \t \t \t \t \t \t DANH SÁCH DANH MỤC\n");
            System.out.println("------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
            System.out.println(String.format(" %-9s|", "STT")
                    + String.format(" %-45s|", " ID")
                    + String.format(" %-20s|", " Tên")
                    + String.format(" %-20s|", " Mã")
                    + String.format(" %-30s", " Mô tả"));
            System.out.println("------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
            for (int i = 0; i < categoryArrayList.size(); i++) {
                System.out.println(String.format(" %-9d| ", i + 1)
                        + String.format(" %-44s| ", categoryArrayList.get(i).getId())
                        + String.format(" %-19s| ", categoryArrayList.get(i).getName())
                        + String.format(" %-19s| ", categoryArrayList.get(i).getCode())
                        + String.format(" %-30s", categoryArrayList.get(i).getDescription()));
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }else{
            System.out.println("Hiện không có danh mục nào!");
        }
        System.out.println("<====  Ấn enter để quay lại!");
        scanner.nextLine();
    }

    @Override
    public void update() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = scanner.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true){
                    System.out.println(category.displayOneCategory());
                    System.out.printf("Bạn có chắc muốn sửa danh mục: %s \n", category.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choiceRemove = scanner.nextInt();
                    scanner.nextLine();
                    switch (choiceRemove) {
                        case 1:
                            System.out.println("Tên danh mục mới(ấn S để bỏ qua không cập nhật tên danh mục): ");
                            String nameUpdate = scanner.nextLine();

                            System.out.println("Mô tả danh mục mới(ấn S để bỏ qua không cập nhật mô tả danh mục): ");
                            String descriptionUpdate = scanner.nextLine();

                            if (nameUpdate.equals("S") && descriptionUpdate.equals("S")){
                                System.out.println("Bịp à ko update cái gì thì vào đây làm gì?");
                                return;
                            }

                            if (descriptionUpdate.equals("S")) descriptionUpdate = category.getDescription();
                            if (nameUpdate.equals("S")) nameUpdate = category.getName();

                            Category categoryUpdate = new Category(category.getCode(), nameUpdate, descriptionUpdate);

                            for (Category item : categoryArrayList) {
                                if (category.getCode().equals(item.getCode())){
                                    item.setCode(categoryUpdate.getCode());
                                    item.setName(categoryUpdate.getName());
                                    item.setDescription(categoryUpdate.getDescription());
                                }
                            }

                            System.out.println("Sửa thành công!");
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            } else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }

    }

    @Override
    public void delete() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = scanner.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true){
                    System.out.println(category.displayOneCategory());
                    System.out.printf("Bạn có chắc muốn xóa danh mục: %s \n", category.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choiceRemove = scanner.nextInt();

                    switch (choiceRemove) {
                        case 1:
                            if (!FoodController.foodArrayList.isEmpty()){
                                for (Food itemFood : FoodController.foodArrayList){
                                    if (category.getCode().equals(itemFood.getCategoryId())){
                                        System.out.println("Hiện đang có món ăn thuộc danh mục này, nên không thể xóa danh mục này!");
                                        System.out.println("===>> Vui lòng xóa món ăn đó trước!");
                                        return;
                                    }
                                }
                            }
                            for (int i = 0; i < categoryArrayList.size(); i++) {
                                if (!category.getCode().isEmpty()){
                                    if (category.getCode().equals(categoryArrayList.get(i).getCode())){
                                        categoryArrayList.remove(i);
                                    }
                                }
                            }
                            System.out.println("Xóa thành công!");
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            } else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }

    }

    @Override
    public void find() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = scanner.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                System.out.println(category.displayOneCategory());
                System.out.println("<======= Ấn enter để quay lại.");
                scanner.nextLine();
            } else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }

    }
}
