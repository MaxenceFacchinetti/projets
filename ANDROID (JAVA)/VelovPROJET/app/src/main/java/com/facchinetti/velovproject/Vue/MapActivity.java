package com.facchinetti.velovproject.Vue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap mMap;
    Marker lastMarker;
    private static boolean isFav = false;
    public static int REQUEST_CODE_MAP_ACTIVITY = 1;
    Button favorisButtonMap;
    EditText rechercheEditTextMap;
    private static String research = "";
    private static int clickCount;
    private static Marker markerClicked;
    private static int premierLancement = 0;
    private double latGPS, lngGPS;

    public double getLatGPS() {
        return latGPS;
    }

    public void setLatGPS(double latGPS) {
        this.latGPS = latGPS;
    }

    public double getLngGPS() {
        return lngGPS;
    }

    public void setLngGPS(double lngGPS) {
        this.lngGPS = lngGPS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        clickCount = 0;
        favorisButtonMap = findViewById(R.id.favorisButtonMap);
        rechercheEditTextMap = findViewById(R.id.rechercheEditTextMap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lastMarker = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lyon = new LatLng(45.75, 4.85);
        //mMap.addMarker(new MarkerOptions().position(lyon).title("Marker in Sydney"));


        if(isFav){
            favorisButtonMap.setBackgroundColor(Color.YELLOW);
        }
        else{
            favorisButtonMap.setBackgroundColor(Color.parseColor("#DBDBDB"));
        }

        for (ListeStation.Station s : MainActivity.listeStation.getListStation()) {
            if(isFav){
                if(!s.isFavorite()){
                    break;
                }
            }
            if(!research.matches("")){
                if(s.getName().contains(research)){
                    LatLng tmp = new LatLng(s.getPosition().getLat(), s.getPosition().getLng());
                    mMap.addMarker(new MarkerOptions().position(tmp).title(s.getName()));
                }
            }
            else{
                LatLng tmp = new LatLng(s.getPosition().getLat(), s.getPosition().getLng());
                mMap.addMarker(new MarkerOptions().position(tmp).title(s.getName()));
            }

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lyon));
        mMap.setOnMarkerClickListener(this);
    }

    public void clickFavorisButtonMap(View v) {
        if (isFav) {
            Toast.makeText(this, "Toutes les stations affichées", Toast.LENGTH_SHORT).show();
            isFav = false;
            favorisButtonMap.setBackgroundColor(Color.YELLOW);
        } else {
            Toast.makeText(this, "Stations favorites affichées", Toast.LENGTH_SHORT).show();
            isFav = true;
            favorisButtonMap.setBackgroundColor(Color.parseColor("#DBDBDB"));
        }
        this.recreate();
    }

    public void clickRechercheButtonMap(View v){
        research = rechercheEditTextMap.getText().toString();
        this.recreate();
    }

    public void clickItineraireButtonMap(View v){

    }


    @SuppressLint("MissingPermission")
    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(markerClicked)){
            clickCount++;
            if(clickCount == 2) {
                for (int i = 0; i < MainActivity.listeStation.getListStation().size(); i++) {
                    if (marker.getTitle().equals(MainActivity.listeStation.getListStation().get(i).getName())) {
                        Intent detailsStationIntent = new Intent(MapActivity.this, DetailsStationActivity.class);
                        detailsStationIntent.putExtra("NumItem", String.valueOf(i));
                        startActivityForResult(detailsStationIntent, REQUEST_CODE_MAP_ACTIVITY);
                        break;
                    }
                }
            }
        }
        else {
            markerClicked = marker;
            if(lastMarker == null){
                clickCount = 1;
            }
            else{
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(lastMarker.getPosition());
                polylineOptions.add(markerClicked.getPosition());

                mMap.addPolyline(polylineOptions);

            }
            lastMarker = marker;
        }
        return false;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_MAP_ACTIVITY){
            if(resultCode == DetailsStationActivity.RESULT_CODE_DETAILS_STATIONS){
                this.recreate();
            }
        }
    }
}
