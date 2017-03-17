/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnemyMissileManager {
    Game game;
    private ArrayList<EnemyMissile> missiles;
    public EnemyMissileManager(Game game){
        this.game=game;
        missiles = new ArrayList<>();
    }
    
    public void fire(){        
            Thread t = new Thread(){
            @Override 
            public void run(){  
                Random generator = new Random();
                int position,delay=1500;
                while(true){
                    position=generator.nextInt(8)+1;
                    if(missiles.size()<10){
                        if(game.isColumnAlive(position)==true){
                            if(game.isShipAlive(1,position)==true){
                                missiles.add(new EnemyMissile(46*position+20*(position-1)+22,230));
                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(EnemyMissileManager.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else if(game.isShipAlive(2,position)==true){
                                missiles.add(new EnemyMissile(46*position+20*(position-1)+22,170));
                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(EnemyMissileManager.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else if(game.isShipAlive(3,position)==true){
                                missiles.add(new EnemyMissile(46*position+20*(position-1)+22,110));
                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(EnemyMissileManager.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }    
                }
            }
            
        };
        t.start();
    }
    
    public ArrayList getMissiles() {
        return missiles;        
    }
}
