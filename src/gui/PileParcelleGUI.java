package gui;


import joueur.Joueur;
import plateau.Parcelle;
import plateau.PileParcelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Crema on 02/04/2015.
 */
public class PileParcelleGUI {

    private ArrayList<PileParcelle> pileParcelles;
    private JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
    private boolean encherencours;

    public PileParcelleGUI(ArrayList<PileParcelle> pileParcelles) {
        this.pileParcelles = pileParcelles;

    }

    public void initialisation() {
        String cheminparcelle = "/ressource/images/parcelle.png";
        URL url_parcelle = this.getClass().getResource(cheminparcelle);
        final ImageIcon iconparcelle = new ImageIcon(url_parcelle);
        String cheminvide = "/ressource/images/vide.png";
        URL url_vide = this.getClass().getResource(cheminvide);
        final ImageIcon iconvide = new ImageIcon(url_vide);


        panel.setSize(new Dimension(215, 50));
        //Possibilité 2 : instanciation puis définition du libellé
        // int compt = 0;
        for (int i = 0; i < 4; i++) {
            final int indice = i;
            final JLabel thumb = new JLabel();
            thumb.setPreferredSize(new Dimension(50, 50));
            thumb.setIcon(iconparcelle);
            //   pileParcellesGUI.add(thumb);
            retournerParcelles(thumb, indice);

            thumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (encherencours) {
                        if (pileParcelles.get(indice).getPileParcelle().size() > 1) {
                            //retirer limage
                            pileParcelles.get(indice).popParcelle();
                            //afficher  la parcelle suivante
                            retournerParcelles(thumb, indice);
                        } else {
                            //retirer limage
                            pileParcelles.get(indice).popParcelle();
                            thumb.setIcon(iconvide);
                        }
                        //stocker la parcelle dans la main du joueur
                    }

                }

            });
            panel.add(thumb);
            //    compt++;


        }
    }

    //TODO afficher les parcelles sur les piles
    public void retournerParcelles(JLabel thumb, int compt) {
        String chemin = null;
        URL url = null;
        switch (pileParcelles.get(compt).recupTypeDessus()) {
            case patate:
                if (pileParcelles.get(compt).recupNbouvrierDessus() == 1) {
                    chemin = "/ressource/images/patate1.png";
                } else {
                    chemin = "/ressource/images/patate2.png";
                }
                break;

            case piment:
                if (pileParcelles.get(compt).recupNbouvrierDessus() == 1) {
                    chemin = "/ressource/images/piment1.png";
                } else {
                    chemin = "/ressource/images/piment2.png";
                }
                break;

            case banane:
                if (pileParcelles.get(compt).recupNbouvrierDessus() == 1) {
                    chemin = "/ressource/images/banane1.png";
                } else {
                    chemin = "/ressource/images/banane2.png";
                }
                break;

            case bambou:
                if (pileParcelles.get(compt).recupNbouvrierDessus() == 1) {
                    chemin = "/ressource/images/bambou1.png";
                } else {
                    chemin = "/ressource/images/bambou2.png";
                }
                break;

            case haricot:
                if (pileParcelles.get(compt).recupNbouvrierDessus() == 1) {
                    chemin = "/ressource/images/haricot1.png";
                } else {
                    chemin = "/ressource/images/haricot2.png";
                }
                break;

            case vide:
                chemin = "/ressource/images/vide.png";
                break;

            case test:
                chemin = "/ressource/images/test.png";
                break;
            default:
                chemin = "/ressource/images/vide.png";
        }
        url = this.getClass().getResource(chemin);
        ImageIcon icon = new ImageIcon(url);
        thumb.setIcon(icon);

    }

    public JPanel affichagePileParcelle() {
        initialisation();
        return panel;
    }

    public Parcelle choixParcelle(Joueur j_actif) {
        setEnchereEnCours(true);
        //TODO wait for click utilisateur
        return null;
    }

    private void setEnchereEnCours(boolean b) {
        encherencours = true;
    }

}