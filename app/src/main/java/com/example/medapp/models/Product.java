package com.example.medapp.models;

import com.example.medapp.utility.InMemoryStorage;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String extraInfo;
    private ProductCategory category;
    private Long categoryId;
    private int number;
    private double price;

    public Product(String name, String extraInfo, Long categoryId, double price) {
        this.name = name;
        this.extraInfo = extraInfo;
        this.categoryId = categoryId;
        this.category = InMemoryStorage.getCategoryById(categoryId);
        this.number = 0;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public int getNumber() {
        return number;
    }

    public ProductCategory getCategory() {
        if(category == null){
            reloadCategories();
        }
        return category;
    }
    public void reloadCategories(){
        category = InMemoryStorage.getCategoryById(categoryId);
        System.out.println("========\n"+category.getName());
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int incrementNumber(){
        number = Math.min(99,number+1);
        return number;
    }
    public int decrementNumber(){
        number = Math.max(0,number-1);
        return number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
