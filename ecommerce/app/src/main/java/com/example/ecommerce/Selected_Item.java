package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Selected_Item extends AppCompatActivity {


    TextView textView1,textView2;
    ImageView less,more;
    Button remove;
    int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        textView1 = (TextView) findViewById(R.id.textView4);
        textView2 = (TextView) findViewById(R.id.textView5);
        less = (ImageView) findViewById(R.id.imageView6);
        more = (ImageView) findViewById(R.id.imageView7);
        remove = (Button) findViewById(R.id.button5);
        SharedPreferences selected_item = getSharedPreferences("PREFS", 0);

        int index = selected_item.getInt("index", 0);
        String text = Cart.cart.get(index).name;
        quantity = Cart.cart.get(index).quantity;
        textView1.setText(text);
        textView2.setText(Integer.toString(quantity));


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.cart.remove(index);
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Cart.cart.get(index).quantity == 1) {
                    Cart.cart.remove(index);
                    Intent intent = new Intent(getApplicationContext(), Cart.class);
                    startActivity(intent);
                }
                else {
                    Cart.cart.get(index).quantity--;
                    quantity--;
                    textView2.setText(Integer.toString(quantity));
                }
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.cart.get(index).quantity++;
                quantity++;
                textView2.setText(Integer.toString(quantity));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Cart.class);
        startActivity(intent);
    }
}