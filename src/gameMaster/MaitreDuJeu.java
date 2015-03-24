package gameMaster;

import joueur.Joueur;
import plateau.Parcelle;
import plateau.PileParcelle;
import plateau.Plateau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Crema on 18/03/2015.
 */
public class MaitreDuJeu {

    private int nbTours; //nombre de tours écoulé dans la partie
    private Plateau plateau; //le plateau de jeu
    private ArrayList<Joueur> joueurs; //la liste des joueurs dans la partie
    private ArrayList<PileParcelle> pileParcelles;

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
    }


    //Créer les différentes pile de parcelles pour la partie
    private void initialisationPileParcelles() {
        int nbPile = pileParcelles.size();
        long seed = System.nanoTime(); //Pour notre randoum
        ArrayList<Parcelle> parcelles = new ArrayList<Parcelle>(45);

        //On créer les 45 tuiles
        CreationParcelles(parcelles);

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

    private void CreationParcelles(ArrayList<Parcelle> parcelles) {
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


}
