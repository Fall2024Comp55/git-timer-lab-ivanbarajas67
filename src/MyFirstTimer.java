import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFirstTimer extends GraphicsProgram implements ActionListener {
    public static final int PROGRAM_HEIGHT = 600;
    public static final int PROGRAM_WIDTH = 800;
    public static final int MAX_STEPS = 20;

    private GLabel myLabel;
    private Timer timer;
    private int count = 0;

    public void init() {
        setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        requestFocus();
    }

    public void run() {
        myLabel = new GLabel("# of times called? 0", 100, 100);
        add(myLabel);

        timer = new Timer(1000, this);  
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        count++;
        myLabel.setLabel("# of times called? " + count);

        if (count == MAX_STEPS) {
            timer.stop();
            myLabel.setLabel("Timer stopped");
        }
    }

    public static void main(String[] args) {
        new MyFirstTimer().start();
    }
}
