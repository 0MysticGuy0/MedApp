package com.example.medapp.interfaces;

import android.content.Context;

import com.example.medapp.models.Product;
import com.example.medapp.models.UserCard;

import java.util.List;

//Интерфейса для сервиса карты пользователя
//27.03.24
//Бычковский В.Р.
public interface UserCardService {
    public void saveCart(Context context, List<UserCard> cart);
    public List<UserCard> loadCart(Context context);
}
