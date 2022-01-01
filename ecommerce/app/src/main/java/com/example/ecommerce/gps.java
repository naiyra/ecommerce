package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class gps extends AppCompatActivity {
    Button btnloc;
    TextView loctxt, loctxt1, loctxt2, loctxt3, loctxt4, loctxt5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);


        btnloc = findViewById(R.id.btnLoc);
        loctxt = findViewById(R.id.loctxt);

        loctxt2 = findViewById(R.id.loctxt2);
        loctxt3 = findViewById(R.id.loctxt3);
        loctxt4 = findViewById(R.id.loctxt4);
        loctxt5 = findViewById(R.id.loctxt5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(gps.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(gps.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });


    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location =task.getResult();
                if(location!=null)
                {

                    try {
                        Geocoder geocoder= new Geocoder(gps.this, Locale.getDefault());
                        List<Address> adresses= geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);


                        loctxt.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude:</b><br><font>"+adresses.get(0).getLatitude()  ));

                        loctxt2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude:</b><br><font>"+adresses.get(0).getLongitude()  ));
                        loctxt3.setText(Html.fromHtml("<font color='#6200EE'><b>Country:</b><br><font>"+adresses.get(0).getCountryName()  ));
                        loctxt4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality:</b><br><font>"+adresses.get(0).getLocality()  ));
                        loctxt5.setText(Html.fromHtml("<font color='#6200EE'><b>Address:</b><br><font>"+adresses.get(0).getAddressLine(0)  ));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}