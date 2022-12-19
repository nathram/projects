import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates and animates the stripes on the road
 *
 * @author Nathra Ramrajvel
 * @version 1/31
 */
public class Stripes extends JComponent implements Runnable
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,base1,base2,length1;
    Color stripecolor;

    /**
     * Default constructor
     */
    public Stripes()
    {
        base1 = 0;
        base2 = 0;
    }
    
    /**
     * Copy constructor
     * @param int base of stripes (in the trapezoid)
     */
    public Stripes(int baseone, int basetwo)
    {
        base1 = baseone;
        base2 = basetwo;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        // invoke the draw method 
        draw(g2);
    }
    
    //-----------------------------------------------------------------
    //  Draws this figure relative to base1 and base2
    //-----------------------------------------------------------------
    public void draw (Graphics2D page) //page is virtual palette
    {
        //stripes
        page.setColor(Color.yellow);
        
        //sets length relative to the base
        length1 = 7*base1-7;
        //draws the stripe
        int[] xstripe = {400-base1, 400+base1, 400+base2, 400-base2};
        int[] ystripe = {225+length1, 225+length1, 225+length1+7*(base2-base1), 225+length1+7*(base2-base1)};
        page.fillPolygon(xstripe, ystripe, 4);
    }
       
    public void run()
    {
        int running  = 0;
        while(true)
        {
            if(running % 40 == 0)
            {
                //moves position of stripe forward based on the current base length
                if (base1 == 22)
                {
                    base1 = 26;
                }
                else if (base2 == 22)
                {
                    base1 = 22;
                    base2 = 26;
                }
                else if (base1 == 26)
                {
                    base1 = 1;
                    base2 = 2;
                }
                else
                {
                    int olddifference = base2-base1;
                    base1 = base2;
                    base2 += olddifference+1;
                }
            }
            else if(running % 40 == 20)
            {
                //moves position of stripe forward based on the current base length
                if (base1 == 22)
                {
                    base1 = 26;
                }
                else if (base2 == 22)
                {
                    base1 = 22;
                    base2 = 26;
                }
                else if (base1 == 26)
                {
                    base1 = 1;
                    base2 = 2;
                }
                else
                {
                    int olddifference = base2-base1;
                    base1 = base2;
                    base2 += olddifference+1;
                }
            }
                running ++;
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }   
}
