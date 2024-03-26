package com.example.medapp.models;

import java.util.Date;

//Класс активити для модели карты пользователя
//26.03.24
//Бычковский В.Р.
public class UserCard {
    private String surname;
    private String name;
    private String fatherName;
    private Date birthDate;
    private String extraInfo;
    private Object avatar;

    public UserCard(String surname, String name, String fatherName, Date birthDate, String extraInfo) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.extraInfo=extraInfo;
    }

    public UserCard() {
        this.surname = "Фамилия";
        this.name = "Имя";
        this.fatherName = "Отчество";
        this.birthDate = new Date(105,1,19);
        this.extraInfo="";
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Object getAvatar() {
        return avatar;
    }

    public String getExtraInfo() {
        return extraInfo;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
