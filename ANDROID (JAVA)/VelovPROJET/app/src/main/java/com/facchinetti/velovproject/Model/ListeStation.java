package com.facchinetti.velovproject.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListeStation { // serializable ne marche pas
    private ArrayList<Station> listStation;
    private int nbFavs;

    public ListeStation() {
        listStation = new ArrayList<>();
        nbFavs = 0;
    }

    public int getNbFavs() {
        return nbFavs;
    }

    public void setNbFavs(int nbFavs) {
        this.nbFavs = nbFavs;
    }

    public void updateNbFavs(){
        nbFavs = 0;
        for(Station s : listStation){
            if(s.isFavorite()){
                nbFavs++;
            }
        }
    }

    // Permet de placer les favoris au début de la liste.

    public void updateListStation(){
        int it = 0;
        for(int i = 0; i < listStation.size() && it < nbFavs; i++){
            if(listStation.get(i).isFavorite()){
                Station tmp = listStation.get(i);
                listStation.set(i,listStation.get(it));
                listStation.set(it,tmp);
                it++;
            }
        }
    }

    public ArrayList<Station> getListStation() {
        return listStation;
    }

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }

    public void addStation(Station s, boolean fav){
        s.setFavorite(fav);
        listStation.add(s);
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        /*for(Station s : listStation){
            Log.d("MAXENCE","slt");
            sb.append(s.toString());
        }*/

        for(Station s : listStation){
            sb.append("Numero : " + s.getNumber() + "\n");
            break;
        }
        return sb.toString();
    }

    public static class Station {

        private String number;
        private String contractName;
        private String name;
        private String address;
        private boolean banking;
        private boolean bonus;
        private int bikeStands;
        private int availableBikeStands;
        private int availableBikes;
        private Status status;
        private String lastUpdate;
        private Position position;
        private boolean favorite;

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public Station(String number,
                       String contractName,
                       String name,
                       String address,
                       boolean banking,
                       boolean bonus,
                       int bikeStands,
                       int availableBikeStands,
                       int availableBikes,
                       Status status,
                       String lastUpdate,
                       Position position) {
            this.number = number;
            this.contractName = contractName;
            this.name = name;
            this.address = address;
            this.banking = banking;
            this.bonus = bonus;
            this.bikeStands = bikeStands;
            this.availableBikeStands = availableBikeStands;
            this.availableBikes = availableBikes;
            this.status = status;
            this.lastUpdate = lastUpdate;
            this.position = position;
        }

        public Station(String number,
                       String contractName,
                       String name,
                       String address,
                       boolean banking,
                       boolean bonus,
                       int bikeStands,
                       int availableBikeStands,
                       int availableBikes,
                       Status status,
                       String lastUpdate,
                       double lat, double lng) {
            this.number = number;
            this.contractName = contractName;
            this.name = name;
            this.address = address;
            this.banking = banking;
            this.bonus = bonus;
            this.bikeStands = bikeStands;
            this.availableBikeStands = availableBikeStands;
            this.availableBikes = availableBikes;
            this.status = status;
            this.lastUpdate = lastUpdate;
            this.position = new Position(lat,lng);
        }

        public Station() {

        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isBanking() {
            return banking;
        }

        public void setBanking(boolean banking) {
            this.banking = banking;
        }

        public boolean isBonus() {
            return bonus;
        }

        public void setBonus(boolean bonus) {
            this.bonus = bonus;
        }

        public int getBikeStands() {
            return bikeStands;
        }

        public void setBikeStands(int bikeStands) {
            this.bikeStands = bikeStands;
        }

        public int getAvailableBikeStands() {
            return availableBikeStands;
        }

        public void setAvailableBikeStands(int availableBikeStands) {
            this.availableBikeStands = availableBikeStands;
        }

        public int getAvailableBikes() {
            return availableBikes;
        }

        public void setAvailableBikes(int availableBikes) {
            this.availableBikes = availableBikes;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(String lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public enum Status {
            OUVERT,FERME;
        }

        public static class Position{
            private double lat,lng;

            public Position(double lat, double lng) {
                this.lat = lat;
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }

        @Override
        public String toString() {
            return "Station : \n\t" +
                    "Number : " + number + "\n\t" +
                    "Contract Name : " + contractName + "\n\t" +
                    "Name : " + name + "\n\t" +
                    "Address : " + address + "\n\t" +
                    "Banking : " + banking + "\n\t" +
                    "Bonus : " + bonus + "\n\t" +
                    "BikeStands : " + bikeStands + "\n\t" +
                    "AvailableBikeStands : " + availableBikeStands + "\n\t" +
                    "AvailableBikes + " + availableBikes + "\n\t" +
                    "Status : " + status + "\n\t" +
                    "Position : \n\t\t" +
                    "Lat : " + position.getLat() + "\n\t\t" +
                    "Lng : " + position.getLng() + "\n";
        }
    }

    public void trierFav(){

    }

}
