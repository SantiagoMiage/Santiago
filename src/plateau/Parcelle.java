/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plateau;

/**
 * Created by Yannis Cipriani on 06/03/2015.
 */
public class Parcelle {
    int nbouvrier;
    int nbouvrieractif = 0;
    boolean irrigue = false;
    boolean secheresse = false;
    typeChamps champs = typeChamps.vide;
    int numligne;
    int numcolonne;


    public boolean isIrrigue() {
        return irrigue;
    }

    public void setIrrigue(boolean irrigue) {
        this.irrigue = irrigue;
    }

    public Parcelle(int nbouvrier, boolean irrigue, boolean secheresse, typeChamps champs) {
        this.nbouvrier = nbouvrier;
        this.irrigue = irrigue;
        this.secheresse = secheresse;
        this.champs = champs;

    }

    public Parcelle(int nbouvrier, int nbouvrieractif, boolean irrigue, boolean secheresse, typeChamps champs, int numligne, int numcolonne) {
        this.nbouvrier = nbouvrier;
        this.nbouvrieractif = nbouvrieractif;
        this.irrigue = irrigue;
        this.secheresse = secheresse;
        this.champs = champs;
        this.numligne = numligne;
        this.numcolonne = numcolonne;
    }

    public int getNbouvrier() {
        return nbouvrier;
    }

    public int getNumligne() {
        return numligne;
    }

    public int getNumcolonne() {
        return numcolonne;
    }

    @Override
    public String toString() {
        return "Parcelle{" +
                "nbouvrier=" + nbouvrier +
                ", nbouvrieractif=" + nbouvrieractif +
                ", irrigue=" + irrigue +
                ", secheresse=" + secheresse +
                ", champs=" + champs +
                ", numligne=" + numligne +
                ", numcolonne=" + numcolonne +
                '}';
    }


    public String toStringlight() {
        return "Parcelle{" +
                " irrigue=" + irrigue +
                ", numligne=" + numligne +
                ", numcolonne=" + numcolonne +
                '}';
    }

    public enum typeChamps {
        patate, piment, banane, bambou, haricot, vide, test;
    }

//Fonction d ajout ouvrier


//


}
