package com.example.medapp.models;

public class Product {
    private String name;
    private String extraInfo;
    private ProductCategory category;
    private int number;
    private double price;

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
        return category;
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
