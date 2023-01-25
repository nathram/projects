import javax.swing.JFrame;
import java.awt.*;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 */
public class CityscapeViewer
{
    // the cityscape will be animated for 180 seconds
    static final int ANIMATION_TIME_IN_SECONDS = 180;
   
    /**
     * main method for the program which creates and configures the frame for the program
     * 
     * @param args  not used
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create and configure the frame (window) for the program
        JFrame frame = new JFrame();
        
        frame.setSize(810 /* x */, 437 /* y */); //size of the window
        frame.setTitle("Cityscape");
        frame.setBackground(Color.red);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the Cityscape component and add it to the frame
        CityscapeComponent component = new CityscapeComponent();
        
        UpLine row = new UpLine();
        frame.add(row);
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // animate the cityscape
        for( int seconds = 0; seconds < ANIMATION_TIME_IN_SECONDS; seconds++ )
        {
            row.nextFrame();
            Thread.sleep( 100 );
        }
        
    }

}
