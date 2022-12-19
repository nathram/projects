import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ApollonianViewer
{
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private ApollonianGasket drawing;
    
    //-----------------------------------------------------------------
    //  Sets up the components for the applet.
    //-----------------------------------------------------------------
    public ApollonianViewer(int d1, int d2)
    {
        drawing = new ApollonianGasket(d1, d2);

        JFrame frame = new JFrame();
        frame.setTitle("Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(drawing);
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        
    }
}