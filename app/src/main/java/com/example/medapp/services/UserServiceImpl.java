package com.example.medapp.services;

import android.content.Context;

import com.example.medapp.interfaces.UserService;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Класс-сервис для работы с пользоватлем
//27.03.24
//Бычковский В.Р.
public class UserServiceImpl implements UserService {

    @Override
    public void saveCart(Context context, List<Product> cart) {
        try (FileOutputStream fos = context.openFileOutput(MyUtility.cartFileName, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cart);
            System.out.println("\n======================================SAVED CART DATA=======================================\n");
        } catch (FileNotFoundException ex) {
            System.out.println("!!!!!!!!!!!!!!!\nNO FILE TO SAVE LOGIN DATA!\n!!!!!!!!!!!!!!!!!");
            throw new RuntimeException();
        } catch (IOException ex) {
            System.out.println("ERROR SAVING LOGIN DATA!!!!!!!!!!!!!");
            throw new RuntimeException();
        }
    }

    @Override
    public List<Product> loadCart(Context context) {
        List<Product> cart = new ArrayList<>();
        try(FileInputStream fis = context.openFileInput(MyUtility.cartFileName);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            cart = (ArrayList<Product>)ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {

        } catch (IOException ex) {
            System.out.println("ERROR READING LOGIN DATA!!!!!!!!!!!!!\n"+ex.getMessage());
            throw new RuntimeException();
        }
        return cart;
    }
}
