package axel.view;

import axel.Observateur;
import axel.control.Contr_SuppListe;
import axel.model.Etudiant;
import axel.model.Promotion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Liste extends JInternalFrame implements Observateur {
    private Promotion promotion;

    // Controleurs
    Contr_SuppListe control_supp;

    // Components
    private JScrollPane scrollPanel;
    private JList listbox;
    private JButton btnDelete;

    public Vue_Liste(Promotion promotion) {
        setTitle("Liste");
        setResizable(true);
        setVisible(true);

        this.promotion = promotion;

        this.btnDelete = new JButton("Supprimer");

        // Create some items to add to the list
        listbox = new JList(promotion.getListeEtudiants().toArray());

        // Create a scrollPanel to hold all other components
        scrollPanel = new JScrollPane(listbox);


        //Layout
        this.setLayout(new GridBagLayout());

        //Constraint
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.weightx = gbc.weighty = 1;
        gbc.gridx = gbc.gridy = 0;

        //ajout à la fenetre
        gbc.weighty = 50;
        add(scrollPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        add(btnDelete, gbc);

        //ajout Listener
        this.btnDelete.addActionListener(new Ecouteur_Suppr2());
        //initialisation Controlleur
        control_supp = new Contr_SuppListe(this.promotion, this);

        //ajout en tant que observateur
        this.promotion.addObservateur(this);
    }

    @Override
    public void update() {
        // Create some items to add to the list
        listbox.setListData(promotion.getListeEtudiants().toArray());

        this.revalidate();
        this.repaint();
    }

    private class Ecouteur_Suppr2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDelete) {

                ArrayList<String> listString = new ArrayList<>();

                Object _indices[] = listbox.getSelectedValues();

                for (int i = 0; i < _indices.length; i++) {
                    String _id = ((Etudiant) _indices[i]).getId();
                    listString.add(_id);
                }
                if (listString.size() > 0) {
                    control_supp.control(listString);
                } else {
                    control_supp.control(null);
                }
            }
        }
    }
}
