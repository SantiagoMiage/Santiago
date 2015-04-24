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

        Parcelle pChoisie = pileParcelleGUI.choixParcelle(j_actif);
        return pChoisie;
    }

    public void depotParcelle(Joueur j_actif) {

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

        //on soustrait le montant offert par le joueur a son total d'argent
        j_actif.setArgent(j_actif.getArgent() - montantInt);

        return montantInt;
    }

    //pour les encheres Canaux
    public boolean enchereCanalOk(Joueur j_actif, int montantInt, ArrayList<Proposition> listProposition) {
        return montantInt < 0 || montantInt > j_actif.getArgent();
    }

    //Montant de la proposition la plus chere
    public int montantPropoLaPluschere(ArrayList<Proposition> listProposition) {
        int res = 0;

        for (Proposition proposition : listProposition) {
            if (proposition.total() > res) {
                res = proposition.total();
            }
        }
        return res;
    }

    //Permet de recuperer le canl choisi par le joueur
    public Canal recupCanal(Joueur j_actif) {
        Canal canal;
        do {
            canal = plateau.choixCanal(j_actif);
        } while (!plateau.estIrriguable(canal));
        return canal;
    }

    public void choixCanalConstructeur(Joueur constructeurCanal, ArrayList<Proposition> listProposition) {
        boolean construit = false;
        Canal canal = null;
        int total = 0;
        do {
            canal = recupCanal(constructeurCanal);

            //si le canal ne fait pas l'objet d une proposition, on  demande au constructeur si il veut payer la proposition la plus chere max+1 pour l irrigue
            if (!estUneProposition(canal, listProposition)) {
                total = montantPropoLaPluschere(listProposition);//prop la plus chere
                int max = total + 1;
                String[] choix = {"OK ", "Annuler"};
                JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
                int rang = jop.showOptionDialog(null, "Voulez vous payer :" + max + " ,pour la construction de ce canal",
                        "Canal non revendiqué", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

                //Si ok on verifie si il peut se le permettre
                if (rang == 0) {
                    String mess = "";
                    if (constructeurCanal.getArgent() >= total) {
                        mess = "Paiement effectué et remboursement des autres joueurs";
                        construit = true;

                        // on decompte au constructeur
                        constructeurCanal.setArgent(constructeurCanal.getArgent() - total);

                    } else {
                        mess = "Erreur argent insuffisant";
                    }
                    jop2.showMessageDialog(null, mess, "Construction de la parcelle", JOptionPane.INFORMATION_MESSAGE);
                }


            } else {
                //sinon il accepte ou non
                //si accepte , on empoche le pognon , on decompte le pognon des joueurs qui gagnent
                total = recupProposition(canal, listProposition).total(); //total de la proposition la plus haute
                String[] choix = {"OK ", "Annuler"};
                JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
                int rang = jop.showOptionDialog(null, "Voulez vous recevoir : " + total + " ,pour la construction de ce canal",
                        "Canal renvendiqué", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

                //Si ok
                if (rang == 0) {
                    String mess = "";
                    mess = "Paiement effectué ,retrait d'argent des autres joueurs";
                    construit = true;

                    jop2.showMessageDialog(null, mess, "Construction de la parcelle", JOptionPane.INFORMATION_MESSAGE);

                    //le constructeur empoche le pognon
                    constructeurCanal.setArgent(constructeurCanal.getArgent() + total);
                    //les joueurs ayant participe à l'offre payent le pognon alloué
                    recupProposition(canal, listProposition).paiementProposition();
                }
            }
        } while (!construit);
        // on irrigue le canal
        plateau.irrigation(canal);
        //on decolore les propositions restantes
        plateau.decolorationProposition(listProposition);
    }

    public void propositionCanalJoueur(Joueur j_actif, ArrayList<Proposition> listProposition) {
        int montantInt = -1;
        String montant = "";
        String mess = "";
        mess += montantPrisCanalString(listProposition);

        //le joueur selectionne un canal sur le plateau
        Canal canal = recupCanal(j_actif);

        //si le canal ne fait pas l'objet d une proposition,il la créée
        if (!estUneProposition(canal, listProposition)) {
            mess = "Emettre une nouvelle proposition pour ce canal : ";
            boolean gogol = false;
            do try {
                JOptionPane jop = new JOptionPane();
                if (!gogol) {
                    montant = jop.showInputDialog(null, mess, " Montant " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
                } else {
                    montant = jop.showInputDialog(null, mess + " Nombre Positif svp !", j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
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
        } else {
            //sinon on recupere la proposition et on ajoute
            Proposition proposition = listProposition.get(listProposition.indexOf(recupProposition(canal, listProposition)));

            boolean gogol = false;
            do try {
                JOptionPane jop = new JOptionPane();
                if (!gogol) {
                    montant = jop.showInputDialog(null, proposition.toString(), "\n Montant à ajouter " + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
                } else {
                    montant = jop.showInputDialog(null, mess + " Nombre Positif svp !", "Enchère canal" + j_actif.getPseudo(), JOptionPane.QUESTION_MESSAGE);
                }
                montantInt = Integer.parseInt(montant);
                if (montantInt < 0) gogol = true;
            } catch (Exception e) {
                gogol = true;
            } while (enchereCanalOk(j_actif, montantInt, listProposition));


            //et on surencherit la proposition
            proposition.soutenirProposition(j_actif, montantInt);
        }
        //colorier le canal de la couleur du joueur, vert pour tous en attendant .....
        plateau.colorieCanalPropVert(canal);

    }

    //si le canal selectioné fais déja l'objet d'une proposition
    public boolean estUneProposition(Canal canal, ArrayList<Proposition> listProposition) {
        boolean res = false;
        for (Proposition proposition : listProposition) {
            if (proposition.getCanal() == canal) {
                res = true;

            }
        }
        return res;
    }

    //recupere la proposition du canal existant
    public Proposition recupProposition(Canal canal, ArrayList<Proposition> listProposition) {
        Proposition propositionrecup = null;
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
            mess += proposition.toString() + " \n";
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
