package com.example.medapp.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

//Класс для хранения различных часто используемых констант
//26.03.24
//Бычковский В.Р.
public abstract class Constants {
    public static final SimpleDateFormat mainDateFormat= new SimpleDateFormat("dd.MM.yyyy");
    private static DecimalFormat moneyFormat = new DecimalFormat("#0.00");
    public static String formatDoubleToMoney(Double sum){
        return moneyFormat.format(sum);
    }
}
