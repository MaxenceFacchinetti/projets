/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControleur;

import java.util.Vector;
import javax.swing.JList;
import javax.swing.JOptionPane;
import packModele.Etudiant;
import packModele.Promotion;
import packVue.VueListe;

/**
 *
 * @author maxen
 */
public class ControleurSupprList extends AbstractControleur {

    public ControleurSupprList(VueListe vl) {
        super(vl);
    }

    @Override
    public void control() {

        System.out.println(vl.getListe().getSelectedIndex());
        
        

        if (vl.getPromo().getListEtudiants().size() > 0) {
            if (vl.getListe().getSelectedValuesList().size() > 0) {

                for (Object e : vl.getListe().getSelectedValuesList()) {
                    for (Etudiant etu : vl.getPromo().getListEtudiants()) {
                        if (e.toString().equals(etu.toString())) {
                            vl.getListEtudiants().removeElement(etu);
                            vl.getPromo().deleteEtudiant(etu.getNumero());

                            break;
                        }
                    }
                }
                
                if (!indexDansListe()) {
                    System.out.println(vl.getListe().getSelectedIndices().length);
                    vl.getListe().setSelectedIndex(0);
                }
                
                vl.getPromo().notifyObservateurs();
            }
        }
    }

    public boolean indexDansListe() {
        
        
        if(vl.getListe().getSelectedIndex() >= vl.getPromo().getListEtudiants().size()){
            System.out.println("plus grand");
            return false;
        }        

        for (int i : vl.getListe().getSelectedIndices()) {
            if (i > vl.getPromo().getListEtudiants().size()) {
                return false;
            }

        }
        return true;
    }
}
