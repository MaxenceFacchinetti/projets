package packVue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import packControleur.ControleurModif;
import packControleur.ControleurSupprList;
import packModele.Etudiant;
import packModele.Promotion;

public class VueListe extends AbstractVue {

    private final JList liste;
    private final JButton btSuppr = new JButton("Supprimer");
    private final JScrollPane scrollPane;
    private final GridBagConstraints gc;
    private Vector<Etudiant> listEtudiants;
    private VueFormulaire vf;

    @Override
    public void update(Observable o, Object arg) {
        liste.updateUI();

        repaint();
    }

    private class EcouteurModif implements MouseListener {

        private VueListe vl;

        public EcouteurModif(VueListe vl) {
            this.vl = vl;
            liste.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent evt) {
            JList list = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
                int indexSelected = liste.getSelectedIndex();

                VueModif vm = new VueModif(win, true, vl);
                vm.setVisible(true);

//                        int index = list.locationToIndex(evt.getPoint());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    private class EcouteurSupprList implements ActionListener {

        private ControleurSupprList controlSupprList;
        private VueListe vl;

        public EcouteurSupprList(VueListe vl) {
            this.vl = vl;
            btSuppr.addActionListener(this);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btSuppr) {
                controlSupprList = new ControleurSupprList(vl);
                controlSupprList.control();
            }
        }

    }

    public void setPromoIntoListEtudiants() {
        listEtudiants.clear();
        for (Etudiant e : promo.getListEtudiants()) {
            listEtudiants.add(e);
        }
    }

    public VueFormulaire getVf() {
        return vf;
    }

    public void setVf(VueFormulaire vf) {
        this.vf = vf;
    }

    MainWindow win;

    public VueListe(Promotion promo, MainWindow win) {
        super(promo);
        this.win = win;
        this.vf = vf;
        liste = new JList();
        listEtudiants = new Vector();
        scrollPane = new JScrollPane(liste);
        gc = new GridBagConstraints();
        EcouteurModif listenerModif = new EcouteurModif(this);
        EcouteurSupprList listenerSupprList = new EcouteurSupprList(this);
        setPromoIntoListEtudiants();

        liste.setLayoutOrientation(JList.VERTICAL);

        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayout(new GridBagLayout());

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(scrollPane, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(btSuppr, gc);
        remplissageListe();
        this.pack();
        liste.setVisibleRowCount((int) (this.getHeight() / 8));
        liste.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.pack();
    }

    public void remplissageListe() {
        liste.removeAll();
        liste.setListData(listEtudiants);
        this.pack();
    }

    public JList getListe() {
        return liste;
    }

    public Vector<Etudiant> getListEtudiants() {
        return listEtudiants;
    }

}
