package entity;

import java.util.ArrayList;
import java.util.UUID;

public class Food {
    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    private String code;

    public Food() {
    }

    public Food(String name, String description, double price, String categoryId, String code) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.code = code;
    }

    public String printList(ArrayList<Food> foods, ArrayList<Category> categories) {
        String content = null;
        if(!foods.isEmpty()){
            String categoryName = "";
            content = "Tổng số món ăn là:" + foods.size() + "\t \t \t \t \t \t \t \t \t \t Danh sách món ăn\n";
            content += String.format(" %-5s|", "STT");
            content += String.format(" %-45s|", "ID");
            content += String.format(" %-20s|", "Name");
            content += String.format(" %-20s|", "Code");
            content += String.format(" %-25s|", "Category");
            content += String.format(" %-30s|", "Description");
            content += "------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------\n";
            for (int i = 0; i < foods.size(); i++) {

                if (!categories.isEmpty()){
                    for (Category category : categories){
                        if (foods.get(i).getCategoryId().equals(category.getCode())){
                            categoryName = category.getName();
                        }else {
                            categoryName = "Không có danh mục";
                        }
                    }
                }
                content += String.format(" %-5d| ", i + 1)
                        + String.format(" %-44s| ", foods.get(i).getId())
                        + String.format(" %-19s| ", foods.get(i).getName())
                        + String.format(" %-19s| ", foods.get(i).getCode())
                        + String.format(" %-24s| ", categoryName)
                        + String.format(" %-30s", foods.get(i).getDescription()) + "\n";
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------\n");
            }
        }
        return content;
    }


    public  Food findFood(ArrayList<Food> foods, String categoryId){
        Food food = null;
        if (!foods.isEmpty()){
            for (Food item : foods){
                if (categoryId.equals(item.getCategoryId())){
                    food = new Food(item.getName(), item.getDescription(), item.getPrice(), item.getCategoryId(), item.getCode());
                }
            }
        }
        return food;
    }
    public String toString(ArrayList<Category> categories, String code) {
        String categoryName = "";

        if (!categories.isEmpty()){
            for (Category category : categories){
                if (code.equals(category.getCode())){
                    categoryName = category.getName();
                }
            }
        }else{
            categoryName = "Không có danh mục";
        }

        String content = String.format(" %-45s|", " ID")
                + String.format(" %-20s|", " Tên")
                + String.format(" %-20s|", " Mã")
                + String.format(" %-25s", " Danh mục")
                + String.format(" %-30s", " Mô tả") + "\n";
        content += "------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------\n";

        content += String.format(" %-44s| ", this.id)
                + String.format(" %-19s| ", this.name)
                + String.format(" %-19s| ", this.code)
                + String.format(" %-24s| ", categoryName)
                + String.format(" %-30s", this.description) + "\n";
        content +="------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------\n";
        return content;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
