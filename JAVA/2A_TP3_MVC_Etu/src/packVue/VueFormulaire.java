package packVue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import packControleur.ControleurSupprForm;
import packModele.Promotion;
import packControleur.ControleurAjout;

public class VueFormulaire extends AbstractVue {

    private final JTextField txtNumeroAjout = new JTextField(10);
    private final JTextField txtNumeroSuppr = new JTextField(10);
    private final JTextField txtNom = new JTextField(10);
    private final JTextField txtPrenom = new JTextField(10);
    private final JComboBox boxBac = new JComboBox();
    private final JComboBox boxDpt = new JComboBox();
    private final JLabel lblNumeroAjout = new JLabel("Numero:");
    private final JLabel lblNumeroSuppr = new JLabel("Numero:");
    private final JLabel lblNom = new JLabel("Nom:");
    private final JLabel lblPrenom = new JLabel("Prénom:");
    private final JLabel lblBac = new JLabel("Bac:");
    private final JLabel lblDpt = new JLabel("Dpt:");
    private final JLabel lblPartieAjout = new JLabel("Ajout d'un étudiant");
    private final JLabel lblPartieSuppr = new JLabel("Suppression d'un étudiant:");
    private final JLabel lblEspace = new JLabel("    ");
    private final JLabel lblSeparation = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    private final JButton btAjout = new JButton("Ajout");
    private final JButton btSuppr = new JButton("Supprimer");
    private VueListe vl;

    public VueListe getVl() {
        return vl;
    }

    public void setVl(VueListe vl) {
        this.vl = vl;
    }

    public JTextField getTxtNumeroAjout() {
        return txtNumeroAjout;
    }

    public JTextField getTxtNom() {
        return txtNom;
    }

    public JTextField getTxtPrenom() {
        return txtPrenom;
    }

    public JComboBox getBoxBac() {
        return boxBac;
    }

    public JComboBox getBoxDpt() {
        return boxDpt;
    }

    public VueFormulaire(Promotion file) {
        super(file);
        EcouteurSupprForm listenerSupprForm = new EcouteurSupprForm(this);
        
        initFrame();
        
        EcouteurAjout listenerAjout = new EcouteurAjout(this);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    private class EcouteurAjout implements ActionListener {

        private ControleurAjout controleurAjout;
        private VueFormulaire vf;

        public EcouteurAjout(VueFormulaire vf) {
            this.vf = vf;
            btAjout.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btAjout) {
                controleurAjout = new ControleurAjout(vl,vf);
                controleurAjout.control();
            }
        }

    }

    private class EcouteurSupprForm implements ActionListener {

        ControleurSupprForm controlSupprFormulaire;
        String idSuppr;
        VueFormulaire vf;

        public EcouteurSupprForm(VueFormulaire vf) {

            this.vf = vf;
            btSuppr.addActionListener(this);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btSuppr) {
                idSuppr = txtNumeroSuppr.getText();
                controlSupprFormulaire = new ControleurSupprForm(vl,idSuppr,vf);
                controlSupprFormulaire.control();
            }
        }
    }

    private void initFrame() {
        //remplissage des box
        boxDpt.addItem("- - -");
        for (int i = 1; i < 96; i++) {
            if (i != 20) {

                boxDpt.addItem("" + i);
            } else {
                boxDpt.addItem("2A");
                boxDpt.addItem("2B");
            }
        }
        for (int i = 971; i < 977; i++) {
            boxDpt.addItem("" + i);
        }
        boxDpt.addItem("Autre");
        //seconde box
        boxBac.addItem("- - -");
        boxBac.addItem("S");
        boxBac.addItem("STI");
        boxBac.addItem("ES");
        boxBac.addItem("STG");
        boxBac.addItem("Etranger");
        boxBac.addItem("Autre");

        //placement des objets
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.add(lblPartieAjout, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        this.add(lblNumeroAjout, gc);
        gc.gridx = 1;
        this.add(txtNumeroAjout, gc);
        gc.gridx = 2;
        this.add(lblPrenom, gc);
        gc.gridx = 3;
        this.add(txtPrenom, gc);
        gc.gridx = 4;
        this.add(lblNom, gc);
        gc.gridx = 5;
        this.add(txtNom, gc);
        gc.gridx = 6;
        this.add(lblBac, gc);
        gc.gridx = 7;
        this.add(boxBac, gc);
        gc.gridx = 8;
        this.add(lblDpt, gc);
        gc.gridx = 9;
        this.add(boxDpt, gc);
        gc.gridx = 10;
        this.add(lblEspace, gc);
        gc.gridx = 11;
        this.add(btAjout, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 11;
        //this.add(lblSeparation, gc);
        this.add(new JSeparator(SwingConstants.HORIZONTAL), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.add(lblPartieSuppr, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        this.add(lblNumeroSuppr, gc);
        gc.gridx = 1;
        this.add(txtNumeroSuppr, gc);
        gc.gridx = 11;
        this.add(btSuppr, gc);
    }
}
