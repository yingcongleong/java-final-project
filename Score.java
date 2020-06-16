package ntou.cs.java2020.project;

public class Score{

    private int score;
    
    public Score(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public void addPoints(){
        score += 10;
    }
}