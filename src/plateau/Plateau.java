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
import java.util.Random;

/**
 * Created by Yannis Cipriani on 16/03/2015.
 */
public class Plateau {

    //Tableaux relatifs aux Parcelles
    ArrayList<JLabel> ListParcelleGUI = new ArrayList<JLabel>();
    ArrayList<Parcelle> ListParcelleModele = new ArrayList<Parcelle>();
    //Tableaux relatifs aux Canaux
    ArrayList<JLabel> ListCanauxGUI = new ArrayList<JLabel>();
    ArrayList<Canal> ListCanauxModele = new ArrayList<Canal>();
    //Tableaux relatifs aux Intersections
    ArrayList<JLabel> ListIntersectGUI = new ArrayList<JLabel>();
    ArrayList<Intersection> ListIntersect = new ArrayList<Intersection>();

    /*
    private JLabel[][] tabParcelleGUI = new JLabel[8][6];   //tableau image
    private Parcelle[][] tabParcelleObjet = new Parcelle[8][6]; //tableau objet
    */
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


        //compteur pour enregistrer les coordonées des Parcelles
        int compteurIparcelle = 0;
        int compteurJparcelle = 0;
        //compteur pour enregistrer les coordonées des canaux HOrizontaux
        int xdebH = 0;
        int ydebH = 0;
        int xfinH = 0;
        int yfinH = 0;
        //compteur pour enregistrer les coordonées des canaux Verticaux
        int xdebV = 0;
        int ydebV = 0;
        int xfinV = 0;
        int yfinV = 2;
        //compteur pour enregistrer les intersections
        int xIntersec = 0;
        int yIntersec = 0;

