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

public class PantsFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pants_fragment,container,false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button B1=view.findViewById(R.id.but1);
        Button B2=view.findViewById(R.id.but2);
        Button B3=view.findViewById(R.id.but3);
        Button B4=view.findViewById(R.id.but4);
        Button B5=view.findViewById(R.id.but5);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product pants = new Product("Pants1",30,1);
                HomePage.addProduct(pants);
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pants = new Product("Pants2",25,1);
                HomePage.addProduct(pants);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pants = new Product("Pants3",28,1);
                HomePage.addProduct(pants);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pants = new Product("Pants4",26,1);
                HomePage.addProduct(pants);
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pants = new Product("Pants5",29,1);
                HomePage.addProduct(pants);
            }
        });


    }


}
