package com.facchinetti.velovproject.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facchinetti.velovproject.Controler.AsyncTaskActualiserListeStations;
import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.R;

public class ListeStationsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_LISTE_STATIONS = 1;
    String urlJCDecauxString;
    ListView listViewListeStations;
    StationAdapter stationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_stations);
        urlJCDecauxString = getIntent().getStringExtra("URLJCDecauxString");

        listViewListeStations = findViewById(R.id.listViewListeStations);
        stationAdapter = new StationAdapter(this);

        listViewListeStations.setAdapter(stationAdapter);

        listViewListeStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDetailsStation = new Intent(ListeStationsActivity.this,DetailsStationActivity.class);
                intentDetailsStation.putExtra("NumItem",String.valueOf(position));
                startActivityForResult(intentDetailsStation,REQUEST_CODE_LISTE_STATIONS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_LISTE_STATIONS){
            if(resultCode == DetailsStationActivity.RESULT_CODE_DETAILS_STATIONS){
                this.recreate();
            }
        }
    }

    public void clickUpdateStationsButtonListeStations(View v){
        new AsyncTaskActualiserListeStations().execute(this,urlJCDecauxString);
    }

    public void clickButtonRetourListeStations(View v){
        super.onBackPressed();
    }

}
