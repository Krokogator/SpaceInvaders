/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;


public class PlayerMissile extends Template{
    private final int speed =5;
    
    public PlayerMissile(int x, int y){
        super(x,y,"PlayerMissile.png");
    }
    
    public void move() {
        y -= speed;
        
        if (y < height){
            visible = false;
        }
    }
}
