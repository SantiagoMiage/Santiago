package plateau;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Crema on 06/03/2015.
 */
public class PileParcelle {

    private ArrayList<Parcelle> pileParcelle = new ArrayList<Parcelle>(11);

    public PileParcelle() {
        pileParcelle = new ArrayList<Parcelle>(11);
    }


    public ArrayList<Parcelle> getPileParcelle() {
        return pileParcelle;
    }

    //Ajout d'une parcelle a la pile de parcelle
    //Appelé lors de l'initialisation de la partie
    public void AjoutParcelle(Parcelle p){
        this.pileParcelle.add(p);
    }

    //Affiche la première parcelle de la pile
    public void afficherDessus(){
        System.out.println(this.pileParcelle.get(0));
    }

    public int recupNbouvrierDessus(){
        return this.pileParcelle.get(0).getNbouvrier();
    }


    //Renvoie le type de la parcelle au sommet de la pile
    public Parcelle.typeChamps recupTypeDessus(){
        return this.pileParcelle.get(0).champs;
    }

    //Retire de la liste la parcelle affichée lors des enchères
    public void popParcelle(){
        if (!this.pileParcelle.isEmpty()) {
            this.pileParcelle.remove(0);
        }
    }

    //Retourne la premiere parcelle de la liste
    public Parcelle getParcelle(){
        return this.pileParcelle.get(0);
    }


    public int getNbParcelle(){
        return this.pileParcelle.size();
    }




}
