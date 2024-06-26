package com.example.medapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.R;
import com.example.medapp.activities.main.MainAnalysesActivity;
import com.example.medapp.activities.other.ProductCardActivity;
import com.example.medapp.models.Product;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

import java.util.List;

//Класс-адаптер для каталога товаров
//27.03.24
//Бычковский В.Р.
public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>{

    private User user;
    private final LayoutInflater inflater;
    private List<Product> products;
    private final MyUtility.onChangeListener onChangeListener;

    public ProductRecyclerAdapter(Context context, List<Product> products, User user, MyUtility.onChangeListener onChangeListener) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
        this.user = user;
        this.onChangeListener = onChangeListener;
    }
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductRecyclerAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.nameTV.setText(product.getName());
        holder.priceTV.setText(MyUtility.formatDoubleToMoney(product.getPrice()));
        holder.numberTV.setText(Integer.toString(product.getNumber()));
        holder.addBtn.setOnClickListener(v -> {
            user.addProduct(product);
            holder.numberTV.setText(Integer.toString(product.getNumber()));
            onChangeListener.changed();
        });
        holder.removeBtn.setOnClickListener(v -> {
            user.removeProduct(product);
            holder.numberTV.setText(Integer.toString(product.getNumber()));
            onChangeListener.changed();
        });
        holder.root.setOnClickListener(v -> {
            Intent intent = new Intent(inflater.getContext(), ProductCardActivity.class);
            ProductCardActivity.user = user;
            ProductCardActivity.product = product;
            inflater.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTV, numberTV, priceTV;
        final ImageButton addBtn,removeBtn;
        final LinearLayout root;
        
        ViewHolder(View view){
            super(view);
            nameTV = view.findViewById(R.id.itemProduct_nameTV);
            numberTV = view.findViewById(R.id.itemProduct_numberTV);
            priceTV = view.findViewById(R.id.itemProduct_priceTV);
            addBtn =  view.findViewById(R.id.itemProduct_addBtn);
            removeBtn =  view.findViewById(R.id.itemProduct_removeBtn);
            root = view.findViewById(R.id.itemProduct_root);
        }
    }
}
