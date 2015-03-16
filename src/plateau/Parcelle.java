/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plateau;


//import javafx.scene.image.Image;

import javax.swing.*;

public class Parcelle extends JComponent {
    int nbouvrier = 0;
    boolean irrigué = false;
    boolean secheresse = false;
    typeChamps champs = null;


    public Parcelle() {

    }


    public Parcelle(int nbouvrier, boolean irrigué, boolean secheresse, typeChamps champs) {
        this.nbouvrier = nbouvrier;
        this.irrigué = irrigué;
        this.secheresse = secheresse;
        this.champs = champs;

    }

    public enum typeChamps {
        patate, piment, banane, bambou, haricot, vide
    }


}
