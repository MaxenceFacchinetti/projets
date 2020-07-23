package com.facchinetti.velovproject.Controler;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.Vue.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class AsyncTaskLoadJSON extends AsyncTask<Object, Void, StringBuilder> {

    String urlJCDecauxString;

    @SuppressLint("WrongThread")
    @Override
    protected StringBuilder doInBackground(Object... params) {
    /*
    Params :
    1 : urlJCDecauxString
    */

        urlJCDecauxString = (String) params[0];
        StringBuilder sb = new StringBuilder();

        try {
            URL urlJCDecaux = new URL(urlJCDecauxString);
            HttpsURLConnection urlJCDecauxConnection = (HttpsURLConnection) urlJCDecaux.openConnection();
            if (urlJCDecauxConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlJCDecauxConnection.getInputStream())
                );

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb;
    }

    @Override
    protected void onPostExecute(StringBuilder sb) {
        try {
            JSONArray jsonArrayJCDecaux = new JSONArray(sb.toString());
            int tailleJSONArray = jsonArrayJCDecaux.length();
            if(MainActivity.listeStation.getListStation().isEmpty()) {
                for (int i = 0; i < tailleJSONArray; i++) {
                    MainActivity.listeStation.addStation(parseJSON(jsonArrayJCDecaux.getJSONObject(i)),false);
                }
            }else{
                ArrayList<String> listNumberStationFav = new ArrayList();
                for(ListeStation.Station s : MainActivity.listeStation.getListStation()){
                    if(s.isFavorite()){
                        listNumberStationFav.add(s.getNumber());
                    }
                }
                MainActivity.listeStation.getListStation().clear();
                for(int i = 0; i< tailleJSONArray; i++){
                    ListeStation.Station tmp = parseJSON(jsonArrayJCDecaux.getJSONObject(i));
                    if(verifyFavorite(listNumberStationFav, tmp)){
                        MainActivity.listeStation.addStation(tmp,true);
                    }
                    else{
                        MainActivity.listeStation.addStation(tmp,false);
                    }
                }
                MainActivity.listeStation.updateNbFavs();
                MainActivity.listeStation.updateListStation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean verifyFavorite(ArrayList<String> listNumberFavStation, ListeStation.Station newStation){
        for(String s : listNumberFavStation){
            if(newStation.getNumber().equals(s)){
                return true;
            }
        }
        return false;
    }

    private ListeStation.Station parseJSON(JSONObject jsonObjectStation) {
        ListeStation.Station newStation;
        try {
            String number = jsonObjectStation.getString("number");
            String contractName = jsonObjectStation.getString("contract_name");
            String name = jsonObjectStation.getString("name");
            String address = jsonObjectStation.getString("address");
            boolean banking = jsonObjectStation.getBoolean("banking");
            boolean bonus = jsonObjectStation.getBoolean("bonus");
            int bikeStands = jsonObjectStation.getInt("bike_stands");
            int availableBikeStands = jsonObjectStation.getInt("available_bike_stands");
            int availableBikes = jsonObjectStation.getInt("available_bikes");
            ListeStation.Station.Status status;

            if (jsonObjectStation.getString("status").equals("OPEN")) {
                status = ListeStation.Station.Status.OUVERT;
            } else {
                status = ListeStation.Station.Status.FERME;
            }

            String lastUpdate = jsonObjectStation.getString("last_update");

            double lat = jsonObjectStation.getJSONObject("position").getDouble("lat");
            double lng = jsonObjectStation.getJSONObject("position").getDouble("lng");

            ListeStation.Station.Position position = new ListeStation.Station.Position(lat, lng);

            newStation = new ListeStation.Station(number, contractName, name, address, banking, bonus, bikeStands, availableBikeStands, availableBikes, status, lastUpdate, position);


            return newStation;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



}
