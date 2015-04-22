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

    Thread threadAttenteChoixPile;
    private ArrayList<PileParcelle> pileParcelles;
    ArrayList<JLabel> pileParcellesGUI=new ArrayList<JLabel>();;
    private JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
    private boolean encherencours;
    private Parcelle parcelleChoisie = null;

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
            //    retournerParcelles(thumb, indice);
            pileParcellesGUI.add(thumb);
            thumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(encherencours);
                    //on remplit la liste des Jlabel des 4 piles pour travailelr dessus plus tard
                    System.out.println("add");

                    System.out.println("liste" + pileParcellesGUI.toString());
                    if (encherencours) {
                        System.out.println("lel");
                        if (thumb.getIcon() != iconvide) {
                            //recuperer la parcelle
                            parcelleChoisie = pileParcelles.get(indice).getParcelle();
                            if (pileParcelles.get(indice).getPileParcelle().size() > 1) {
                                //retirer limage
                                pileParcelles.get(indice).popParcelle();
                                //afficher  la parcelle suivante
                                // retournerParcelles(thumb, indice);
                                thumb.setIcon(iconvide);
                            } else {
                                //retirer limage
                                pileParcelles.get(indice).popParcelle();
                                thumb.setIcon(iconvide);
                            }
                        }
                        synchronized (threadAttenteChoixPile) {
                            threadAttenteChoixPile.notify();
                            //stocker la parcelle dans la main du joueur
                        }
                    }

                }

            });
            panel.add(thumb);
            //    compt++;


        }
    }

    public void retournerParcelles(JLabel thumb, int compt) {
        System.out.println("retournerParcelles");
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

    //retourneretourner la parcelle au sommet de chacunes des 4 piles
    public void retournerLesPilesParcelles() {
        System.out.println("retournerLesPilesParcelles");
        System.out.println(pileParcellesGUI.toString());
        int compteur = 0;
        //pour chacune des 4 piles , retourner la parcelle au sommet de la pile
        for (JLabel jLabel : pileParcellesGUI) {
            JLabel thumb = jLabel;
            retournerParcelles(thumb, compteur);
            compteur++;
        }


    }

    public JPanel affichagePileParcelle() {
        initialisation();
        return panel;
    }

    public Parcelle choixParcelle(Joueur j_actif) {
        setEnchereEnCours(true);
        parcelleChoisie = null;
        Thread t = new Thread();
        threadAttenteChoixPile = t;
        threadAttenteChoixPile.start();
        synchronized (threadAttenteChoixPile) {
            while (parcelleChoisie == null) {
                System.out.println("att");
                System.out.println(encherencours);
                try {
                    threadAttenteChoixPile.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("clik");
            //quand le thread est fini, on return parcelleChoisie
            setEnchereEnCours(false);
            return parcelleChoisie;
        }
    }

    private void setEnchereEnCours(boolean b) {
        encherencours = b;
    }

}