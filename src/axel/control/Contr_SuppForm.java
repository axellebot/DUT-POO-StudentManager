package axel.control;

import axel.model.Etudiant;
import axel.model.Promotion;
import axel.view.Vue_Formulaire;
import axel.view.Vue_Principal;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by axell on 01/12/15.
 */
public class Contr_SuppForm extends AbstractControleur {

    private Vue_Formulaire view;

    public Contr_SuppForm(Promotion promotion, Vue_Formulaire view) {
        this.promotion = promotion;
        this.view = view;
    }

    @Override
    public void control(ArrayList<String> list) {
        if(!list.get(0).isEmpty()) {
            Etudiant etu = promotion.searchEtudiant(list.get(0));
            if(etu!=null) {
                promotion.removeEtudiant(etu);
                JOptionPane.showMessageDialog(view, "L'étudiant '" + etu + "' a été supprimé.");
            }else{
                JOptionPane.showMessageDialog(view, "L'étudiant n'existe pas.");
            }
        }else {
            JOptionPane.showMessageDialog(view, "Le champ est vide.");
        }
    }
}