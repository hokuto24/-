package com.example.myapplication;
import java.lang.reflect.Type;
import java.util.Collections;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    List<String> toDoList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    EditText editText;
    private String workTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData(); // データを最初に読み込む

        // toDoListの初期化はloadDataの後に移動
        if (toDoList == null) {
            toDoList = new ArrayList<>();
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoList); // 使用するレイアウトを変更
        listView = findViewById(R.id.id_list_view);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("アイテム削除")
                        .setMessage("このアイテムを削除しますか？")
                        .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItem(position);
                            }
                        })
                        .setNegativeButton("いいえ", null)
                        .show();
            }
        });
        Button goToSecondActivityButton = findViewById(R.id.id_button_add);
        goToSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2.class);
                startActivity(intent);
            }
        });

        // インテントからデータを受け取る
        Intent intent = getIntent();
        if (intent != null) {
            workTitle = intent.getStringExtra("workNameKey");
            addItemToList(workTitle);
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toDoList);
        editor.putString("todo_list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("todo_list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        toDoList = gson.fromJson(json, type);

        if (toDoList == null) {
            toDoList = new ArrayList<>();
        }
    }

    public void addItemToList(String item) {
        if (item != null && !item.isEmpty()) {
            toDoList.add(item);
            arrayAdapter.notifyDataSetChanged();
            saveData(); // データを保存
        }
    }

    public void deleteItem(int position) {
        toDoList.remove(position);
        arrayAdapter.notifyDataSetChanged();
    }

    public void sortToDoList(View view) {
        Collections.sort(toDoList);
        arrayAdapter.notifyDataSetChanged();
    }
}