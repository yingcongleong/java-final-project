package ntou.cs.java2020.project;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.Border;


public class Game2 extends JPanel implements ActionListener{

    private boolean effect = false;
    private boolean turtle = false;
    private boolean rabbit = false;
    public int count = 0;
    public int speed = 100;
    private int effectNum = 2;

    private int height;
    private int width;
    private int snakeBodyX[] = new int[50];
    private int snakeBodyY[] = new int[50];
    private int snakeLength;
    public int snakeSize = 10;

    private int appleX;
    private int appleY;
    private int rabbitX;
    private int rabbitY;
    private int turtleX;
    private int turtleY;

    private ImageIcon b;
    private ImageIcon h;
    private ImageIcon a;
    private ImageIcon r;
    private ImageIcon t;
    private ImageIcon w;

    private Timer timer;

    public boolean inGame;
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;

    public Score myScore = new Score();
    public JLabel scoreLabel = new JLabel();
    

    public Game2(){
        addKeyListener(new TAdapter());
        Border line = BorderFactory.createLineBorder(Color.white);
        setBorder(line);
        
        setFocusable(true);
        setBackground(Color.black);
        height = 780;
        width = 400;
        snakeLength = 5;
        snakeBodyX[0] = 300;  //head x
        snakeBodyY[0] = 300;  //head y
        snakeBodyX[1] = 290;  //body1 x
        snakeBodyY[1] = 300;  //body1 y
        snakeBodyX[2] = 280;  //body2 x
        snakeBodyY[2] = 300;  //body2 y
        snakeBodyX[3] = 270;  //body3 x
        snakeBodyY[3] = 300;  //body3 y
        snakeBodyX[4] = 260;  //tail x
        snakeBodyY[4] = 300;  //tail y

        LocateApple();
        LocateRabbit();
        LocateTurtle();
        checkEffect();
        b = new ImageIcon("image\\body2.png");
        h = new ImageIcon("image\\head2.png");
        a = new ImageIcon("image\\apple2.png");
        t = new ImageIcon("image\\turtle2.png");
        r = new ImageIcon("image\\rabbit2.png");
        w = new ImageIcon("image\\wall.png");
        Image tempA = a.getImage();
        Image tempH = h.getImage();
        Image tempB = b.getImage();
        Image tempT = t.getImage();
        Image tempR = r.getImage();
        Image tempW = w.getImage();
        tempH = tempH.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempB = tempB.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempA = tempA.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempT = tempT.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempR = tempR.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempW = tempW.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        h = new ImageIcon(tempH);
        b = new ImageIcon(tempB);
        a = new ImageIcon(tempA);
        t = new ImageIcon(tempT);
        r = new ImageIcon(tempR);
        w = new ImageIcon(tempW);

        timer = new Timer(speed,this);
        timer.start();
        inGame = true;
        up = false;
        down = false;
        left = false;
        right = true;
    }   

    public void LocateApple(){
        Random rnd = new Random();
        appleX = rnd.nextInt(36) + 2;
        appleY = rnd.nextInt(76) + 2;
        appleX *= snakeSize;
        appleY *= snakeSize;
    }

    public void LocateRabbit(){
        Random rnd = new Random();
        rabbitX = rnd.nextInt(36) + 3;
        rabbitY = rnd.nextInt(76) + 3;
        rabbitX *= snakeSize;
        rabbitY *= snakeSize;
    }

    public void LocateTurtle(){
        Random rnd = new Random();
        turtleX = rnd.nextInt(36) + 2;
        turtleY = rnd.nextInt(76) + 2;
        turtleX *= snakeSize;
        turtleY *= snakeSize;
    }
    
    public void checkAppleEaten(){
        if ((snakeBodyX[0] == appleX) && (snakeBodyY[0] == appleY)){
            effect = false;
            rabbit = false;
            turtle = false;
            checkEffect();
            randomEffect();
            LocateApple();
            snakeLength++;
            myScore.addPoints();
        }
    }

