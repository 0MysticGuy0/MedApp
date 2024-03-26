package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.medapp.R;
import com.example.medapp.activities.other.CreateCardActivity;
import com.example.medapp.activities.other.EditCardActivity;
import com.example.medapp.models.UserCard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainAnalysesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    public static UserCard userCard = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_analyses);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mainA_SRL);//обновление по вертикальному свайпу
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                updateData();
            }
        });

        RecyclerView newsRV = findViewById(R.id.mainA_newsRV);

        RecyclerView catalogRV = findViewById(R.id.mainA_catalogRV);

        updateData();

        BottomNavigationView bnv=findViewById(R.id.mainA_bottomNavMenu);//нижнее меню
        bnv.setOnItemSelectedListener(item ->{
            if(item.getItemId()==R.id.profileNavBtn)
            {
                System.out.println("PROFILE TAB");
                Intent intent;
                if(userCard == null){
                    intent= new Intent(this, CreateCardActivity.class);
                }else{
                    intent= new Intent(this, EditCardActivity.class);
                    EditCardActivity.userCard = userCard;
                }
                startActivity(intent);
            }
            return true;
        });
    }

    //обновление данных об акциях/новостях
    private void updateData(){
        System.out.println("Обновление данных...");
    }
}