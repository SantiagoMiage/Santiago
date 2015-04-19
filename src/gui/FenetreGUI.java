package gui;

import joueur.Joueur;
import plateau.Parcelle;
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

    public void retournerLesPilesParcelles(){
        pileParcelleGUI.retournerLesPilesParcelles();
    }

    //Ajoute le panel de PileParcelleGUI dans la fenêtre
    public void creationParcelle(ArrayList<PileParcelle> pileParcelles) {
        panel.add(initialisationPileParcelle(pileParcelles));

    }

    //A finir doit gérer le choix de la parcelle d'un joueur
    //Pour l'instant ajoute le panel de PileParcelleGUI
    public Parcelle choixParcelle(Joueur j_actif, ArrayList<PileParcelle> pileParcelles) {
        System.out.println(j_actif);
        Parcelle pChoisie=pileParcelleGUI.choixParcelle(j_actif);
        return pChoisie;
    }

    public void depotParcelle(Joueur j_actif) {
        System.out.println("Depot  Parcelle");
        System.out.println(j_actif);
        plateau.depotParcelle(j_actif);
    }


    public void creationFenetre(ArrayList<PileParcelle> pileParcelles) {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Santiago");
        fenetre.setPreferredSize(new Dimension(1000, 900));
        fenetre.setContentPane(panel);
        panel.add(plateau.getPanel());
        System.out.println("Creation pile Parcelle");
        creationParcelle(pileParcelles);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    //popup qui demande le montant que le joueur fait pour l'enchère
    public int offreJoueur(Joueur j_actif, int[] montantEnchere) {
        int montantInt = -1;
        String montant = "";
        String mess = "Combien voulez mettre? Offre déjà faites : ";
        mess+= montantPrisString(montantEnchere);
        boolean gogol = false;
        do try {
            JOptionPane jop = new JOptionPane();
            if(!gogol) {
                montant = jop.showInputDialog(null, mess, "Enchère parcelle ! "+j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
            }else{
                montant = jop.showInputDialog(null, mess + " Nombre Positif plz !", "Enchère parcelle ! "+j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);

            }
            montantInt = Integer.parseInt(montant);
            if(montantInt < 0) gogol = true;
        } catch (Exception e) {
            gogol = true;
        }while(montantInt < 0 || montantdejaPris(montantInt, montantEnchere) || montantInt > j_actif.getArgent());
        return montantInt;
    }

    private String montantPrisString(int[] montantEnchere) {
        String mess = "";
        if(montantEnchere[0] < 0){
            return "Aucune ";
        }
        for(int i = 0; i<montantEnchere.length; i++){
            if(montantEnchere[i] > -1){
                mess+=montantEnchere[i]+" ";
            }
        }
        return mess;
    }

    //vérifie que le proposition du joueur n'a pas déjà été prise
    //0 équivaut à passer donc 0 est exclue de la comparaison
    private boolean montantdejaPris(int montantInt, int[] montantEnchere) {
        boolean dejaPris = false;
        if(montantInt == 0){
            return dejaPris;
        }
        for(int i = 0; i<montantEnchere.length; i++){
            if(montantInt == montantEnchere[i])
                dejaPris = true;
        }
        return dejaPris;
    }
}
