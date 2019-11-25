package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import View.PlayZonePanel;
import View.ShowNextPanel;
import model.Board;


/**
 *This class set up the GUI.
 *@author tian1212
 *@version Winter 2018
 */
public class TetrisGUI implements Observer {
    
    /**
     *the mainframe. 
     */
    private JFrame myFrame;
    
    /**
     *The menu bar. 
     */
    private JMenuBar myMenuBar;
    
    /**
     *the big panel. 
     */
    private JPanel myBigPanel;
    
    /**
     * the right panel.
     */
    private JPanel myRightPanel;
    
    /**
     *the timer delay. 
     */
    private final int myTimerDelay = 400;
    
    /**
     * the timer.
     */
    private final Timer myTimer;
    
    /**
     * the info panel.
     */
    InfoPanel info;
    
    
    /**
     * play zone panel.
     */
    PlayZonePanel pz;
    
    /**next piece panel.
     * 
     */
    ShowNextPanel snp;
    
    /**
     * the board object.
     */
    Board board;
    

    /**
     * game over flag.
     */
    private boolean isOver = false;
    
    /**
     *the constructor. 
     */
    public TetrisGUI() {
        
        myTimer = new Timer(myTimerDelay, new TimerListener());
        board = new Board();
        info = new InfoPanel();
        myRightPanel = new JPanel();
        pz = new PlayZonePanel(board, myTimer);
        myBigPanel = new JPanel();
        snp = new ShowNextPanel();
        
        setUpGUI();          
    }
    
    /**
     * SetUP my gui.
     */
    private void setUpGUI() {
        
        final int seperator = 5;
        buildFrame();
        setJMenuBar();
        myBigPanel.setLayout(new GridLayout(1, 2));
        
        myRightPanel.setLayout(new GridLayout(2, 1, seperator, seperator));
        myRightPanel.add(snp);
        myRightPanel.add(info);
         
        myBigPanel.add(pz);
        myBigPanel.add(myRightPanel);
        myFrame.add(myBigPanel);
        
        board.addObserver(pz);
        board.addObserver(snp);
        board.addObserver(this);
        
        int total = 0;
        int n=20;
        for (int i = n; i < n-1; i ++){
        for (int j = 0; j < n; j ++){
        total = total + 1;
        System.out.println(total);
        }
        }

        
        
        
    }
    
    /**
     * start the game method.
     */
    public void startGame() {
        
        if (board != null && myTimer != null) {
            board.newGame();
            myTimer.start();
        }
        
    }
    
    /**
     * build up the frame.
     */
    private void buildFrame() {
        
        final int width = 400;
        final int height = 500;
        myFrame = new JFrame("Triton's Tetris");
        myFrame.setSize(width, height);
        
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        myFrame.pack();
        
    }
    
    
    /**
     * set up the menu bar.
     */
    private void setJMenuBar() {
              
        
        myMenuBar = new JMenuBar();
        final JMenu game = new JMenu("Game");
        final JMenu help = new JMenu("Help");
        final JMenuItem controls = new JMenuItem("Control Info");
        controls.addActionListener(new ActionListener() {

            @Override
        public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "Move Left: Press ←, 'a', or 'A'\n" 
                    +
                    "Move Right: Press →, 'd', or 'D'\n" 
                                +
                    "Move Down: Press ↓, 's', or 'S'\n" 
                                + 
                    "Rotate: Press ↑, 'w', or 'W'\n" 
                                +
                            "Drop: Press Space\n" 
                                + "Pause: Press 'p' or 'P'\n",
                                  "Tetris Controls", JOptionPane.INFORMATION_MESSAGE);    
            }                       
        });
        
        final JMenuItem info = new JMenuItem("About");
        info.addActionListener(new ActionListener() {

            @Override
        public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, 
                                  "Author: Triton\nClass: TCSS 350\nVersion: Winter 2018",
                                  "About", JOptionPane.INFORMATION_MESSAGE);    
            }                       
        });
        
        
        
        final JMenuItem newGame = new JMenuItem("New Game ^N");
        newGame.addActionListener(new ActionListener() {           
            public void actionPerformed(final ActionEvent arg0) {
                startGame();
            }          
            }); 
        
        
        
        final JMenuItem endGame = new JMenuItem("End Game   ^E");
        endGame.addActionListener(new ActionListener() {
            
            public void actionPerformed(final ActionEvent arg0) {
                gameOver();
            }          
            });
        
        
        final JMenuItem exit = new JMenuItem("Exit               ^X");        
        exit.addActionListener(new ActionListener() {
            
            public void actionPerformed(final ActionEvent arg0) {
                myFrame.dispatchEvent(new WindowEvent(myFrame, 
                    WindowEvent.WINDOW_CLOSING));             
            }          
            });
        game.add(newGame);
        game.add(endGame);
        game.addSeparator();
        game.add(exit);
        help.add(controls);
        help.add(info);
        
        myMenuBar.add(game);
        myMenuBar.add(help);
        myFrame.setJMenuBar(myMenuBar);      
    }
    
    
    
    /**
     * the main method.
     * @param theArgs
     */
    public static void main(final String[] theArgs) {
        new TetrisGUI();
    }
    
    /**
     *the timer listener.
     */
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            board.step();
        }
    }
    
    /**
     *the game over method.
     */
    private void gameOver() {
        myTimer.stop();
        isOver = true;
        JOptionPane.showMessageDialog(null, "Game Over!",
                                      "Game Over",
                                      JOptionPane.INFORMATION_MESSAGE);
        
    }
    


    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof Boolean) {
            gameOver();
        }
        
    }
    

}
