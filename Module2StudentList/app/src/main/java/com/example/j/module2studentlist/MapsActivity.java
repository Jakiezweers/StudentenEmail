package com.example.j.module2studentlist;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String Adres, Naam;
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
                    if(std.getStudentnr().equals(b.getString("StudNR"))){
                        Adres = std.getPostcode()+", "+std.getPlaats()+", Netherlands";
                        Naam = std.getNaam()+ " " + std.getAchternaam() + " " + std.getTussenvoegsel();

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
        Geocoder gc = new Geocoder(getApplicationContext());
        Address address = null;
        List<Address> addressList = null;
        try {
            if (!TextUtils.isEmpty(Adres)) {
                addressList = gc.getFromLocationName(Adres, 5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Add a marker in Sydney and move the camera
        LatLng City = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(City).title(Naam));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(City));
    }

    public static String geocodeAddress(String addressStr, Geocoder gc) {
        Address address = null;
        List<Address> addressList = null;
        try {
            if (!TextUtils.isEmpty(addressStr)) {
                addressList = gc.getFromLocationName(addressStr, 5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != addressList && addressList.size() > 0) {
            address = addressList.get(0);
        }
        double latitude = 0;
        double longitude = 0;
        if (null != address && address.hasLatitude()
                && address.hasLongitude()) {
            latitude = address.getLatitude();
            longitude = address.getLongitude();
        }

        return latitude + "|" + longitude;
//        if (latitude != 0 && longitude != 0)
//        {
//
//            mGoogleMap2.addMarker(new MarkerOptions()
//                    .position(new LatLng(latitude, longitude)));
//            mGoogleMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                    new LatLng(latitude, longitude), 10));
//        }

    }
}
