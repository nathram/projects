import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates the road
 *
 * @author Nathra Ramrajvel
 * @version 1/31
 */
public class Road extends JComponent
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,sidedistance,base1;
    Color stripecolor;

    /**
     * Default constructor
     */
    public Road()
    {
        sidedistance = 0;
        base1 = 0;
    }
    
    /**
     * Copy constructer
     * @param int distance from the edge of the buildings, top base length (how far the road goes)
     */
    public Road(int distance, int baseone)
    {
        sidedistance = distance;
        base1 = baseone;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        // invoke the draw method 
        draw(g2);
    }

    //-----------------------------------------------------------------
    //  Draws this figure relative to sidedistance
    //-----------------------------------------------------------------
    public void draw (Graphics2D page) //page is virtual palette
    {
        //draws road
        page.setColor(new Color(150, 150, 150));
        int[] xroad = {400-base1/2+sidedistance, 400+base1/2-sidedistance, 800-sidedistance, sidedistance};
        int[] yroad = {200+base1/2, 200+base1/2, 400, 400};
        page.fillPolygon(xroad, yroad, 4);
    }
}
