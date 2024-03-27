package com.example.medapp.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

//Класс-помощник для создания часто-встречаемых всплываемых окон и т.п.
//27.03.24
//Бычковский В.Р.
public abstract class ActivityHelper {

    public static void createErrorMessagePopup(Context context, String errorMessage){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(context);
        a_builder.setMessage(errorMessage)
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Ошибка!");
        alert.show();
    }

}
