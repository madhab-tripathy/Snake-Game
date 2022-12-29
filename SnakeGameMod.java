import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SnakeGameMod {

    JFrame frame;
    SnakeGameMod(){
        frame = new JFrame("My Snake Game");
        frame.setBounds(10,10,905,700);
//        Color color=new Color(118,191,68);
//        frame.getContentPane().setBackground(color);
//        JLabel background=new JLabel(new ImageIcon("Icons/startbg.jpg"));
//        if(true){
            GamePanel panel = new GamePanel();
            panel.setBackground(Color.orange);
            frame.add(panel);
//        }
//        background.setLayout(new FlowLayout());
//        frame.add(background);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGameMod startGame = new SnakeGameMod();
    }
}
