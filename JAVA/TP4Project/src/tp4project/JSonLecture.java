/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author maxen
 */
public class JSonLecture {

    public static final String url = "http://perso.univ-lyon1.fr/lionel.buathier/qcm/qcm.php";
    private static final String listeQCM = url + "?file=liste-qcm";
    private static final String listeQuestionPHP1 = url + "?file=php-01";
    private static final String listeQuestionPHP2 = url + "?file=php-02";
    private String fichierJSONQCM;
    private JSONObject objetJSONQCM;
    private String fichierJSONQuestionPHP1;
    private String fichierJSONQuestionPHP2;
    private ArrayList<JSONObject> objetJSONQuestion;
    private ArrayList<QCM> listQCM;
    private ArrayList<Question> listQuestions;
    private Map<String, ArrayList<String>> catID;

    public void print(String s) {
        System.out.println(s);
    }

    public void initQCM() {
        objetJSONQCM = new JSONObject(fichierJSONQCM);
        listQCM = new ArrayList();
        catID = new HashMap<>();
        ArrayList<String> listID;
        for (int i = 0; i < objetJSONQCM.names().length(); i++) {
            listID = new ArrayList();
            String category = objetJSONQCM.names().getString(i);
            //print(category);
            for (int j = 0; j < objetJSONQCM.getJSONObject(category).names().length(); j++) {
                String varID = objetJSONQCM.getJSONObject(category).names().getString(j);
                //print("\t" + varID);
                String id = objetJSONQCM.getJSONObject(category).getString(varID);
                //print("\t\t" + id);
                listQCM.add(new QCM(category, id));

                listID.add(id);

            }
            catID.put(category, listID);
        }

    }

    public void initQuestion() {
        objetJSONQuestion = new ArrayList();
        objetJSONQuestion.add(new JSONObject(fichierJSONQuestionPHP1));
        objetJSONQuestion.add(new JSONObject(fichierJSONQuestionPHP2));
        listQuestions = new ArrayList();
        String category, id, titre;
        int reponse;
        ArrayList<String> choix;
        
        category = id = "";

        for (JSONObject jsonQuestion : objetJSONQuestion) {

            category = jsonQuestion.getString("categorie");
            id = jsonQuestion.getString("id");
            
            //print(category + " - " + id);

            for (int i = 0; i < jsonQuestion.getJSONArray("questions").length(); i++) {
                choix = new ArrayList();
                //print(jsonQuestion.getJSONArray("questions").getJSONObject(i).toString());
                titre = jsonQuestion.getJSONArray("questions").getJSONObject(i).getString("titre");
                reponse = jsonQuestion.getJSONArray("questions").getJSONObject(i).getInt("reponse");

                for (int j = 0; j < jsonQuestion.getJSONArray("questions").getJSONObject(i).getJSONArray("choix").length(); j++) {
                    choix.add(jsonQuestion.getJSONArray("questions").getJSONObject(i).getJSONArray("choix").getString(j));
                }

                listQuestions.add(new Question(category, id, titre, choix, reponse));

            }
        }
        
//        for(Question q : listQuestion){
//            print(q.toString());
//        }

        for (QCM q : listQCM) {
            
            for(Question qst : listQuestions){
                if(q.getCategory().equals(qst.getCategory().toLowerCase()) && q.getId().equals(qst.getId())){
                    q.addQuestion(qst);
                }
            }
            
//            if (q.getCategory().equals(category.toLowerCase()) && q.getId().equals(id)) { // obligé d'utiliser toLowerCase() car dans le JSON du QCM, les catégories sont en minuscules, et dans le JSON des questions, elles sont en majuscule, donc pas égales.
//                print(q.getCategory() + " - " + q.getId());
//                q.setListQuestions(listQuestions);
//
//            }

        }

    }

