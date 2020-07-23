/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author maxen
 */
public class Window extends javax.swing.JFrame {

    /**
     * Creates new form WindowTest
     */
    private ArrayList<QCM> listQCM;
    private Vector<String> listID;
    private GridBagConstraints gridBagConstraintsQuestions;
    private GridBagLayout gridBagLayoutWindow;
    private GridBagConstraints gridBagConstraintsWindow;
    private JPanelNoQuestions panoNoQuestions;
    private JPanelNoMoreQuestions panoNoMoreQuestions;
    private MenuChoisi menuChoisi;
    private Map<String, Integer> score;
    private Map<String, Integer> nbQuestions;
    private JPanelScore panoScore;
    private Map<String, Integer> itQuestions;
    private JPanel panoPrincipal;
    private boolean reponseSelected = false;

    public Window(ArrayList<QCM> listQCM) {
        this.listQCM = listQCM;
        menuChoisi = MenuChoisi.QUESTIONS;
        this.setVisible(true);
        remplissageListID();
        initComponents();
    }

    public void remplissageListID() {
        listID = new Vector();
        score = new HashMap<>();
        nbQuestions = new HashMap<>();
        itQuestions = new HashMap<>();
        for (QCM q : listQCM) {
            listID.add(q.getId());
            score.put(q.getId(), 0);
            itQuestions.put(q.getId(), 0);
            nbQuestions.put(q.getId(), q.getListQuestions().size());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jList = new javax.swing.JScrollPane();
        jListID = new javax.swing.JList<>();
        panoQuestions = new javax.swing.JPanel();
        questionTitre = new javax.swing.JLabel();
        suivantBtn = new javax.swing.JButton();
        listRadioButton = new ArrayList();
        menuBar = new javax.swing.JMenuBar();
        menuQCM = new javax.swing.JMenu();
        questionsMenuItem = new javax.swing.JMenuItem();
        scoreMenuItem = new javax.swing.JMenuItem();
        panoNoQuestions = new JPanelNoQuestions();
        panoNoMoreQuestions = new JPanelNoMoreQuestions();
        gridBagConstraintsWindow = new GridBagConstraints();
        gridBagLayoutWindow = new GridBagLayout();
        panoScore = new JPanelScore();
        panoPrincipal = new JPanel();

        this.setContentPane(panoPrincipal);

        suivantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itQ, newScore;
                for (QCM q : listQCM) {
                    if (q.getId().equals(jListID.getSelectedValue())) {
                        

                        for (int i = 0; i < listRadioButton.size(); i++) {
                            if (listRadioButton.get(i).isSelected()) {
                                if (q.getListQuestions().get(itQuestions.get(q.getId())).getResponse() == i) {
                                    newScore = score.get(q.getId()) + 1;
                                    score.replace(q.getId(), newScore);
                                } else {
                                }
                                itQ = itQuestions.get(q.getId()) + 1;
                                itQuestions.replace(q.getId(), itQ);
                                initQuestions();
                                break;
                            }
                            if(i == listRadioButton.size() - 1){
                                JOptionPane.showMessageDialog(panoPrincipal, "Veuillez entrer une réponse.");
                            }
                        }

                    }
                }
            }

        });

        gridBagLayoutWindow.setConstraints(this, gridBagConstraintsWindow);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QCM");
        getContentPane().setLayout(gridBagLayoutWindow);

        jListID.setListData(listID);
        jList.setViewportView(jListID);

        jListID.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panoQuestions.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panoQuestions.setLayout(new java.awt.GridBagLayout());

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                listListener(e);
            }
        };
        jListID.addMouseListener(mouseListener);

        gridBagConstraintsWindow.gridx = 0;
        gridBagConstraintsWindow.gridy = 0;

        getContentPane().add(jList, gridBagConstraintsWindow);

        gridBagConstraintsWindow.gridx = 1;
        gridBagConstraintsWindow.gridy = 0;

        getContentPane().add(panoQuestions, gridBagConstraintsWindow);
        getContentPane().add(panoNoQuestions, gridBagConstraintsWindow);
        getContentPane().add(panoNoMoreQuestions, gridBagConstraintsWindow);
        getContentPane().add(panoScore, gridBagConstraintsWindow);

        panoNoQuestions.setVisible(false);
        panoNoMoreQuestions.setVisible(false);
        panoQuestions.setVisible(false);
        panoScore.setVisible(false);

        menuQCM.setText("QCM");

        questionsMenuItem.setText("Questions");
        questionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionsMenuItemActionPerformed(evt);
            }
        });
        menuQCM.add(questionsMenuItem);

        scoreMenuItem.setText("Score");
        scoreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreMenuItemActionPerformed(evt);
            }
        });
        menuQCM.add(scoreMenuItem);

        menuBar.add(menuQCM);

        questionsMenuItem.setSelected(true);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>                        

    private void radioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void questionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        menuChoisi = MenuChoisi.QUESTIONS;
        initQuestions();
        repaint();
    }

    private void scoreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        menuChoisi = MenuChoisi.SCORE;
        initScore();
        repaint();
    }

    private void initQuestions() {
        panoScore.setVisible(false);
        for (QCM q : listQCM) {
            if (jListID.getSelectedValue().equals(q.getId())) {
                if (q.getListQuestions().size() == 0) {
                    getContentPane().add(panoNoQuestions);
                    panoNoMoreQuestions.setVisible(false);
                    panoNoQuestions.setVisible(true);
                    panoQuestions.setVisible(false);
                    pack();
                } else {

                    if (itQuestions.get(q.getId()) == nbQuestions.get(q.getId())) {
                        panoNoMoreQuestions.setVisible(true);
                        panoNoQuestions.setVisible(false);
                        panoQuestions.setVisible(false);
                        pack();
                    } else {
                        for (Map.Entry<String, Integer> map : itQuestions.entrySet()) {
                            if (q.getId().equals(map.getKey())) {

                                if (nbQuestions.get(map.getKey()) > map.getValue()) {

                                    panoQuestions.removeAll();

                                    gridBagConstraintsQuestions = new java.awt.GridBagConstraints();
                                    questionTitre.setText(q.getListQuestions().get(map.getValue()).getTitre());
                                    gridBagConstraintsQuestions.gridx = 0;
                                    gridBagConstraintsQuestions.gridy = 0;
                                    panoQuestions.add(questionTitre, gridBagConstraintsQuestions);

                                    suivantBtn.setText("Suivant");

                                    for (String choix : q.getListQuestions().get(map.getValue()).getChoix()) {
                                        listRadioButton.add(new JRadioButton());
                                    }

                                    for (int i = 0; i < q.getListQuestions().get(map.getValue()).getChoix().size(); i++) {
                                        listRadioButton.get(i).setText(q.getListQuestions().get(map.getValue()).getChoix().get(i));
                                        buttonGroup1.add(listRadioButton.get(i));
                                        gridBagConstraintsQuestions.gridx = 0;
                                        gridBagConstraintsQuestions.gridy = i + 1;
                                        panoQuestions.add(listRadioButton.get(i), gridBagConstraintsQuestions);
                                    }

                                    gridBagConstraintsQuestions.gridx = 0;
                                    gridBagConstraintsQuestions.gridy = listRadioButton.size() + 1;
                                    panoQuestions.add(suivantBtn, gridBagConstraintsQuestions);

                                    panoNoQuestions.setVisible(false);
                                    panoQuestions.setVisible(true);
                                    panoNoMoreQuestions.setVisible(false);
                                    pack();
                                }
                                break;
                            }
                        }
                    }

                }
                break;
            }
        }

    }

    private void initScore() {
        panoNoQuestions.setVisible(false);
        panoNoMoreQuestions.setVisible(false);
        panoQuestions.setVisible(false);
        panoScore.setVisible(true);

        for (Map.Entry<String, Integer> map : score.entrySet()) {
            if (jListID.getSelectedValue().equals(map.getKey())) {
                panoScore.setScore(map.getValue());
            }
        }
        for (Map.Entry<String, Integer> map : nbQuestions.entrySet()) {
            if (jListID.getSelectedValue().equals(map.getKey())) {
                panoScore.setNbQuestions(map.getValue());
            }
        }
        pack();
    }

    private void listListener(MouseEvent e) {
        if (menuChoisi == MenuChoisi.QUESTIONS) {
            initQuestions();
        } else {
            initScore();
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new WindowTest().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jList;
    private javax.swing.JList<String> jListID;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuQCM;
    private javax.swing.JPanel panoQuestions;
    private javax.swing.JLabel questionTitre;
    private javax.swing.JMenuItem questionsMenuItem;
    private ArrayList<JRadioButton> listRadioButton;
    private javax.swing.JMenuItem scoreMenuItem;
    private javax.swing.JButton suivantBtn;
    // End of variables declaration                   
}
