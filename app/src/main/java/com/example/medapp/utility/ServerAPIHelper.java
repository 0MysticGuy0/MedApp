package com.example.medapp.utility;

import android.os.AsyncTask;

import com.example.medapp.models.Article;
import com.example.medapp.models.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Класс для обращений к серверу
//28.03.24
//Бычковский В.Р.
public class ServerAPIHelper {
    private final String SERVER_ADDR = "http://10.17.92.15:8080/api/v1/med";
    private final String USER_AGENT = "MedApp";
    public String getMailCode(String email){
        return "123321";
    }
    public void getAllArticles(APIrequestResponce responce){//для запроса всех новостей с сервера
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-=-=-=-=-==-=-=--=-==-");
                    URL obj = new URL(SERVER_ADDR + "/articles");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    //con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", USER_AGENT);
                    int responseCode = con.getResponseCode();
                    System.out.println("GET article Response Code :: " + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                            System.out.println(inputLine);
                        }
                        in.close();

                        ObjectMapper objectMapper = new ObjectMapper();
                        Article[] articles = objectMapper.readValue(response.toString(), Article[].class);
                        List<Article> res = Arrays.stream(articles).collect(Collectors.toList());
                        InMemoryStorage.setArticles(res);
                        responce.responced(true);
                        //System.out.println(response.toString());
                    } else {
                        System.out.println("GET articles request did not work.");
                        responce.responced(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    responce.responced(false);
                }
            }
            });

    }
    public void getAllCategories(APIrequestResponce responce){//для запроса всех категорий с сервера
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-=-=-=-=-==-=-=--=-==-");
                    URL obj = new URL(SERVER_ADDR + "/product_categories");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    //con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", USER_AGENT);
                    int responseCode = con.getResponseCode();
                    System.out.println("GET categories Response Code :: " + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                            System.out.println(inputLine);
                        }
                        in.close();

                        ObjectMapper objectMapper = new ObjectMapper();
                        ProductCategory[] categories = objectMapper.readValue(response.toString(), ProductCategory[].class);
                        List<ProductCategory> res = Arrays.stream(categories).collect(Collectors.toList());
                        InMemoryStorage.setCategories(res);
                        responce.responced(true);
                        //System.out.println(response.toString());
                    } else {
                        System.out.println("GET categories request did not work.");
                        responce.responced(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    responce.responced(false);
                }
            }
        });

    }

    public interface APIrequestResponce {
        void responced(boolean success);
    }
}
