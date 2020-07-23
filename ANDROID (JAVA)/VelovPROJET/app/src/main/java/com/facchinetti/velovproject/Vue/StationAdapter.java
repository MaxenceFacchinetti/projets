package com.facchinetti.velovproject.Vue;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.facchinetti.velovproject.Model.ListeStation;
import com.facchinetti.velovproject.R;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class StationAdapter extends BaseAdapter {

    Context context;
    ListeStationsActivity listeStationsActivity;

    public StationAdapter(ListeStationsActivity listeStationsActivity) {
        this.context = listeStationsActivity.getBaseContext();
        this.listeStationsActivity = listeStationsActivity;
    }

    @Override
    public int getCount() {
        return MainActivity.listeStation.getListStation().size();
    }

    @Override
    public ListeStation.Station getItem(int position) {
        return MainActivity.listeStation.getListStation().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.activity_station, parent, false);

        final ViewHolder vh = new ViewHolder();
        vh.textViewNameStation = (TextView) convertView.findViewById(R.id.textViewNameStation);
        vh.textViewNumberStation = (TextView) convertView.findViewById(R.id.textViewNumberStation);
        vh.imageViewFavStation = (ImageView) convertView.findViewById(R.id.imageViewFavStation);
        vh.imageViewStatusStation = (ImageView) convertView.findViewById(R.id.imageViewStatusStation);

        vh.textViewNumberStation.setText(MainActivity.listeStation.getListStation().get(position).getNumber());
        vh.textViewNameStation.setText(MainActivity.listeStation.getListStation().get(position).getName());

        vh.imageViewFavStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listeStation.getListStation().get(position).isFavorite()){
                    MainActivity.listeStation.getListStation().get(position).setFavorite(false);
                    vh.imageViewFavStation.setImageDrawable(context.getDrawable(R.drawable.notfav));
                }
                else{
                    MainActivity.listeStation.getListStation().get(position).setFavorite(true);
                    vh.imageViewFavStation.setImageDrawable(context.getDrawable(R.drawable.fav));
                }
                MainActivity.listeStation.updateNbFavs();
                MainActivity.listeStation.updateListStation();
                listeStationsActivity.recreate(); // actualiser afin que les stations favorites soit en début de liste
            }
        });

        if(MainActivity.listeStation.getListStation().get(position).getStatus() == ListeStation.Station.Status.FERME){
            vh.imageViewStatusStation.setImageDrawable(context.getDrawable(R.drawable.closed_station));
        }
        else{
            vh.imageViewStatusStation.setImageDrawable(context.getDrawable(R.drawable.open_station));
        }

        if(MainActivity.listeStation.getListStation().get(position).isFavorite()){
            vh.imageViewFavStation.setImageDrawable(context.getDrawable(R.drawable.fav));
        }
        else{
            vh.imageViewFavStation.setImageDrawable(context.getDrawable(R.drawable.notfav));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView textViewNameStation;
        TextView textViewNumberStation;
        ImageView imageViewStatusStation;
        ImageView imageViewFavStation;
    }

}
