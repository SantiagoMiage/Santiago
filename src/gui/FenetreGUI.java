package gui;

import joueur.Joueur;
import joueur.Proposition;
import plateau.Canal;
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

    JFrame fenetre;
    private PileParcelleGUI pileParcelleGUI;
    private Plateau plateau;
    private MainJoueurGUI mainJoueurGUI;
    private JPanel panel = new JPanel(new GridBagLayout());
    private LauncherGUI launcher = new LauncherGUI();


    public FenetreGUI() {
        pileParcelleGUI = null;
        this.plateau = new Plateau();
        this.plateau.initialisation();
        fenetre = new JFrame();
    }

    public LauncherGUI getLauncher() {
        return launcher;
    }

    //Créer une pile parcelle et la retourne
    public JPanel initialisationPileParcelle(ArrayList<PileParcelle> pileParcelles) {
        pileParcelleGUI = new PileParcelleGUI(pileParcelles);
        return pileParcelleGUI.affichagePileParcelle();
    }

    public void retournerLesPilesParcelles() {
        pileParcelleGUI.retournerLesPilesParcelles();
    }

    //Ajoute le panel de PileParcelleGUI dans la fenêtre
    public void creationParcelle(ArrayList<PileParcelle> pileParcelles) {

        panel.add(initialisationPileParcelle(pileParcelles));

    }

    //Créer une pile parcelle et la retourne
    public JPanel initialisationMainJoueur() {
        mainJoueurGUI = new MainJoueurGUI();
        return mainJoueurGUI.affichageMain();
    }

    //Ajoute le panel de PileParcelleGUI dans la fenêtre
    public void creationMainJoueur() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 3;
        panel.add(initialisationMainJoueur(), gc);

    }

    //A finir doit gérer le choix de la parcelle d'un joueur
    //Pour l'instant ajoute le panel de PileParcelleGUI
    public Parcelle choixParcelle(Joueur j_actif, ArrayList<PileParcelle> pileParcelles) {
        System.out.println(j_actif);
        Parcelle pChoisie = pileParcelleGUI.choixParcelle(j_actif);
        return pChoisie;
    }

    public void depotParcelle(Joueur j_actif) {
        System.out.println(j_actif);
        plateau.depotParcelle(j_actif);
    }

    public void creationPlateau(ArrayList<PileParcelle> pileParcelles) {
        fenetre.getContentPane().removeAll();
        fenetre.setContentPane(panel);
        //Ajout des 3 sous-panels principaux
        creationParcelle(pileParcelles);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 2;
        panel.add(plateau.getPanel(), gc);

        creationMainJoueur();

        fenetre.revalidate();
    }

    public void creationLauncher() {
        fenetre.setTitle("Santiago");
        fenetre.setPreferredSize(new Dimension(1000, 900));
        fenetre.setContentPane(launcher.getPanel());
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    //pour les encheres Parcelles
    public boolean enchereOk(Joueur j_actif, int montantInt, int[] montantEnchere) {
        return montantInt < 0 || montantdejaPris(montantInt, montantEnchere) || montantInt > j_actif.getArgent();
    }


    //popup qui demande le montant que le joueur fait pour l'enchère
    public int offreJoueur(Joueur j_actif, int[] montantEnchere) {
        int montantInt = -1;
        String montant = "";
        String mess = "Combien voulez mettre? Offre déjà faites : ";
        mess += montantPrisString(montantEnchere);
        boolean gogol = false;
        do try {
            JOptionPane jop = new JOptionPane();
            if (!gogol) {
                montant = jop.showInputDialog(null, mess, "Enchère parcelle ! " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
            } else {
                montant = jop.showInputDialog(null, mess + " Nombre Positif plz !", "Enchère parcelle ! " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);

            }
            montantInt = Integer.parseInt(montant);
            if (montantInt < 0) gogol = true;
        } catch (Exception e) {
            gogol = true;
        } while (enchereOk(j_actif, montantInt, montantEnchere));
        return montantInt;
    }

    //pour les encheres Canaux
    public boolean enchereCanalOk(Joueur j_actif, int montantInt, ArrayList<Proposition> listProposition) {
        return montantInt > j_actif.getArgent();
    }

    //Permet de recuperer le canl choisi par le joueur
    public Canal recupCanal(Joueur j_actif){
        return plateau.choixCanal(j_actif);
    }

    public void propositionCanalJoueur(Joueur j_actif, ArrayList<Proposition> listProposition) {

        int montantInt = -1;
        String montant = "";
        String mess = "Emettre une nouvelle proposition ? Offre déjà faites : ";
        mess += montantPrisCanalString(listProposition);

        //le joueur selectionne un canal sur le plateau
        Canal canal = recupCanal(j_actif);
        
        //si le canal ne fait pas l'objet d une proposition,il la créée
        if (!estUneProposition(canal,listProposition)) {
           
            boolean gogol = false;
            do try {
                JOptionPane jop = new JOptionPane();
                if (!gogol) {
                    montant = jop.showInputDialog(null, mess, "Enchère canal ! " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
                } else {
                    montant = jop.showInputDialog(null, mess + " Nombre Positif plz !", "Enchère canal ! " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);

                }
                montantInt = Integer.parseInt(montant);
                if (montantInt < 0) gogol = true;
            } catch (Exception e) {
                gogol = true;
            } while (enchereCanalOk(j_actif, montantInt, listProposition));


            //Creation de la proposition
            Proposition proposition = new Proposition(canal);
            proposition.setCanal(canal);
            proposition.soutenirProposition(j_actif, montantInt);
            //Ajout de la proposition a la liste
            listProposition.add(proposition);
        }else {
            //sinon on recupere la proposition

            Proposition proposition = listProposition.get(listProposition.indexOf(recupProposition(canal,listProposition)));
            //et il surencherit
            proposition.soutenirProposition(j_actif,montantInt);
        }
    }
    
    //si le canal selectioné fais déja l'objet d'une proposition
    public boolean estUneProposition(Canal canal,ArrayList<Proposition> listProposition){
        boolean res=false;
        for (Proposition proposition : listProposition) {
            if(proposition.getCanal()==canal){
                res=true;
                
            }
        }
        return res;
    }
    //recupere la proposition du canal existant
    public Proposition recupProposition(Canal canal,ArrayList<Proposition> listProposition){
        Proposition propositionrecup = null ;
        if (!listProposition.isEmpty()) {
            for (Proposition proposition : listProposition) {
                if (proposition.getCanal() == canal) {
                    propositionrecup = proposition;

                }
            }
        }
        return propositionrecup;
    }
    //parcours de la liste d'enchere pour les canaux
    private String montantPrisCanalString(ArrayList<Proposition> listProposition) {
        String mess = "";
        if (listProposition.isEmpty()) {
            return "Aucune ";
        }
        for (Proposition proposition : listProposition) {
            mess += proposition.toString() + " ";
        }
        return mess;
    }

    //parcours du tableau d enchere parcelle
    private String montantPrisString(int[] montantEnchere) {
        String mess = "";
        if (montantEnchere[0] < 0) {
            return "Aucune ";
        }
        for (int i = 0; i < montantEnchere.length; i++) {
            if (montantEnchere[i] > -1) {
                mess += montantEnchere[i] + " ";
            }
        }
        return mess;
    }

    //vérifie que le proposition du joueur n'a pas déjà été prise
    //0 équivaut à passer donc 0 est exclue de la comparaison
    private boolean montantdejaPris(int montantInt, int[] montantEnchere) {
        boolean dejaPris = false;
        if (montantInt == 0) {
            return dejaPris;
        }
        for (int i = 0; i < montantEnchere.length; i++) {
            if (montantInt == montantEnchere[i])
                dejaPris = true;
        }
        return dejaPris;
    }
}
