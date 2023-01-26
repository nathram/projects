import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 */
public class WorldViewer
{
    // it will be animated forever
    public static boolean up = false, down = false, right = false, left = false, space = false;
    public static JLabel timer;
    /**
     * main method for the program which creates and configures the frame for the program
     * 
     * @param args  not used
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
            
        JFrame frame = new JFrame();
        
        
        frame.setSize(1600 /* x */, 1200 /* y */); //size of the window
        frame.setTitle("World");
        frame.setBackground(Color.red);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        
        
        // a frame contains a single component; create the Cityscape component and add it to the frame
        WorldComponent component = new WorldComponent();
        
        LineUp row = new LineUp();
        
        frame.add(row);
        
        
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        
        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_UP:
                        up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        space = true;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        space = false;
                        break;
                    }
            }
        });
        
        
        // animate
        while(true)
        {
            row.nextFrame();
            Thread.sleep( 100 );
        }
        
    }
}
