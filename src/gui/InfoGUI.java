package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Crema on 01/05/2015.
 */
public class InfoGUI {

    private JPanel panel = new JPanel();

    public JPanel getPanel() {
        return panel;
    }

    public InfoGUI(String mess){
        panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel(mess));

    }
}
