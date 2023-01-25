import java.awt.*;
import java.util.*;
    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
/**
 * Creates the buildings and windows, and animates them
 * 
 * @author Nathra Ramrajvel 
 * @version 1-31
 */
public class Buildings extends JComponent implements Runnable
{
    // instance variables
    private int x1,x2,x3,x4,y1,y2,y3,y4,distancefactor1,distancefactor2,extension,heightchange;
    private String side;
    private Color colour, windowcolor;
    Random generator = new Random();
    private int rcolor = generator.nextInt(200);
    private int gcolor = generator.nextInt(200);
    private int bcolor = generator.nextInt(200);
    
    /**
     * Default constructor
     */
    public Buildings()
    {
        distancefactor1 = 0;
        distancefactor2 = 25;
        extension = 0;
        heightchange = 0;
    }
    
    /**
     * Copy constructor, sets the building
     * @param int distance of sides of building from left, width of building, height, and position
     */
    public Buildings(int leftside, int rightside, int width, int height, String roadside)
    {
        distancefactor1 = leftside;
        distancefactor2 = rightside;
        extension = width;
        heightchange = height;
        side = roadside;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        // invoke the draw method 
        draw(g2);
    }
    
    //-----------------------------------------------------------------
    //  Draws this figure relative to distancefactor1, distancefactor2, heightchange, and extension
    //-----------------------------------------------------------------
    public void draw (Graphics2D page) //page is virtual palette
    {
        //perspective lines (for reference)
        page.setColor(Color.black);
        //page.drawRect(375, 175, 50, 50);
        //page.drawLine(375, 175, 0, 0);
        //page.drawLine(425, 175, 800, 0);
        //page.drawLine(375, 225, 0, 400);
        //page.drawLine(425, 225, 800, 400);
        
        //buildings
        colour = new Color(rcolor, gcolor, bcolor);
        page.setColor(colour);
        if (side.equals("left"))
        {
            //draws building based on previous inputs
            int[] xbuilding = {15*distancefactor1, 15*distancefactor1, 15*distancefactor2, 15*distancefactor2};
            int[] ybuilding = {7*distancefactor1-heightchange, 400-7*distancefactor1, 400-7*distancefactor2, 7*distancefactor2-heightchange};
            page.fillPolygon(xbuilding, ybuilding, 4);
            page.setColor(new Color(rcolor/2, gcolor/2, bcolor/2));
            page.fillRect(15*distancefactor1-extension, 7*distancefactor1-heightchange, extension, 400-14*distancefactor1+heightchange);
            //two for loops to create rows and columns of windows
            for (int j = 1; j <= 13; j += 3)
            {
                for (int i = 2; i <= 6; i += 4)
                {
                    //adjusts for error caused by the coordinates needing to be integers
                    int errorfactor1 = 0;
                    int errorfactor2 = 0;
                    int errorfactor3 = 0;
                    if (distancefactor2 == 24 && !(j == 1 || j == 13))
                        errorfactor1 = 1;
                    else if (distancefactor2 == 24 && j == 1)
                        errorfactor2 = 1;
                    else if (distancefactor2 == 24)
                        errorfactor3 = 2;
                    //creates individual window based on the building dimensions and positioning
                    int[] xwindow = {15*distancefactor1+3*i*(distancefactor2-distancefactor1)/2, 15*distancefactor1+3*i*(distancefactor2-distancefactor1)/2,
                    15*distancefactor1+3*(i+2)*(distancefactor2-distancefactor1)/2, 15*distancefactor1+3*(i+2)*(distancefactor2-distancefactor1)/2,};
                    int[] ywindow = {j*(400-14*(i*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*(i*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange,
                    (j+2)*(400-14*(i*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*(i*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange,
                    (j+2)*(400-14*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange-errorfactor1-errorfactor3,
                    j*(400-14*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange+errorfactor1+errorfactor2};
                    //randomly sets window color to yellow or white
                    if (generator.nextInt(2) == 1)
                        windowcolor = new Color(240, 240, 240);
                    else
                        windowcolor = Color.yellow;
                    page.setColor(windowcolor);
                    page.fillPolygon(xwindow, ywindow, 4);
                }
            }
        }
        else if (side.equals("right"))
        {
            //draws building based on previous inputs
            int[] xbuilding = {800-(15*distancefactor1), 800-(15*distancefactor1), 800-(15*distancefactor2), 800-(15*distancefactor2)};
            int[] ybuilding = {7*distancefactor1-heightchange, 400-7*distancefactor1, 400-7*distancefactor2, 7*distancefactor2-heightchange};
            page.fillPolygon(xbuilding, ybuilding, 4);
            page.setColor(new Color(rcolor/2, gcolor/2, bcolor/2));
            page.fillRect(800-(15*distancefactor1), 7*distancefactor1-heightchange, extension, 400-14*distancefactor1+heightchange);
            //two for loops to create rows and columns of windows
            for (int j = 1; j <= 13; j += 3)
            {
                for (int i = 2; i <= 6; i += 4)
                {
                    //adjusts for error caused by the coordinates needing to be integers
                    int errorfactor1 = 0;
                    int errorfactor2 = 0;
                    int errorfactor3 = 0;
                    if (distancefactor2 == 24 && !(j == 1 || j == 13))
                        errorfactor1 = 1;
                    else if (distancefactor2 == 24 && j == 1)
                        errorfactor2 = 1;
                    else if (distancefactor2 == 24)
                        errorfactor3 = 2;
                    //creates individual window based on the building dimensions and positioning
                    int[] xwindow = {800-(15*distancefactor1+3*i*(distancefactor2-distancefactor1)/2), 800-(15*distancefactor1+3*i*(distancefactor2-distancefactor1)/2),
                    800-(15*distancefactor1+3*(i+2)*(distancefactor2-distancefactor1)/2), 800-(15*distancefactor1+3*(i+2)*(distancefactor2-distancefactor1)/2)};
                    int[] ywindow = {j*(400-14*(i*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*(i*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange,
                    (j+2)*(400-14*(i*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*(i*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange,
                    (j+2)*(400-14*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange-errorfactor1-errorfactor3,
                    j*(400-14*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)+heightchange)/16+7*((i+2)*(distancefactor2-distancefactor1)/10+distancefactor1)-heightchange+errorfactor1+errorfactor2};
                    //randomly sets window color to yellow or white
                    if (generator.nextInt(2) == 1)
                        windowcolor = new Color(240, 240, 240);
                    else
                        windowcolor = Color.yellow;
                    page.setColor(windowcolor);
                    page.fillPolygon(xwindow, ywindow, 4);
                }
            }
        }
    }
       
    public void run()
    {
        int running  = 0;
        while(true)
        {
            //animates the buildings
            if(running % 40 == 0)
            {
                //moves the building by changing the variables used to set the building's dimensions and positioning
                if (distancefactor2 == 7)
                {
                    distancefactor2 = 0;
                }
                else if (distancefactor2 == 24)
                {
                    distancefactor2 = distancefactor1;
                    distancefactor1 = 18;
                    extension += 10;
                }
                else if (distancefactor1 == 0)
                {
                    distancefactor1 = 22;
                    distancefactor2 = 24;
                    extension = generator.nextInt(31)+10;
                    heightchange = (int)(Math.random()*70)-20;
                    rcolor = generator.nextInt(200);
                    gcolor = generator.nextInt(200);
                    bcolor = generator.nextInt(200);
                }
                else
                {
                    int olddifference = distancefactor2 - distancefactor1;
                    distancefactor2 = distancefactor1;
                    distancefactor1 = distancefactor1 - olddifference - 1;
                    extension += 10;
                }
                //randomly changes the window color to white or yellow
                if (generator.nextInt(2) == 1)
                    windowcolor = new Color(240, 240, 240);
                else
                    windowcolor = Color.yellow;
            }
            else if(running % 40 == 20)
            {
                //moves the building by changing the variables used to set the building's dimensions and positioning
                if (distancefactor2 == 7)
                {
                    distancefactor2 = 0;
                }
                else if (distancefactor2 == 24)
                {
                    distancefactor2 = distancefactor1;
                    distancefactor1 = 18;
                    extension += 10;
                }
                else if (distancefactor1 == 0)
                {
                    distancefactor1 = 22;
                    distancefactor2 = 24;
                    extension = generator.nextInt(31)+10;
                    heightchange = (int)(Math.random()*70)-20;
                    rcolor = generator.nextInt(200);
                    gcolor = generator.nextInt(200);
                    bcolor = generator.nextInt(200);
                }
                else
                {
                    int olddifference = distancefactor2 - distancefactor1;
                    distancefactor2 = distancefactor1;
                    distancefactor1 = distancefactor1 - olddifference - 1;
                    extension += 10;
                }
                //randomly changes the window color to white or yellow
                if (generator.nextInt(2) == 1)
                    windowcolor = new Color(240, 240, 240);
                else
                    windowcolor = Color.yellow;
            }
            running ++;
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }
}
