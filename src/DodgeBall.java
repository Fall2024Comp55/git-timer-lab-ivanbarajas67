import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class DodgeBall extends GraphicsProgram implements ActionListener {
    private ArrayList<GOval> balls;
    private ArrayList<GRect> enemies;
    private GLabel text;
    private Timer movement;
    private RandomGenerator rgen;
    private int numTimes = 0;

    public static final int SIZE = 25;
    public static final int SPEED = 2;
    public static final int MS = 50;
    public static final int MAX_ENEMIES = 10;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 300;

    public void run() {
        rgen = RandomGenerator.getInstance();
        balls = new ArrayList<>();
        enemies = new ArrayList<>();

        text = new GLabel("" + enemies.size(), 0, WINDOW_HEIGHT);
        add(text);

        movement = new Timer(MS, this);
        movement.start();
        addMouseListeners();
    }

    public void actionPerformed(ActionEvent e) {
        moveAllBallsOnce();

        
        numTimes++;
        if (numTimes % 40 == 0) {
            addAnEnemy();
        }

        moveAllEnemiesOnce();
    }

    public void mousePressed(MouseEvent e) {
        for (GOval b : balls) {
            if (b.getX() < SIZE * 2.5) {
                return;
            }
        }
        addABall(e.getY());
    }

    private void addABall(double y) {
        GOval ball = makeBall(SIZE / 2, y);
        add(ball);
        balls.add(ball);
    }

    private GOval makeBall(double x, double y) {
        GOval temp = new GOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        temp.setColor(Color.RED);
        temp.setFilled(true);
        return temp;
    }

    private void addAnEnemy() {
        GRect e = makeEnemy(rgen.nextInt(0, WINDOW_HEIGHT - SIZE));
        enemies.add(e);
        text.setLabel("" + enemies.size());
        add(e);
    }

    private GRect makeEnemy(double y) {
        GRect temp = new GRect(WINDOW_WIDTH - SIZE, y - SIZE / 2, SIZE, SIZE);
        temp.setColor(Color.GREEN);
        temp.setFilled(true);
        return temp;
    }

    private void moveAllBallsOnce() {
        ArrayList<GOval> ballsToRemove = new ArrayList<>();
        ArrayList<GRect> enemiesToRemove = new ArrayList<>();
        for (GOval ball : balls) {
            ball.move(SPEED, 0);
            double checkX = ball.getX() + SIZE;
            double checkY = ball.getY() + SIZE / 2;
            GObject obj = getElementAt(checkX, checkY);
            if (obj instanceof GRect) {
                enemiesToRemove.add((GRect) obj);
                ballsToRemove.add(ball);
            }
        }
        for (GRect e : enemiesToRemove) {
            remove(e);
            enemies.remove(e);
        }
        for (GOval b : ballsToRemove) {
            remove(b);
            balls.remove(b);
        }
    }

    private void moveAllEnemiesOnce() {
        for (GRect enemy : enemies) {
            enemy.move(0, rgen.nextInt(-2, 2)); 
        }
    }

    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public static void main(String[] args) {
        new DodgeBall().start();
    }
}
