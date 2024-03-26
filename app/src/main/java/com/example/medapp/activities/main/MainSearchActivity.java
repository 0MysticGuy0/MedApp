package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.medapp.R;
import com.example.medapp.models.User;

public class MainSearchActivity extends AppCompatActivity {
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        ImageButton backBtn = findViewById(R.id.mainASearch_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

        RecyclerView resRV = findViewById(R.id.mainASearch_resRV);

        EditText searchET = findViewById(R.id.mainASearch_searchET);

        ImageButton searchBtn = findViewById(R.id.mainASearch_searchBtn);
        searchBtn.setOnClickListener(v -> {
            String request = searchET.getText().toString();
            //поиск
        });

    }
}