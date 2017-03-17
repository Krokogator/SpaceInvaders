/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.util.ArrayList;
import java.util.Random;

public final class StarsEffect{
    private final ArrayList<Star> stars;
    private final int[] xlist;
        
    public StarsEffect(){
            stars = new ArrayList<>();
            xlist = new int[10];
            CreateStars();
    }
    
    public void CreateStars(){
        
        Thread t = new Thread(){
            @Override 
            public void run(){  
                Random generator = new Random();
                int starX,starY;
                while(true){
                    try { 
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println("Exception in CreateStars thread");
                    }
                    starX=generator.nextInt(600);
                    starY=generator.nextInt(100);
                    stars.add(new Star(starX,starY-100));          
                }
            }
        };
        t.start();
    }
    
    public ArrayList getStars() {
        return stars;
    }
}
