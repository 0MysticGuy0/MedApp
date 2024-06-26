package com.example.medapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Класс активити для модели категории товара
//26.03.24
//Бычковский В.Р.
public class ProductCategory implements Serializable {
    private  Long id;
    private String name;
    private static List<ProductCategory> allCategories = new ArrayList<>();

    public ProductCategory(String name) {
        this.name = name;
        allCategories.add(this);
    }

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<ProductCategory> getAllCategories(){
        return allCategories;
    }
    public static ProductCategory getCategoryById(Long id){
        return allCategories.stream().filter(c -> c.id.equals(id)).findFirst().orElse(null);
    }
}
