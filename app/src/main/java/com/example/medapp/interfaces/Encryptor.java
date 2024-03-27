package com.example.medapp.interfaces;

//интерфейс для шифровщика
//27.03.24
//Бычковский В.Р.
public interface Encryptor {
    String encrypt(String text);
    String decrypt(String text);
}
