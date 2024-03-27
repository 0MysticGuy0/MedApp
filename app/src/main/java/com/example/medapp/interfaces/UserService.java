package com.example.medapp.interfaces;

import android.content.Context;

import com.example.medapp.models.Product;
import com.example.medapp.models.User;

import java.util.List;

//Интерфейса для сервиса пользователя
//27.03.24
//Бычковский В.Р.
public interface UserService {
    public void saveCart(Context context, List<Product> cart);
    public List<Product> loadCart(Context context);
}
