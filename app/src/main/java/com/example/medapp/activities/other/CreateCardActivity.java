package com.example.medapp.activities.other;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.activities.main.MainAnalysesActivity;
import com.example.medapp.models.User;
import com.example.medapp.models.UserCard;
import com.github.dhaval2404.imagepicker.ImagePicker;

import com.example.medapp.R;
import com.example.medapp.utility.Constants;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

//Класс активити для создания карты
//26.03.24
//Бычковский В.Р.
public class CreateCardActivity extends AppCompatActivity {

    public static User user;
    private Date selectedDate = null;
    private ImageView tempIcoIV;
    private Uri selectedIcoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        EditText surnameET = findViewById(R.id.createCard_surnameET);
        EditText nameET = findViewById(R.id.createCard_nameET);
        EditText fatherNameET = findViewById(R.id.createCard_fatherNameET);
        EditText birthDateET = findViewById(R.id.createCard_birthDateET);
        EditText extraInfoET = findViewById(R.id.createCard_extraInfoET);

        ImageView avatarIV = findViewById(R.id.createCard_avatarIV);//выбор фотографии
        avatarIV.setOnClickListener(v -> {
            tempIcoIV = avatarIV;
            ImagePicker.with(this)
                    .crop(1,1)	    			//Crop image(Optional), Check Customization for more option
                    .compress(512)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(512, 512)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });

        ImageButton selectDateBtn = findViewById(R.id.createCard_selectDateBtn); // выбор даты через календарь
        selectDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    selectedDate = new Date(year - 1900, month, dayOfMonth);
                    birthDateET.setText(Constants.mainDateFormat.format(selectedDate));
                }
            }, year, month, day);
            dpd.show();
        });

        Button createBtn = findViewById(R.id.createCard_createBtn);//создание карточки
        createBtn.setOnClickListener(v ->{
            String surname = surnameET.getText().toString();
            String name = nameET.getText().toString();
            String fatherName = fatherNameET.getText().toString();
            String birthdate = birthDateET.getText().toString();
            String extraInfo = extraInfoET.getText().toString();
            try {
                Date date = Constants.mainDateFormat.parse(birthdate);
                if(surname.length()==0 || name.length()==0 ||  birthdate.length()==0 || date == null){
                    Toast.makeText(this, "Введите все обязательные данные(*)!",Toast.LENGTH_LONG).show();
                }else{
                    birthDateET.setText(Constants.mainDateFormat.format(date));
                    UserCard userCard = new UserCard(surname,name,fatherName,date,extraInfo);
                    user.setUserCard(userCard);
                    Intent intent= new Intent(this, MainAnalysesActivity.class);
                    MainAnalysesActivity.user = user;
                    startActivity(intent);
                    finish();
                }
            }catch(ParseException ex){
                Toast.makeText(this, "Введите корректную дату(дд.мм.гггг)!",Toast.LENGTH_LONG).show();
            }
        });

        TextView skipBtn = findViewById(R.id.createCard_skipBtn);//пропустить создание карты
        skipBtn.setOnClickListener(v -> {
            Intent intent= new Intent(this, MainAnalysesActivity.class);
            MainAnalysesActivity.user = user;
            startActivity(intent);
            finish();
        });
    }

    //метод для добавления фотографии
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            tempIcoIV.setImageURI(uri);
            selectedIcoUri = uri;
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}