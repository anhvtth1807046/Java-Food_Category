package entity;

import java.util.ArrayList;
import java.util.UUID;

public class Category {
    private String id;
    private String code;
    private String name;
    private String description;

    public Category() {
    }

    public Category(String code,String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public  Category findCategory(ArrayList<Category> categories, String code){
        Category category = new Category();
        if (!categories.isEmpty()){
            for (Category category1 : categories) {
                if (code.equals(category1.code)) {
                    category =  category1;
                    break;
                }
                category = null;
            }
        }
        return category;
    }

    public String displayOneCategory() {
        String content = "";
        content += "----------------------------------------------------------------------------------------------------------------------------------------------------------------- \n";
        content += String.format(" %-45s|", " ID") + String.format(" %-20s|", " Tên") + String.format(" %-20s|", " Mã") + String.format(" %-30s", " Mô tả") + "\n";
        content += "----------------------------------------------------------------------------------------------------------------------------------------------------------------- \n";
        content += String.format(" %-45s| ", this.id) + String.format(" %-19s| ", this.name) + String.format(" %-19s| ", this.code) + String.format(" %-30s", this.description) + "\n";
        content += "-----------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        return content;
    }

    public String tempoListCategory(ArrayList<Category> categories) {
        String content = null;
        if (!categories.isEmpty()){
            content = String.format("%10s|", "Mã") + String.format("%20s", "Tên danh mục") + "\n";
            content += "-----------------------------------------------\n";
            int count = 2;
            for (int i = 0; i < categories.size(); i++) {
                content += String.format("%10s|", categories.get(i).getCode()) + String.format("%20s", categories.get(i).getName());
                content += ("\n");
                content += "-----------------------------------------------\n";
            }
        }
        return content;
    }

    public boolean checkExistCode(ArrayList<Category> categories) {
        for (Category item : categories) {
            if (this.code.equals(item.getCode())) return true;
        }
        return false;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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



}

