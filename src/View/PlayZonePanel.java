package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import actions.MyKeyListener;
import model.Block;
import model.Board;

/**
 * @author tian1212
 * @version Winter  2018
 * the play zone pan01.
 */
public class PlayZonePanel extends JPanel implements Observer {
   
    /**
     * the serial ID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * for testing purpose.
     */
    JLabel test;
    
    /**
     * the block list.
     */
    private Block[][] blockList;
    
    /**
     * the block size.
     */
    private final int blockSize = 20;
    
    /**
     * the board. 
     */
    Board myBoard;
    
    /**
     * the timer.
     */
    Timer myTimer;     
    
    /**
     * my play zone panel.
     * @param  theBoard my board.
     * @param theTimer myTimer.
     */
    public PlayZonePanel(final Board theBoard, final Timer theTimer) {
        
        myBoard = theBoard;
        myTimer = theTimer;
        
        this.addKeyListener(new MyKeyListener(myBoard, myTimer));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setBackground(getRandomColor());       
    }
    /**
     * paint the components.
     * @param theGraphics 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D graph = (Graphics2D) theGraphics;
        
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
        
        final int scale = getWidth() / 10;
        final int num = 4;
        //draw the grid
        for (int i = 0; i <= (getWidth() / scale); i++) {
            for (int j = 0; j <= (getHeight() / scale); j++) {
                graph.drawRect(i * scale, j * scale, scale + 1 - num, scale + 1 - num);
               
            }
        }
        
        //draw the components
        if (blockList != null) {
            for (int i = 0; i < blockList.length; i++) {
                for (int j = 0; j < blockList[i].length; j++) {
                    if (blockList[i][j] != null) {
                        final Shape shape = new Rectangle2D.Double(scale * j,
                             -getHeight() / 20 * (i + 1) + getHeight(), 
                             getWidth() / 10, getHeight() / 20);
                        graph.fill(shape);
                        Color c = getRandomColor();
                        graph.setPaint(c);
                        graph.draw(shape);
                        repaint();
                    }
                }
            }
        }
    }

    /**
     * get the random color.
     * @return random color.
     */
    private Color getRandomColor() {
        final Random rand = new Random();
        final float r = rand.nextFloat();
        final float g = rand.nextFloat();
        final float b = rand.nextFloat();
        final Color randomColor = new Color(r, g, b);
        
        return randomColor;
    }
    
    
    @Override
    public void update(final Observable arg0, final Object arg1) {
        
        if (arg1 instanceof Block[][]) {           
            blockList = (Block[][]) arg1;
            repaint();
        }

        
    }

    
}
