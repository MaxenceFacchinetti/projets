package com.facchinetti.velovproject.Vue;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.R;

import org.w3c.dom.Text;

public class DetailsStationActivity extends AppCompatActivity {

    private int numItem;
    private ImageView imageViewFavDetailsStation;
    private TextView textViewNumberDetailsStation;
    private TextView textViewNameDetailsStation;
    private TextView textViewContractNameDetailsStation;
    private TextView textViewAdresseDetailsStation;
    private TextView textViewBankingDetailsStation;
    private TextView textViewBonusDetailsStation;
    private TextView textViewBikeStandsDetailsStation;
    private TextView textViewAvailableBikeStandsDetailsStation;
    private TextView textViewAvailableBikesDetailsStation;
    private TextView textViewFermeOuvertDetailsStation;
    private TextView textViewLastUpdateDetailsStation;
    private TextView textViewLATDetailsStation;
    private TextView textViewLNGDetailsStation;
    private ListeStation.Station stationDetaillee;
    public static final int RESULT_CODE_DETAILS_STATIONS = 1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_station);
        numItem = Integer.parseInt(getIntent().getStringExtra("NumItem"));
        stationDetaillee = MainActivity.listeStation.getListStation().get(numItem);

        assigneElementToAttributs();

        if(stationDetaillee.isFavorite()){
            imageViewFavDetailsStation.setImageDrawable(this.getDrawable(R.drawable.fav));
        }
        else{
            imageViewFavDetailsStation.setImageDrawable(this.getDrawable(R.drawable.notfav));
        }

        textViewNumberDetailsStation.setText(stationDetaillee.getNumber());
        textViewNameDetailsStation.setText(stationDetaillee.getName());
        textViewContractNameDetailsStation.setText("Contract Name : " + stationDetaillee.getContractName());
        textViewAdresseDetailsStation.setText("Adresse : " + stationDetaillee.getAddress());

        if(stationDetaillee.isBanking()){
            textViewBankingDetailsStation.setText("Banking : ✓");
        }
        else{
            textViewBankingDetailsStation.setText("Banking : X");
        }

        if(stationDetaillee.isBonus()){
            textViewBonusDetailsStation.setText("Bonus : ✓");
        }
        else{
            textViewBonusDetailsStation.setText("Bonus : X");
        }

        textViewBikeStandsDetailsStation.setText("Nombre de stands : " + stationDetaillee.getBikeStands());
        textViewAvailableBikeStandsDetailsStation.setText("Stands disponibles : " + stationDetaillee.getAvailableBikeStands());
        textViewAvailableBikesDetailsStation.setText("Vélos disponibles : " + stationDetaillee.getAvailableBikes());

        if(stationDetaillee.getStatus() == ListeStation.Station.Status.FERME){
            textViewFermeOuvertDetailsStation.setTextColor(Color.RED);
            textViewFermeOuvertDetailsStation.setText("Fermé");
        }
        else{
            textViewFermeOuvertDetailsStation.setTextColor(Color.GREEN);
            textViewFermeOuvertDetailsStation.setText("Ouvert");
        }

        textViewLastUpdateDetailsStation.setText("Last Update : " + stationDetaillee.getLastUpdate());
        textViewLATDetailsStation.setText("LAT : " + stationDetaillee.getPosition().getLat());
        textViewLNGDetailsStation.setText("LNG : " + stationDetaillee.getPosition().getLng());

    }

    private void assigneElementToAttributs(){
        imageViewFavDetailsStation = findViewById(R.id.imageViewFavDetailsStation);
        textViewNumberDetailsStation = findViewById(R.id.textViewNumberDetailsStation);
        textViewNameDetailsStation = findViewById(R.id.textViewNameDetailsStation);
        textViewContractNameDetailsStation = findViewById(R.id.textViewContractNameDetailsStation);
        textViewAdresseDetailsStation = findViewById(R.id.textViewAdresseDetailsStation);
        textViewBankingDetailsStation = findViewById(R.id.textViewBankingDetailsStation);
        textViewBonusDetailsStation = findViewById(R.id.textViewBonusDetailsStation);
        textViewBikeStandsDetailsStation = findViewById(R.id.textViewBikeStandsDetailsStation);
        textViewAvailableBikeStandsDetailsStation = findViewById(R.id.textViewAvailableBikeStandsDetailsStation);
        textViewAvailableBikesDetailsStation = findViewById(R.id.textViewAvailableBikesDetailsStation);
        textViewFermeOuvertDetailsStation = findViewById(R.id.textViewFermeOuvertDetailsStation);
        textViewLastUpdateDetailsStation = findViewById(R.id.textViewLastUpdateDetailsStation);
        textViewLATDetailsStation = findViewById(R.id.textViewLATDetailsStation);
        textViewLNGDetailsStation = findViewById(R.id.textViewLNGDetailsStation);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void clickImageViewFavDetailsStation(View v){
        Toast.makeText(this,"Fav",Toast.LENGTH_SHORT).show();
        if(stationDetaillee.isFavorite()){
            MainActivity.listeStation.getListStation().get(numItem).setFavorite(false);
            imageViewFavDetailsStation.setImageDrawable(this.getDrawable(R.drawable.notfav));
        }
        else{
            MainActivity.listeStation.getListStation().get(numItem).setFavorite(true);
            imageViewFavDetailsStation.setImageDrawable(this.getDrawable(R.drawable.fav));
        }
        MainActivity.listeStation.updateNbFavs();
        MainActivity.listeStation.updateListStation();
    }

    public void clickButtonRetourDetailsStation(View v){
        setResult(RESULT_CODE_DETAILS_STATIONS);
        super.onBackPressed();
    }

}
