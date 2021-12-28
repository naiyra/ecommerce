package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class shortsfrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shorts_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button B1=view.findViewById(R.id.bu1);
        Button B2=view.findViewById(R.id.bu2);
        Button B3=view.findViewById(R.id.bu3);
        Button B4=view.findViewById(R.id.bu4);
        Button B5=view.findViewById(R.id.bu4);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product shorts = new Product("Shorts1",20,1);
                HomePage.addProduct(shorts);
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shorts = new Product("Shorts2",10,1);
                HomePage.addProduct(shorts);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shorts = new Product("Shorts3",15,1);
                HomePage.addProduct(shorts);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shorts = new Product("Shorts4",18,1);
                HomePage.addProduct(shorts);
            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shorts = new Product("Shorts5",15,1);
                HomePage.addProduct(shorts);
            }
        });

    }



}
