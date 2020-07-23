/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControleur;

import javax.swing.JOptionPane;
import packModele.Etudiant;
import packVue.VueListe;
import packVue.VueModif;

/**
 *
 * @author maxen
 */
public class ControleurModif extends AbstractControleur {

    private Etudiant newEtu, etuModif;
    private VueModif vm;

    public ControleurModif(VueListe vl, VueModif vm, Etudiant newEtu, Etudiant etuModif) {
        super(vl);
        this.vm = vm;
        this.newEtu = newEtu;
        this.etuModif = etuModif;
    }

    @Override
    public void control() {

        System.out.println(newEtu.getNumero());

        if (newEtu.getNom().equals("") || newEtu.getNumero().equals("") || newEtu.getPrenom().equals("")) {
            JOptionPane.showMessageDialog(vm, "Vous ne pouvez pas laisser de champs vides.");
        } else if (numeroEtudiantValide(newEtu.getNumero()) == -1) {
            JOptionPane.showMessageDialog(vm, "L'ID ne doit comporter uniquement des chiffres.");
        } else if (numeroEtudiantValide(newEtu.getNumero()) == 0 && !newEtu.getNumero().equals(etuModif.getNumero())) {

            JOptionPane.showMessageDialog(vm, "L'ID est déjà pris par un autre étudiant.");
        } else {
            vl.getPromo().modifierEtudiant(vl.getListe().getSelectedIndex(), newEtu);
            vl.setPromoIntoListEtudiants();
            vl.remplissageListe();
            vl.getPromo().notifyObservateurs();
            vm.dispose();
        }
    }

}
