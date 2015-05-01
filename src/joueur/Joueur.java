package joueur;

import plateau.Parcelle;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Crema on 06/03/2015.
 */
public class Joueur implements Serializable {

    private  static  final  long serialVersionUID =  1350092881346723535L;

    private String pseudo;
    private int argent;
    private int score;
    private Parcelle parcelleMain;
    private boolean canalComplementaire = true;
    private Color couleur;




    public Joueur(String pseudo, int argent, Color couleur){
        this.pseudo=pseudo;
        this.argent=argent;
        this.score = 0;
        this.couleur = couleur;
    }

    public Joueur(Joueur j_actif) {
        this.pseudo=j_actif.pseudo;
        this.argent=j_actif.argent;
        this.score =j_actif.score;
        this.parcelleMain = j_actif.parcelleMain;
        this.couleur = j_actif.couleur;
    }

    public String getPseudo() {
        return pseudo;
    }

     public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Parcelle getParcelleMain() {
        return parcelleMain;
    }

    public void setParcelleMain(Parcelle parcelleMain) {
        this.parcelleMain = parcelleMain;
    }

    public void setCanalComplementaire(boolean canalComplementaire) {
        this.canalComplementaire = canalComplementaire;
    }

    public boolean isCanalComplementaire() {
        return canalComplementaire;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "pseudo='" + pseudo + '\'' +
                ", argent=" + argent +
                ", score=" + score +
                ", parcelleMain=" + parcelleMain +
                '}';
    }




    public String affichePseudo() {

        return "Joueur :" + pseudo;
    }


}
