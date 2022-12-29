import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    ImageIcon gameBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/background.jpg")));
    ImageIcon gameTitle = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/SnakeTitleT.png")));
    ImageIcon snakeBody = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/snakebody.png")));
    ImageIcon right = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/rightmouth.png")));
    ImageIcon left = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/leftmouth.png")));
    ImageIcon up = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/upmouth.png")));
    ImageIcon down = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/downmouth.png")));
    ImageIcon food = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/enemy.png")));
    ImageIcon t1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t1.png")));
    ImageIcon t2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t3.png")));
    ImageIcon t3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t4.png")));
    ImageIcon t4 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t5.png")));
    ImageIcon t5 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t6.png")));
    ImageIcon t7 = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/t7.png")));

    int move = 0;
    int lengthOfSnake = 3;

    int[] snakeX = new int[750];
    int[] snakeY = new int[750];

    boolean isRight = true;
    boolean isLeft = false;
    boolean isUp = false;
    boolean isDown = false;
    Timer time;
    Random random = new Random();
    int[] posX = {100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    int[] posY = {100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    int randomFrute;
    int[] frutes = {0,1,2,3,4,5,6};
    int foodX = 150;
    int foodY = 150;
    int score = 0;

    boolean isGameOver = false;
    GamePanel(){
        addKeyListener(this);
        setFocusable(true);
        time = new Timer(100,this);
        time.start();
    }

//  Create Our Game Panel, design for game panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.yellow);
//      make rectangle for Title
        g.drawRect(24,10,851,55);
//      make rectangle for game panel
        g.drawRect(24,80,851,575);
        gameTitle.paintIcon(this,g,24,11);
        g.setColor(Color.black);
        gameBackground.paintIcon(this,g,24,80);
//        g.fillRect(24,80,851,575);

//      Create Start body Snake
        if(move == 0){
//          body size of the snake 25px, it is also the starting position of the snake
//          x co-ordinate
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;
//          Y co-ordinate
            snakeY[0] = 100;
            snakeY[1] = 100;
            snakeY[2] = 100;
        }
        if(isRight)
            right.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isLeft)
            left.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isUp)
            up.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isDown)
            down.paintIcon(this,g,snakeX[0],snakeY[0]);
//      Snake body Started
        for (int i = 1; i < lengthOfSnake; i++){
            snakeBody.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        if(frutes[randomFrute] == 0){
            food.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 1){
            t1.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 2){
            t2.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 3){
            t3.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 4){
            t4.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 5){
            t5.paintIcon(this,g,foodX,foodY);
        }
        else if(frutes[randomFrute] == 6){
            t7.paintIcon(this,g,foodX,foodY);
        }
        if(isGameOver){
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("Game Over",380,300);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
            g.drawString("Press Space To Restart The Game",310,360);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("ITALIC",Font.PLAIN, 15));
        g.drawString("Score "+score,750,35);
        g.drawString("Length "+lengthOfSnake,750,55);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = lengthOfSnake-1; i > 0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        if(isRight){
            snakeX[0] = snakeX[0] + 25;
        }
        if(isLeft){
            snakeX[0] = snakeX[0] - 25;
        }
        if(isUp){
            snakeY[0] = snakeY[0] - 25;
        }
        if(isDown){
            snakeY[0] = snakeY[0] + 25;
        }
//      Boundary Conditions
        if(snakeX[0] > 850) snakeX[0] = 25;
        if(snakeX[0] < 25) snakeX[0] = 850;
        if(snakeY[0] > 625) snakeY[0] = 75;
        if(snakeY[0] < 75) snakeY[0] = 625;
        collisionWithFood();
        collisionWithBody();
        repaint();
    }
    private void playMusic(String path){
        try {
            AudioInputStream inputStrem = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStrem);
            clip.loop(0);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

    }
    private void collisionWithBody() {
        for(int i = lengthOfSnake - 1; i > 0; i--){
            if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]){
                playMusic("Musics/game-over.wav");
                isGameOver = true;
                time.stop();
            }
        }
    }

    private void collisionWithFood() {

        if(snakeX[0] == foodX && snakeY[0] == foodY){
            playMusic("Musics/frute-eat.wav");
            newFood();
            score++;
            lengthOfSnake++;
//          Copy the snake body for next body
            snakeX[lengthOfSnake-1] = snakeX[lengthOfSnake-2];
            snakeY[lengthOfSnake-1] = snakeY[lengthOfSnake-2];
        }
    }

    private void newFood() {
        randomFrute = random.nextInt(6);
        foodX = posX[random.nextInt(posX.length-1)];
        foodY = posY[random.nextInt(posY.length-1)];
        for(int i = lengthOfSnake - 1; i >= 0; i--){
            if(snakeX[i] == foodX && snakeY[i] == foodY){
                newFood();
            }
        }
    }
    private void restart() {
        isGameOver = false;
        score = 0;
        lengthOfSnake = 3;
        move = 0;
        isLeft = false;
        isRight = true;
        isUp = false;
        isDown = false;
        time.start();
        newFood();
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            restart();
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            time.stop();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            time.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && !isLeft){
            isRight = true;
            isLeft = false;
            isUp = false;
            isDown = false;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT && !isRight){
            isRight = false;
            isLeft = true;
            isUp = false;
            isDown = false;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && !isDown){
            isRight = false;
            isLeft = false;
            isUp = true;
            isDown = false;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && !isUp){
            isRight = false;
            isLeft = false;
            isUp = false;
            isDown = true;
            move++;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
