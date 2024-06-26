package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.medapp.R;
import com.example.medapp.activities.other.CreateCardActivity;
import com.example.medapp.adapters.ProductRecyclerAdapter;
import com.example.medapp.models.User;
import com.example.medapp.utility.InMemoryStorage;

public class MainSearchActivity extends AppCompatActivity {
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        ImageButton backBtn = findViewById(R.id.mainASearch_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainAnalysesActivity.class);
            MainAnalysesActivity.user = user;
            startActivity(intent);
            finish();
        });

        RecyclerView resRV = findViewById(R.id.mainASearch_resRV);
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(this, InMemoryStorage.getProducts(),user,()->{});
        resRV.setAdapter(adapter);

        EditText searchET = findViewById(R.id.mainASearch_searchET);

        ImageButton searchBtn = findViewById(R.id.mainASearch_searchBtn);
        searchBtn.setOnClickListener(v -> {
            String request = searchET.getText().toString();
            //поиск
            adapter.setData(InMemoryStorage.getProductsByName(request));

        });

    }
}