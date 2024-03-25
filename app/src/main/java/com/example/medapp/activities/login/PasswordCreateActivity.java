package com.example.medapp.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.MainActivity;
import com.example.medapp.R;

//Класс активити для создания пароля
//26.03.24
//Бычковский В.Р.
public class PasswordCreateActivity extends AppCompatActivity {

    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        if(email == null)
            throw new RuntimeException("PasswordCreateActivity: email==null");

        ImageButton homeBtn = findViewById(R.id.paswdCreate_homeBtn);
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        EditText paswdET = findViewById(R.id.paswdCreate_paswdET);
        EditText paswdConfirmET = findViewById(R.id.paswdCreate_paswdConfirmET);

        TextView skipBtn = findViewById(R.id.paswdCreate_skipBtn);
        skipBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button nextBtn = findViewById(R.id.paswdCreate_nextBtn);
        nextBtn.setOnClickListener(v -> {
            String paswd = paswdET.getText().toString();
            String paswdConfirm = paswdConfirmET.getText().toString();
            if(paswd.length() == 0 || paswdConfirm.length() == 0){
                Toast toast = Toast.makeText(this, "Не введён пароль!",Toast.LENGTH_LONG);
                toast.show();
            }else if(!paswd.equals(paswdConfirm)){
                Toast.makeText(this, "Введенные пароли не совпадают!",Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}