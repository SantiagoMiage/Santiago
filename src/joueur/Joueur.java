package joueur;

import plateau.Parcelle;

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




    public Joueur(String pseudo, int argent){
        this.pseudo=pseudo;
        this.argent=argent;
        this.score = 0;

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

    public Parcelle getParcelleMain() {
        return parcelleMain;
    }

    public void setParcelleMain(Parcelle parcelleMain) {
        this.parcelleMain = parcelleMain;
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
