package com.example.medapp.utility;

import com.example.medapp.models.Article;
import com.example.medapp.models.Product;
import com.example.medapp.models.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//Класс для внутреннего хранилища в памяти
//26.03.24
//Бычковский В.Р.
public class InMemoryStorage {
    private static List<Article> articles = new ArrayList<>();
    public static List<ProductCategory> categories = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();

    static{
        try {
            categories.add(new ProductCategory("Анализы"));
            categories.add(new ProductCategory("Лекарства"));
            categories.add(new ProductCategory("Устройства"));
            categories.add(new ProductCategory("Продукты"));
            categories.add(new ProductCategory("Другое"));
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public static void setArticles(List<Article> articles) {
        InMemoryStorage.articles = articles;
        articles.sort(
                (a1,a2) -> (a1.getDate().after(a2.getDate()))?-1:0 );
    }

    public static List<Article> getArticles() {
        return articles;
    }
}
