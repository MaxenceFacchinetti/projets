
package packVue;

import java.util.Observer;
import javax.swing.JInternalFrame;
import packModele.Promotion;

public abstract class AbstractVue extends JInternalFrame implements Observer{
    
    protected Promotion promo;
   
    public AbstractVue(Promotion promo){
        this.promo = promo;
        promo.addObserver(this);
    }  

    public Promotion getPromo() {
        return promo;
    } 
}