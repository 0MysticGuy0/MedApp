package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medapp.R;
import com.example.medapp.models.User;

public class CartActivity extends AppCompatActivity {
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageButton backBtn = findViewById(R.id.cart_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

        TextView sumPriceTV = findViewById(R.id.cart_sumPriceTV);
        RecyclerView cartRV = findViewById(R.id.cart_cartRV);

        initializeData();

        Button clearCartBtn = findViewById(R.id.cart_clearBtn);

        Button nextBtn = findViewById(R.id.cart_nextBtn);
        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SuccessfullyPaidActivity.class);
            SuccessfullyPaidActivity.user = user;
            startActivity(intent);
            finish();
        });
    }

    private void initializeData(){
        if(user == null) throw new RuntimeException("CartActivity: user==null");
    }
}