package com.example.medapp.utility;

import com.example.medapp.models.Article;
import com.example.medapp.models.Product;
import com.example.medapp.models.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Класс для внутреннего хранилища в памяти
//26.03.24
//Бычковский В.Р.
public class InMemoryStorage {
    public static List<Article> articles = new ArrayList<>();
    public static List<ProductCategory> categories = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();

    static{
        try {
            articles.add(new Article("Чемпионат начался", LocalDate.of(2024,3,26)));
            articles.add(new Article("Чемпионат продолжается", LocalDate.of(2024,3,26)));
            articles.add(new Article("Боль в коленях ушла, когда я использовал...", LocalDate.of(2024,3,27)));
            articles.add(new Article("Проводятся ставки на победителей", LocalDate.of(2024,3,27)));
            articles.sort(
                    (a1,a2) -> (a1.getDate().isAfter(a2.getDate()))?-1:0 );

            categories.add(new ProductCategory("Анализы"));
            categories.add(new ProductCategory("Лекарства"));
            categories.add(new ProductCategory("Устройства"));
            categories.add(new ProductCategory("Продукты"));
            categories.add(new ProductCategory("Другое"));
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
