import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates the ground
 *
 * @author
 * @version
 */
public class Ground extends JComponent
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,base1,base2,length1;
    private boolean jumping;
    public static ArrayList<int[]> grounds = new ArrayList<int[]>();
    

    /**
     * Constructor for objects of class Ground
     */
    public Ground()
    {
        grounds.add(new int[]{-1, 0, 1600, 700});
        grounds.add(new int[]{0, 0, 600, 700});
        grounds.add(new int[]{0, 1200, 400, 700});
        grounds.add(new int[]{1, 0, 1600, 700});
        grounds.add(new int[]{2, 0, 1600, 700});
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
        if (Player.getPanel() == -1)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 1600, 500);
            //panel, bound1, distance, groundheight
        }
        else if (Player.getPanel() == 0)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 600, 500);
            page.fillRect(1200, 700, 400, 500);
        }
        else if (Player.getPanel() == 1)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 1600, 500);
        }
        else if (Player.getPanel() == 2)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 1600, 500);
        }
    }
    
    public static int groundHeight()
    {
        int groundHeight = -100;
        
        if (grounds.isEmpty())
            groundHeight = 500;
        else
            for (int[] arr: grounds)
            {
                for (int i = 0; i < 50; i++)
                    if (arr[0] == Player.getPanel() && Player.getSideDistance()+i >= arr[1] && Player.getSideDistance()+i < arr[1]+arr[2] && 1200 - arr[3] > groundHeight)
                        groundHeight = 1200 - arr[3];
            }
        return groundHeight;
    }
    
    /*public static int groundHeight2()
    {
        int groundHeight = -100;
        
        if (grounds.isEmpty())
            groundHeight = 500;
        else
            for (int[] arr: grounds)
            {
                for (int i = 0; i < 50; i++)
                    if (arr[0] == Player.getPanel() && Enemy.getSideDistance()+i >= arr[1] && Enemy.getSideDistance()+i < arr[1]+arr[2] && 1200 - arr[3] > groundHeight)
                        groundHeight = 1200 - arr[3];
            }
        return groundHeight;
    }
    */
} 
