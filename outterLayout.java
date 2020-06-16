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

import ntou.cs.java2020.project.Game2;

import java.util.Random;

public class outterLayout extends JFrame implements ActionListener{

    private JPanel blank = new JPanel();
    public JPanel scorePanel = new JPanel();
    private Game2 game = new Game2();
    private JButton quit = new JButton();
    

    public outterLayout(){
        quit.setSize(300, 300);
        quit.setText("Quit");
        setTitle("final");
        setVisible(true);
        setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        quit.addActionListener(this);
        blank.setBackground(Color.GRAY);
        setLayout(new GridLayout(1,1));
        game.setSize(200, 200);
        
        scorePanel.setLayout(new GridLayout(3,1,10,10));
        scorePanel.add(game.scoreLabel);
        scorePanel.add(quit);
        scorePanel.setSize(400,800);
        add(game);
        add(scorePanel);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
}