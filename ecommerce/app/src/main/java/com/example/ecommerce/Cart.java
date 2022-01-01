package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    Button submit;
    Button remove;
    Button back;
    ListView items;
    TextView sumText;
    public static ArrayList<Product> cart = new ArrayList<Product>();
    ArrayList<String> item_names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        submit = (Button) findViewById(R.id.add_button);
        remove = (Button) findViewById(R.id.remove_button);
        items = (ListView) findViewById(R.id.listItems);
        back = (Button) findViewById(R.id.button7);
        sumText = (TextView) findViewById(R.id.textView7);

        float sum = 0;
        for(int i = 0; i < cart.size(); i++)
        {
            item_names.add(cart.get(i).name);
            sum += cart.get(i).quantity * cart.get(i).price;
        }
        sumText.setText("Total cost is: " + sum);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,item_names);

        items.setAdapter(adapter);

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SharedPreferences item_name = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = item_name.edit();

                editor.putInt("index",i);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),Selected_Item.class);
                startActivity(intent);

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.clear();
                item_names.clear();
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),gps.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }
}