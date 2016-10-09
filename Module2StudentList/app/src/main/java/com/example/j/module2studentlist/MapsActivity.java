package com.example.j.module2studentlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String Adres, Naam, studNr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        try{
            Bundle b = this.getIntent().getExtras();
            if(b != null)
            {
                for(Student std : Student.getStudentList()){
                    if(std.getStudentnr().equals(b.getString("StudNr"))){
                        Adres = std.getPostcode()+", "+std.getPlaats()+", Nederland";
                        Naam = std.getNaam()+ " " + std.getAchternaam() + " " + std.getTussenvoegsel();
                        studNr = std.getStudentnr();
                    }
                }
            }
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double longitude = 0;
        double latitude = 0;
        Geocoder gc = new Geocoder(getApplicationContext());
        List<Address> addresses = null;
        try {
            addresses = gc.getFromLocationName(Adres, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses.size() > 0){
            longitude = addresses.get(0).getLongitude();
            latitude = addresses.get(0).getLatitude();
        }

        String name = ("n"+studNr).trim();
        int id = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
        Bitmap icon = BitmapFactory.decodeResource(getResources(), id);
        icon = Bitmap.createScaledBitmap(icon, 200, 240, false);
        if(longitude + latitude != 0) {
            // Add a marker in Sydney and move the camera
            LatLng City = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions()
                    .position(City)
                    .title(Naam)
                    .snippet(Adres)
                    .icon(BitmapDescriptorFactory.fromBitmap(icon))).showInfoWindow();
            //.icon(BitmapDescriptorFactory.fromResource(id)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(City, 15));

        }
    }
}
