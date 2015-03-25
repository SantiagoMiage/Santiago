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
import java.util.ArrayList;

/**
 * Created by Yannis Cipriani on 16/03/2015.
 */
public class Plateau {

    //Tableaux relatifs aux Parcelles
    ArrayList<JLabel> ListParcelleGUI = new ArrayList<JLabel>();
    ArrayList<Parcelle> ListParcelleModele = new ArrayList<Parcelle>();
    //Tableaux relatifs aux Intersections
    ArrayList<Intersection> ListIntersect = new ArrayList<Intersection>();
    private JLabel[][] tabParcelleGUI = new JLabel[8][6];   //tableau image
    private Parcelle[][] tabParcelleObjet = new Parcelle[8][6]; //tableau objet
    private JPanel panel = new JPanel(new GridBagLayout());

    public Plateau() {

    }


    public Plateau(JPanel panel) {
        this.panel = panel;
    }

    public void initialisation() {

        panel.setSize(new Dimension(600, 500));

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

        int compteurIparcelle = 0;
        int compteurJparcelle = 0;
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 13; j++) {
                GridBagConstraints gc = new GridBagConstraints();

                final JLabel thumb = new JLabel();
                //si intersection
                if (testIntersection(i, j)) {
                    thumb.setIcon(iconintersection);
                    gc.gridx = j;
                    gc.gridy = i;
                    //creation de l'objet  Intersection
                    Intersection inter = new Intersection(i, j);
                    //ajout au tableau d'intersection
                    ListIntersect.add(inter);
                } else {
                    //si canal horizontal
                    //  if (testCanalHori(i, j)) {
                    if ((i == 0 || i == 3 || i == 6 || i == 9)) {
                        if (j == 1 || j == 4 || j == 7 || j == 10 || j == 13) {
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
                        //si canal vertical
                        if (i == 1 || i == 4 || i == 7 || i == 10) {
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
                    } else {//si Parcelle


                        //
                        thumb.setPreferredSize(new Dimension(50, 50));
                        thumb.setIcon(iconparcelle);
                        //ajout au panel
                        gc.gridx = j;
                        gc.gridy = i;
                        //creation de l'objet  Parcelle
                        Parcelle parcelle = new Parcelle(0,0, false, false, Parcelle.typeChamps.vide,compteurIparcelle,compteurJparcelle);
                        //ajout aux tableaux
                        ListParcelleGUI.add(thumb);
                        ListParcelleModele.add(parcelle);
                        //Calcul pour remplir correctement le tableau 8*6 de Parcelle
                        if(compteurJparcelle<7){
                            compteurJparcelle++;
                        }else{
                            compteurJparcelle=0;
                            compteurIparcelle++;
                        }
                        //gestion listener pour le label
                        thumb.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                //on recupere la position de la parcelle en question dans la liste des GUI
                                int indexParcelle = ListParcelleGUI.indexOf(thumb);

                                //on travaille sur l'objet Parcelle se trouvant a la même position dans ListParcelleModele
                                //tester le changement d'icone
                                if (thumb.getIcon() == icontest) {
                                    thumb.setIcon(iconpatate1);
                                    ListParcelleModele.get(indexParcelle).champs = Parcelle.typeChamps.patate;
                                } else {
                                    thumb.setIcon(icontest);
                                    ListParcelleModele.get(indexParcelle).champs = Parcelle.typeChamps.test;
                                }

                                System.out.println(ListParcelleModele.get(indexParcelle).toString());
                            }

                        });
                    }
                }
                panel.add(thumb, gc);
            }
        }
    }


    //Les différents test
    public boolean testIntersection(int i, int j) {
        if ((i == 0 || i == 3 || i == 6 || i == 9) && (j == 0 || j == 3 || j == 6 || j == 9 || j == 12)) {
            return true;
        } else
            return false;
    }

    public boolean testCanalHori(int i, int j) {
        if (i == 0 || i == 3 || i == 6 || i == 9) {
            if (j == 1 || j == 4 || j == 7 || j == 10 || j == 13) {
                return true;
            }
            return false;
        } else
            return false;
    }


    //Creation et affichage du plateau
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
    
    
    
    
    

    

    
    
    
    
    
    
    
    

