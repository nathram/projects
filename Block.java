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
public class Block extends JComponent implements Runnable
{
    // instance variables
    private int x, y, panelNum, origtype, type, pos, blockNum;
    public static ArrayList<int[]> blocks = new ArrayList<int[]>();
    public int[] arr1;
    public static boolean changeType = false;

    /**
     * Default constructor
     */
    public Block()
    {
        x = 50;
        y = 50;
        origtype = 0; //unbreakable, coin when hit
        type = 0;
        panelNum = 0;
        pos = 0;
        arr1 = new int[] {x, y, type};
        blocks.add(arr1);
    }
    
    /**
     * Copy constructor
     * @param int base of stripes (in the trapezoid)
     */
    public Block(int panel, int x1, int y1, int thetype, int number)
    {
        x = x1;
        y = y1;
        origtype = thetype;
        type = thetype;
        //0 - unbreakable, dead block when hit(coin)
        //1 - breakable
        //2 - question, dead block when hit(coin)
        //3 - question, powerup
        //5 - dead block
        //6 - broken block
        panelNum = panel;
        pos = 0;
        blockNum = number;
        arr1 = new int[] {x, y, type};
        blocks.add(arr1);
    }
    
    public static ArrayList<int[]> getBlocks()
    {
        return blocks;
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
        //blocks
        if (type == 5)
        {
            page.setColor(new Color(139, 79, 57));
            page.fillRect(arr1[0], arr1[1], 50, 50);
            page.setColor(Color.black);
            
            page.setStroke(new BasicStroke(3));
            page.drawLine(arr1[0], arr1[1], arr1[0]+50, arr1[1]);
            page.drawLine(arr1[0]+50, arr1[1], arr1[0]+50, arr1[1]+50);
            page.drawLine(arr1[0]+50, arr1[1]+50, arr1[0], arr1[1]+50);
            page.drawLine(arr1[0], arr1[1]+50, arr1[0], arr1[1]);
            page.setStroke(new BasicStroke(1));
            
            
            page.fillOval(2+arr1[0], 2+arr1[1], 4, 4);
            page.fillOval(2+arr1[0], 44+arr1[1], 4, 4);
            page.fillOval(44+arr1[0], 2+arr1[1], 4, 4);
            page.fillOval(44+arr1[0], 44+arr1[1], 4, 4);
        }
        else if (type == 6)
        {
            page.setColor(new Color(173, 216, 230));
            page.fillRect(arr1[0], arr1[1], 50, 50);
            arr1[0] = -50;
            arr1[1] = -50;
        }
        else if (type == 0 || type == 1)
        {
            page.setColor(new Color(139, 79, 57));
            page.fillRect(arr1[0], arr1[1], 50, 50);
            page.setColor(Color.black);
            
            page.setStroke(new BasicStroke(3));
            page.drawLine(arr1[0], arr1[1], arr1[0]+50, arr1[1]);
            page.drawLine(arr1[0]+50, arr1[1], arr1[0]+50, arr1[1]+50);
            page.drawLine(arr1[0]+50, arr1[1]+50, arr1[0], arr1[1]+50);
            page.drawLine(arr1[0], arr1[1]+50, arr1[0], arr1[1]);
            
            page.drawLine(arr1[0], arr1[1]+16, arr1[0]+50, arr1[1]+16);
            page.drawLine(arr1[0], arr1[1]+34, arr1[0]+50, arr1[1]+34);
            page.drawLine(arr1[0]+10, arr1[1]+16, arr1[0]+10, arr1[1]+34);
            page.drawLine(arr1[0]+40, arr1[1]+16, arr1[0]+40, arr1[1]+34);
            page.drawLine(arr1[0]+25, arr1[1], arr1[0]+25, arr1[1]+16);
            page.drawLine(arr1[0]+25, arr1[1]+34, arr1[0]+25, arr1[1]+50);
            page.setStroke(new BasicStroke(1));
        }
        else if (type == 2 || type == 3 || type == 4)
        {
            page.setColor(new Color(243, 204, 10));
            page.fillRect(arr1[0], arr1[1], 50, 50);
            
            int[] xarr = {9, 9, 13, 37, 41, 41, 37, 30, 29, 29, 21, 21, 25, 30, 31, 31, 30, 20, 19, 19, 9};
            int[] yarr = {20, 12, 8, 8, 12, 24, 28, 28, 29, 35, 35, 26, 22, 22, 21, 15, 14, 14, 15, 20, 20};
            int[] xarr2 = {21, 29, 29, 21, 21};
            int[] yarr2 = {38, 38, 44, 44, 38};
            int[] xarr3 = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
            int[] xarr4 = {50, 50, 50, 50, 50};
            
            for (int i = 0; i < xarr.length; i++)
            {
                if (xarr[i] + pos >= 0)
                {
                    xarr[i] = xarr[i] + arr1[0] + pos;
                    xarr3[i] = xarr3[i] + arr1[0];
                }
                else
                {
                    xarr3[i] = xarr3[i] + arr1[0] + xarr[i] + pos;
                    xarr[i] = arr1[0]+2;
                }
                yarr[i] = yarr[i] + arr1[1];
            }
            for (int i = 0; i < xarr2.length; i++)
            {
                if (xarr2[i] + pos >= 0)
                {
                    xarr2[i] = xarr2[i] + arr1[0] + pos;
                    xarr4[i] = xarr4[i] + arr1[0];
                }
                else
                {
                    xarr4[i] = xarr4[i] + arr1[0] + xarr2[i] + pos;
                    xarr2[i] = arr1[0]+2;
                }
                yarr2[i] = yarr2[i] + arr1[1];
            }
            
            page.setColor(Color.white);
            page.fillPolygon(xarr, yarr, 20);
            page.fillPolygon(xarr2, yarr2, 4);
            page.fillPolygon(xarr3, yarr, 20);
            page.fillPolygon(xarr4, yarr2, 4);
            
            page.setColor(new Color(151, 125, 20));
            page.fillOval(2+arr1[0], 2+arr1[1], 4, 4);
            page.fillOval(2+arr1[0], 44+arr1[1], 4, 4);
            page.fillOval(44+arr1[0], 2+arr1[1], 4, 4);
            page.fillOval(44+arr1[0], 44+arr1[1], 4, 4);
            
            if (xarr3[0] <= 9 + arr1[0])
            {
                pos = 0;
            }
            page.setColor(Color.black);
            
            page.setStroke(new BasicStroke(3));
            page.drawLine(arr1[0], arr1[1], arr1[0]+50, arr1[1]);
            page.drawLine(arr1[0]+50, arr1[1], arr1[0]+50, arr1[1]+50);
            page.drawLine(arr1[0]+50, arr1[1]+50, arr1[0], arr1[1]+50);
            page.drawLine(arr1[0], arr1[1]+50, arr1[0], arr1[1]);
            page.setStroke(new BasicStroke(1));
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
                arr1[2] = origtype;
                blocks.set(blockNum, arr1);
            }
            if (Player.getPanel() == panelNum && blocks.get(blockNum)[2] != 6)
            {
                arr1[0] = x;
                arr1[1] = y;
                type = blocks.get(blockNum)[2];
                arr1[2] = type;
                blocks.set(blockNum, arr1);
            }
            else if (Player.getPanel() != panelNum || blocks.get(blockNum)[2] == 6)
            {
                arr1[0] = -50;
                arr1[1] = -50;
                type = blocks.get(blockNum)[2];
                arr1[2] = type;
                blocks.set(blockNum, arr1);
            }
            pos -= 1;
                running ++;
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }   
}
