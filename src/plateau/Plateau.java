/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plateau;


import joueur.Joueur;

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
public class Plateau extends JApplet {

    //Tableaux relatifs aux Parcelles
    ArrayList<JLabel> ListParcelleGUI = new ArrayList<JLabel>();
    ArrayList<Parcelle> ListParcelleModele = new ArrayList<Parcelle>();
    //Tableaux relatifs aux Canaux
    ArrayList<JLabel> ListCanauxGUI = new ArrayList<JLabel>();
    ArrayList<Canal> ListCanauxModele = new ArrayList<Canal>();
    //Tableaux relatifs aux Intersections
    ArrayList<JLabel> ListIntersectGUI = new ArrayList<JLabel>();
    ArrayList<Intersection> ListIntersect = new ArrayList<Intersection>();

    JLabel parcelleChoisie = null;
    JLabel canalChoisi = null;
    boolean depotencours = false;
    boolean propositionCanalEncours = false;

    //les threads
    Thread threadAttenteDepotParcelle;
    Thread threadAttenteChoixCanalProposition;


    private JPanel panel = new JPanel(new GridBagLayout());

    public Plateau() {

    }

    public Plateau(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void initialisation() {

        panel.setSize(new Dimension(500, 500));
        panel.setBorder(BorderFactory.createLineBorder(Color.blue));
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
        String cheminvide = "/ressource/images/vide.png";
        URL url_vide = this.getClass().getResource(cheminvide);
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
                iconvide = new ImageIcon(url_vide),
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
                    xIntersec = xIntersec + 2;
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

                                    if (propositionCanalEncours) {

                                        if (estIrriguable(canal)) {
                                            canalChoisi = thumb;
                                            synchronized (threadAttenteChoixCanalProposition) {
                                                threadAttenteChoixCanalProposition.notify();

                                            }
                                        }
                                            /*Irrigation(canal);
                                            tester le changement d'icone pour canal irrigue
                                            if (thumb.getIcon() == iconcanalhori) {
                                                thumb.setIcon(iconcanalhorirrigue);
                                                canal.setIrrigue(true);
                                            }
                                            */

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

                            thumb.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {

                                    if (propositionCanalEncours) {
                                        if (estIrriguable(canal)) {
                                            canalChoisi = thumb;
                                            synchronized (threadAttenteChoixCanalProposition) {
                                                threadAttenteChoixCanalProposition.notify();

                                            }
                                        }
                                        /*
                                        Irrigation(canal);
                                        tester le changement d'icone pour canal irrigue
                                        if (thumb.getIcon() == iconcanalverti) {
                                            thumb.setIcon(iconcanalvertirrigue);
                                            canal.setIrrigue(true);
                                        }
                                        }*/


                                    }

                                }

                            });
                            thumb.setIcon(iconcanalverti);
                            //on enregistre l'image du canal dans la liste GUI
                            ListCanauxGUI.add(thumb);
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

                                if (depotencours) {
                                    if (thumb.getIcon() == iconparcelle) {
                                        parcelleChoisie = thumb;
                                        //message avertissant le thread

                                        synchronized (threadAttenteDepotParcelle) {
                                            threadAttenteDepotParcelle.notify();
                                            //stocker la parcelle dans la main du joueur
                                        }

                                        //Desssiner un Rectangle et modifier sa couleur de fond
                                        //     colorierOuvrier(thumb);

                                    }
                                }

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
        return ((elem.getNumligne() < canal.yfin) && (elem.getNumligne() >= canal.ydeb) && ((elem.getNumcolonne() == canal.xdeb) || (elem.getNumcolonne() == canal.xdeb - 1)));
    }

    //Test pour savoir si une parcelle est adjacente  a un canal horizontal
    public boolean trouveAdjacentHori(Canal canal, Parcelle elem) {
        return ((elem.getNumcolonne() < canal.xfin) && (elem.getNumcolonne() >= canal.xdeb) && ((elem.getNumligne() == canal.ydeb) || (elem.getNumligne() == canal.ydeb - 1)));
    }

