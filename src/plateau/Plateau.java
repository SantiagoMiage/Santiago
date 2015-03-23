/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plateau;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Created by Yannis Cipriani on 16/03/2015.
 */
public class Plateau {
    private JLabel[][] tabParcelleGUI = new JLabel[6][8];   //tableau image
    private Parcelle[][] tabParcelleModele = new Parcelle[6][8]; //tableau objet
    private JPanel panel = new JPanel(new GridLayout(10, 13));

    public Plateau() {

    }


    public Plateau(JPanel panel) {
        this.panel = panel;
    }

    public void initialisation() {

        //pour la performance , on instancie seulement  au début , pour ne pas devoir le faire plus tard dans le listener, gain
        String cheminparcelle = "/ressource/images/parcelle.png";
        URL url_parcelle = this.getClass().getResource(cheminparcelle);
        String cheminpatate1 = "/ressource/images/patate1.png";
        URL url_patate1 = this.getClass().getResource(cheminpatate1);
        String cheminpiment1 = "/ressource/images/piment1.png";
        URL url_piment1 = this.getClass().getResource(cheminpiment1);
        String cheminbanane1 = "/ressource/images/banane1.png";
        URL url_banane1 = this.getClass().getResource(cheminbanane1);
        String cheminbambou1 = "/ressource/images/bambou1.png";
        URL url_bambou1 = this.getClass().getResource(cheminbambou1);
        String cheminharicot1 = "/ressource/images/haricot1.png";
        URL url_haricot1 = this.getClass().getResource(cheminharicot1);
        String chemintest = "/ressource/images/test.png";
        URL url_test = this.getClass().getResource(chemintest);

        //Les canals
        String chemincanalhori = "/ressource/images/canalhori.png";
        URL url_canalhori = this.getClass().getResource(chemincanalhori);
        String chemincanalverti = "/ressource/images/canalverti.png";
        URL url_canalverti = this.getClass().getResource(chemincanalverti);

        //intersection
        String cheminintersection = "/ressource/images/intersection.png";
        URL url_intersection = this.getClass().getResource(cheminintersection);

//[A FAIRE]rajouter les plantations 2 ouvriers
        final ImageIcon iconparcelle = new ImageIcon(url_parcelle),
                iconpatate1 = new ImageIcon(url_patate1),
                iconpiment1 = new ImageIcon(url_piment1),
                iconbanane1 = new ImageIcon(url_banane1),
                iconbambou1 = new ImageIcon(url_bambou1),
                iconharicot1 = new ImageIcon(url_haricot1),
                icontest = new ImageIcon(url_test),
                iconcanalhori = new ImageIcon(url_canalhori),
                iconcanalverti = new ImageIcon(url_canalverti),
                iconintersection = new ImageIcon(url_intersection);


        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 13; j++) {
                final JLabel thumb = new JLabel();
                if ((i == 0 || i == 3 || i == 6 || i == 9) && (j == 0 || j == 3 || j == 6 || j == 9 || j == 12)) {
                    System.out.println("c est une intersection i : " + i + " j : " + j);

                    thumb.setPreferredSize(new Dimension(10, 10));
                    thumb.setIcon(iconintersection);
                  //  panel.add(thumb, i, j);
                } else {
                   // final JLabel thumbc = new JLabel();
                    System.out.println("ce n est pas une intersection i : " + i + " j : " + j);
                    if ((i == 0 || i == 3 || i == 6 || i == 9)) {
                        System.out.println("canal hori i : " + i + " j : " + j);

                        thumb.setPreferredSize(new Dimension(50, 10));
                        thumb.setIcon(iconcanalhori);
                      //  panel.add(thumb, i, j);
                    } else if ((j == 0 || j == 3 || j == 6 || j == 9 || j == 12)) {
                        System.out.println("canal verti i : " + i + " j : " + j);
                       // final JLabel thumbcc = new JLabel();
                        thumb.setPreferredSize(new Dimension(10, 50));
                        thumb.setIcon(iconcanalverti);
                       // panel.add(thumb, i, j);

                    } else {
                        System.out.println("parcelle i : " + i + " j : " + j);
//creation label
                     //   final JLabel thumb = new JLabel();
                        thumb.setPreferredSize(new Dimension(100, 100));
//affectation image au label par défaut sur parcelle
                        thumb.setIcon(iconparcelle);
//gestion listener pour le label
                        thumb.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                //tester le changement d'icone

                                if (thumb.getIcon() == icontest) {
                                    thumb.setIcon(iconpatate1);
                                } else {
                                    thumb.setIcon(icontest);
                                }
                            }

                        });
//ajout au panel
                      //  panel.add(thumb, i, j);//utiliser un gridpanel a faire
                        //creation de l'objet  Parcelle
                        Parcelle parcelle = new Parcelle(0, false, false, Parcelle.typeChamps.vide);
//ajout aux tableaux
//                tabParcelleGUI[m][n] = thumb;
                        //      tabParcelleModele[m][n] = parcelle;

                    }


                }

                panel.add(thumb, i, j);
            }
        }

    }


    public void creationFenetre() {
        panel.setPreferredSize(new Dimension(850, 640));

        JFrame fenetre = new JFrame();
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);

    }
}
    
    
    
    
    

    

    
    
    
    
    
    
    
    

