/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4project;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author maxen
 */
public class QCM {
    private String category;
    private String id;
    private ArrayList<Question> listQuestions;

    public QCM(String category, String id) {
        this.category = category;
        this.id = id;
        listQuestions = new ArrayList();
    }
    
    public QCM(){
        category = null;
        id = null;
        listQuestions = null;
    }

    @Override
    public String toString() {
        return "Categorie : " + category +
                "\nID : " + id +
                "\nQuestions : " + listQuestions;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

   public void addQuestion(Question q){
       listQuestions.add(q);
   }
    
    public void printQuestion(){
        for(Question q : listQuestions){
            System.out.println(q);
        }
    }

    public ArrayList<Question> getListQuestions() {
        return listQuestions;
    }
    
    
    
    
}
