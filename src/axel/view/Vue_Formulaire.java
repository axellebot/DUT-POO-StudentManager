package axel.view;

import axel.control.Contr_AjoutForm;
import axel.control.Contr_SuppForm;
import axel.control.Contr_SuppListe;
import axel.model.Etudiant;
import axel.model.Promotion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Formulaire extends JInternalFrame {
    private Promotion promotion;

    // Controlleur
    private Contr_AjoutForm control_ajout;
    private Contr_SuppForm control_supp;

    private JLabel lblAdd, lblDelete;
    private JLabel lblNum1, lblPrenom, lblNom, lblBac, lblDpt, lblNum2;
    private JTextField txtNum1, txtNum2, txtPrenom, txtNom;
    private JComboBox<String> cbbBac, cbbDpt;
    private JButton btnAdd, btnDelete;


    public Vue_Formulaire(Promotion promotion) {
        super("Form",true,true,true,true);

        this.promotion = promotion;

        //setPreferredSize(new Dimension(500,100));
        pack();
        setVisible(true);

        //labels
        lblAdd = new JLabel("Ajout d'un étudiant :");
        lblDelete = new JLabel("Suppression d'un étudiant :");
        lblNum1 = new JLabel("Numéro :");
        lblPrenom = new JLabel("Prénom :");
        lblNom = new JLabel("Nom :");
        lblBac = new JLabel("Bac :");
        lblDpt = new JLabel("Dpt :");
        lblNum2 = new JLabel("Numéro :");

        //txtField
        txtNum1 = new JTextField("", 10);
        txtPrenom = new JTextField("", 10);
        txtNom = new JTextField("", 10);
        txtNum2 = new JTextField("", 10);

        //button
        btnAdd = new JButton("Ajouter");
        btnDelete = new JButton("Suprimer");

        //combobox
        cbbBac = new JComboBox<String>();
        String bacs[] = {"S", "ES", "STI", "STG", "Etranger", "Autre"};
        for (int i = 0; i < bacs.length; i++) {
            cbbBac.addItem(bacs[i]);
        }

        cbbDpt = new JComboBox<String>();
        for (Integer i = 1; i < 95; i++) {
            if (i < 10) {
                cbbDpt.addItem("0" + i.toString());
            } else {
                cbbDpt.addItem(i.toString());
            }
        }


        //Layout
        this.setLayout(new GridBagLayout());

        //Constraint
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight=gbc.gridwidth=1;
        gbc.weightx=gbc.weighty=1;
        gbc.gridx=gbc.gridy=0;

        //ajout à la fenetre
        add(lblAdd, gbc);
        gbc.gridy = 1;
        add(lblNum1, gbc);
        gbc.gridx = 1;
        add(txtNum1, gbc);
        gbc.gridx = 2;
        add(lblPrenom, gbc);
        gbc.gridx = 3;
        add(txtPrenom, gbc);
        gbc.gridx = 4;
        add(lblNom, gbc);
        gbc.gridx = 5;
        add(txtNom, gbc);
        gbc.gridx = 6;
        add(lblBac, gbc);
        gbc.gridx = 7;
        add(cbbBac, gbc);
        gbc.gridx = 8;
        add(lblDpt, gbc);
        gbc.gridx = 9;
        add(cbbDpt, gbc);
        gbc.gridy++;
        gbc.gridx = 10;
        add(btnAdd, gbc);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        Dimension dimensionSeparator = separator.getPreferredSize();
        dimensionSeparator.width = this.getPreferredSize().width;
        separator.setPreferredSize(dimensionSeparator);
        separator.setBackground(Color.BLACK);
        gbc.gridwidth = 11;
        gbc.gridx = 0;
        gbc.gridy++;
        add(separator, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(lblDelete, gbc);
        gbc.gridy++;
        add(lblNum2, gbc);
        gbc.gridx = 1;
        add(txtNum2, gbc);
        gbc.gridy++;
        gbc.gridx = 10;
        add(btnDelete, gbc);

        //ajout des listeners
        btnAdd.addActionListener(new Ecouteur_Ajout());
        btnDelete.addActionListener(new Ecouteur_Suppr1());

        //initialisation controlleur
        this.control_ajout=new Contr_AjoutForm(this.promotion,this);
        this.control_supp = new Contr_SuppForm(this.promotion,this);

        //Rendre visible
        setVisible(true);
    }

    private class Ecouteur_Ajout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(txtNum1.getText());
                list.add(txtPrenom.getText());
                list.add(txtNom.getText());
                list.add(cbbBac.getSelectedItem().toString());
                list.add(cbbDpt.getSelectedItem().toString());

                control_ajout.control(list);
            }
        }
    }

    private class Ecouteur_Suppr1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDelete) {

                //Création List String de l'etudiant
                ArrayList<String> list = new ArrayList<String>();
                list.add(txtNum2.getText());

                control_supp.control(list);
            }
        }
    }


}
