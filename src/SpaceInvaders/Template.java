/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Template {    //klasa odpowiedzialna za tworzenie obiektów
    private Image image;
    public int x;
    public int y;
    protected int width;
    protected int height;
    protected boolean visible;
    
    public Template(int x, int y, String image){
        this.x=x;
        this.y=y;
        visible=true;
        ImageIcon icon = new ImageIcon(image);
        this.image=icon.getImage();
        imageDimensions();        
    }
    
    public void setImage(String image){
        ImageIcon icon = new ImageIcon(image);
        this.image=icon.getImage();
        imageDimensions();
    }
    
    public Image getImage(){
        return image;
    }
    
    public void setVisible(boolean state){
        visible=state;
    }
    
     public boolean getVisible(){
        return visible;
    }   
    
    
    private void imageDimensions(){
        this.width=image.getWidth(null);
        this.height=image.getHeight(null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
