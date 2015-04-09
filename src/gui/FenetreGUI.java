package gui;

import joueur.Joueur;
import plateau.PileParcelle;
import plateau.Plateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Crema on 02/04/2015.
 */
public class FenetreGUI {

    private PileParcelleGUI pileParcelleGUI;
    private Plateau plateau;
    private JPanel panel = new JPanel(new GridLayout(1,2,10,10));

    public FenetreGUI(){
        pileParcelleGUI = null;
        this.plateau = new Plateau();
        this.plateau.initialisation();
    }

    //Créer une pile parcelle et la retourne
    public JPanel initialisationPileParcelle(ArrayList<PileParcelle> pileParcelles){
        pileParcelleGUI = new PileParcelleGUI(pileParcelles);
        return pileParcelleGUI.affichagePileParcelle();
    }

    public void retournerParcelles(){
    //    pileParcelleGUI.retournerParcelles();
    }

    //Ajoute le panel de PileParcelleGUI dans la fenêtre
    public void creationParcelle(ArrayList<PileParcelle> pileParcelles) {
        panel.add(initialisationPileParcelle(pileParcelles));

    }

    //A finir doit gérer le choix de la parcelle d'un joueur
    //Pour l'instant ajoute le panel de PileParcelleGUI
    public void choixParcelle(Joueur j_actif, ArrayList<PileParcelle> pileParcelles) {
        System.out.println("Creation pile Parcelle");
        creationParcelle(pileParcelles);
        System.out.println(j_actif);


    }

    public void creationFenetre() {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Santiago");
        fenetre.setPreferredSize(new Dimension(1000, 900));
        fenetre.setContentPane(panel);
        panel.add(plateau.getPanel());

               //panel.add(initialisationPileParcelle(pileParcelles));
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }
}
