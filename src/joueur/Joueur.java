package joueur;

import plateau.Parcelle;

/**
 * Created by Crema on 06/03/2015.
 */
public class Joueur {

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
}
