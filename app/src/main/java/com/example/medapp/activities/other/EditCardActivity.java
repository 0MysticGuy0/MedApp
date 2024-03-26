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
import android.widget.Toast;

import com.example.medapp.R;
import com.example.medapp.activities.main.MainAnalysesActivity;
import com.example.medapp.models.User;
import com.example.medapp.models.UserCard;
import com.example.medapp.utility.Constants;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

//Класс активити для редактирования карты
//26.03.24
//Бычковский В.Р.
public class EditCardActivity extends AppCompatActivity {

    public static User user = null;

    private EditText surnameET;
    private EditText nameET;
    private EditText fatherNameET;
    private EditText birthDateET;
    private EditText extraInfoET;
    private Date selectedDate = null;
    private ImageView tempIcoIV;
    private Uri selectedIcoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        ImageButton backBtn = findViewById(R.id.editCard_backBtn);//кнопка назад
        backBtn.setOnClickListener(v -> {
            finish();
        });

         surnameET = findViewById(R.id.editCard_surnameET);
         nameET = findViewById(R.id.editCard_nameET);
         fatherNameET = findViewById(R.id.editCard_fatherNameET);
         birthDateET = findViewById(R.id.editCard_birthDateET);
         extraInfoET = findViewById(R.id.editCard_extraInfoET);

         initializeData();

        ImageView avatarIV = findViewById(R.id.editCard_avatarIV);//выбор фотографии
        avatarIV.setOnClickListener(v -> {
            tempIcoIV = avatarIV;
            ImagePicker.with(this)
                    .crop(1,1)	    			//Crop image(Optional), Check Customization for more option
                    .compress(512)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(512, 512)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });

        ImageButton selectDateBtn = findViewById(R.id.editCard_selectDateBtn); // выбор даты через календарь
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

        Button saveBtn = findViewById(R.id.editCard_saveBtn);//сохранение изменений
        saveBtn.setOnClickListener(v ->{
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

                    UserCard userCard = user.getUserCard();
                    userCard.setBirthDate(date);
                    userCard.setName(name);
                    userCard.setSurname(surname);
                    userCard.setFatherName(fatherName);
                    userCard.setExtraInfo(extraInfo);
                    Toast.makeText(this, "Данные сохранены",Toast.LENGTH_LONG).show();

                    Intent intent= new Intent(this, MainAnalysesActivity.class);
                    MainAnalysesActivity.user = user;
                    startActivity(intent);
                    finish();
                }
            }catch(ParseException ex){
                Toast.makeText(this, "Введите корректную дату(дд.мм.гггг)!",Toast.LENGTH_LONG).show();
            }
        });

    }

    //проинициализировать начальные значения полей
    private void initializeData(){
        UserCard userCard = user.getUserCard();
        if(user == null || userCard == null) throw new RuntimeException("EditCardActivity: userCard==null");
        surnameET.setText(userCard.getSurname());
        nameET.setText(userCard.getName());
        fatherNameET.setText(userCard.getFatherName());
        birthDateET.setText(
                Constants.mainDateFormat.format(userCard.getBirthDate())    );
        extraInfoET.setText(userCard.getExtraInfo());
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