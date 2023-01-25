import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates the ground
 *
 * @author Nathra Ramrajvel
 * @version 10/11
 */
public class Ground extends JComponent
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,base1,base2,length1;
    

    /**
     * Constructor for objects of class Ground
     */
    public Ground()
    {
        
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        // invoke the draw method 
        draw(g2);
    }
    
    //-----------------------------------------------------------------
    //  Draws the background
    //-----------------------------------------------------------------
    public void draw (Graphics2D page) //page is virtual palette
    {
        //Background
        page.setColor(new Color(173, 216, 230));
        page.fillRect(0, 0, 1600, 1200);
        page.setColor(new Color(90, 191, 50));
        page.fillRect(0, 700, 1600, 500);
        page.setColor(new Color(159, 111, 41));
        page.fillRect(0, 710, 1600, 490);
    } 
}
