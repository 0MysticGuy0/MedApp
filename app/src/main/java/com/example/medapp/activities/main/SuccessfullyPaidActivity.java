package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.medapp.R;
import com.example.medapp.models.User;

public class SuccessfullyPaidActivity extends AppCompatActivity {
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_paid);

        Button toMainBtn = findViewById(R.id.success_toMainBtn);
        toMainBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainAnalysesActivity.class);
            MainAnalysesActivity.user = user;
            startActivity(intent);
            finish();
        });
    }
}