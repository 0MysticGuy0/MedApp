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
import com.example.medapp.activities.other.CreateCardActivity;
import com.example.medapp.interfaces.Encryptor;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyPasswordEncryptor;

//Класс активити для создания пароля
//26.03.24
//Бычковский В.Р.
public class PasswordCreateActivity extends AppCompatActivity {

    public static User user;
    private Encryptor encryptor = new MyPasswordEncryptor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        if(user == null || user.getEmail() == null)
            throw new RuntimeException("PasswordCreateActivity: email==null");

        ImageButton homeBtn = findViewById(R.id.paswdCreate_homeBtn);//переход на главную входа
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        EditText paswdET = findViewById(R.id.paswdCreate_paswdET);
        EditText paswdConfirmET = findViewById(R.id.paswdCreate_paswdConfirmET);

        TextView skipBtn = findViewById(R.id.paswdCreate_skipBtn);//пропустить ввод пароля
        skipBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateCardActivity.class);
            CreateCardActivity.user = user;
            startActivity(intent);
            finish();
        });

        Button nextBtn = findViewById(R.id.paswdCreate_nextBtn);//переход дальше
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
                user.setPassword(encryptor.encrypt(paswd));
                Intent intent = new Intent(this, CreateCardActivity.class);
                CreateCardActivity.user = user;
                startActivity(intent);
                finish();
            }
        });
    }
}