package ntou.cs.java2020.project;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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


public class GameTest extends JFrame{
    public GameTest(){
        add(new Game2());
        setTitle("this is the new new one");
        setVisible(true);
        setSize(800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        JFrame frame = new GameTest();
        
        /*
        game.jf.setTitle("JF");
        game.jf.setSize(800,800);
        game.jf.setVisible(true);
        game.jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.jf.add(game.snake);
        */
    }
}