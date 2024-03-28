package com.example.medapp.models;

import java.time.LocalDate;
import java.util.Date;

//Класс для новостей
//27.03.24
//Бычковский В.Р.
public class Article {
    private int id;
    private String name;
    private Date date;

    public Article() {
    }

    public Article(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Article(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
