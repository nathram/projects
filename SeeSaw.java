import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 *
 *
 * @author
 * @version
 */
public class SeeSaw extends JComponent implements Runnable
{
    // instance variables
    public int ropewidth, height1, height2, platform, x, y, panelNum, seesawNum, origh1, origh2;
    public static ArrayList<int[]> seesaws = new ArrayList<int[]>();
    public int[] arr1;

    /**
     * Default constructor
     */
    public SeeSaw()
    {
        x = 0;
        y = 0;
        panelNum = 0;
        seesawNum = 0;
        ropewidth = 300;
        height1 = 200;
        height2 = 200;
        origh1 = 200;
        origh2 = 200;
        platform = 150;
        arr1 = new int[] {x, y, height1, height2};
        seesaws.add(arr1);
    }
    
    /**
     * Copy constructor
     * @param int base of stripes (in the trapezoid)
     */
    public SeeSaw(int x1, int y1, int w, int h, int p, int panel, int snum)
    {
        x = x1;
        y = y1;
        panelNum = panel;
        ropewidth = w;
        height1 = h;
        height2 = h;
        origh1 = h;
        origh2 = h;
        platform = p;
        seesawNum = snum;
        arr1 = new int[] {x, y, height1, height2};
        seesaws.add(arr1);
    }
    
    public static ArrayList<int[]> getSeesaws()
    {
        return seesaws;
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
        if (height1 < 400 && height2 < 400 && Player.getPanel() == panelNum)
        {
            page.setColor(Color.white);
            page.setStroke(new BasicStroke(5));
            page.drawLine(x-ropewidth/2, y, x+ropewidth/2, y);
            page.drawLine(x-ropewidth/2, y, x-ropewidth/2, y+arr1[2]);
            page.drawLine(x+ropewidth/2, y, x+ropewidth/2, y+arr1[3]);
            page.setColor(Color.yellow);
            page.fillRect(x-ropewidth/2-platform/2, y+arr1[2], platform, 20);
            page.fillRect(x+ropewidth/2-platform/2, y+arr1[3], platform, 20);
            
            page.setStroke(new BasicStroke(1));
            page.setColor(Color.black);
            page.fillOval(x-ropewidth/2-2, y-2, 4, 4);
            page.fillOval(x+ropewidth/2-2, y-2, 4, 4);
        }
        }
    
       
    public void run()
    {
        int running  = 0;
        while(true)
        {
            if (Player.timer==500)
            {
                arr1[0] = x;
                arr1[1] = y;
                arr1[2] = origh1;
                arr1[3] = origh2;
                seesaws.set(seesawNum, arr1);
            }
            if (Player.getPanel() == panelNum && arr1[2] < 400 && arr1[3] < 400)
            {
                arr1 = getSeesaws().get(seesawNum);
                if (arr1[0] < 0)
                {
                    arr1[0] = x;
                    arr1[1] = y;
                    seesaws.set(seesawNum, arr1);
                }
                else
                {
                    height1 = arr1[2];
                    height2 = arr1[3];
                }
            }   
            else if (Player.getPanel() == panelNum && arr1[2] >= 400 || arr1[3] >= 400)
            {
                arr1 = new int[]{-1000, -1000, 400, 400};
                seesaws.set(seesawNum, arr1);
                height1 = 400;
                height2 = 400;
                Player.onseesaw = false;
            }
            else if (Player.getPanel() != panelNum)
            {
                arr1 = new int[]{-1000, -1000, height1, height2};
                seesaws.set(seesawNum, arr1);
            }
                running ++;
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }
}
