import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class BallLauncher extends GraphicsProgram implements ActionListener {
    public static final int PROGRAM_HEIGHT = 600;
    public static final int PROGRAM_WIDTH = 800;
    public static final int SIZE = 25;
    public static final int DELAY = 30;
    public static final int SPEED = 5;

    private ArrayList<GOval> balls;
    private Timer timer;

    public void init() {
        setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        requestFocus();
    }

    public void run() {
        balls = new ArrayList<>();
        addMouseListeners();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void mousePressed(MouseEvent e) {
        GOval ball = makeBall(SIZE / 2, e.getY());
        add(ball);
        balls.add(ball);
    }

    public GOval makeBall(double x, double y) {
        GOval temp = new GOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        temp.setColor(Color.RED);
        temp.setFilled(true);
        return temp;
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < balls.size(); i++) {
            GOval b = balls.get(i);
            b.move(0, -SPEED);
            if (b.getY() + SIZE < 0) {
                remove(b);
                balls.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        new BallLauncher().start();
    }
}
