package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * next piece panel.
 * @version Winter 2018
 * @author tian1212
 */
public class ShowNextPanel extends JPanel implements Observer {
    
    /**
     * serial ID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * the block size. 
     */
    private int blockSize = 20;
    
    /**
     * the point array.
     */
    Point[] point;
    
    /**
     *the tetris piece.
     */
    TetrisPiece myPiece;
    
    /**
     * constructor.
     */
    public ShowNextPanel() {

        this.setBackground(Color.pink);
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myPiece = (TetrisPiece) theArg;
            point = myPiece.getPoints();
            repaint();
        }
        
    }
    
    /**
     * paint the block in Tetris panel.
     * @param theGraphics the graph.
     * 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        Shape shape;
        
        final int f = 4;
        final int t = 2;
        final int th = 3;
        final int tf = 25;
        final int ten = 10;
        final double df = 1;
        
        final Graphics2D graph = (Graphics2D) theGraphics;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        
        if (myPiece != null && point != null) {
            for (int i = 0; i < myPiece.getPoints().length; i++) {
                final int x = myPiece.getPoints()[i].x();
                final int y = myPiece.getPoints()[i].y();             
               
              
                if (myPiece.getBlock() == Block.I) {
                    shape = new Rectangle2D.Double(getWidth() / f   + (x * tf), 
                                                   (getHeight() / th * t - ten)  - (y * tf), 
                                                   blockSize, blockSize); 
                    
                } else if (myPiece.getBlock() == Block.O) {
                    shape = new Rectangle2D.Double(getWidth() / f  + (x * tf), 
                           (getHeight() / th * t - ten)  - (y * tf), 
                                                             blockSize, blockSize); 
                } else {
                    shape = new Rectangle2D.Double(getWidth() / th + (x * tf), 
                                                 getHeight() / 2 + df  - (y * tf), 
                                                 blockSize, blockSize);
                }
              
                graph.draw(shape);
                graph.fill(shape);
                graph.setPaint(Color.BLACK);
                                                  
                   
            }
        }
        
    }
   
    

}
