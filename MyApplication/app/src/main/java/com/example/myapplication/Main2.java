package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.content.Intent;



public class Main2 extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);


        final EditText editText = findViewById(R.id.edit_text);
        final EditText editText2 = findViewById(R.id.edit_text2);
        final EditText editText3 = findViewById(R.id.edit_text3);
        final EditText editText4 = findViewById(R.id.edit_text4);
        final EditText editText7 = findViewById(R.id.edit_text7);


        /*editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean flag) {
                if(!flag){
                    String str = editText.getText().toString().trim();
                    if(!str.matches("^[0-2]{2}[0-9]{2}[0-1]{1}[1-9]{1}[0-3]{1}[1-9]{1}$")){
                        Toast toast = Toast.makeText(Main2.this,"正しい入力値を入れてください",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });*/

        Button saveButton = findViewById(R.id.KeepButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // データを取得
                //String seriesName = editText.getText().toString();
                String workName = editText2.getText().toString();
                //String volume = editText3.getText().toString();
                //String authorName = editText4.getText().toString();
                //String mediaType = getSelectedRadioButtonText();
                //String storageLocation = editText7.getText().toString();

                Intent intent = new Intent(Main2.this, MainActivity.class);

                // データをキーと一緒に追加
                //intent.putExtra("seriesNameKey", seriesName);
                intent.putExtra("workNameKey", workName);
                //intent.putExtra("volumeKey", volume);
                //intent.putExtra("authorNameKey", authorName);
                //intent.putExtra("mediaTypeKey", mediaType);
                //intent.putExtra("storageLocationKey", storageLocation);

                startActivity(intent);
            }
        });
    }

    private String getSelectedRadioButtonText() {
        RadioGroup radioGroupMedia = findViewById(R.id.radio_group_media);
        int selectedRadioButtonId = radioGroupMedia.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            return selectedRadioButton.getText().toString();
        }
        return "";
    }
}
