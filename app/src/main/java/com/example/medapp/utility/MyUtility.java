package com.example.medapp.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Класс для хранения различных часто используемых констант
//26.03.24
//Бычковский В.Р.
public abstract class MyUtility {

    public static final ServerAPIHelper serverAPI;
    public static final SimpleDateFormat mainDateFormat= new SimpleDateFormat("dd.MM.yyyy");
    public static final DateTimeFormatter mainDateFrormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int timeToEnterMailCode = 20;
    private static DecimalFormat moneyFormat = new DecimalFormat("#0.00");
    private static final String emailPattern = "([a-z])+@([a-z])+\\.ru";
    public static final String cartFileName = "cart-data.dat";
    public static final String cardsFileName = "cards-data.dat";

    public static String formatDoubleToMoney(Double sum){
        return moneyFormat.format(sum);
    }
    public static boolean isCorrectEmail(String email){
        return email.matches(emailPattern);
    }
    public static final String formatDate(LocalDate date){
        return mainDateFrormatter.format(date);
    }
    public static final String formatDate(Date date){
        return mainDateFormat.format(date);
    }

    static{
        serverAPI = new ServerAPIHelper();
    }
}
