import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Creates the player
 *
 * @author Nathra Ramrajvel
 * @version 10/12
 */
public class Player extends JComponent implements Runnable
{
    // instance variables
    private static int sidedistance, panel;
    private static int height, width, timer, coins = 0;
    private static double groundDistance, jumpheight, ycoord, scalefactor;
    private static ArrayList<Integer> onblocks = new ArrayList<Integer>();
    public static boolean death = false, inpit = false, onblock = false;

    /**
     * Default constructor
     */
    public Player()
    {
        sidedistance = 100;
        scalefactor = 6;
        jumpheight = 40;
        groundDistance = 0;
        timer = 500;
    }
    
    /**
     * Copy constructer
     * @param int distance from the edge of the buildings, top base length (how far the road goes)
     */
    public Player(int s)
    {
        sidedistance = 100;
        scalefactor = s;
        jumpheight = 40;
        groundDistance = 0;
        timer = 500;
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
        //draws star
        page.setColor(new Color(238, 238, 155));
        double x = scalefactor*Math.tan(54*Math.PI/180);
        double h = scalefactor*Math.tan(72*Math.PI/180);
        double y = scalefactor/Math.cos(72*Math.PI/180);
        double z = x/Math.cos(36*Math.PI/180);
        height = (int)((x+h)*(1+Math.sin(58*Math.PI/180)));
        width = (int)(2*y+2*scalefactor);
        int[] array1 = {0, (int)(y+scalefactor - z*Math.cos(22*Math.PI/180)), (int)(y+scalefactor - (x+h)*Math.cos(58*Math.PI/180)), (int)(y+scalefactor), (int)(y+scalefactor + (x+h)*Math.cos(58*Math.PI/180)),
        (int)(y+scalefactor + z*Math.cos(22*Math.PI/180)), width, (int)(y+2*scalefactor), (int)(y+scalefactor), (int)y};
        int[] array2 = {(int)h, (int)(x+h + z*Math.sin(22*Math.PI/180)), height, (int)(h+x+z), height,
        (int)(x+h + z*Math.sin(22*Math.PI/180)), (int)h, (int)h, 0, (int)h};
        for (int i = 0; i < array1.length; i++)
            array1[i] = array1[i] + sidedistance;
        for (int i = 0; i < array2.length; i++)
            array2[i] = array2[i] + 700 - height - (int)groundDistance;
        page.fillPolygon(array1, array2, 10);
        
        //eyes
        page.setColor(new Color(0, 0, 0));
        int a = (int)(y+2*scalefactor/5)+sidedistance-(int)(scalefactor/6);
        int b = (int)(y+8*scalefactor/5)+sidedistance-(int)(scalefactor/6);
        int c = (int)(h+x/4)+700-height-(int)groundDistance;
        page.fillRect(a,c, (int)(scalefactor/2), (int)(scalefactor/2));
        page.fillRect(b,c, (int)(scalefactor/2), (int)(scalefactor/2));
        //smiley
        page.drawLine(a+1, c+5 + (int)(x/4), a+2, c+7 + (int)(x/4));
        page.drawLine(a+2, c+7 + (int)(x/4), a+4, c+9 + (int)(x/4));
        page.drawLine(a+4, c+9 + (int)(x/4), a+5, c+9 + (int)(x/4));
        page.drawLine(a+5, c+9 + (int)(x/4), a+8, c+7 + (int)(x/4));
        page.drawLine(a+8, c+7 + (int)(x/4), a+9, c+5 + (int)(x/4));
        //timer
        Font stringFont = new Font("SansSerif", Font.PLAIN, 18);
        page.setFont(stringFont);
        page.drawString(""+timer+"s", 730, 20);
        //coins
        page.drawString("coins: " + coins, 5, 20);
    }
    
    public static int getSideDistance()
    {
        return sidedistance;
    }
    
    public static int getPanel()
    {
        return panel;
    }
    
    public static boolean hasPlayerLost()
    {
        death = false;
        if (groundDistance <= -600 || timer == 0)
            death = true;
        return death;
    }
    
    public boolean onBlock()
    {
        onblock = false;
        onblocks.clear();
        for (int i = 0; i < Block.getBlocks().size(); i++)
        {
            if (sidedistance >= Block.getBlocks().get(i)[0]-width && sidedistance <= Block.getBlocks().get(i)[0]+50)
            
                if (Math.abs(Block.getBlocks().get(i)[1] - 700 + groundDistance) <= 1)
                {
                    onblock = true;
                    onblocks.add(i);
                }
        }
        return onblock;
    }
    
