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

public class outterLayout extends JPanel{

    private JPanel blank = new JPanel();
    public JPanel scorePanel = new JPanel();
    private Game2 game = new Game2();

    public outterLayout(){
        blank.setBackground(Color.GRAY);
        setLayout(new BorderLayout());
        game.setSize(200, 200);
        
        scorePanel.add(game.scoreLabel);
        scorePanel.setSize(400,800);
        add(scorePanel,BorderLayout.EAST);
        add(game,BorderLayout.CENTER);
        add(blank,BorderLayout.WEST);
    }
}