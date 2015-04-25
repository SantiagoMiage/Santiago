package joueur;

import plateau.Canal;

import java.util.ArrayList;

/**
 * Created by Yannis on 23/04/2015.
 */
public class Proposition {

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

    //ajoute le montant du joueur qui soutient la proposition nouvelle ou non
    public void soutenirProposition(Joueur joueur,int montant){
        listJoueur.add(joueur);
        listMontant.add(montant);
    }


    public void paiementProposition(){
        int nbj=0;
        //pour chaque joueur pn retire le paiement qu'il s'est engagé à payer
        for (Joueur joueur : listJoueur) {
            joueur.setArgent(joueur.getArgent()-listMontant.get(nbj));
            nbj++;
        }
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


    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    @Override
    public String toString() {
        return "Proposition{"  +
                "listJoueur=" + listJoueur.toString() +
                ", listmontant=" + listMontant +
                ", total=" + total() +
                '}';
    }

    public String affichageProposition() {
        String mess = "";
        for (Joueur joueur : listJoueur) {
            int montant = listMontant.get(listJoueur.indexOf(joueur));
            mess= mess+joueur.getPseudo()+" donne "+montant+" escudos "+'\n';
        }
        return mess;
    }
}