        //Construction du Plateau
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
                    if (xIntersec > 8) {
                        xIntersec = 0;
                        yIntersec = yIntersec + 2;

                    }
                    Intersection inter = new Intersection(xIntersec, yIntersec);
                    xIntersec =  xIntersec + 2 ;
                    //ajout au tableau d'intersection
                    ListIntersect.add(inter);
                    ListIntersectGUI.add(thumb);
                } else {
                    //si canal
                    if (testCanal(i) || testCanaldeux(j)) {
                        //si canal horizontal
                        if (testCanalHori(j)) {
                            //on ajoute 2 a xfin
                            xfinH = xfinH + 2;
                            if (xfinH > 8) {
                                xdebH = 0;
                                xfinH = 2;
                                ydebH = ydebH + 2;
                                yfinH = yfinH + 2;
                            }
                            //on creer le canal
                            final Canal canal = new Canal(false, xdebH, ydebH, xfinH, yfinH);
                            //on enregistre le canal dans la liste modele
                            ListCanauxModele.add(canal);
                            //on modifie les compteurs
                            xdebH = xfinH;
                            //on travaille dessus
                            thumb.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (estIrriguable(canal)) {
                                        System.out.println(canal.toString());
                                        Irrigation(canal);

                                        //tester le changement d'icone pour canal irrigué
                                        if (thumb.getIcon() == iconcanalhori) {
                                            thumb.setIcon(iconcanalhorirrigue);
                                            canal.setIrrigue(true);
                                        } else {
                                            thumb.setIcon(iconcanalhori);
                                            canal.setIrrigue(false);
                                        }
                                    }
                                }


                            });
                            thumb.setIcon(iconcanalhori);
                            //on enregistre l'image du canal dans la liste GUI
                            ListCanauxGUI.add(thumb);
                            gc.gridwidth = 2;
                            gc.fill = GridBagConstraints.HORIZONTAL;
                            gc.gridx = j;
                            gc.gridy = i;
                        }
                        //si canal vertical
                        if (testCanalVerti(i)) {
                            //on creer le canal
                            final Canal canal = new Canal(false, xdebV, ydebV, xfinV, yfinV);
                            //on enregistre le canal dans la liste modele
                            ListCanauxModele.add(canal);
                            //on modifie les compteurs
                            if (xdebV > 7) {
                                xdebV = 0;
                                xfinV = 0;
                                ydebV = yfinV;
                                yfinV = ydebV + 2;
                            } else {//a chaque tour
                                xdebV = xdebV + 2;
                                xfinV = xdebV;
                                yfinV = ydebV + 2;
                            }
                            //on travaille dessus
                            thumb.setIcon(iconcanalverti);
                            thumb.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (estIrriguable(canal)) {

                                        Irrigation(canal);
                                        //tester le changement d'icone pour canal irrigué
                                        if (thumb.getIcon() == iconcanalverti) {
                                            thumb.setIcon(iconcanalvertirrigue);
                                            canal.setIrrigue(true);
                                        } else {
                                            thumb.setIcon(iconcanalverti);
                                            canal.setIrrigue(false);
                                        }
                                    }
                                }

                            });
                            gc.gridheight = 2;
                            gc.fill = GridBagConstraints.VERTICAL;
                            gc.gridx = j;
                            gc.gridy = i;
                        }
                    } else {//si Parcelle
                        thumb.setPreferredSize(new Dimension(50, 50));
                        thumb.setIcon(iconparcelle);
                        //ajout au panel
                        gc.gridx = j;
                        gc.gridy = i;
                        //creation de l'objet  Parcelle
                        Parcelle parcelle = new Parcelle(0, 0, false, false, Parcelle.typeChamps.vide, compteurIparcelle, compteurJparcelle);
                        //ajout aux tableaux
                        ListParcelleGUI.add(thumb);
                        ListParcelleModele.add(parcelle);
                        //Calcul pour remplir correctement le tableau 8*6 de Parcelle
                        if (compteurJparcelle < 7) {
                            compteurJparcelle++;
                        } else {
                            compteurJparcelle = 0;
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

                                System.out.println(ListParcelleModele.get(indexParcelle).toStringlight());
                            }

                        });
                    }
                }
                panel.add(thumb, gc);
            }
        }
        initialisationSource();
    }

    ///////////////////
    ///////TESTS///////
    ///////////////////
    public boolean testIntersection(int i, int j) {
        return ((i == 0 || i == 3 || i == 6 || i == 9) && (j == 0 || j == 3 || j == 6 || j == 9 || j == 12));
    }

    public boolean testCanal(int i) {
        return (i == 0 || i == 3 || i == 6 || i == 9);
    }

    public boolean testCanaldeux(int j) {
        return (j == 0 || j == 3 || j == 6 || j == 9 || j == 12);
    }

    public boolean testCanalHori(int j) {
        return (j == 1 || j == 4 || j == 7 || j == 10 || j == 13);
    }

    public boolean testCanalVerti(int i) {
        return (i == 1 || i == 4 || i == 7 || i == 10);
    }

    //Test pour savoir si une parcelle est adjacente  a un canal vertical
    public boolean trouveAdjacentVerti(Canal canal, Parcelle elem) {
        return ((elem.numligne < canal.yfin) && (elem.numligne >= canal.ydeb) && ((elem.numcolonne == canal.xdeb) || (elem.numcolonne == canal.xdeb - 1)));
    }

    //Test pour savoir si une parcelle est adjacente  a un canal horizontal
    public boolean trouveAdjacentHori(Canal canal, Parcelle elem) {
        return ((elem.numcolonne < canal.xfin) && (elem.numcolonne >= canal.xdeb) && ((elem.numligne == canal.ydeb) || (elem.numligne == canal.ydeb - 1)));
    }

    //Test un canal si il peut etre irrigué (touche la source ou un autre canal)
    public boolean estIrriguable(Canal canal) {

        System.out.println("estIrriguable");
        int xdeb = canal.getXdeb();
        int ydeb = canal.getYdeb();
        int xfin = canal.getXfin();
        int yfin = canal.getYfin();
        System.out.println(canal.toString());
        boolean ok = false;
        for (Intersection elem : ListIntersect) {
            //si l'intersection est irrigué

            if (elem.isIrrigué()) {
                System.out.println("element est irrigué");
                System.out.println(elem.toString());
                //si les coordonnées correspondent au debut du canal
                if ((xdeb == elem.getJ()) && (ydeb == elem.getI()) ) {
                    //alors on renvoie vrai
                    ok = true;
                    System.out.println("ok deb");
                    //on passe l'autre intersection (fin) a irrigue
                    irrigueIntersection(yfin, xfin);
                } else if ((xfin == elem.getJ()) && (yfin == elem.getI()) ) {  //si les coordonnées correspondent a la fin du canal
                    ok = true;
                    System.out.println("ok fin");
                    //on passe l'autre intersection (debut) a irrigue
                    irrigueIntersection(ydeb, xdeb);
                }
            }
        }
        return ok;

    }

    ///////////////////
    /////FONCTION//////
    ///////////////////

    public void irrigueIntersection(int i, int j) {
        for (Intersection elem : ListIntersect) {
            if (elem.getI() == i && elem.getJ() == j) {
                elem.setIrrigué(true);
            }
        }
    }



    //Renvoie la liste des Parcelles Adjacentes au canal
    public ArrayList<Parcelle> listeParcellesAdjacentes(Canal canal) {
        System.out.println("listeParcellesAdjacentes");
        ArrayList<Parcelle> listeP = new ArrayList<Parcelle>();

        for (Parcelle elem : ListParcelleModele) {
            if (canal.estHorizontale()) {
                System.out.println("listeParcAdj hori");
                if (trouveAdjacentHori(canal, elem)) {
                    listeP.add(elem);
                }
            } else if (canal.estVerticale()) {
                if (trouveAdjacentVerti(canal, elem)) {
                    listeP.add(elem);
                }
            }
        }
        return listeP;
    }

    //irrigue les Parcelles Adjacentes au canal
    public void Irrigation(Canal canal) {
        System.out.println("irrigation()");
        ArrayList<Parcelle> listeP;
        listeP = listeParcellesAdjacentes(canal);
        System.out.println(listeP.toString());
        for (Parcelle parcelle : listeP) {
            System.out.println(parcelle.toString());
            parcelle.irrigué = true;
        }
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

    public void initialisationSource() {
        String cheminsource = "/ressource/images/source.png";
        URL url_source = this.getClass().getResource(cheminsource);
        ImageIcon iconsource = new ImageIcon(url_source);

        Random randomGenerator;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(ListIntersect.size());
        ListIntersect.get(index).setIrrigué(true);
        ListIntersectGUI.get(index).setIcon(iconsource);
    }


}
    
    
    
    
    

    

    
    
    
    
    
    
    
    

