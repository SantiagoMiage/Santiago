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
    int nbouvrieractif=0;
    boolean irrigué=false;
    boolean secheresse=false;
    typeChamps champs=typeChamps.vide;



public Parcelle(int nbouvrier, boolean irrigué, boolean secheresse, typeChamps champs) {
    this.nbouvrier = nbouvrier;
    this.irrigué = irrigué;
    this.secheresse = secheresse;
    this.champs = champs;

}            
            
  public enum typeChamps {
  patate, piment, banane, bambou, haricot, vide, test;
}


    @Override
    public String toString() {
        return "Parcelle{" +
                "nbouvrier=" + nbouvrier +
                ", irrigué=" + irrigué +
                ", secheresse=" + secheresse +
                ", champs=" + champs +
                '}';
    }

//Fonction d ajout ouvrier



//



}
