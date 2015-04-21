package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.IOException;

/**
 * Created by Crema on 21/04/2015.
 */
public class LauncherGUI extends JFrame {

    private JPanel panel = new JPanel();
    private JButton boutonRejoindre = new JButton("Rejoindre partie");
    private JButton boutonCreer = new JButton("Creer partie");
    private JLabel labelPseudo = new JLabel("Entrez votre pseudo");
    private JLabel info = new JLabel(" ");
    private JTextField jtf = new JTextField("  Pseudo  ");

    public LauncherGUI() {
        String cheminparcelle = "/ressource/images/santiago.jpg";
        URL url_parcelle = this.getClass().getResource(cheminparcelle);
        final ImageIcon fondLauncher = new ImageIcon(url_parcelle);

        this.setTitle("Launcher Santiago");
        this.setSize(500, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setUndecorated(true);
        setContentPane(new JLabel(fondLauncher));
        this.setLayout(new GridBagLayout());

        //L'objet servant à positionner les composants
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LAST_LINE_END ;
        //On positionne la case de départ du composant
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(info, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelPseudo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(jtf, gbc);
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(boutonCreer, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(boutonRejoindre, gbc);

        this.getContentPane().add(panel);

        this.setVisible(true);

        boutonCreer.addActionListener(new BoutonCreerListener());
        boutonRejoindre.addActionListener(new BoutonRejoindreListener());

    }

    class BoutonCreerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            info.setText("En attente de joueurs");
            ServerSocket socketserver  ;
            Socket socketduserveur ;

            try {

                socketserver = new ServerSocket(2009);
                socketduserveur = socketserver.accept();
                info.setText("Un zéro s'est connecté !");

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

    public static void main(String[] args){
        LauncherGUI launcher = new LauncherGUI();

    }

}