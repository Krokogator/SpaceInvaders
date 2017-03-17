/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

public class Star extends Template{
    private final int speed=1;
    
    public Star(int x, int y) {
        super(x, y, "Star.png");
    }
    
    public void move() {
        y += speed;
        
        if (y > 900){
            visible = false;
        }
    }
}
