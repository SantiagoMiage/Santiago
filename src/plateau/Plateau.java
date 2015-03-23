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

    // private JLabel[][] tabParcelleGUI = new JLabel[6][8];   //tableau image
    // private Parcelle[][] tabParcelleModele = new Parcelle[6][8]; //tableau objet
    private JLabel[][] tabParcelleGUI = new JLabel[10][13];   //tableau image
    private Parcelle[][] tabParcelleModele = new Parcelle[10][13]; //tableau objet
            /* On ajoute un gridbagLauout au panel */
    //private JPanel panel = new JPanel(new GridLayout(10, 13));
    private JPanel panel = new JPanel(new GridBagLayout());

    public Plateau() {

    }


    public Plateau(JPanel panel) {
        this.panel = panel;
    }

    public void initialisation() {
       //   panel.setSize(new Dimension(850, 640));
        panel.setSize(new Dimension(600, 500));
            /* Le gridBagConstraints va définir la position et la taille des éléments */
      //  GridBagConstraints gc = new GridBagConstraints();
       // gc.fill=GridBagConstraints.BOTH;



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
        String chemincanalhorirrigue = "/ressource/images/canalhorirrigue.png";
        URL url_canalhorirrigue = this.getClass().getResource(chemincanalhorirrigue);
        String chemincanalverti = "/ressource/images/canalverti.png";
        URL url_canalverti = this.getClass().getResource(chemincanalverti);
        String chemincanalvertirrigue = "/ressource/images/canalvertirrigue.png";
        URL url_canalvertirrigue = this.getClass().getResource(chemincanalvertirrigue);
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
                iconcanalhorirrigue = new ImageIcon(url_canalhorirrigue),
                iconcanalverti = new ImageIcon(url_canalverti),
                iconcanalvertirrigue = new ImageIcon(url_canalvertirrigue),
                iconintersection = new ImageIcon(url_intersection);

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 13; j++) {
                GridBagConstraints gc = new GridBagConstraints();

                final JLabel thumb = new JLabel();
                if ((i == 0 || i == 3 || i == 6 || i == 9) && (j == 0 || j == 3 || j == 6 || j == 9 || j == 12)) {
                    thumb.setIcon(iconintersection);
                    gc.gridx=j;
                    gc.gridy=i;
                } else {
                    if ((i == 0 || i == 3 || i == 6 || i == 9)) {
                        if (j == 1 || j ==4 || j ==7|| j ==10 || j ==13) {
                            thumb.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    //tester le changement d'icone pour canal irrigué
                                    if (thumb.getIcon() == iconcanalhori) {
                                        thumb.setIcon(iconcanalhorirrigue);
                                    } else {
                                        thumb.setIcon(iconcanalhori);
                                    }
                                }

                            });
                            thumb.setIcon(iconcanalhori);
                            gc.gridwidth = 2;
                            gc.fill = GridBagConstraints.HORIZONTAL;
                            gc.gridx = j;
                            gc.gridy = i;
                        }
                    } else if ((j == 0 || j == 3 || j == 6 || j == 9 || j == 12)) {
                        if (i == 1 || i ==4 || i ==7|| i ==10) {
                            thumb.setIcon(iconcanalverti);
                            thumb.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    //tester le changement d'icone pour canal irrigué
                                    if (thumb.getIcon() == iconcanalverti) {
                                        thumb.setIcon(iconcanalvertirrigue);
                                    } else {
                                        thumb.setIcon(iconcanalverti);
                                    }
                                }

                            });
                            gc.gridheight = 2;
                            gc.fill = GridBagConstraints.VERTICAL;
                            gc.gridx = j;
                            gc.gridy = i;
                        }
                    } else {
                        thumb.setPreferredSize(new Dimension(50, 50));
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
                        gc.gridx=j;
                        gc.gridy=i;
//creation de l'objet  Parcelle
                        Parcelle parcelle = new Parcelle(0, false, false, Parcelle.typeChamps.vide);
//ajout aux tableaux
                        tabParcelleGUI[i][j] = thumb;
                        tabParcelleModele[i][j] = parcelle;
                    }
                }
                panel.add(thumb, gc);
            }
        }
    }


    public void creationFenetre() {


        JFrame fenetre = new JFrame();
        fenetre.setTitle("Santiago");
        fenetre.setPreferredSize(new Dimension(1000, 900));
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);

    }
}
    
    
    
    
    

    

    
    
    
    
    
    
    
    

