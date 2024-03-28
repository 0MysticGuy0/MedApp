package com.example.medapp.services;

import android.content.Context;

import com.example.medapp.interfaces.UserCardService;
import com.example.medapp.interfaces.UserService;
import com.example.medapp.models.Product;
import com.example.medapp.models.UserCard;
import com.example.medapp.utility.MyUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//Класс-сервис для работы с пользоватлем
//27.03.24
//Бычковский В.Р.
public class UserCardServiceImpl implements UserCardService {

    @Override
    public void saveCart(Context context, List<UserCard> cart) {
        try (FileOutputStream fos = context.openFileOutput(MyUtility.cardsFileName, Context.MODE_PRIVATE);
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
    public List<UserCard> loadCart(Context context) {
        List<UserCard> cards = new ArrayList<>();
        try(FileInputStream fis = context.openFileInput(MyUtility.cardsFileName);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            cards = (ArrayList<UserCard>)ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {

        } catch (IOException ex) {
            System.out.println("ERROR READING LOGIN DATA!!!!!!!!!!!!!\n"+ex.getMessage());
            throw new RuntimeException();
        }
        return cards;
    }
}
