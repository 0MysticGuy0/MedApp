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

//Класс активити для ввода кода из почты
//26.03.24
//Бычковский В.Р.
public class MailCodeActivity extends AppCompatActivity {
    public static String email;
    private String correctCode = "123321";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_code);
        if(email == null)
            throw new RuntimeException("MailCodeActivity: email==null");

        ImageButton homeBtn = findViewById(R.id.mailCode_homeBtn);//возврат на главную входа
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        TextView emailTV = findViewById(R.id.mailCode_emailTV);
        emailTV.setText(email);

        EditText mailCodeET = findViewById(R.id.mailCode_mailCodeET);

        Button nextBtn = findViewById(R.id.mailCode_nextBtn);//переход далее
        nextBtn.setOnClickListener(v -> {
            String code = mailCodeET.getText().toString();
            correctCode=code;//----------------------------убрать-------------
            if(code.length() == 0 ){
                Toast.makeText(this, "Не введён код!",Toast.LENGTH_LONG).show();
            }
            else if(!code.equals(correctCode)){
                Toast.makeText(this, "Введён неверный код!",Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent= new Intent(this, PasswordCreateActivity.class);
                PasswordCreateActivity.email = email;
                startActivity(intent);
            }
        });
    }
}