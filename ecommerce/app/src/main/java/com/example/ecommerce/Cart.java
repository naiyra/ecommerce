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

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    Button add;
    Button remove;
    ListView items;
    public static ArrayList<Product> cart = new ArrayList<Product>();
    ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        add = (Button) findViewById(R.id.add_button);
        remove = (Button) findViewById(R.id.remove_button);
        items = (ListView) findViewById(R.id.listItems);

        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1,android.R.id.text1,cart);

        items.setAdapter(adapter);

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = items.getItemAtPosition(i).toString();
                SharedPreferences item_name = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = item_name.edit();
                editor.putString("text",text);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),Selected_Item.class);
                startActivity(intent);

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}