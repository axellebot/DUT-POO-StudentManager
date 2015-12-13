package axel;

import axel.model.Promotion;
import axel.view.Vue_Principal;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Promotion promotion=new Promotion();
        promotion.loadFileCSV();

        //IHM
         Vue_Principal _fenetre = new Vue_Principal(promotion);
        _fenetre.pack();
        RefineryUtilities.centerFrameOnScreen(_fenetre);
        _fenetre.setVisible(true);
        _fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
