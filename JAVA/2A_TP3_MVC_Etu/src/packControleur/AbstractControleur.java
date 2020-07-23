/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControleur;

import packModele.Etudiant;
import packVue.VueListe;




/**
 *
 * @author maxen
 */
public abstract class AbstractControleur {
    public abstract void control();
    
    protected VueListe vl;
    
    public AbstractControleur(VueListe vl){
        this.vl = vl;
    }
    
    protected int numeroEtudiantValide(String num) {
        
        /*
        
        Si numeroValide renvoie -1 : le numéro ne respecte pas le regex
        Si numeroValide renvoie 0 : le numéro appartient déjà à un étudiant
        Sinon, numeroValide renvoie 1 : il est donc valide.
        
        */
        
        if (num.matches("^[0-9]+$")) {
            for (Etudiant e : vl.getPromo().getListEtudiants()) {
                if (e.getNumero().equals(num)) {
                    return 0;
                }
            }
            return 1;
        }
        return -1;
    }
    
}
