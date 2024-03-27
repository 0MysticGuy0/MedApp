package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medapp.MainActivity;
import com.example.medapp.R;
import com.example.medapp.adapters.AnalysisProductRecyclerAdapter;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.utility.InMemoryStorage;

import java.util.List;

//Класс активити для оформления заказа(настройки анализов)
//26.03.24
//Бычковский В.Р.
public class OrderProcessingActivity extends AppCompatActivity {
    public static User user = null;
    private List<Product> products;
    private List<Product> analyses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_processing);
        ImageButton backBtn = findViewById(R.id.orderProcessing_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

        TextView sumPriceTV = findViewById(R.id.orderProcessing_sumPriceTV);

        initializeData();

        RecyclerView analysesRV = findViewById(R.id.orderProcessing_analysesRV);
        AnalysisProductRecyclerAdapter analysisAdapter = new AnalysisProductRecyclerAdapter(this, InMemoryStorage.products,user);
        analysesRV.setAdapter(analysisAdapter);

        Button finishBtn = findViewById(R.id.orderProcessing_finishBtn);
        finishBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SuccessfullyPaidActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initializeData(){

    }
}