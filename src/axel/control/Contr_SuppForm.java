package axel.control;

import axel.model.Etudiant;
import axel.model.Promotion;
import axel.view.Vue_Formulaire;

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
        if (!list.get(0).isEmpty()) {
            try {
                int _tmp = Integer.parseInt(list.get(0));
                Etudiant etu = promotion.searchEtudiant(list.get(0));
                if (etu != null) {
                    promotion.removeEtudiant(etu);
                    JOptionPane.showMessageDialog(view,
                            "L'étudiant '" + etu + "' a été supprimé.",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view,
                            "L'étudiant n'existe pas.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                System.out.println("Le numéro saisit n'est pas un nombre.\n");
                JOptionPane.showMessageDialog(view,
                        "Le numéro saisit n'est pas un nombre.",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);

            }
        } else

        {
            System.out.println("Le champ est vide.\n");
            JOptionPane.showMessageDialog(view,
                    "Le champ est vide.",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
