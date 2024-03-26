package com.example.medapp.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.medapp.MainActivity;
import com.example.medapp.R;
import com.example.medapp.models.User;

//Класс активити для регистрации(ввода имейла)
//26.03.24
//Бычковский В.Р.
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton backBtn = findViewById(R.id.register_backBtn);// кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

        EditText emailET = findViewById(R.id.register_mailET);

        Button nextBtn = findViewById(R.id.register_nextBtn);//переход дальше
        nextBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString();

            if(email.length() == 0 ){
                Toast toast = Toast.makeText(this, "Не введена почта!",Toast.LENGTH_LONG);
                toast.show();
            }else{
                Intent intent = new Intent(this, MailCodeActivity.class);
                MailCodeActivity.user = new User(email);
                startActivity(intent);
            }
        });
    }
}