    //Test un canal si il peut etre irrigue (touche la source ou un autre canal)
    public boolean estIrriguable(Canal canal) {
        int xdeb = canal.getXdeb();
        int ydeb = canal.getYdeb();
        int xfin = canal.getXfin();
        int yfin = canal.getYfin();
        boolean ok = false;
        //si le canal n est pas irrigue a la base
        if (!canal.isIrrigue()) {
            for (Intersection elem : ListIntersect) {
                //si l'intersection est irrigue
                if (elem.isirrigue()) {
                    //si les coordonnées correspondent au debut du canal
                    if ((xdeb == elem.getI()) && (ydeb == elem.getJ())) {
                        //alors on renvoie vrai
                        ok = true;
                        //on passe l'autre intersection (fin) a irrigue
                        irrigueIntersection(xfin, yfin);
                    } else if ((xfin == elem.getI()) && (yfin == elem.getJ())) {  //si les coordonnées correspondent a la fin du canal
                        ok = true;
                        //on passe l'autre intersection (debut) a irrigue
                        irrigueIntersection(xdeb, ydeb);
                    }
                }
            }
        }
        return ok;

    }

    ///////////////////
    /////FONCTION//////
    ///////////////////

    public void colorierOuvrier(final JLabel thumb) {
        Parcelle parcelle = guiToModeleParcelle(thumb);
        //recup le nb ouvrier de la parcelle correspondant au thumb
        final int nbouv = parcelle.getNbouvrier();

        //recuperer la position de la parcelle
        int posx = thumb.getX();
        int posy = thumb.getY();
        System.out.println("posx " + posx + "posy " + posy);

        //calcul de la position du premier carré de couleur
        final int posxcarre = posx + 5;
        final int posycarre = posy + 5;


        //coloriage du premier ouvrier
        JPanel aDessiner = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.MAGENTA);
                g.fillOval(posxcarre, posycarre, 10, 10);
                //thumb.paintComponents(g);
                //g.fillOval(posxcarre, posycarre, 10, 10);
                //thumb.paint(g);
                System.out.println(posxcarre + " " + posycarre);
            }
        };

        //   panel.add(aDessiner);
        //this.panel.add(aDessiner);
        //  this.panel.revalidate();

        panel.getParent().add(aDessiner, 2);
        this.panel.revalidate();
        // panel.getParent().add(aDessiner,2);
        // panel.add(aDessiner);

        //coloriage du second ouvrier si il y en a un
    /*    if (nbouv > 1) {
            //calcul de la position du second carré de couleur
            int posxcarredeux = posxcarre + 15;
            int posycarredeux = posycarre;
            //coloriage du second ouvrier
        }
*/
    }


    public JLabel modeleToGuiIParcelle(Parcelle parcelle) {
        int index = ListParcelleModele.indexOf(parcelle);
        return ListParcelleGUI.get(index);
    }

    public Parcelle guiToModeleParcelle(JLabel thumb) {
        int index = ListParcelleGUI.indexOf(thumb);
        return ListParcelleModele.get(index);
    }


    public void irrigueIntersection(int i, int j) {
        for (Intersection elem : ListIntersect) {
            if (elem.getI() == i && elem.getJ() == j) {
                elem.setirrigue(true);
            }
        }
    }

    //Renvoie la liste des Parcelles Adjacentes au canal
    public ArrayList<Parcelle> listeParcellesAdjacentes(Canal canal) {
        ArrayList<Parcelle> listeP = new ArrayList<Parcelle>();
        for (Parcelle elem : ListParcelleModele) {
            if (canal.estHorizontale()) {

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
        ArrayList<Parcelle> listeP;
        listeP = listeParcellesAdjacentes(canal);
        for (Parcelle parcelle : listeP) {
            parcelle.setIrrigue(true);
        }
    }


    public void initialisationSource() {
        String cheminsource = "/ressource/images/source.png";
        URL url_source = this.getClass().getResource(cheminsource);
        ImageIcon iconsource = new ImageIcon(url_source);

        Random randomGenerator;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(ListIntersect.size());
        ListIntersect.get(index).setirrigue(true);
        ListIntersectGUI.get(index).setIcon(iconsource);
    }

    //permet de choisir sur le plateau le canal que l'on veut encherir
    public Canal choixCanal(Joueur joueur) {

        propositionCanalEncours = true;
        canalChoisi = null;
        Thread t = new Thread();
        threadAttenteChoixCanalProposition = t;
        threadAttenteChoixCanalProposition.start();
        synchronized (threadAttenteChoixCanalProposition) {
            while (canalChoisi == null) {
                try {
                    threadAttenteChoixCanalProposition.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            propositionCanalEncours = false;

            //on recupere le canal correspondant au JLabel et on le return
            int index = ListCanauxGUI.indexOf(canalChoisi);
            System.out.println("index "+index);
            Canal canal = ListCanauxModele.get(index);
            return canal;
        }

    }

    //permet de deposer sur le plateau la parcelle que l'on a en main
    public void depotParcelle(Joueur joueur) {

        depotencours = true;
        parcelleChoisie = null;
        Thread t = new Thread();
        threadAttenteDepotParcelle = t;
        threadAttenteDepotParcelle.start();
        synchronized (threadAttenteDepotParcelle) {
            while (parcelleChoisie == null) {
                try {
                    threadAttenteDepotParcelle.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            depotencours = false;
            deposerParcelle(joueur);
        }
    }

    public void deposerParcelle(Joueur joueur) {

        //on recupere la position de la parcelle sur laquel le joueur a cliquer
        int indexParcelle = ListParcelleGUI.indexOf(parcelleChoisie);
        //on recupere la parcelle que le joueur posse (a obtenu dans la phase d'enchere)
        Parcelle parcelleMain = joueur.getParcelleMain();
        //on rempli les ouvriers
        parcelleMain.setNbouvrieractif(parcelleMain.getNbouvrier());
        //on met a jour la liste des parcelles du plateau
        ListParcelleModele.set(indexParcelle, parcelleMain);
        //on modifie l'Affichage de la parcelle sur le plateau
        retournerParcelle(parcelleChoisie, parcelleMain);
        ListParcelleGUI.set(indexParcelle, parcelleChoisie);
        //on vide la main du joueur
        joueur.setParcelleMain(null);
    }


    //TODO affiche la parcelle nouvellemtn placé
    public void retournerParcelle(JLabel thumb, Parcelle parcelle) {
        String chemin = null;
        URL url = null;
        switch (parcelle.getChamps()) {
            case patate:
                if (parcelle.getNbouvrier() == 1) {
                    chemin = "/ressource/images/patate1.png";
                } else {
                    chemin = "/ressource/images/patate2.png";
                }
                break;

            case piment:
                if (parcelle.getNbouvrier() == 1) {
                    chemin = "/ressource/images/piment1.png";
                } else {
                    chemin = "/ressource/images/piment2.png";
                }
                break;

            case banane:
                if (parcelle.getNbouvrier() == 1) {
                    chemin = "/ressource/images/banane1.png";
                } else {
                    chemin = "/ressource/images/banane2.png";
                }
                break;

            case bambou:
                if (parcelle.getNbouvrier() == 1) {
                    chemin = "/ressource/images/bambou1.png";
                } else {
                    chemin = "/ressource/images/bambou2.png";
                }
                break;

            case haricot:
                if (parcelle.getNbouvrier() == 1) {
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


    private static class Jeton extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Tracer une ligne rouge entre les points (x=5, y=30) et (x=50, y=70)
            g.setColor(Color.red);
            g.drawLine(5, 30, 50, 70);
            //carre
            g.setColor(Color.blue);
            g.fillRect(100, 100, 50, 50);

            g.drawRect(10, 10, 50, 60);
            g.fillRect(65, 65, 30, 40);


        }

        public Dimension getPreferredSize() {
            return new Dimension(10, 10); // appropriate constants
        }

    }


}

















