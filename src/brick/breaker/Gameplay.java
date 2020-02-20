package brick.breaker;


import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Gameplay extends JLabel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int highScore = 0;


    private Timer time;
    private int delay = 4;
    private MyTimer myTimer;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private MapGenerator map;

    private int randomRow = 7 + (int) (Math.random() * ((20 - 7) + 1));
    private int randomCol = 3 + (int) (Math.random() * ((20 - 3) + 1));
    private int totalBricks;

    public Gameplay() {
        myTimer = new MyTimer();
        myTimer.start();
        map = new MapGenerator(randomRow,randomCol);
        totalBricks = randomRow * randomCol;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize(new Dimension(700, 600));
        time = new Timer(delay, this);
        time.start();

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint paint = new GradientPaint(playerX, 550, Color.GREEN, playerX + 101, 550 + 9, Color.YELLOW );
        GradientPaint paintBall = new GradientPaint(ballposX, ballposY, Color.ORANGE, ballposX + 21, ballposY + 21, Color.YELLOW);


        //background
//        g.setColor(Color.black);
//        g.fillRect(1,1,695,595);

        //draw map
        map.draw((Graphics2D)g);


        //borders
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,697,3);
        g.fillRect(697,0,3,592);

        //the paddle
        g.setColor(Color.BLUE);
        g.fillRect(playerX,550,100,8);
        g2.setPaint(paint);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(playerX,550,101,9);

        //draw score board
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 580, 30);

        //draw highScore board
        g.setColor(Color.GREEN);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Highscore: " + highScore, 20,30);

        //draw the timer
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString(myTimer.minutes + ":" + myTimer.secondsLeft + myTimer.secondsRight, 320, 30);
        //ball
        g.setColor(Color.RED);
        g.fillOval(ballposX,ballposY,20,20);
        g2.setPaint(paintBall);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(ballposX,ballposY,21,21);

        //if you win
        if (totalBricks <= 0) {
            play = false;
            ballYDir = 0;
            ballXDir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Win! Score: " + score, 190, 250);
            if (score > highScore) {
                highScore = score;
            }
            g.drawString("Highscore: " + highScore, 240, 300);
            g.drawString("Your Time: " + myTimer.minutes + ":" + myTimer.secondsLeft + myTimer.secondsRight, 220, 350);
            myTimer.stop();
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Enter", 180, 400);
            g.drawString("Shift", 347,400);
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("for New OR           to Restart", 237, 400);
        }

        //if game ends
        if (ballposY > 580) {
            play = false;
            ballYDir = 0;
            ballXDir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 250);
            if (score > highScore) {
                highScore = score;
            }
            g.drawString("Highscore: " + highScore, 240, 300);
            g.drawString("Your Time: " + myTimer.minutes + ":" + myTimer.secondsLeft + myTimer.secondsRight, 220, 350);
            myTimer.stop();
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Enter", 180, 400);
            g.drawString("Shift", 347,400);
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("for New OR           to Restart", 237, 400);

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
        //new game
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score = 0;
                randomRow = 7 + (int) (Math.random() * ((20 - 7) + 1));
                randomCol = 3 + (int) (Math.random() * ((20 - 3) + 1));
                totalBricks = randomRow * randomCol;
                map = new MapGenerator(randomRow,randomCol);

                repaint();
                myTimer.restart();
            }
        }
        //restart game
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            if (!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score = 0;
                totalBricks = randomRow * randomCol;
                map = new MapGenerator(randomRow,randomCol);

                repaint();
                myTimer.restart();
            }
        }

    }

    private void moveLeft() {
        play = true;
        playerX -= 30;
    }

    private void moveRight() {
        play = true;
        playerX += 30;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550,101,9))) {
                ballYDir = -ballYDir;
            }

            // generate map functions
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            //need to fix left and right intersection of brick
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXDir = - ballXDir;
                            }
                            else {
                                ballYDir = -ballYDir;
                            }

                            break A;
                        }
                    }
                }
            }


            ballposX += ballXDir;
            ballposY += ballYDir;
            if (ballposX < 0) {
                ballXDir = -ballXDir;
            }
            else if (ballposY < 0) {
                ballYDir = -ballYDir;
            }
            else if (ballposX > 670) {
                ballXDir = -ballXDir;
            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}

}
