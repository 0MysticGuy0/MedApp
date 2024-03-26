package com.example.medapp.models;

import java.util.ArrayList;
import java.util.List;

//Класс активити для модели пользователя
//26.03.24
//Бычковский В.Р.
public class User {
    private String email;
    private String password;
    private UserCard userCard;
    private List<Product> shoppingCart;

    public User(String email) {
        this.email = email;
        shoppingCart = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserCard getUserCard() {
        return userCard;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }
    public void addProduct(Product product){
        if(!shoppingCart.contains(product)){
            shoppingCart.add(product);
        }
        product.incrementNumber();
    }
    public void removeProduct(Product product){
        if(shoppingCart.contains(product)){
            int n =product.decrementNumber();
            if(n==0){
                shoppingCart.remove(product);
            }
        }
    }
}
