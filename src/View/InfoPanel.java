package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *the info panel.
 *@version Winter 2018
 *@author tian1212
 */
public class InfoPanel extends JPanel {
    
    /**
     * the serial ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     *JLabel display the score. 
     */
    private JLabel myScore;
    
    /**
     * the constructor. 
     */
    public InfoPanel() {
        
        
        this.setLayout(new GridLayout(7, 1));
        final JLabel show = new JLabel("Move Left: Press ←, 'a', or 'A'");
        final JLabel show2 = new JLabel("Move Right: Press →, 'd', or 'D'");
        final JLabel show3 = new JLabel("Move Down: Press ↓, 's', or 'S'");
        final JLabel show4 = new JLabel("Rotate: Press ↑, 'w', or 'W'");
        final JLabel show5 = new JLabel("Drop: Press Space");
        final JLabel show6 = new JLabel("Pause: Press 'p' or 'P'");
        final JLabel show7 = new JLabel("Restart: Press 'R' or 'r'");
        this.add(show);
        this.add(show2);
        this.add(show3);
        this.add(show4);
        this.add(show5);
        this.add(show6);
        this.add(show7);
        this.setBackground(Color.yellow);
    }
    
}
