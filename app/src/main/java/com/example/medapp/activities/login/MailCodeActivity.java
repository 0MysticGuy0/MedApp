package com.example.medapp.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.MainActivity;
import com.example.medapp.R;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

//Класс активити для ввода кода из почты
//26.03.24
//Бычковский В.Р.
public class MailCodeActivity extends AppCompatActivity {
    public static User user;
    private String correctCode = "123321";
    private TextView emailTV;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_code);
        if(user == null || user.getEmail() == null)
            throw new RuntimeException("MailCodeActivity: email==null");

        ImageButton homeBtn = findViewById(R.id.mailCode_homeBtn);//возврат на главную входа
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        emailTV = findViewById(R.id.mailCode_emailTV);
        EditText mailCodeET = findViewById(R.id.mailCode_mailCodeET);
        TextView timerTV = findViewById(R.id.mailCode_timerTV);


        initializeData();
        startTimer(timerTV);
        correctCode = MyUtility.serverAPI.getMailCode(user.getEmail());

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
                timer.cancel();
                Intent intent= new Intent(this, PasswordCreateActivity.class);
                PasswordCreateActivity.user = user;
                startActivity(intent);
            }
        });
    }

    private void initializeData(){
        emailTV.setText(user.getEmail());
    }
    private void startTimer(TextView timerTV){
        timer = new CountDownTimer(MyUtility.timeToEnterMailCode*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTV.setText(Long.toString(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                startTimer(timerTV);
            }
        };
        timer.start();
    }
}