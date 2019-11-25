package actions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Board;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

/**
 *key listener.
 *@author tian1212
 *@Version Winter 2018. 
 */
public class MyKeyListener extends KeyAdapter {
    
    /**
     *the board. 
     */
    Board myBoard;
      
    /**
     *the timer. 
     */
    Timer myTimer;
    
//    /**
//     * the audio player.
//     */
//    AudioPlayer MGP;
//    
//    /**
//     * the bgm.
//     */
//    AudioStream BGM;
    
    

    /**
     *@param theBoard my board.
     *@param theTimer my timer.
     *constructor.
     * 
     */
    public MyKeyListener(final Board theBoard, final Timer theTimer) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;

    }
    
    /**
     * key pressed actions.
     * @param theEvent event 
     */
    public void keyPressed(final KeyEvent theEvent) {

        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_A:                
            case KeyEvent.VK_LEFT:
                myBoard.left();
                break;    

            case KeyEvent.VK_D:            
            case KeyEvent.VK_RIGHT:
                myBoard.right();
                break;    

            case KeyEvent.VK_W:    
            case KeyEvent.VK_UP:
                myBoard.rotateCW();
                break;    

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                myBoard.down();
                break;    

            case KeyEvent.VK_SPACE:
                myBoard.drop();
                break;
                
            case KeyEvent.VK_P:
                myTimer.stop();
                JOptionPane.showMessageDialog(null, 
                                              "You paused the game!\nPress R to continue",
                                              "Game Paused",
                                              JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case KeyEvent.VK_R:
                myTimer.restart();
                break;
                
//            case KeyEvent.VK_M:
//                setMusic();
//                break;  
                
                
            case KeyEvent.VK_N:
                myBoard.newGame();
                break;
                
            case KeyEvent.VK_E:
                gameOver();
             
                
                
                
              
            default:
                break;     
        }
    }
    
    /**
     *the game over method. 
     */
    private void gameOver() {
        myTimer.stop();
        JOptionPane.showMessageDialog(null, "Game Over!",
                                      "Game Over",
                                      JOptionPane.INFORMATION_MESSAGE);      
    }
    
    
//    /**
//     * call set music.
//     */
//    public void setMusic() {
//        music();
//        MGP.start(BGM);
//    }
//    
//    /**
//     *get the music. 
//     */
//    public void music()  {
//        MGP = AudioPlayer.player;
//      
//        try {
//            BGM = new AudioStream(new FileInputStream(new File("./support_files/ojbk.wav")));
//        } catch (final FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (final IOException e) {
//            e.printStackTrace();
//        }
//               
//    }

}
