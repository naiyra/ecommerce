package com.example.ecommerce;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

public class HomePage extends AppCompatActivity {

    private DrawerLayout drawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }
}