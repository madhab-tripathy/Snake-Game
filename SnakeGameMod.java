import javax.swing.*;
import java.awt.*;

public class SnakeGameMod {

    JFrame frame;

    SnakeGameMod(){
        frame = new JFrame("My Snake Game");
        frame.setBounds(10,10,905,700);
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.GRAY);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGameMod startGame = new SnakeGameMod();
        System.out.println("Changes");
    }
}
