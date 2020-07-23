/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import packModele.Etudiant;
import packVue.VueFormulaire;
import packVue.VueListe;

/**
 *
 * @author maxen
 */
public class ControleurAjout extends AbstractControleur {

    private VueFormulaire vf;

    public ControleurAjout(VueListe vl, VueFormulaire vf) {
        super(vl);
        this.vf = vf;
    }

    @Override
    public void control() {
        if (!vf.getTxtNumeroAjout().getText().equals("")
                && !vf.getTxtPrenom().getText().equals("")
                && !vf.getTxtNom().getText().equals("")
                && !vf.getBoxBac().getSelectedItem().toString().equals("- - -")
                && !vf.getBoxDpt().getSelectedItem().toString().equals("- - -")) {

            String numero, nom, prenom, departement, bac;
            numero = vf.getTxtNumeroAjout().getText();
            nom = vf.getTxtNom().getText();
            prenom = vf.getTxtPrenom().getText();
            departement = vf.getBoxDpt().getSelectedItem().toString();
            bac =  vf.getBoxBac().getSelectedItem().toString();

            if (numeroEtudiantValide(numero) == 1) {
                Etudiant etu = new Etudiant(numero, nom, prenom, departement, bac);
                System.out.println(etu);
                vl.getPromo().addEtudiant(etu);
                vl.getListEtudiants().addElement(etu);
                vl.remplissageListe();
                vl.getPromo().notifyObservateurs();
            } else if (numeroEtudiantValide(numero) == 0) {
                JOptionPane.showMessageDialog(vf, "Ce numéro étudiant n'est plus disponible.");
            } else {
                JOptionPane.showMessageDialog(vf, "Le numéro d'étudiant ne doit comporter uniquement des chiffres.");
            }
        } else {
            JOptionPane.showMessageDialog(vf, "Vous n'avez pas entré tous les champs.");
        }
    }

}
