/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packModele;

/**
 *
 * @author maxen
 */
public class Etudiant {

    private String numero;
    private String nom;
    private String prenom;
    private String bac;
    private String departement;

    public Etudiant(String numero, String nom, String prenom, String departement, String bac) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.bac = bac;
        this.departement = departement;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getBac() {
        return bac;
    }

    public void setBac(String bac) {
        this.bac = bac;
    }

    @Override
    public String toString() {
        return numero + " - " + nom + " " + prenom + " (" + bac + " - " +  departement + ")";
    }

}