    public void qcmConsole() {
        print("Choisissez votre catégorie :");

        String categorieChoisie, idChoisi = "";

        for (Map.Entry<String, ArrayList<String>> map : catID.entrySet()) {
            print("\t" + map.getKey());
        }
        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        while (!verifCategorie(reponse)) { // tant que la catégorie n'est pas bonne, il ne peut pas avancer, obligé de mettre un nom de catégorie correct
            print("La catégorie que vous avez entré n'existe pas.");
            reponse = sc.nextLine();
        }
        categorieChoisie = reponse;
        print("Choisissez l'ID : ");

        for (Map.Entry<String, ArrayList<String>> map : catID.entrySet()) {
            if (map.getKey().equals(reponse)) {
                for (String s : map.getValue()) {
                    print("\t" + s);
                }
                reponse = sc.nextLine();
                while (!verifString(reponse, map.getValue())) {
                    print("L'ID que vous avez entré n'existe pas.");
                    reponse = sc.nextLine();
                }
                idChoisi = reponse;
                break;
            }
        }

        QCM qcmChoisi = new QCM(); // sinon il indiquera que qcmChoisi peut ne pas être initialisé
        for (QCM q : listQCM) {
            if (q.getCategory().equals(categorieChoisie) && q.getId().equals(idChoisi)) {
                qcmChoisi = q;
                break;
            }
        }
        int score = 0;
        int numQuestion = 1;
        for (Question q : qcmChoisi.getListQuestions()) {
            print("Question " + numQuestion + "/" + qcmChoisi.getListQuestions().size() + " : " + q.getTitre());
            for (String choixPossibles : q.getChoix()) {
                print("\t" + choixPossibles);
            }
            reponse = sc.nextLine();
            while (!verifString(reponse, q.getChoix())) {
                print("La réponse que vous avez entré n'existe pas.");
                reponse = sc.nextLine();
            }

            if (reponse.equals(q.getChoix().get(q.getResponse()))) {
                score++;
            }

            numQuestion++;
        }

        print("Votre score est de : " + score);
    }

    public void qcmWindow() {
        Window fen = new Window(listQCM);
    }

    public boolean verifString(String s, ArrayList<String> listString) {
        for (String str : listString) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifCategorie(String s) {
        for (Map.Entry map : catID.entrySet()) {
            if (map.getKey().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        
        try {
            
            
            
            URL urlQCM = new URL(listeQCM);
            URL urlQuestionPHP1 = new URL(listeQuestionPHP1);
            URL urlQuestionPHP2 = new URL(listeQuestionPHP2);

            HttpURLConnection urlConnectionQCM = (HttpURLConnection) urlQCM.openConnection();
            HttpURLConnection urlConnectionQuestionPHP1 = (HttpURLConnection) urlQuestionPHP1.openConnection();
            HttpURLConnection urlConnectionQuestionPHP2 = (HttpURLConnection) urlQuestionPHP2.openConnection();

            if (urlConnectionQCM.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader inQCM = new BufferedReader(new InputStreamReader(urlConnectionQCM.getInputStream()));
                BufferedReader inQuestionPHP1 = new BufferedReader(new InputStreamReader(urlConnectionQuestionPHP1.getInputStream()));
                BufferedReader inQuestionPHP2 = new BufferedReader(new InputStreamReader(urlConnectionQuestionPHP2.getInputStream()));

                String inputLine;
                StringBuilder responseQCM = new StringBuilder();
                StringBuilder responseQuestionPHP1 = new StringBuilder();
                StringBuilder responseQuestionPHP2 = new StringBuilder();

                while ((inputLine = inQCM.readLine()) != null) {
                    responseQCM.append(inputLine);
                }

                fichierJSONQCM = responseQCM.toString();

                while ((inputLine = inQuestionPHP1.readLine()) != null) {
                    responseQuestionPHP1.append(inputLine);
                }

                fichierJSONQuestionPHP1 = responseQuestionPHP1.toString();

                while ((inputLine = inQuestionPHP2.readLine()) != null) {
                    responseQuestionPHP2.append(inputLine);
                }

                fichierJSONQuestionPHP2 = responseQuestionPHP2.toString();

                inQCM.close();   // et on ferme le flux
                inQuestionPHP1.close();
                inQuestionPHP2.close();
                
                

                initQCM();
                initQuestion();

//                qcmConsole(); // mis en commentaire pour laisser place à qcmWindow();
                qcmWindow();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("pb url");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("pb d'acces au serveur");
        }

    }

}
