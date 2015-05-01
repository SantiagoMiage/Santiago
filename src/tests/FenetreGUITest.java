package tests;

import gameMaster.MaitreDuJeu;
import joueur.Joueur;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FenetreGUITest {


    @Test
    //cas nominal
    public void testOffreJoueur(){
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
        listeJoueurs.add(new Joueur("Matthieu", 10, Color.red));
        listeJoueurs.add(new Joueur("Yannis", 10, Color.blue));
        listeJoueurs.add(new Joueur("Soraya", 10, Color.pink));
        listeJoueurs.add(new Joueur("Thomas", 10, Color.green));
        MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);
        int[] montantEnchere = new int[listeJoueurs.size()];

        boolean res=mj.getFenetre().enchereOk(listeJoueurs.get(0), 5, montantEnchere);
        assertFalse(res);
    }

    @Test
    public void testOffreJoueursNegative(){
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
        listeJoueurs.add(new Joueur("Matthieu", 10, Color.red));
        listeJoueurs.add(new Joueur("Yannis", 10, Color.blue));
        listeJoueurs.add(new Joueur("Soraya", 10, Color.pink));
        listeJoueurs.add(new Joueur("Thomas", 10, Color.green));
        MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);
        int[] montantEnchere = new int[listeJoueurs.size()];

        boolean res=mj.getFenetre().enchereOk(listeJoueurs.get(0),-5, montantEnchere);
        assertTrue(res);
    }

    @Test
    public void testOffreJoueursSuperieurMontantJoueur(){
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
        listeJoueurs.add(new Joueur("Matthieu", 10, Color.red));
        listeJoueurs.add(new Joueur("Yannis", 10, Color.blue));
        listeJoueurs.add(new Joueur("Soraya", 10, Color.pink));
        listeJoueurs.add(new Joueur("Thomas", 10, Color.green));
        MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);
        int[] montantEnchere = new int[listeJoueurs.size()];

        boolean res=mj.getFenetre().enchereOk(listeJoueurs.get(0),15, montantEnchere);
        assertTrue(res);
    }

    @Test
    public void testOffreJoueursDejaExistante(){
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
        listeJoueurs.add(new Joueur("Matthieu", 10, Color.red));
        listeJoueurs.add(new Joueur("Yannis", 10, Color.blue));
        listeJoueurs.add(new Joueur("Soraya", 10, Color.pink));
        listeJoueurs.add(new Joueur("Thomas", 10, Color.green));
        MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);
        int[] montantEnchere = new int[listeJoueurs.size()];
        montantEnchere[0] = 4;
        boolean res=mj.getFenetre().enchereOk(listeJoueurs.get(0),4, montantEnchere);
        assertTrue(res);
    }
}