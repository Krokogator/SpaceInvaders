/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame{
    public Main(){
        initGame();
    }
    public void initGame(){
        add(new Game());
        
        setResizable(false);
        pack();
        
        setTitle("Space Invaders v0.5 Alpha");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main gameThread = new Main();
                gameThread.setVisible(true);
            }
        });
    }    
}
