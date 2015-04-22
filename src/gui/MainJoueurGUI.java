package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yannis on 22/04/2015.
 */
public class MainJoueurGUI {

     JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));


    public void initialisation() {
        panel.setSize(new Dimension(800, 300));

    }



    public JPanel affichageMain() {
        initialisation();
        return panel;
    }
}
