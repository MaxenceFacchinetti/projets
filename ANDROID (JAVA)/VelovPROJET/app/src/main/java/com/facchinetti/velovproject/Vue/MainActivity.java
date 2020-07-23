package com.facchinetti.velovproject.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facchinetti.velovproject.Controler.AsyncTaskLoadJSON;
import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.R;

public class MainActivity extends AppCompatActivity {

    // Lien JCDecaux Lyon : https://api.jcdecaux.com/vls/v1/stations?contract=Lyon&apiKey=76a33cf5e864bef83b919c66320a7a2ec623959c
    // ✓ ou X

    String urlJCDecauxString = "https://api.jcdecaux.com/vls/v1/stations?contract=Lyon&apiKey=76a33cf5e864bef83b919c66320a7a2ec623959c";
    public static ListeStation listeStation;
    public static final int REQUEST_CODE_MAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listeStation = new ListeStation();
        new AsyncTaskLoadJSON().execute(urlJCDecauxString);
        setContentView(R.layout.activity_main);
    }

    public void clickButtonListeStationMain(View v){
        Intent intentListeStations = new Intent(MainActivity.this,ListeStationsActivity.class);
        intentListeStations.putExtra("URLJCDecauxString",urlJCDecauxString);
        startActivity(intentListeStations);
    }

    public void clickButtonGoogleMapMain(View v){

        Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
        intentMap.putExtra("PremierLancement","1");
        startActivity(intentMap);
    }

}
