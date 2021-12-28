package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Selected_Item extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        textView = (TextView) findViewById(R.id.textView4);
        SharedPreferences selected_item = getSharedPreferences("PREFS", 0);
        String text = selected_item.getString("text","");
        textView.setText(text);
    }
}