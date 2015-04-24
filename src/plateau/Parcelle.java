/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plateau;

import java.io.Serializable;

import joueur.Joueur;

/**
 * Created by Yannis Cipriani on 06/03/2015.
 */
public class Parcelle implements Serializable{

    private  static  final  long serialVersionUID =  1350092745346723535L;

    private int nbouvrier;
    private int nbouvrieractif = 0;
    private boolean irrigue = false;
    private boolean secheresse = false;
    private typeChamps champs = typeChamps.vide;
    private int numligne;
    private int numcolonne;
    private Joueur Proprio;


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

    public typeChamps getChamps() {
        return champs;
    }

    public void setChamps(typeChamps champs) {
        this.champs = champs;
    }

    public Joueur getProprio() {
        return Proprio;
    }

    public void setProprio(Joueur proprio) {
        Proprio = proprio;
    }

    public boolean isIrrigue() {
        return irrigue;
    }

    public void setIrrigue(boolean irrigue) {
        this.irrigue = irrigue;
    }

    public void setNbouvrieractif(int nbouvrieractif) {
        this.nbouvrieractif = nbouvrieractif;
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
