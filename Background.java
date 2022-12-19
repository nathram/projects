import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Write a description of class Background here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Background extends JComponent
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,base1,base2,length1;
    
    Color stripecolor;

    /**
     * Constructor for objects of class Background
     */
    public Background()
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
        page.setColor(new Color(0,200, 200));
        page.fillRect(0, 0, 800, 400);
    } 
}
