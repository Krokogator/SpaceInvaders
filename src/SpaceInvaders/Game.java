/*
 * @author Michał Szczepanowski
 */
package SpaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{
    private final int width=600;
    private final int height=900;
    private final int playerX=275;
    private final int playerY=840;
    private PlayerShip playerShip;
    private StarsEffect starsEffect;
    private ArrayList<EnemyShip> enemyShip;
    private EnemyMissileManager EMM;
    private Player player;
    private JButton newGameButton;
    private JPanel panel;
    
    private int playing;
    private Timer timer;
    private final int FPS = 120;
    private final int DELAY = 1000/FPS;
    Random generator = new Random();
    
    private int[] enemy1;
    private int[] enemy2;
    private int[] enemy3;
    
    public Game(){
        InitGame();
    }
    
    private void InitGame(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        playing = 1;
        setPreferredSize(new Dimension(width, height));
        playerShip = new PlayerShip(playerX, playerY); 
        starsEffect = new StarsEffect();
        timer = new Timer(DELAY, this);
        timer.start();
        initEnemyShip();
        EMM = new EnemyMissileManager(this);
        EMM.fire();
        player = new Player("Adam");
        
    }
    
    public void initEnemyShip() {
        int max=8;
        enemy1 = new int[max];
        enemy2 = new int[max];
        enemy3 = new int[max];
        int startpx=46;
        int space=20;
        for (int i=0;i<max;i++){
            enemy1[i]=startpx;
            enemy2[i]=startpx;
            enemy3[i]=startpx;
            startpx=startpx+46+space;
        }
        
        enemyShip = new ArrayList<>();
        int position=0;
        for (int p : enemy1) {enemyShip.add(new EnemyShip(p,200,1,position));position++;}position=0;
        for (int p : enemy2) {enemyShip.add(new EnemyShip(p,140,2,position));position++;}position=0;
        for (int p : enemy3) {enemyShip.add(new EnemyShip(p,80,3,position));position++;}
    }
    
    private void drawObjects(Graphics g) {
        

        ArrayList<PlayerMissile> pms = playerShip.getMissiles();
        for (PlayerMissile m : pms) {
            if (m.getVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }
        
        ArrayList<EnemyMissile> ems = EMM.getMissiles();
        try{
        for (EnemyMissile m : ems) {
            if (m.getVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }
        }
        catch (Exception ex) {System.out.println("Exception 1 - EnemyMissile arraylist");};

        ArrayList<Star> st = starsEffect.getStars();
        try{
        for (Star s : st) {
            if (s.getVisible()) {
                g.drawImage(s.getImage(), s.getX(), s.getY(), this);
            }
        }
        } 
        catch (Exception ex) {System.out.println("Exception 2 - Star arraylist");};
        for (EnemyShip a : enemyShip) {
            if (a.getVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
        
        
        
        
        if (playerShip.getVisible()){
            g.drawImage(playerShip.getImage(),playerShip.getX(), playerShip.getY(), this);
            }

        g.setColor(Color.WHITE);
        g.drawString("Score: "+player.getScore(), 5, 15);
    }
    
    private void drawNewGame(Graphics g){
        String msg = "New game, enter your nickname:";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width - fm.stringWidth(msg)) / 2,
                height / 2);
        
        newGameButton = new JButton("New Game!");
        panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(200, 200);
        panel.add(newGameButton);
    }
    
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width - fm.stringWidth(msg)) / 2,
                height / 2);
    }

    private void updatePlayerShip(){
        playerShip.move();
    }    
    private void updateStar(){
        ArrayList<Star> ms = starsEffect.getStars();

        for (int i = 0; i < ms.size(); i++) {

            Star m = ms.get(i);
            if (m.getVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }    
    private void updatePlayerMissiles() {

        ArrayList<PlayerMissile> ms = playerShip.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            PlayerMissile m = ms.get(i);

            if (m.getVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }     
    }    
    private void updateEnemyMissiles(){
        ArrayList<EnemyMissile> ms = EMM.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            EnemyMissile m = ms.get(i);

            if (m.getVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }   

    } 
    private void updateAliens() {

//        if (enemyShip.isEmpty()) {
//
//            //ingame = false;
//            return;
//        }

        for (int i = 0; i < enemyShip.size(); i++) {

            EnemyShip a = enemyShip.get(i);
//            if (a.getVisible()) {
//                a.move();
//            } else {
            if(!a.getVisible()){
                enemyShip.remove(i);
            }
            
        }
    }
    
    public void checkCollisions() {
    ArrayList<EnemyMissile> ms = EMM.getMissiles();    
    Rectangle r3 = playerShip.getBounds();

        for (EnemyMissile ES : ms) {
            Rectangle r2 = ES.getBounds();

            if (r3.intersects(r2)) {
                playerShip.setVisible(false);
                ES.setVisible(false);
                playing=2;
                //ingame = false;
            }
        }    
        
    ArrayList<PlayerMissile> ms2 = playerShip.getMissiles();

        for (PlayerMissile PM : ms2) {

            Rectangle r1 = PM.getBounds();

            for (EnemyShip ES : enemyShip) {

                Rectangle r2 = ES.getBounds();

                if (r1.intersects(r2)) {
                    PM.setVisible(false);
                    ES.setVisible(false);
                    player.addPoints(100);
                }
            }
        }
    }
    
    public boolean isColumnAlive(int position){
        for (EnemyShip ES : enemyShip) {
            if(ES.getPosition()==position){  
                return true;
            }
        }    
        return false;   
    }
    
    public boolean isShipAlive(int row,int position){
        for (EnemyShip ES : enemyShip) {
            if(ES.getRow()==row && ES.getPosition()==position){
                return true;
            }
        }
        return false;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {

//        inGame();
        updatePlayerShip();
        updatePlayerMissiles();
        
        updateEnemyMissiles();
        
        updateStar();
        updateAliens();
        checkCollisions();
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(playing==0){
            drawNewGame(g);
        }
        if(playing==1){
            drawObjects(g);
        }
        else if(playing==2){
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }  
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            playerShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            playerShip.keyPressed(e);
        }
    }
}
