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
import com.example.medapp.adapters.ArticleRecyclerAdapter;
import com.example.medapp.adapters.ProductsInCategoryRecyclerAdapter;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.models.UserCard;
import com.example.medapp.utility.InMemoryStorage;
import com.example.medapp.utility.MyUtility;
import com.example.medapp.utility.ServerAPIHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

//Класс активити для Главной/анализы
//26.03.24
//Бычковский В.Р.
public class MainAnalysesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    public static User user = null;

    private ArticleRecyclerAdapter articleAdapter;
    private ProductsInCategoryRecyclerAdapter catalogAdapter;
    private ServerAPIHelper.APIrequestResponce getArticlesResponce;
    private ServerAPIHelper.APIrequestResponce getCategoriesResponce;
    private TextView productsNumTV;
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

        EditText searchBtn = findViewById(R.id.mainA_searchBtn); //поиск
        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainSearchActivity.class);
            MainSearchActivity.user = user;
            startActivity(intent);
            finish();
        });

        RecyclerView newsRV = findViewById(R.id.mainA_newsRV);
        catalogAdapter = new ProductsInCategoryRecyclerAdapter(this,InMemoryStorage.getCategories(),InMemoryStorage.getProducts(), user, () -> {
            //обновления числа элементов возле кнопки карзины
            int n = 0;
            for(Product p:user.getShoppingCart()){
                n+=p.getNumber();
            }
            productsNumTV.setText(Integer.toString(n));
        });
        articleAdapter = new ArticleRecyclerAdapter(this, InMemoryStorage.getArticles());
        newsRV.setAdapter(articleAdapter);
        getArticlesResponce = (success) -> {
            if(success){
                newsRV.post(() -> {
                    System.out.println("+__+_+_+_+_+_+_");
                    articleAdapter.setData(InMemoryStorage.getArticles());
                });
            }
        };

        MyUtility.serverAPI.getAllArticles(getArticlesResponce);

        ImageButton showCatalogBtn = findViewById(R.id.mainA_showCatalogBtn);//всплывающее окно с каталогом продуктов
        showCatalogBtn.setOnClickListener(v -> {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_catalog);
            bottomSheetDialog.setCanceledOnTouchOutside(false);

            productsNumTV = bottomSheetDialog.findViewById(R.id.bsdCatalog_productsNumTV);

            ImageButton closeBtn = bottomSheetDialog.findViewById(R.id.bsdCatalog_closeBtn);
            closeBtn.setOnClickListener(bsd_v -> {
                bottomSheetDialog.cancel();
            });


            ImageButton cartBtn = bottomSheetDialog.findViewById(R.id.bsdCatalog_cartBtn);//переход в корзину
            cartBtn.setOnClickListener(bsd_btn_v -> {
                Intent intent = new Intent(this, CartActivity.class);
                CartActivity.user = user;
                startActivity(intent);
            });

            RecyclerView catalogRV = bottomSheetDialog.findViewById(R.id.bsdCatalog_catalogRV);
            catalogRV.setAdapter(catalogAdapter);
            getCategoriesResponce = ((success) -> {
                if(success){
                    catalogRV.post(() -> {
                        System.out.println("+_+_+_+_categories_+_+_+_");
                        catalogAdapter.setData(InMemoryStorage.getCategories(), InMemoryStorage.getProducts());
                    });
                }
            });
            MyUtility.serverAPI.getAllCategories(getCategoriesResponce);

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
        MyUtility.serverAPI.getAllArticles(getArticlesResponce);
    }
}