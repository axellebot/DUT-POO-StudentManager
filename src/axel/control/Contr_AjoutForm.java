package axel.control;

import axel.model.Etudiant;
import axel.model.Promotion;
import axel.view.Vue_Formulaire;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by axell on 01/12/15.
 */
public class Contr_AjoutForm extends AbstractControleur {

    private Vue_Formulaire view;

    public Contr_AjoutForm(Promotion promotion, Vue_Formulaire view) {
        this.promotion = promotion;
        this.view = view;
    }

    @Override
    public void control(ArrayList<String> list) {
        String _id = "", _prenom = "", _nom = "", _dept = "", _bac = "";
        _id = list.get(0);
        _prenom = list.get(1);
        _nom = list.get(2);
        _bac = list.get(3);
        _dept = list.get(4);


        if (!_id.isEmpty() && !_prenom.isEmpty() && !_nom.isEmpty() && !_dept.isEmpty() && !_bac.isEmpty()) {

            try {
                int _tmp = Integer.parseInt(_id);

                boolean existant = false;
                for (Etudiant etu : promotion.getListeEtudiants()) {
                    if (_id.equals(etu.getId())) {
                        System.out.println("Id déjà utilisé par : " + etu + "\n");
                        JOptionPane.showMessageDialog(view,
                                "Le numéro d'étudiant '" + _id + "' est déjà utilisé.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        existant = true;
                        break;
                    }
                }
                if (!existant) {
                    System.out.println("\nL'ID n'est pas déjà utilisé\n");
                    promotion.addEtudiant(new Etudiant(_id, _nom, _prenom, _bac, _dept));
                    JOptionPane.showMessageDialog(view,
                            "L'étudiant a été ajouté.",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                System.out.println("Le numéro saisit n'est pas un nombre.\n");
                JOptionPane.showMessageDialog(view,
                        "Le numéro saisit n'est pas un nombre.",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Un ou des champs sont vides.\n");
            JOptionPane.showMessageDialog(view,
                    "Un ou des champs sont vides.",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
}