    public void checkRabbitEaten(){
        if ((snakeBodyX[0] == rabbitX) && (snakeBodyY[0] == rabbitY)){
            rabbit = false;
            effect = false;
            timer.stop();
            speed = speed *100/110;
            if (speed < 10){
                speed = 10;
            }
            timer = new Timer(speed, this);
            timer.start();
        }
    }

    public void checkTurtleEaten(){
        if ((snakeBodyX[0] == turtleX) && (snakeBodyY[0] == turtleY)){
            turtle = false;
            effect = false;
            timer.stop();
            speed = speed * 110/100;
            timer = new Timer(speed, this);
            timer.start();
        }
    }

    public void checkEffect(){ //10% random a effect
        Random rnd = new Random();
        int rnd_effect = rnd.nextInt(99)+1;
        if(rnd_effect >= 30){
            LocateRabbit();
            LocateTurtle();
            effect = true;
        }
        else{
            effect = false;
        }
    }

    public void randomEffect(){
        Random rnd = new Random();
        int rnd_effect = rnd.nextInt(effectNum);
        if (rnd_effect == 0){
            //turtle effect
            turtle = true;
            rabbit = false;
        }
        else if (rnd_effect == 1){
            //rabbit effect
            turtle = false;
            rabbit = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g){
        a.paintIcon(this, g, appleX, appleY);
        for(int i = 0;i<width;i++){
            w.paintIcon(this, g, i, 0);
            w.paintIcon(this, g, i, height);
        }
        for(int i = 0;i<height;i++){
            w.paintIcon(this, g, 0, i);
            w.paintIcon(this, g, width, i);
        }
        if (effect){         
            if(turtle){                 //turtle effect
                t.paintIcon(this, g, turtleX, turtleY);
            }    
            else if(rabbit){            //rabbit effect
                r.paintIcon(this, g, rabbitX, rabbitY);
            }  
        }
        int length = snakeLength;
        for(int i = 0; i< length;i++){
            int x = snakeBodyX[i];
            int y = snakeBodyY[i];
            if(i == 0){
                h.paintIcon(this, g, x, y);
            }
            else{
                b.paintIcon(this, g, x, y);
            }
        }
        gameOver();
    }

    
    
    public void movement(){
        for(int i = snakeLength; i > 0 ; i--){
            snakeBodyX[i] = snakeBodyX[i-1];
            snakeBodyY[i] = snakeBodyY[i-1];
        }
        if (left){
            snakeBodyX[0] -= 10;
        }
        else if (right){
            snakeBodyX[0] += 10;
        }
        else if (up){
            snakeBodyY[0] -= 10;
        }
        else if (down){
            snakeBodyY[0] += 10;
        }
        
    }

    public void checkWall(){
        if (snakeBodyY[0] >= height){
            inGame = false;
        }
        else if (snakeBodyY[0] < 10){
            inGame = false;
        }
        else if (snakeBodyX[0] >= width){
            inGame = false;
        }
        else if (snakeBodyX[0] < 20){
            inGame = false;
        }
        for(int i = 1;i<=snakeLength;i++){
            if(snakeBodyX[0] == snakeBodyX[i] && snakeBodyY[0] == snakeBodyY[i]){
                inGame = false;
            }
        }
    }

    private void gameOver() {
        if(inGame == false){
            JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.WARNING_MESSAGE);
            JComponent comp = this;
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
            MainMenu m2 = new MainMenu();
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            movement();
            checkWall();
            repaint(); 
            checkAppleEaten();
            scoreLabel.setText("SCORE: "+myScore.getScore());
            if(effect){                      //if true then must draw a effect
                if(turtle){                     //turtle effect
                    checkTurtleEaten();
                }                    
                else if (rabbit){                //rabbit effect
                    checkRabbitEaten();
                }         
            }
        }
        if(!inGame){
            timer.stop();
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
     
}