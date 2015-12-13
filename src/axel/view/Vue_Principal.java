package axel.view;

import axel.model.Promotion;

import javax.swing.*;
import java.awt.*;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Principal extends JFrame {
    private Promotion promotion;
    private Vue_Formulaire vue_formulaire;
    private Vue_Histogramme vue_histogramme;
    private Vue_Camembert vue_camembert;
    private Vue_Liste vue_liste;


    public Vue_Principal(Promotion promotion){
        setTitle("TP2");
        //setPreferredSize(new Dimension(720,480));
        setResizable(true);
        setVisible(true);

        this.promotion=promotion;

        this.vue_formulaire=new Vue_Formulaire(promotion);
        this.vue_histogramme=new Vue_Histogramme(promotion);
        this.vue_camembert=new Vue_Camembert(promotion);
        this.vue_liste=new Vue_Liste(promotion);


        //Layout
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=gbc.gridy=0;

        //ajout à la fenetre
        gbc.gridx=0;gbc.gridy=0;gbc.gridwidth=3;gbc.gridheight=1;this.add(vue_formulaire,gbc);
        gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=2;gbc.gridheight=1;this.add(vue_camembert,gbc);
        gbc.gridx=2;gbc.gridy=1;gbc.gridwidth=1;gbc.gridheight=1;this.add(vue_histogramme,gbc);
        gbc.gridx=3;gbc.gridy=0;gbc.gridwidth=1;gbc.gridheight=2;this.add(vue_liste,gbc);


        //this.pack();
        this.repaint();
    }
}
