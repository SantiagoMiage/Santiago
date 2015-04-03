package tests;

import static org.junit.Assert.assertEquals;
import gameMaster.MaitreDuJeu;

import java.util.ArrayList;

import joueur.Joueur;

import org.junit.Test;

public class MaitreDuJeuTest {

	@Test
	public void initPileParcellesTestTaillePile() {
		ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(4);
		listeJoueurs.add(new Joueur("Matthieu", 10));
		listeJoueurs.add(new Joueur("Yannis", 10));
		listeJoueurs.add(new Joueur("Soraya", 10));
		listeJoueurs.add(new Joueur("Thomas", 10));
		MaitreDuJeu mj = new MaitreDuJeu(listeJoueurs);

		assertEquals(11, mj.getPileParcelles().get(0).getNbParcelle());

	}
}