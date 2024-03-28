package com.example.medapp.utility;

import com.example.medapp.models.Article;
import com.example.medapp.models.Product;
import com.example.medapp.models.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//Класс для внутреннего хранилища в памяти
//26.03.24
//Бычковский В.Р.
public class InMemoryStorage {
    private static List<Article> articles = new ArrayList<>();
    private static List<ProductCategory> categories = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();

    static{
        try {
            MyUtility.serverAPI.getAllCategories(success -> {
                if(success){
                    products.add(new Product("Анализ крови","полный анализ крови.",1L,1000));
                    products.add(new Product("Узи желудка","узи желудка.",1L,400));
                    products.add(new Product("ЭКГ","Электрокардиограмма.",1L,300));
                    products.add(new Product("Цитромон","В виде таблеток",2L,100));
                    products.add(new Product("Канефрон","Таблетки для поддержки работы почек, 60 шт.",2L,700));
                    products.add(new Product("Активированный уголь","",2L,100));
                    products.add(new Product("Электрический градусник","",3L,500));
                    products.add(new Product("Клизма","Резиновая.",3L,150));
                    products.add(new Product("Ингалятор","",3L,500));
                    products.add(new Product("Гематоген, 50 гр","",4L,75));
                    products.add(new Product("Вода, 0.5 л.","Питьевая вода.",4L,75));
                }
            });


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

    public static List<ProductCategory> getCategories() {
        return categories;
    }

    public static void setCategories(List<ProductCategory> categories) {
        InMemoryStorage.categories = categories;
        products.forEach(Product::reloadCategories);
    }
    public static ProductCategory getCategoryById(Long id){
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        InMemoryStorage.products = products;
    }
    public static List<Product> getProductsByName(String name){
        return products.stream().filter(p -> p.getName().contains(name)).collect(Collectors.toList());
    }
}
