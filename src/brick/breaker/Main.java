package brick.breaker;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class Main {

    public static void main(String[] args)  {
        MainPanel mainPanel = new MainPanel();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setBounds(500,250,700,600);
        frame.setContentPane(mainPanel);
        frame.setTitle("Brick Breaker Game");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        MusicPlayer musicPlayer = new MusicPlayer("/Users/denniszhang/IdeaProjects/Brick Breaker/src/brick/breaker/myClip.mp3");
    }
}
