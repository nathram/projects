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
        grounds.add(new int[]{-1, 0, 200, 700});
        grounds.add(new int[]{-1, 400, 1200, 700});
        grounds.add(new int[]{0, 0, 800, 700});
        grounds.add(new int[]{0, 1000, 600, 500});
        grounds.add(new int[]{1, 0, 150, 700});
        grounds.add(new int[]{1, 1300, 300, 500});
        grounds.add(new int[]{2, 0, 150, 700});
        grounds.add(new int[]{2, 1300, 300, 700});
        grounds.add(new int[]{3, 0, 1600, 700});
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
            page.fillRect(0, 700, 200, 500);
            page.fillRect(400, 700, 1200, 500);
            page.setColor(Color.black);
            Font stringFont = new Font("SansSerif", Font.PLAIN, 30);
            page.setFont(stringFont);
            page.drawString("WELCOME TO ", 400, 210);
            page.drawString("currently unnamed game", 450, 340);
            page.drawString("which is DEFINITELY NOT", 500, 470);
            Font stringFont2 = new Font("SansSerif", Font.PLAIN, 10);
            page.setFont(stringFont2);
            page.drawString("a mario rip-off...................", 550, 535);
            page.drawString("happy bday", 700, 630);
            Font stringFont3 = new Font("SansSerif", Font.PLAIN, 18);
            page.setFont(stringFont3);
            
            page.drawString("bubble has been kidnapped by an unnamed villain!!! go get her back", 600, 600);
            page.drawString("over here!!!! this is the correct path!!!!", 200, 650);
            page.drawString("certain doom this way ---->", 1250, 650);
            //panel, bound1, distance, groundheight
        }
        else if (Player.getPanel() == 0)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 800, 500);
            page.fillRect(1000, 500, 600, 700);
        }
        else if (Player.getPanel() == 1)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 150, 500);
            page.fillRect(1300, 500, 300, 700);
        }
        else if (Player.getPanel() == 2)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 150, 500);
            page.fillRect(1300, 700, 300, 500);
            page.setColor(new Color(169,169,169));
            page.fillOval(1350, 675, 50, 25);
            page.fillOval(1340, 665, 20, 20);
            Font stringFont3 = new Font("SansSerif", Font.PLAIN, 18);
            page.setFont(stringFont3);
            page.setColor(Color.black);
            page.drawString("pretend this is a bunny", 1250, 650);
        }
        else if (Player.getPanel() == 3)
        {
            page.setColor(new Color(90, 191, 50));
            page.fillRect(0, 700, 1600, 500);
            Font stringFont3 = new Font("SansSerif", Font.PLAIN, 30);
            page.setFont(stringFont3);
            page.setColor(Color.black);
            page.drawString("you won :D", 650, 500);
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
