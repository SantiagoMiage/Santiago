package plateau;

import java.util.ArrayList;

/**
 * Created by Crema on 06/03/2015.
 */
public class PileParcelle {

    private ArrayList<Parcelle> pileParcelle = new ArrayList<Parcelle>(11);

    public PileParcelle(ArrayList<Parcelle> pileParcelle) {
        pileParcelle = pileParcelle;
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

    //Retire de la liste la parcelle affichée lors des enchères
    public void popParcelle(){
        this.pileParcelle.remove(0);
    }

    //Retourne la parcelle parcelle de la liste
    public Parcelle getParcelle(){
        return this.pileParcelle.get(0);
    }
}
