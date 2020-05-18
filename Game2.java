package ntou.cs.java2020.project;
import javax.imageio.ImageIO;
import ntou.cs.java2020.project.Board;
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



public class Game2 extends JPanel implements ActionListener{

    private int height;
    private int width;
    private int snakeBodyX[] = new int[50];
    private int snakeBodyY[] = new int[50];
    private int snakeLength;
    public int snakeSize = 10;

    private int appleX;
    private int appleY;

    private ImageIcon b;
    private ImageIcon h;
    private ImageIcon a;

    private Timer timer;

    public boolean inGame;
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;

    public Game2(){
        addKeyListener(new TAdapter());
        
        
        setFocusable(true);
        setBackground(Color.black);
        height = 800;
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

        b = new ImageIcon("D:\\project\\body2.png");
        h = new ImageIcon("D:\\project\\head2.png");
        a = new ImageIcon("D:\\project\\apple.png");
        Image tempA = a.getImage();
        Image tempH = h.getImage();
        Image tempB = b.getImage();
        tempH = tempH.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempB = tempB.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        tempA = tempA.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        h = new ImageIcon(tempH);
        b = new ImageIcon(tempB);
        a = new ImageIcon(tempA);

        timer = new Timer(280,this);
        timer.start();
        timer.addActionListener(this);
        inGame = true;
        up = false;
        down = false;
        left = true;
        right = false;
    }   

    public void LocateApple(){
        System.out.println(appleX);
        System.out.println(appleY);
        Random rnd = new Random();
        appleX = rnd.nextInt(40);
        appleY = rnd.nextInt(80);
        System.out.println("Apple X = " + appleX);
        System.out.println("Apple Y = " + appleY);
        appleX *= snakeSize;
        appleY *= snakeSize;
    }
    
    public void checkAppleEaten(){
        System.out.println("Apple x = "+ appleX);
        if ((snakeBodyX[0] == appleX) && (snakeBodyY[0] == appleY)){
            LocateApple();
            snakeLength++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("I did painting\n\n");
        doDrawing(g);
    }

    public void doDrawing(Graphics g){
        
        a.paintIcon(this, g, appleX, appleY);
        int length = snakeLength;
        System.out.println("UP: "+up);
        System.out.println("DOWN: "+down);
        System.out.println("LEFT: "+left);
        System.out.println("RIGHT: "+right);
        for(int i = 0; i< length;i++){
            int x = snakeBodyX[i];
            int y = snakeBodyY[i];
            if(i == 0){
                System.out.println("i print head");
                h.paintIcon(this, g, x, y);
            }
            else{
                System.out.println("i print body");
                b.paintIcon(this, g, x, y);
            }
        }
    }

    
    
    public void movement(){

        for(int i = snakeLength-1; i > 0 ; i--){

        snakeBodyX[i] = snakeBodyX[i-1];
        snakeBodyY[i] = snakeBodyY[i-1];
        }
        if (left){
            snakeBodyX[0] -= snakeSize;
        }
        else if (right){
            snakeBodyX[0] += snakeSize;
        }
        else if (up){
            snakeBodyY[0] -= snakeSize;
        }
        else if (down){
            snakeBodyY[0] += snakeSize;
        }
        
    }

    public void checkWall(){
        if (snakeBodyY[0] >= height){
            inGame = false;
        }
        else if (snakeBodyY[0] <= 0){
            inGame = false;
        }
        else if (snakeBodyX[0] >= width){
            inGame = false;
        }
        else if (snakeBodyX[0] <= 0){
            inGame = false;
        }
        if (inGame == true){
            System.out.println("False");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            movement();
            checkWall();
            repaint(); 
            checkAppleEaten();
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