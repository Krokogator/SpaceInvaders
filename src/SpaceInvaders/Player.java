package SpaceInvaders;

/*
 * @author Michał Szczepanowski
 */

public class Player {
    private String nick;
    private int life;
    private int score;
    private int highscore;
    
    public Player(String nick){ //konstruktor, tworzy gracza i ustawia ilość życia
        this.nick=nick;
        life=3;
        score=0;
    }
    public boolean looselife(){ //odejmuje życie i zwraca true dla gameover
        life--;
        if (life<=0){
            return true;
        }
        return false;
    }
    
    public void score(int points){ //wynik gracza
        score=points;
        if(score>highscore){
            highscore=score;
        }
    }
    
    public void addPoints(int points){
        score+=points;
    }
    
    public int getScore(){
        return score;
    }
}
