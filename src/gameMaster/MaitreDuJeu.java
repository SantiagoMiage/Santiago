package gameMaster;

import gui.FenetreGUI;
import gui.PileParcelleGUI;
import joueur.Joueur;
import plateau.Parcelle;
import plateau.PileParcelle;
import plateau.Plateau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Créma on 18/03/2015.
 */
public class MaitreDuJeu {

    private int nbTours; //nombre de tours écoulé dans la partie
    private Plateau plateau; //le plateau de jeu
    private ArrayList<Joueur> joueurs; //la liste des joueurs dans la partie
    private ArrayList<PileParcelle> pileParcelles;
    private PileParcelleGUI ppgui;
    private FenetreGUI fenetre;
    private Joueur j_actif;


    public int getNbTours() {
        return nbTours;
    }

    public ArrayList<PileParcelle> getPileParcelles() {
        return pileParcelles;
    }

    public MaitreDuJeu(ArrayList<Joueur> joueurs){
        this.nbTours = 0;
        this.plateau = new Plateau();
        this.plateau.initialisation();
        //this.plateau.creationFenetre();
        this.joueurs = joueurs;
        //créer autant de pile de parcelle qu'il y a de joueurs
        this.pileParcelles = new ArrayList<PileParcelle>(joueurs.size());
        initialisationPileParcelles();
        this.fenetre = new FenetreGUI();
    }

    public void afficherJeu(){
        this.fenetre.creationFenetre(pileParcelles);
    }

    //Créer les différentes pile de parcelles pour la partie
    private void initialisationPileParcelles() {

        //on init les piles parcelles
        for(int i = 0; i<joueurs.size(); i++){
            pileParcelles.add(new PileParcelle());
        }

        int nbPile = pileParcelles.size();
        long seed = System.nanoTime(); //Pour notre randoum
        ArrayList<Parcelle> parcelles = new ArrayList<Parcelle>(45);

        //On créer les 45 tuiles
        creationParcelles(parcelles);

        Collections.shuffle(parcelles, new Random(seed));

        //Si on est à 4 joueurs on retire une parcelle du jeu
        if(nbPile == 4){
            parcelles.remove(0);
        }
        while(!parcelles.isEmpty()){
            for(int i = 0; i<nbPile;  i++){
                pileParcelles.get(i).AjoutParcelle(parcelles.get(0));
                parcelles.remove(0);
            }
        }
    }

    private void afficherPileParcelle() {
        fenetre.creationParcelle(pileParcelles);
    }


    //gère la première phase du jeu les enchères pour les parcelles
    public void enchereParcelle(){
        int[] montantEnchere = new int[joueurs.size()];
        for(int i = 0; i<joueurs.size();i++){
            montantEnchere[i] = -1;
        }
        this.retournerPlantation();
        for(int i = 0; i<joueurs.size();i++){
            j_actif = joueurs.get(i);
            montantEnchere[i] = fenetre.offreJoueur(j_actif, montantEnchere);

        }
        ArrayList<Joueur> toursJoueurs = triJoueurTour(montantEnchere);
        for(int i = 0; i<joueurs.size(); i++){
            j_actif = toursJoueurs.get(i);
            Parcelle pChoisie = fenetre.choixParcelle(j_actif, pileParcelles);
            j_actif.setParcelleMain(pChoisie);
            System.out.println("Voici le choix");
            System.out.println(pChoisie.toString());
        }
    }

    //Retourne une liste de joueurs trié pour la phase de tour 1
    private ArrayList<Joueur> triJoueurTour(int[] montantEnchere) {
        ArrayList<Joueur> res = new ArrayList<Joueur>(joueurs.size());
        int max = -1;
        int maxExclu = 10000;
        int pos = -1;
        for(int j = 0; j<joueurs.size(); j++) {
            for (int i = 0; i < joueurs.size(); i++) {
                if (max < montantEnchere[i] && montantEnchere[i] < maxExclu) {
                    max = montantEnchere[i];
                    pos = i;
                }
            }
            maxExclu = max;
            System.out.println(pos);
            res.add(joueurs.get(pos));
            max = -1;
        }
        return res;
    }

    public void setJoueur(Joueur joueur) {
        this.j_actif = joueur;
    }

    private void retournerPlantation() {
        fenetre.retournerParcelles();
    }

    private void creationParcelles(ArrayList<Parcelle> parcelles) {
        //les 6 patates avec 1 travailleur
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.patate));
        //les 3 patates avec 2 travailleurs
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.patate));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.patate));

        //les 6 piments avec 1 travailleur
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.piment));
        //les 3 piments avec 2 travailleurs
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.piment));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.piment));

        //les 6 bananes avec 1 travailleur
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.banane));
        //les 3 bananes avec 2 travailleurs
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.banane));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.banane));

        //les 6 bambous avec 1 travailleur
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.bambou));
        //les 3 bambous avec 2 travailleurs
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.bambou));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.bambou));

        //les 6 haricots avec 1 travailleur
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(1,false,false, Parcelle.typeChamps.haricot));
        //les 3 haricots avec 2 travailleurs
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.haricot));
        parcelles.add(new Parcelle(2,false,false, Parcelle.typeChamps.haricot));
    }

    public static void main(String[] args){

        //A la place une interface graphique devras permettre de choisir les joueurs
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
        listeJoueurs.add(new Joueur("Matthieu", 10));
        listeJoueurs.add(new Joueur("Yannis", 10));
        listeJoueurs.add(new Joueur("Soraya", 10));
        listeJoueurs.add(new Joueur("Thomas", 10));
        MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);
        mj.afficherJeu();
        //mj.afficherPileParcelle();
        mj.setJoueur(listeJoueurs.get(0));
        mj.enchereParcelle();

    }




}
