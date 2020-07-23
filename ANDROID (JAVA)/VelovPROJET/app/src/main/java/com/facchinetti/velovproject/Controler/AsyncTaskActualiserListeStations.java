package com.facchinetti.velovproject.Controler;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.facchinetti.velovproject.Vue.ListeStationsActivity;

public class AsyncTaskActualiserListeStations extends AsyncTask<Object,Void, ListeStationsActivity> {

    private ListeStationsActivity listeStationsActivity;
    private String urlJCDecauxString;

    @SuppressLint("WrongThread")
    @Override
    protected ListeStationsActivity doInBackground(Object... params) {
        /*
        params :
        0 : listeStationsActivity
        1 : urlJCDecauxString
         */

        listeStationsActivity = (ListeStationsActivity) params[0];
        urlJCDecauxString = (String) params[1];

        new AsyncTaskLoadJSON().execute(urlJCDecauxString);

        return listeStationsActivity;
    }

    @Override
    protected void onPostExecute(ListeStationsActivity listeStationsActivity) {
        listeStationsActivity.recreate();
    }
}
