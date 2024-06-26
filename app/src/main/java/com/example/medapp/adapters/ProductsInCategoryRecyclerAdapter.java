package com.example.medapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.R;
import com.example.medapp.models.Article;
import com.example.medapp.models.Product;
import com.example.medapp.models.ProductCategory;
import com.example.medapp.models.User;
import com.example.medapp.utility.MyUtility;

import java.util.List;
import java.util.stream.Collectors;

//Класс-адаптер для каталога товаров
//27.03.24
//Бычковский В.Р.
public class ProductsInCategoryRecyclerAdapter extends RecyclerView.Adapter<ProductsInCategoryRecyclerAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private  List<Product> products;
    private List<ProductCategory> categories;
    private User user;
    private final MyUtility.onChangeListener onChangeListener;

    public ProductsInCategoryRecyclerAdapter(Context context, List<ProductCategory> categories, List<Product> products, User user, MyUtility.onChangeListener onChangeListener) {
        this.products = products;
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
        this.user = user;
        this.onChangeListener = onChangeListener;
    }
    @Override
    public ProductsInCategoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_products_in_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsInCategoryRecyclerAdapter.ViewHolder holder, int position) {
        ProductCategory category = categories.get(position);
        List<Product> productsInCategory = products.stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
        holder.nameTV.setText(category.getName());
        ProductRecyclerAdapter productAdapter = new ProductRecyclerAdapter(inflater.getContext(), productsInCategory, user, onChangeListener);
        System.out.println("safdasfasfsafaf \n\n\n"+productsInCategory.size()+" "+category.getName());
        holder.productsRV.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setData(List<ProductCategory> categories,List<Product> products) {
        this.categories = categories;
        System.out.println("1010010110 : "+categories.size());
        this.products = products;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTV;
        final RecyclerView productsRV;
        ViewHolder(View view){
            super(view);
            nameTV = view.findViewById(R.id.productsInCat_categoryNameTV);
            productsRV = view.findViewById(R.id.productsInCat_productsRV);
        }
    }
}
