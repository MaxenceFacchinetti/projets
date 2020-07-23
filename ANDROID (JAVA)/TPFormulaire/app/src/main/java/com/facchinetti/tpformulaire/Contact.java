package com.facchinetti.tpformulaire;


import android.widget.ImageView;

public class Contact {
    private ImageView avatarContact;
    private Sexe sexe;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String numTel;
    private String mail;
    private String codePostal;
    private String adresse;
    private double tauxCashflow;

    public Contact(ImageView avatarContact, Sexe sexe, String nom, String prenom, String dateNaissance, String numTel, String mail, String codePostal, String adresse) {
        this.avatarContact = avatarContact;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.mail = mail;
        this.codePostal = codePostal;
        this.adresse = adresse;
        tauxCashflow = 30;
    }

    public double getTauxCashflow() {
        return tauxCashflow;
    }

    public void setTauxCashflow(double tauxCashflow) {
        tauxCashflow = Math.round(tauxCashflow * 10.0)/10.0; // Arrondir au dixième
        this.tauxCashflow = tauxCashflow;
    }

    public void addCashflow(){
        tauxCashflow+=0.3;
        tauxCashflow = Math.round(tauxCashflow * 10.0)/10.0; // Arrondir au dixième
    }

    public void removeCashflow(){
        tauxCashflow-=0.3;
        tauxCashflow = Math.round(tauxCashflow * 10.0)/10.0; // Arrondir au dixième
    }

    public ImageView getAvatarContact() {
        return avatarContact;
    }

    public void setAvatarContact(ImageView avatarContact) {
        this.avatarContact = avatarContact;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "avatarContact=" + avatarContact +
                ", sexe=" + sexe +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", numTel='" + numTel + '\'' +
                ", mail='" + mail + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tauxCashflow=" + tauxCashflow +
                '}';
    }
}
