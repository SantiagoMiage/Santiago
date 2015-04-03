package gui;


import plateau.PileParcelle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Crema on 02/04/2015.
 */
public class PileParcelleGUI {

    private ArrayList<PileParcelle> pileParcelles;
    private JPanel panel = new JPanel(new GridLayout());


    public PileParcelleGUI(ArrayList<PileParcelle> pileParcelles){
        this.pileParcelles = pileParcelles;

    }

    public void initialisation(){
        panel.setSize(new Dimension(200, 300));
        //Possibilité 2 : instanciation puis définition du libellé
        JButton bouton = new JButton();
        bouton.setText("Mon deuxième bouton");
        panel.add(bouton);
    }

    //TODO afficher les parcelles sur les piles
    public void retournerParcelles(){

    }

    public JPanel affichagePileParcelle(){
        initialisation();
        return panel;
    }

}
