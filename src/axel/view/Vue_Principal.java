package axel.view;

import axel.model.Promotion;

import javax.swing.*;
import java.awt.*;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Principal extends JFrame {
    //model
    private Promotion promotion;

    //Components
    private Vue_Formulaire vue_formulaire;
    private Vue_Histogramme vue_histogramme;
    private Vue_Camembert vue_camembert;
    private Vue_Liste vue_liste;


    public Vue_Principal(Promotion promotion) {
        setTitle("TP2");
        setResizable(true);
        setVisible(true);

        this.promotion = promotion;

        this.vue_formulaire = new Vue_Formulaire(promotion);
        this.vue_histogramme = new Vue_Histogramme(promotion);
        this.vue_camembert = new Vue_Camembert(promotion);
        this.vue_liste = new Vue_Liste(promotion);

        //ajout à la fenetre avec les contraintes
        //Layout
        this.setLayout(new GridBagLayout());

        //Constraint
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.weightx = gbc.weighty = 1;
        gbc.gridx = gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        this.add(vue_formulaire, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weighty = 2;
        gbc.weightx = 2;
        this.add(vue_camembert, gbc);
        gbc.weightx = 1;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(vue_histogramme, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 2;
        this.add(vue_liste, gbc);

        //ajout à la fenetre sans les contraintes
        /*
        vue_formulaire.setBounds(0, 0, 250, 250);
        vue_camembert.setBounds(0, 250, 250, 250);
        vue_histogramme.setBounds(250, 250, 250, 250);
        vue_liste.setBounds(500, 0, 250, 500);
        this.add(vue_formulaire);
        this.add(vue_camembert);
        this.add(vue_histogramme);
        this.add(vue_liste);*/
    }
}
