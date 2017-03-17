/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

public class EnemyMissile extends Template{   
    private final int speed=2;
    
    public EnemyMissile(int x, int y) {
        super(x, y, "EnemyMissile.png");
    }
    
    public void move() {
        y += speed;
        
        if (y > 900){
            visible = false;
        }
    }
}
