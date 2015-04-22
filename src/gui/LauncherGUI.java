package gui;

import gameMaster.MaitreDuJeu;
import joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Crema on 21/04/2015.
 */
public class LauncherGUI {

    public JPanel getPanel() {
        return panel;
    }

    private JPanel panel = new JPanel();
    private JButton boutonRejoindre = new JButton("Rejoindre partie");
    private JButton boutonCreer = new JButton("Creer partie");
    private JButton boutonSolo = new JButton("Creer partie en local");
    private JLabel labelPseudo = new JLabel("Entrez votre pseudo");
    private JLabel info = new JLabel(" ");
    public JTextField jtf = new JTextField("  Pseudo  ");

    public String pseudo2String;
    public String pseudo3String;
    public String pseudo4String;

    public LauncherGUI() {
        String cheminparcelle = "/ressource/images/santiago.jpg";
        URL url_parcelle = this.getClass().getResource(cheminparcelle);
        final ImageIcon fondLauncher = new ImageIcon(url_parcelle);


        //this.setUndecorated(true);
        JLabel fond = new JLabel(fondLauncher);

        //L'objet servant à positionner les composants
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LAST_LINE_END ;
        //On positionne la case de départ du composant
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panel.add(info, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPseudo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(jtf, gbc);
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(boutonCreer, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(boutonRejoindre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(boutonSolo, gbc);

        boutonCreer.addActionListener(new BoutonCreerListener());
        boutonRejoindre.addActionListener(new BoutonRejoindreListener());
        boutonSolo.addActionListener(new BoutonSoloListener());

    }

    class BoutonCreerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            info.setText("En attente de joueurs");
            ServerSocket socketserver  ;
            Socket socketduserveur ;

            try {

                socketserver = new ServerSocket(2009);
                socketduserveur = socketserver.accept();
                info.setText("quelqu'un s'est connecté !");

                socketserver.close();
                socketduserveur.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class BoutonRejoindreListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            Socket socket;

            try {

                socket = new Socket(InetAddress.getLocalHost(),2009);
                socket.close();

            }catch (UnknownHostException e) {

                e.printStackTrace();
            }catch (IOException e) {

                e.printStackTrace();
            }
            labelPseudo.setText("Vous avez cliqué sur le bouton 1");
        }
    }

    class BoutonSoloListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane pseudo2 = new JOptionPane();
            JOptionPane pseudo3 = new JOptionPane();
            JOptionPane pseudo4 = new JOptionPane();
            pseudo2String = pseudo2.showInputDialog(null, "Entrez le pseudo joueur 2", "Pseudo joueur 2! ", JOptionPane.QUESTION_MESSAGE);
            pseudo3String = pseudo3.showInputDialog(null, "Entrez le pseudo joueur 3", "Pseudo joueur 3! ", JOptionPane.QUESTION_MESSAGE);
            pseudo4String = pseudo4.showInputDialog(null, "Entrez le pseudo joueur 4", "Pseudo joueur 4! ", JOptionPane.QUESTION_MESSAGE);

        }

    }
}