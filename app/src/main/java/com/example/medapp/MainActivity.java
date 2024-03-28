package com.example.medapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.medapp.activities.login.LoginActivity;
import com.example.medapp.activities.login.RegisterActivity;
import com.example.medapp.activities.main.MainAnalysesActivity;
import com.example.medapp.models.Product;
import com.example.medapp.utility.MyPasswordEncryptor;
import com.example.medapp.utility.MyUtility;

//Класс активити для главной страницы входа/регистрации
//26.03.24
//Бычковский В.Р.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button loginBtn = findViewById(R.id.enter_loginBtn);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            //Intent intent = new Intent(this, MainAnalysesActivity.class);
            startActivity(intent);
        });

        Button regBtn = findViewById(R.id.enter_regBtn);
        regBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}