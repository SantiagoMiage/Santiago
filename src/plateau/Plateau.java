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


public class Plateau {
    private JLabel[][] tabParcelleGUI = new JLabel[6][8];   //tableau image
    private Parcelle[][] tabParcelleModele = new Parcelle[6][8]; //tableau objet
    private JPanel panel = new JPanel();

    public Plateau() {

    }


    public Plateau(JPanel panel) {
        this.panel = panel;
    }

    public void initialisation() {

        //pour la performance , on instance seulement 5 fois au début , pour ne pas devoir le faire plus tard dans le listener, gain
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

//rajouter les plantations 2 ouvriers
        final ImageIcon iconparcelle = new ImageIcon(url_parcelle),
                iconpatate1 = new ImageIcon(url_patate1),
                iconpiment1 = new ImageIcon(url_piment1),
                iconbanane1 = new ImageIcon(url_banane1),
                iconbambou1 = new ImageIcon(url_bambou1),
                iconharicot1 = new ImageIcon(url_haricot1),
                icontest = new ImageIcon(url_test);



        //  panel.setLayout(new GridLayout(6, 8));
        for (int i = 0; i < tabParcelleModele.length; i++) {
            for (int j = 0; j < tabParcelleModele[0].length; j++) {


//creation label
                final JLabel thumb = new JLabel();
                thumb.setPreferredSize(new Dimension(100, 100));
//creation image
                //     String cheminimage= "/ressource/images/parcelle.png";
                //     URL url_icone = this.getClass().getResource(cheminimage);
                // ImageIcon imgThisImg = new ImageIcon(parcelle);
//affectation image au label par défaut sur parcelle
                thumb.setIcon(iconparcelle);
//gestion listener pour le label
                thumb.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("yolo");
                        //tester le changement d'icone

                        if (thumb.getIcon()==icontest){
                            thumb.setIcon(iconpatate1);
                        }else{
                            thumb.setIcon(icontest);
                        }
                    }

                });
//ajout au panel
                panel.add(thumb);//utiliser un gridpanel a faire
//creation de l'objet  Parcelle
                Parcelle parcelle = new Parcelle(0, false, false, Parcelle.typeChamps.vide);
//ajout aux tableaux
                tabParcelleGUI[i][j] = thumb;
                tabParcelleModele[i][j] = parcelle;

            }
        }

    }


    public void creationFenetre() {
        panel.setPreferredSize(new Dimension(800, 600));
        JFrame fenetre = new JFrame();
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);

    }
}
    
    
    
    
    

    

    
    
    
    
    
    
    
    

