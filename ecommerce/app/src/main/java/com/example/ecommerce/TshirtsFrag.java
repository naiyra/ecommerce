package com.example.ecommerce;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TshirtsFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tshirt_fragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button B1=view.findViewById(R.id.b1);
        Button B2=view.findViewById(R.id.b2);
        Button B3=view.findViewById(R.id.b3);
        Button B4=view.findViewById(R.id.b4);
         B1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Product shirt = new Product("Tshirt1",20,1);
                 HomePage.addProduct(shirt);
             }
         });

         B2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Product shirt = new Product("Tshirt2",30,1);
                 HomePage.addProduct(shirt);
             }
         });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shirt = new Product("Tshirt3",35,1);
                HomePage.addProduct(shirt);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product shirt = new Product("Tshirt4",40,1);
                HomePage.addProduct(shirt);
            }
        });


    }


}
