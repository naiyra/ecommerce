package com.example.ecommerce;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class myactionlistener implements LocationListener {

    private Context activityContext;

    @Override
    public void onLocationChanged(@NonNull Location location) {

        Toast.makeText(activityContext,location.getAltitude()+","+location.getLongitude(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    public myactionlistener(Context activityContext) {
        this.activityContext = activityContext;
    }



    @Override
    public void onProviderEnabled(@NonNull String provider) {

        Toast.makeText(activityContext,"GPS Enable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

        Toast.makeText(activityContext,"GPS Disabled",Toast.LENGTH_SHORT).show();


    }


}
