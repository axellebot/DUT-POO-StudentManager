/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package axel.model;

import java.io.Serializable;

/**
 * @author Effantin
 */
public class Etudiant implements Serializable {
    private String id, nom, prenom, serieBac, dpt;

    public Etudiant(String id, String nom, String prenom, String serieBac, String dpt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.serieBac = serieBac;
        this.dpt = dpt;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSerieBac() {
        return serieBac;
    }

    public void setSerieBac(String serieBac) {
        this.serieBac = serieBac;
    }

    public String toString() {
        return getId() + " - " + getNom() + "  " + getPrenom() + "  (" + getDpt() + ")";
    }

}
