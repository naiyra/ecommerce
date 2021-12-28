package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class mapss extends FragmentActivity {

    private GoogleMap mMap;
    EditText addressText;
    LocationManager locmanager;
    myactionlistener locListener;
    Button getLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapss);

        addressText = (EditText) findViewById(R.id.editxt);
        getLocation= (Button) findViewById(R.id.btn11);
        locListener=new myactionlistener(getApplicationContext());
        locmanager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);


        try{
            locmanager.requestLocationUpdates(locmanager.GPS_PROVIDER,6000,0,locListener);
        }
        catch (SecurityException ex)
        {
            Toast.makeText(getApplicationContext(), "You are not allowed to access the current location", Toast.LENGTH_SHORT).show();
        }

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    public void onMapReady(GoogleMap googleMap)
    {
        mMap=googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.0444190,31.235711600),8));

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                Geocoder coder=new Geocoder(getApplicationContext());
                List<Address> addressList;
                Location loc=null;

                try{
                    loc=locmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                }
                catch (SecurityException x)
                {
                    Toast.makeText(getApplicationContext(),"You did not allow access to the current loc",Toast.LENGTH_SHORT).show();

                }

                if(loc != null)
                {
                    LatLng myPosition = new LatLng(loc.getLatitude(),loc.getLongitude());
                    try{
                        addressList=coder.getFromLocation(myPosition.latitude,myPosition.longitude,1);
                        if(!addressList.isEmpty()){
                            String address="";
                            for(int i=0;i<=addressList.get(0).getMaxAddressLineIndex();i++)
                                address+=addressList.get(0).getAddressLine(1)+",";
                            mMap.addMarker(new MarkerOptions().position(myPosition).title("My location").snippet(address)).setDraggable(true);
                            addressText.setText(address);

                        }
                    } catch (IOException e) {
                        mMap.addMarker(new MarkerOptions().position(myPosition).title("My location"));
                        e.printStackTrace();
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,15));

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please wait untill your postion is determined",Toast.LENGTH_SHORT).show();
                }

                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        Geocoder coder = new Geocoder(getApplicationContext());
                        List<Address>addressList;
                        try{
                            addressList=coder.getFromLocation(marker.getPosition().latitude,marker.getPosition().longitude,1);
                           if(!addressList.isEmpty())
                           {
                               String address="";
                               for (int i=0;i<addressList.get(0).getMaxAddressLineIndex();i++)
                                   address+=addressList.get(0).getAddressLine(i)+",";
                               addressText.setText(address);
                               }
                           }
                         catch (IOException e) {
                            Toast.makeText(getApplicationContext(),"Can't get the address check networks",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}