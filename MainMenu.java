package ntou.cs.java2020.project;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu implements ActionListener{

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JButton start = new JButton();
    private JButton setting = new JButton();
    private JButton quit = new JButton();

    private JFrame game = new JFrame();

    public MainMenu(){
        
        start.setText("PLAY");
        setting.setText("SETTING");
        quit.setText("QUIT");
        start.addActionListener(this);
        setting.addActionListener(this);
        quit.addActionListener(this);
        panel.add(start);
        panel.add(setting);
        panel.add(quit);
        frame.add(panel);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MainMenu");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton actionSource = (JButton) e.getSource();
        if(actionSource.equals(start)){
            game.add(new outterLayout());
            game.setTitle("this is one");
            game.setVisible(true);
            game.setSize(900, 900);
            game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(false);
            //do start things
        }
        else if (actionSource.equals(setting)){
            //do setting thing
        }
        else if (actionSource.equals(quit)){
            frame.dispose();
        }
    }
}