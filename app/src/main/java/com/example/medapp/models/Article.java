package com.example.medapp.models;

import java.time.LocalDate;

public class Article {
    private int id;
    private String name;
    private LocalDate date;

    public Article(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Article(int id, String name, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
