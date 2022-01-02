package com.example.ecommerce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class barcodeScanner extends AppCompatActivity {
Button btscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        btscan=findViewById(R.id.scan);

        btscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator= new IntentIntegrator(barcodeScanner.this);
                intentIntegrator.setPrompt("For flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);


                intentIntegrator.initiateScan();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult.getContents()!=null)
        {
            AlertDialog.Builder builder= new AlertDialog.Builder(barcodeScanner.this);
            builder.setTitle("Result");
            //builder.setMessage(intentResult.getContents());
            String word = intentResult.getContents();
            String word2 = "";
            for(int i = 0; i < word.length(); i++)
            {
                if(word.charAt(i) != '+')
                {
                    word2 += word.charAt(i);
                }
            }
            word2 = word2.toLowerCase();
            builder.setMessage(word2);
            if(word2.equals("pant1"))
            {
                Toast.makeText(getApplicationContext(), "Do you want to add pant1 to shopping cart?", Toast.LENGTH_LONG).show();
                find("pants");
            }
            else if(word2.equals("short1"))
            {
                Toast.makeText(getApplicationContext(), "Do you want to add short1 to shopping cart?", Toast.LENGTH_LONG).show();
                find("shorts");
            }
            else if(word2.equals("tshirt1"))
            {
                Toast.makeText(getApplicationContext(), "Do you want to add tshirt1 to shopping cart?", Toast.LENGTH_LONG).show();
                find("t-shirts");
            }
            else
            {
                Toast.makeText(getApplicationContext(), "product not found", Toast.LENGTH_SHORT).show();
            }
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }else {
            Toast.makeText(getApplicationContext(), "You didnt scan anything", Toast.LENGTH_SHORT).show();
        }
    }
    public void find(String s)
    {
        SharedPreferences item_name = getSharedPreferences("PREF",0);
        SharedPreferences.Editor editor = item_name.edit();


        editor.putBoolean("is Search",true);
        editor.putString("category",s);
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }
}