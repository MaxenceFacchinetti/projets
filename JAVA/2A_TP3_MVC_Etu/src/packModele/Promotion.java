/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template nomFile, choose Tools | Templates
 * and open the template in the editor.
 */
package packModele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxen
 */
public class Promotion extends Observable {

    private ArrayList<Etudiant> listEtudiants;
    private String nomFile;
    private File file;
    private int nbBacS = 0, nbBacSTI = 0, nbBacES = 0, nbBacSTG = 0, nbBacEtranger = 0, nbBacAutre = 0;

    public Promotion(String nomFile) {
        this.nomFile = nomFile;
        file = new File(nomFile);
        listEtudiants = new ArrayList();
        chargerFichier();
    }
    
    public void modifierEtudiant(int index, Etudiant newEtu){
        listEtudiants.set(index, newEtu);
    }

    private void triNbBac() {

        nbBacSTI = nbBacS = nbBacES = nbBacSTG = nbBacEtranger = nbBacAutre = 0;

        for (Etudiant e : listEtudiants) {
            switch (e.getBac()) {
                case "STI":
                    nbBacSTI++;
                    break;
                case "S":
                    nbBacS++;
                    break;
                case "ES":
                    nbBacES++;
                    break;
                case "STG":
                    nbBacSTG++;
                    break;
                case "Etranger":
                    nbBacEtranger++;
                    break;
                case "Autre":
                    nbBacAutre++;
                    break;
                default:
                    nbBacAutre++;
                    break;
            }
        }
    }

    private void chargerFichier() {
        try {

            String elements[] = new String[5];

            Scanner read = new Scanner(file);

            while (read.hasNext()) {

                elements = read.nextLine().split(";");

                addEtudiant(new Etudiant(elements[0], elements[1], elements[2], elements[3], elements[4]));

            }

            this.triNbBac();

        } catch (Exception e) {
            System.out.println("bug dans chargerFichier");
        }
    }

    public void affichePromo() {
        for (Etudiant e : listEtudiants) {
            System.out.println(e);
        }
    }

    public ArrayList<Etudiant> getListEtudiants() {
        return listEtudiants;
    }

    public void addEtudiant(Etudiant e) {
        for (Etudiant et : listEtudiants) {
            if (e.getNumero().equals(et.getNumero())) {
                return;
            }
        }

        listEtudiants.add(e);
        triNbBac();
        this.notifyObservers();
    }
    
    
    public void notifyObservateurs(){
        this.setChanged();
        this.notifyObservers();
    }

    public boolean deleteEtudiant(String numero) {
        for (Etudiant e : listEtudiants) {
            if (e.getNumero() == numero) {
                listEtudiants.remove(e);
                this.triNbBac();
                return true;
            }
        }
        return false;
    }

    public String getNomFile() {
        return nomFile;
    }

    public int getNbBacS() {
        return nbBacS;
    }

    public int getNbBacSTI() {
        return nbBacSTI;
    }

    public int getNbBacES() {
        return nbBacES;
    }

    public int getNbBacSTG() {
        return nbBacSTG;
    }

    public int getNbBacEtranger() {
        return nbBacEtranger;
    }

    public int getNbBacAutre() {
        return nbBacAutre;
    }

}