    public void run()
    {
        int running  = 0;
        int jumptime = 0;
        int falltime = 0;
        double jumpFrame = 0;
        double fallFrame = 0;
        boolean down = false;
        boolean falling = false;
        boolean jumping = false;
        int miny = 9999;
        int minx = 9999;
        int maxy = 0;
        int maxx = 0;
        int block = -1;
        panel = 0;
        while(true)
        {
            running ++;
            if (running%57 == 0)
                timer--;
            int bound1 = 0;
            int bound2 = 0;
            onBlock();            
            if (hasPlayerLost())
            {
                sidedistance = 100;
                panel = 0;
                groundDistance = 0;
                inpit = false;
                timer = 500;
                coins = 0;
            }
            //check if player is in a pit, set bounds to be that of the pit
            for (int i = 0; i < Pit.getPits().size(); i++)
                if (sidedistance >= Pit.getPits().get(i)[0] && sidedistance <= Pit.getPits().get(i)[0]+Pit.getPits().get(i)[2]-width)
                {
                    if (groundDistance <= 0)
                    {
                        inpit = true;
                    }
                    bound1 = Pit.getPits().get(i)[0];
                    bound2 = Pit.getPits().get(i)[2];
                }
            //move into another panel of the game
            if (sidedistance >= 1440)
            {
                sidedistance -= 1340;
                panel++;
            }
            else if (sidedistance <= 10 && panel > 0)
            {
                sidedistance += 1340;
                panel--;
            }
            //falling not jumping
            if (groundDistance > -600 && !jumping && inpit || groundDistance > 1 && !jumping && !onblock)
            {
            if (!falling && !WorldViewer.up || falling)
            {
                if (!falling)
                    falltime = running;
                falling = true;
                //System.out.println(onBlock());
                if ((running - falltime) % 3 == 0 && falltime != 0)
                {
                    fallFrame += 1;
                    for (int i = 0; i < Block.getBlocks().size(); i++)
                    {
                        if (sidedistance > Block.getBlocks().get(i)[0]-width && sidedistance < Block.getBlocks().get(i)[0]+50)
                        
                            if (Block.getBlocks().get(i)[1] >= 700-groundDistance && Block.getBlocks().get(i)[1] <= 700-groundDistance + jumpheight*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) && Block.getBlocks().get(i)[1] < miny)
                            {
                                miny = Block.getBlocks().get(i)[1];
                                block = i;
                            }
                    }
                    if (groundDistance - jumpheight*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) <= -600)
                    {
                        groundDistance = -600;
                        fallFrame = 0;
                        falltime = 0;
                        falling = false;
                    }
                    else if (miny != 9999)
                    {
                        groundDistance = 700-Block.getBlocks().get(block)[1];
                        fallFrame = 0;
                        falltime = 0;
                        miny = 9999;
                        block = -1;
                        onblock = true;
                        falling = false;
                    }
                    else if (groundDistance - jumpheight*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) <= 0 && bound2 == 0)
                    {
                        groundDistance = 0;
                        fallFrame = 0;
                        falltime = 0;
                        falling = false;
                    }
                    else
                        groundDistance -= jumpheight*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame);
                }
            }
            }
            
            //jumping
            
            if (WorldViewer.up && !falling || jumping && !falling)
            {
                if (!jumping)
                    jumptime = running;
                jumping = true;
                //if ((running - jumptime) % 3 == 0 && jumptime != 0)
                //{
                    jumpFrame += 1;
                    for (int i = 0; i < Block.getBlocks().size(); i++)
                    {
                        if (!onblock && sidedistance > Block.getBlocks().get(i)[0]-width && sidedistance < Block.getBlocks().get(i)[0]+50)
                        
                            if (Block.getBlocks().get(i)[1] >= 700-groundDistance && Block.getBlocks().get(i)[1] <= 700-groundDistance + jumpheight*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) && Block.getBlocks().get(i)[1] < miny)
                            {
                                miny = Block.getBlocks().get(i)[1];
                                block = i;
                            }
                    }
                    if (jumpFrame <= 17)
                    {
                        for (int i = 0; i < Block.getBlocks().size(); i++)
                        {
                            if (!onblock && sidedistance > Block.getBlocks().get(i)[0]-width && sidedistance < Block.getBlocks().get(i)[0]+50)
                            
                                if (Block.getBlocks().get(i)[1] + 50 <= 700-groundDistance-height && Block.getBlocks().get(i)[1] + 50 >= 700-groundDistance-height - jumpheight/Math.pow(1.2, jumpFrame) && Block.getBlocks().get(i)[1] > maxy)
                                {
                                    maxy = Block.getBlocks().get(i)[1]+50;
                                    block = i;
                                }
                        }
                        if (maxy != 0)
                        {
                            groundDistance = -Block.getBlocks().get(block)[1] + 650 - height;
                            jumpFrame = 17;
                            if (Block.getBlocks().get(block)[2] == 0 || Block.getBlocks().get(block)[2] == 2 && sidedistance + width/2 <= Block.getBlocks().get(block)[0]+50 && sidedistance + width/2 >= Block.getBlocks().get(block)[0])
                            {
                                Block.getBlocks().set(block, new int[] {Block.getBlocks().get(block)[0], Block.getBlocks().get(block)[1], 5});
                                coins += 1;
                            }
                            else if (Block.getBlocks().get(block)[2] == 1 && sidedistance + width/2 <= Block.getBlocks().get(block)[0]+50 && sidedistance + width/2 >= Block.getBlocks().get(block)[0])
                                Block.getBlocks().set(block, new int[] {0, 0, 6});
                            maxy = 0;
                            block = -1;
                        }
                        else
                            groundDistance += jumpheight/Math.pow(1.2, jumpFrame);
                    }
                    else if (groundDistance > 0)
                    {
                        down = true;
                        fallFrame += 1;
                        if (groundDistance - jumpheight*Math.pow(1.2, -17)*Math.pow(1.2, fallFrame) < 0)
                            groundDistance = 0;
                        else if (miny != 9999)
                        {
                            groundDistance = 700-Block.getBlocks().get(block)[1];
                            miny = 9999;
                            block = -1;
                            fallFrame = 0;
                            jumpFrame = 0;
                            down = false;
                            jumping = false;
                            onblock = true;
                        }
                        else
                            groundDistance -= jumpheight*Math.pow(1.2, -17)*Math.pow(1.2, fallFrame);
                    }
                    else if (groundDistance > -600 && inpit)
                    {
                        down = true;
                        fallFrame += 1;
                        if (groundDistance - jumpheight*Math.pow(1.2, -17)*Math.pow(1.2, fallFrame) < -600)
                            groundDistance = -600;
                        else
                            groundDistance -= jumpheight*Math.pow(1.2, -17)*Math.pow(1.2, fallFrame);
                    }
                    else
                    {
                        fallFrame = 0;
                        jumpFrame = 0;
                        down = false;
                        jumping = false;
                    }
                //}
            }
            
            //move left and/or right (constrained by other objects)
            onBlock();
            if (WorldViewer.right && WorldViewer.left)
                sidedistance += 0;
            else if (WorldViewer.right)
            {
                for (int i = 0; i < Block.getBlocks().size(); i++)
                    if (700-groundDistance > Block.getBlocks().get(i)[1] && 700-groundDistance <= Block.getBlocks().get(i)[1]+50)
                        if (sidedistance + width + 5 >= Block.getBlocks().get(i)[0] && sidedistance + width <= Block.getBlocks().get(i)[0])
                            block = i;
                if (falling && sidedistance + 5 > bound1+bound2-width && inpit)
                    sidedistance = bound1+bound2-width;
                else if (block != -1)
                {
                    sidedistance = Block.getBlocks().get(block)[0]-width;
                    block = -1;
                }
                else
                    sidedistance += 5;
            }
            else if (WorldViewer.left)
            {
                for (int i = 0; i < Block.getBlocks().size(); i++)
                    if (700-groundDistance > Block.getBlocks().get(i)[1] && 700-groundDistance <= Block.getBlocks().get(i)[1]+50)
                        if (sidedistance - 55 <= Block.getBlocks().get(i)[0] && sidedistance - 50 >= Block.getBlocks().get(i)[0])
                            block = i;                   
                if (falling && sidedistance - 5 < bound1 && inpit)
                    sidedistance = bound1;
                else if (block != -1)
                {
                    sidedistance = Block.getBlocks().get(block)[0]+50;
                    block = -1;
                }
                else
                    sidedistance -= 5;
            }
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }
}
