/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4project;

import java.util.ArrayList;

/**
 *
 * @author maxen
 */
public class Question {
    private String titre;
    private ArrayList<String> choix;
    private int response;
    private String id;
    private String category;

    public Question(String category, String id, String titre, ArrayList<String> choix, int response) {
        this.category = category;
        this.id = id;
        this.titre = titre;
        this.choix = choix;
        this.response = response;
    }

    @Override
    public String toString() {
        return "ID : " + id + " - " + "Question : " + titre + "\n";
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<String> getChoix() {
        return choix;
    }

    public int getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
    
    
    
}
