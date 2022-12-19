import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates pits
 *
 * @author Nathra Ramrajvel
 * @version 10/11
 */
public class Pit extends JComponent implements Runnable
{
    // instance variables
    
    public static ArrayList<int[]> pit = new ArrayList<int[]>();
    private int[] arr1;
    public int panelNum, position1, position2;
    /**
     * Constructor for objects of class Pit
     */
    public Pit()
    {
        arr1 = new int[] {300, 700, 100, 500};
        position1 = 300;
        position2 = 100;
        panelNum = 0;
        pit.add(arr1);
    }
    
    public Pit(int panel, int pos1, int pos2)
    {
        arr1 = new int[] {pos1, 700, pos2, 500};
        panelNum = panel;
        position1 = pos1;
        position2 = pos2;
        pit.add(arr1);
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
        //Obstacle
        page.setColor(new Color(173, 216, 230));
        page.fillRect(arr1[0], arr1[1], arr1[2], arr1[3]);
        
    }
    
    public static ArrayList<int[]> getPits()
    {
        return pit;
    }
    
    public void run()
    {
        int running  = 0;
        while (true)
        {
            running++;
            if (Player.getPanel() == panelNum)
            {
                arr1[0] = position1;
                arr1[2] = position2;
            }
            else if (Player.getPanel() != panelNum)
            {
                arr1[0] = 0;
                arr1[2] = 0;
            }
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }
}
