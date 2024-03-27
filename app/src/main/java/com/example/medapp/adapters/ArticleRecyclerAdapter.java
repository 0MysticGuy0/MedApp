package com.example.medapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.R;
import com.example.medapp.models.Article;
import com.example.medapp.utility.MyUtility;

import java.util.List;

//Класс-адаптер для новостей
//27.03.24
//Бычковский В.Р.
public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Article> articels;

    public ArticleRecyclerAdapter(Context context, List<Article> articels) {
        this.articels = articels;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ArticleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news_main_analyses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleRecyclerAdapter.ViewHolder holder, int position) {
        Article article = articels.get(position);
        holder.nameTV.setText(article.getName());
        holder.dateTV.setText(MyUtility.formatDate(article.getDate()));
    }

    @Override
    public int getItemCount() {
        return articels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView icoIV;
        final TextView nameTV,dateTV;
        ViewHolder(View view){
            super(view);
            icoIV = view.findViewById(R.id.itemNews_icoIV);
            nameTV = view.findViewById(R.id.itemNews_textTV);
            dateTV = view.findViewById(R.id.itemNews_dateTV);
        }
    }
}
