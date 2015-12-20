package axel.control;

import axel.model.Etudiant;
import axel.model.Promotion;
import axel.view.Vue_Liste;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by axell on 01/12/15.
 */
public class Contr_SuppListe extends AbstractControleur {

    private Vue_Liste view;

    public Contr_SuppListe(Promotion promotion, Vue_Liste view) {
        this.promotion = promotion;
        this.view = view;
    }

    @Override
    public void control(ArrayList<String> list) {
        if (list != null) {
            String all_etu = "";
            Etudiant etu;
            for (int i = 0; i < list.size(); i++) {
                etu = promotion.searchEtudiant(list.get(i));
                promotion.removeEtudiant(etu);
                all_etu += etu + "\n";
            }
            this.promotion.notifyObservateur();

            if (list.size() < 2) {
                System.out.println("L'étudiant :\n" + all_etu + " a été supprimé.");
                JOptionPane.showMessageDialog(view,
                        "L'étudiant :\n" + all_etu + " a été supprimé.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Les étudiants :\n" + all_etu + "ont été supprimés.");
                JOptionPane.showMessageDialog(view,
                        "Les étudiants :\n" + all_etu + "ont été supprimés.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            this.promotion.notifyObservateur();
        } else {
            System.out.println("Aucun étudiant n'a été selectionné.");
            JOptionPane.showMessageDialog(view,
                    "Aucun étudiant n'a été selectionné.",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
}
