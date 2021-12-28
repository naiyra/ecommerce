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

        Product p1 = new Product("lol",20,1);
        Product p2 = new Product("lol1",20,12);
        Product p3 = new Product("lol2",20,13);
        Cart.cart.add(p1);
        Cart.cart.add(p2);
        Cart.cart.add(p3);
        Button cart = findViewById(R.id.button);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });



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
            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
        //item was selected
    }
}