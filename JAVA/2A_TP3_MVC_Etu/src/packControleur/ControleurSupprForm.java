/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControleur;

import javax.swing.JOptionPane;
import packModele.Etudiant;
import packVue.VueFormulaire;
import packVue.VueListe;

/**
 *
 * @author maxen
 */
public class ControleurSupprForm extends AbstractControleur {

    String id;
    VueFormulaire vf;
    
    public ControleurSupprForm(VueListe vl, String id, VueFormulaire vf){
        super(vl);
        this.id = id;
        this.vf = vf;
    }

    @Override
    public void control() {
        if (id.matches("^[0-9]+$")) {
            for(Etudiant etu : vf.getPromo().getListEtudiants()){
                if(id.equals(etu.getNumero())){
                   vl.getListEtudiants().remove(etu);
                   vf.getPromo().deleteEtudiant(etu.getNumero());
                   vf.getPromo().notifyObservateurs();
                   return;
                }
            }
            JOptionPane.showMessageDialog(null, "L'étudiant ayant l'id " + id + " n'existe pas.");
        } else if (id.matches("^$")) {
            JOptionPane.showMessageDialog(null, "Vous devez entrer un ID pour supprimer un étudiant.");
        } else {
            JOptionPane.showMessageDialog(null, "L'ID doit comporter uniquement des chiffres.");
        }
    }

}
