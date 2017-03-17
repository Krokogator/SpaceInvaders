/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerShip extends Template{
    private ArrayList<PlayerMissile> missiles;
    private int direction;
    private boolean leftpressed=false;
    private boolean rightpressed=false;
    private boolean spacepressed=false;
    
    public PlayerShip(int x, int y){
        super(x,y,"PlayerShip.png");
        direction=0;
        missiles = new ArrayList<>();
    }
    
    public void move(){
        x += direction;
        if (x < -25) {
            x = 575;
        }
        if (x > 575){
            x = -25;
        }
    }
    
    private void fire(){        
        missiles.add(new PlayerMissile(x + width / 2 - 1, y-5));
    }
    
    public ArrayList getMissiles() {
        return missiles;        
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            if(spacepressed==false){
                fire();
                spacepressed=true;
            }
        }

        if (key == KeyEvent.VK_LEFT) {
            direction = -3;
            leftpressed=true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            direction = 3;
            rightpressed=true;
        }
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            spacepressed=false;
        }
        
        if (key == KeyEvent.VK_LEFT) {
            leftpressed=false;
            if(rightpressed==false){
                direction = 0;
            }
            else{
                direction = 3;
            }
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            rightpressed=false;
            if(leftpressed==false){
                direction = 0;
            }
            else{
                direction = -3;
            }
        }
    }
}
