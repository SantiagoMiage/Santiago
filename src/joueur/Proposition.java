package joueur;

import plateau.Canal;

import java.util.ArrayList;

/**
 * Created by Yannis on 23/04/2015.
 */
public class Proposition {
    int numProposition;
    ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
    ArrayList<Integer> listMontant = new ArrayList<Integer>();
    Canal canal ;

    public Proposition(Canal canal ) {

        this.canal = canal;
    }

    public int total(){
        int total=0;

        for (Integer elem : listMontant) {
            total += elem;
        }

        return total;
    }

    //ajoute le montant du joueur qui soutient la proposition
    public void soutenirProposition(Joueur joueur,int montant){
        listJoueur.add(joueur);
        listMontant.add(montant);
    }









    public ArrayList<Joueur> getListJoueur() {
        return listJoueur;
    }

    public void setListJoueur(ArrayList<Joueur> listJoueur) {
        this.listJoueur = listJoueur;
    }

    public ArrayList<Integer> getListmontant() {
        return listMontant;
    }

    public void setListmontant(ArrayList<Integer> listmontant) {
        this.listMontant = listmontant;
    }

    public int getNumProposition() {
        return numProposition;
    }

    public void setNumProposition(int numProposition) {
        this.numProposition = numProposition;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    @Override
    public String toString() {
        return "Proposition{"  + numProposition +
                ", listJoueur=" + listJoueur +
                ", listmontant=" + listMontant +
                ", total=" + total() +
                '}';
    }
}
