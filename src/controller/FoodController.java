package controller;

import entity.Category;
import entity.Food;
import view.MainView;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;


public class FoodController implements ApplicationController{
    public static ArrayList<Food> foodArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    @Override
    public void create() {
        if (!CategoryController.categoryArrayList.isEmpty()){
            boolean isLoop = false;
            int count = 0;
            Category category = new Category();
            while (!isLoop) {
                System.out.printf("Món ăn thứ: %d \n", count + 1);
                System.out.println("--------------*--------------");
                System.out.println("Nhập mã món ăn: ");
                String code = scanner.nextLine();
                System.out.println("Nhập tên món ăn: ");
                String name = scanner.nextLine();
                System.out.println("Nhập mô tả món ăn: ");
                String description = scanner.nextLine();
                System.out.println("Nhập giá món ăn: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                String content = category.tempoListCategory(CategoryController.categoryArrayList);
                if (content.isEmpty()){
                    content = "Hiện không có danh mục nào!";
                }
                System.out.println(content);
                System.out.println("Nhập mã danh mục: ");
                String codeCategory = scanner.nextLine();
                category.setCode(codeCategory);

                boolean checkExistCode = category.checkExistCode(CategoryController.categoryArrayList);
                codeCategory = getString(codeCategory, category, checkExistCode);
                Food food = new Food(name, description, price, category.getCode(), code);
//                System.out.println(food);
                foodArrayList.add(food);
//                System.out.println(Arrays.toString(foodArrayList.toArray()));
//                return;
//
                System.out.println("Thêm món ăn `" + name + "` thành công!");
                System.out.println("Ấn enter để tiếp tục. Nhập HUY để trở ra màn hình chính.");
                String choice = scanner.nextLine();

                if (choice.equals("HUY")) {
                    isLoop = true;
                } else {
                    System.out.println("Tiếp tục thêm món ăn! \n");
                }
                count++;

            }
        }else{
            System.out.println("Vui lòng tạo danh mục món ăn trước khi tạo món ăn!");
            new MainView().menu();
            return;
        }
    }

    @Override
    public void readList() {
        String content ="";
        if (!foodArrayList.isEmpty() && !CategoryController.categoryArrayList.isEmpty()){
            content = new Food().printList(foodArrayList, CategoryController.categoryArrayList);
        }

        if (content.isEmpty()){
            content = "Hiện không có món ăn nào!";
        }
        System.out.println(content);
        System.out.println("<==== Ấn enter để quay lại.");
        scanner.nextLine();
    }


    @Override
    public void update() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = scanner.nextLine();
            Food foodResult = new Food().findFood(foodArrayList, code);
            if (!CategoryController.categoryArrayList.isEmpty()){
                while (true){
                    System.out.println(foodResult.toString(CategoryController.categoryArrayList, foodResult.getCategoryId()));
                    System.out.println("Bạn có chắc muốn sửa món ăn: " + foodResult.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại\n");
                    System.out.println("Nhập lựa chọn của bạn");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice){
                        case 1:
                            System.out.println("Tên món ăn mới(ấn S để bỏ qua không cập nhật tên món ăn): ");
                            String nameUpdate = scanner.nextLine();
                            System.out.println("Mô tả món ăn mới(ấn S để bỏ qua không cập nhật mô tả món ăn): ");
                            String descriptionUpdate = scanner.nextLine();
                            System.out.println("Giá món ăn mới(ấn 0 để bỏ qua không cập nhật giá món ăn): ");
                            double priceUpdate = scanner.nextDouble();
                            scanner.nextLine();
                            String content = new Category().tempoListCategory(CategoryController.categoryArrayList);
                            System.out.println(content);
                            System.out.println("Mã danh mục mới(ấn S để bỏ qua không cập nhật mã danh mục món ăn): ");
                            String categoryIdUpdate = scanner.nextLine();

                            if (nameUpdate.equals("S") && descriptionUpdate.equals("S") && priceUpdate == 0 && categoryIdUpdate.equals("S")){
                                System.out.println("Bịp à ko update cái gì thì vào đây làm gì?");
                                return;
                            }

                            if (descriptionUpdate.equals("S")) descriptionUpdate = foodResult.getDescription();
                            if (nameUpdate.equals("S")) nameUpdate = foodResult.getName();
                            if (priceUpdate == 0) priceUpdate = foodResult.getPrice();
                            if (categoryIdUpdate.equals("S")) categoryIdUpdate = foodResult.getCategoryId();

                            Category category = new Category();
                            category.setCode(categoryIdUpdate);
                            boolean checkExistCode = category.checkExistCode(CategoryController.categoryArrayList);
                            categoryIdUpdate = getString(categoryIdUpdate, category, checkExistCode);

                            Food food = new Food(nameUpdate, descriptionUpdate, priceUpdate, categoryIdUpdate, foodResult.getCode());

                            for(Food index : foodArrayList){
                                if (food.getCode().equals(index.getCode())){
                                    index.setName(food.getName());
                                    index.setDescription(food.getDescription());
                                    index.setPrice(food.getPrice());
                                    index.setCode(food.getCode());
                                }
                            }
                            System.out.println("Sửa thành công!");
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Lựa chọn sai, vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            }else{
                System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
                return;
            }
        }else{
            System.out.println("Hiện tại không có món ăn nào!");
        }
    }

    private String getString(String categoryIdUpdate, Category category, boolean checkExistCode) {
        String content;
        if (!checkExistCode){
            while (!checkExistCode){
                content = category.tempoListCategory(CategoryController.categoryArrayList);
                System.out.println(content);
                System.out.println("Mã danh mục không tồn tại, vui lòng nhập lại: ");
                System.out.println("Nhập mã danh mục");
                categoryIdUpdate = scanner.nextLine();
                category.setCode(categoryIdUpdate);
                checkExistCode = category.checkExistCode(CategoryController.categoryArrayList);
            }
        }
        return categoryIdUpdate;
    }

    @Override
    public void delete() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = scanner.nextLine();
            Food foodResult = new Food().findFood(foodArrayList, code);
            if (!CategoryController.categoryArrayList.isEmpty()){
                System.out.println(foodResult.toString(CategoryController.categoryArrayList, foodResult.getCategoryId()));
                while (true){
                    System.out.println("Bạn có chắc muốn xóa món ăn: " + foodResult.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            for (int i = 0; i < foodArrayList.size(); i++) {
                                if (foodResult.getCode().equals(foodArrayList.get(i).getCode())){
                                    foodArrayList.remove(i);
                                }
                            }
                            System.out.println("Xóa món ăn `" + foodResult.getName() +"` thành công!");
                            System.out.println("<=== Ấn enter để ra màn hình chính.");
                            scanner.nextLine();
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Lựa chọn sai, vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            }else{
                System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
                return;
            }
        }else{
            System.out.println("Hiện tại không có món ăn nào!");
        }
    }


    @Override
    public void find() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = scanner.nextLine();
            Food food = new Food();
            Food foodResult = food.findFood(foodArrayList, code);
            if (!CategoryController.categoryArrayList.isEmpty()){
                System.out.println("Kết quả tìm kiếm mới mã món ăn là: " + code);
                System.out.println(foodResult.toString(CategoryController.categoryArrayList, foodResult.getCategoryId()));
            }else{
                System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
                return;
            }
        }else {
            System.out.println("Hiện không có món ăn nào!");
        }
    }
}



