package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.os.PerformanceHintManager;
import android.view.View;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        Button cart = findViewById(R.id.button);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });

    }

    public static void addProduct(Product prod)
    {
        boolean found = false;
        for(int i = 0; i < Cart.cart.size(); i++)
        {
            if(Cart.cart.get(i).name.equals(prod.name))
            {
                Cart.cart.get(i).quantity++;
                found = true;
                break;
            }
        }
        if(!found)
            Cart.cart.add(prod);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_tshirts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TshirtsFrag()).commit();
                break;
            case R.id.nav_pants:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PantsFrag()).commit();
                break;
            case R.id.nav_shorts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new shortsfrag()).commit();
                break;
            case R.id.nav_bags:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new bagsfrag()).commit();
                break;
            case R.id.nav_shoes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new shoesfrag()).commit();
                break;
            case R.id.nav_searchVoice:
                Intent intent = new Intent(getApplicationContext(), searchByVoice.class);
                startActivity(intent);
                break;
            case R.id.nav_Home:
                intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
               intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
        //item was selected
    }

}