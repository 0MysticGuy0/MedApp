package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medapp.R;
import com.example.medapp.activities.other.CreateCardActivity;
import com.example.medapp.activities.other.EditCardActivity;
import com.example.medapp.models.User;
import com.example.medapp.models.UserCard;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

//Класс активити для Главной/анализы
//26.03.24
//Бычковский В.Р.
public class MainAnalysesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    public static User user = null;


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

        EditText searchBtn = findViewById(R.id.mainA_searchBtn);
        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainSearchActivity.class);
            MainSearchActivity.user = user;
            startActivity(intent);
        });

        RecyclerView newsRV = findViewById(R.id.mainA_newsRV);

        ImageButton showCatalogBtn = findViewById(R.id.mainA_showCatalogBtn);//всплывающее окно с каталогом продуктов
        showCatalogBtn.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_catalog);
            bottomSheetDialog.setCanceledOnTouchOutside(false);

            ImageButton closeBtn = bottomSheetDialog.findViewById(R.id.bsdCatalog_closeBtn);
            closeBtn.setOnClickListener(bsd_v -> {
                bottomSheetDialog.cancel();
            });
            TextView productsNumTV = bottomSheetDialog.findViewById(R.id.bsdCatalog_productsNumTV);

            ImageButton cartBtn = bottomSheetDialog.findViewById(R.id.bsdCatalog_cartBtn);//переход в корзину
            cartBtn.setOnClickListener(bsd_btn_v -> {
                Intent intent = new Intent(this, CartActivity.class);
                CartActivity.user = user;
                startActivity(intent);
            });

            RecyclerView catalogRV = bottomSheetDialog.findViewById(R.id.bsdCatalog_catalogRV);

            bottomSheetDialog.show();
        });

        updateData();

        BottomNavigationView bnv=findViewById(R.id.mainA_bottomNavMenu);//нижнее меню
        bnv.setOnItemSelectedListener(item ->{
            if(item.getItemId()==R.id.profileNavBtn)
            {
                System.out.println("PROFILE TAB");
                Intent intent;
                if(user.getUserCard() == null){
                    intent= new Intent(this, CreateCardActivity.class);
                }else{
                    intent= new Intent(this, EditCardActivity.class);
                    EditCardActivity.user = user;
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