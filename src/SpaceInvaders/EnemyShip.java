/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

public class EnemyShip extends Template{
    private final int row;
    private final int position;
    
    public EnemyShip(int x, int y, int row, int position){
        super(x,y,"EnemyShip.png");
        this.row=row;
        this.position=position;
    }  
    
    public int getPosition(){return position+1;}
    public int getRow(){return row;}
}
