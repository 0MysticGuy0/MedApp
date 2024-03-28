package com.example.medapp.models;

import android.content.Context;

import com.example.medapp.interfaces.UserCardService;
import com.example.medapp.interfaces.UserService;
import com.example.medapp.services.UserCardServiceImpl;
import com.example.medapp.services.UserServiceImpl;

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
    private UserService service = new UserServiceImpl();
    private UserCardService userCardService = new UserCardServiceImpl();
    private Context context;

    public User(Context context, String email) {
        this.email = email;
        shoppingCart = service.loadCart(context);
        List<UserCard> cards = userCardService.loadCart(context);
        if(cards != null && cards.size()>0)
            userCard = cards.get(0);
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

    public void setContext(Context context) {
        this.context = context;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
        List<UserCard> cards = new ArrayList<>();
        cards.add(userCard);
        userCardService.saveCart(context,cards);
    }
    public void addProduct(Product product){
        if(!shoppingCart.contains(product)){
            shoppingCart.add(product);
        }
        product.incrementNumber();
        updateCartData();
    }
    public void removeProduct(Product product){
        if(shoppingCart.contains(product)){
            int n = product.decrementNumber();
            if(n==0){
                shoppingCart.remove(product);
            }
            updateCartData();
        }
    }
    public void clearCart(){
        shoppingCart.clear();
        updateCartData();
    }
    private void updateCartData(){
        if(context != null) {
            service.saveCart(context,shoppingCart);
        }
    }
}
