package com.example.medapp.activities.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medapp.R;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

//Класс активити для карточки продукта
//26.03.24
//Бычковский В.Р.
public class ProductCardActivity extends AppCompatActivity {

    public static Product product = null;
    public static User user = null;

    private TextView nameTV;
    private TextView extraInfoTV;
    private TextView numberTV;
    private TextView categoryTV;
    private TextView priceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_card);
        ImageButton backBtn = findViewById(R.id.productCard_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });


        nameTV = findViewById(R.id.productCard_nameTV);
        extraInfoTV = findViewById(R.id.productCard_extraInfoTV);
        numberTV = findViewById(R.id.productCard_numberTV);
        categoryTV = findViewById(R.id.productCard_categoryTV);
        priceTV = findViewById(R.id.productCard_priceTV);

        initializeData();


        ImageButton addBtn = findViewById(R.id.productCard_addBtn);
        addBtn.setOnClickListener(v -> {
            updateProductNumber(true);
        });

        ImageButton removeBtn = findViewById(R.id.productCard_removeBtn);
        removeBtn.setOnClickListener(v -> {
            updateProductNumber(false);
        });
    }

    private void initializeData(){
        if(product == null) throw new RuntimeException("ProductCardActivity: product==null");
        nameTV.setText(product.getName());
        extraInfoTV.setText(product.getExtraInfo());
        numberTV.setText(Integer.toString(product.getNumber()));
        categoryTV.setText(product.getCategory().getName());
        priceTV.setText(MyUtility.formatDoubleToMoney(product.getPrice()));
    }
    private void updateProductNumber(boolean increment){
        if(increment){
            user.addProduct(product);
        }else{
            user.removeProduct(product);
        }
        numberTV.setText(Integer.toString(product.getNumber()));
    }
}