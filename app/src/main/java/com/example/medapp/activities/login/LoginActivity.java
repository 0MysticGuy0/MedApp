package com.example.medapp.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.R;
import com.example.medapp.interfaces.Encryptor;
import com.example.medapp.utility.ActivityHelper;
import com.example.medapp.activities.main.MainAnalysesActivity;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyPasswordEncryptor;
import com.example.medapp.utility.MyUtility;

//Класс активити для входа в аккаунт
//26.03.24
//Бычковский В.Р.
public class LoginActivity extends AppCompatActivity {
    private Encryptor encryptor = new MyPasswordEncryptor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton backBtn = findViewById(R.id.login_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

        EditText emailET = findViewById(R.id.login_mailET);
        EditText passwdET = findViewById(R.id.login_paswdET);

        TextView noPasswordBtn = findViewById(R.id.login_noPasswordBtn);//пропустить ввод пароля
        noPasswordBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString();
            User user = new User(this, email);
            if(email.length() != 0) {
                Intent intent = new Intent(this, MailCodeActivity.class);
                MailCodeActivity.user = user;
                startActivity(intent);
            }else{
                Toast.makeText(this, "Введите почту!",Toast.LENGTH_LONG).show();
            }
        });

        Button nextBtn = findViewById(R.id.login_nextBtn);//переход дальше
        nextBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString();
            String passwd = passwdET.getText().toString();


            if(email.length() == 0 || passwd.length() == 0){
                Toast toast = Toast.makeText(this, "Не введены пароль или почта!",Toast.LENGTH_LONG);
                toast.show();
            }else if(!MyUtility.isCorrectEmail(email)){
                ActivityHelper.createErrorMessagePopup(this,getResources().getString(R.string.error_emailPattern));
            }
            else{
                passwd = encryptor.encrypt(passwd);

                //todo: запрос на плучение пользователя

                Intent intent = new Intent(this, MainAnalysesActivity.class);
                User user = new User(this,email);
                user.setPassword(passwd);
                MainAnalysesActivity.user = user;//--------------изменитиь---------------
                startActivity(intent);
                finish();
            }
        });
    }
}