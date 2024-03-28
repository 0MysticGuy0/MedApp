package com.example.medapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medapp.R;
import com.example.medapp.adapters.ProductRecyclerAdapter;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

//Класс активити для корзины
//26.03.24
//Бычковский В.Р.
public class CartActivity extends AppCompatActivity {
    public static User user = null;
    private TextView sumPriceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageButton backBtn = findViewById(R.id.cart_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainAnalysesActivity.class);
            MainAnalysesActivity.user = user;
            startActivity(intent);
        });

        sumPriceTV = findViewById(R.id.cart_sumPriceTV);
        updateSumData();

        RecyclerView cartRV = findViewById(R.id.cart_cartRV);
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(this,user.getShoppingCart(),user, () -> updateSumData());
        cartRV.setAdapter(adapter);

        initializeData();

        Button clearCartBtn = findViewById(R.id.cart_clearBtn);
        clearCartBtn.setOnClickListener(v -> {
            user.clearCart();
            adapter.setData(user.getShoppingCart());
        });

        Button nextBtn = findViewById(R.id.cart_nextBtn);
        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderProcessingActivity.class);
            OrderProcessingActivity.user = user;
            startActivity(intent);
        });
    }

    private void initializeData(){
        if(user == null) throw new RuntimeException("CartActivity: user==null");
        user.setContext(this);
    }
    private void updateSumData(){
        Double sum = 0.0;
        for(Product p: user.getShoppingCart()){
            sum+=p.getPrice()*p.getNumber();
        }
        sumPriceTV.setText(MyUtility.formatDoubleToMoney(sum));
    }
}