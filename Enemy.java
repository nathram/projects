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
public class Enemy extends JComponent implements Runnable
{
    // instance variables
    private int sidedistance, panelNum, direction, groundHeight, origs, origg, origp;
    private int height, width;
    private double groundDistance, scalefactor;
    private ArrayList<Integer> onblocks = new ArrayList<Integer>();
    public boolean death = false,  onblock = false, onseesaw = false;

    /**
     * Default constructor
     */
    public Enemy()
    {
        sidedistance = 700;
        groundDistance = 0;
        panelNum = 0;
    }
    
    /**
     * Copy constructor
     * @param int base of stripes (in the trapezoid)
     */
    public Enemy(int s, int p, int d, int g)
    {
        sidedistance = s;
        groundDistance = g;
        panelNum = p;
        direction = d;
        origs = s;
        origg = g;
        origp = p;
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
        if (Player.getPanel() == panelNum && !death)
        {
            height = 43;
            width = 30;
            page.setColor(new Color(181, 107, 69));
            page.fillRect(sidedistance, 700-(int)groundDistance-height, 30, 30);
            page.setColor(new Color(212,180,143));
            page.fillRect(sidedistance+10, 700-(int)groundDistance+30-height, 10, 10);
            page.setColor(Color.black);
            page.fillOval(sidedistance+3, 700-(int)groundDistance+35-height, 10, 8);
            page.fillOval(sidedistance+17, 700-(int)groundDistance+35-height, 10, 8);
            page.setColor(Color.white);
            page.fillOval(sidedistance+6, 700-(int)groundDistance+6-height, 7, 13);
            page.fillOval(sidedistance+17, 700-(int)groundDistance+6-height, 7, 13);
            page.setColor(Color.black);
            page.fillOval(sidedistance+9, 700-(int)groundDistance+12-height, 3, 3);
            page.fillOval(sidedistance+18, 700-(int)groundDistance+12-height, 3, 3);
            page.setStroke(new BasicStroke(3));
            page.drawLine(sidedistance+7, 700-(int)groundDistance+4-height, sidedistance+13, 700-(int)groundDistance+8-height);
            page.drawLine(sidedistance+23, 700-(int)groundDistance+4-height, sidedistance+17, 700-(int)groundDistance+8-height);
            
        }
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
    
    public boolean onSeeSaw()
    {
        onseesaw = false;
        for (int i = 0; i < SeeSaw.getSeesaws().size(); i++)
            if (sidedistance > SeeSaw.getSeesaws().get(i)[0]-225-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]-75)
            {
                if (Math.abs(SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height1 - 700 + groundDistance) <= 1)
                {
                    onseesaw = true;
                }
            }
            else if (sidedistance > SeeSaw.getSeesaws().get(i)[0]+75-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]+225)
                if (Math.abs(SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height2 - 700 + groundDistance) <= 1)
                {
                    onseesaw = true;
                }
        return onseesaw;
    }
    
    public int[] onThisSeeSaw()
    {
        int seesaw = -1;
        int side = -1;
        for (int i = 0; i < SeeSaw.getSeesaws().size(); i++)
            if (sidedistance > SeeSaw.getSeesaws().get(i)[0]-225-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]-75)
            {
                if (Math.abs(SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height1 - 700 + groundDistance) <= 0)
                {
                    seesaw = i;
                    side = 0;
                }
            }
            else if (sidedistance > SeeSaw.getSeesaws().get(i)[0]+75-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]+225)
                if (Math.abs(SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height2 - 700 + groundDistance) <= 0)
                {
                    seesaw = i;
                    side = 1;
                }
        //System.out.println("hi");
        return new int[]{seesaw, side};
    }
    
    public int groundHeight()
    {
        int groundHeight = -100;
        
        if (Ground.grounds.isEmpty())
            groundHeight = 500;
        else
            for (int[] arr: Ground.grounds)
            {
                for (int i = 0; i < 50; i++)
                    if (arr[0] == Player.getPanel() && sidedistance+i >= arr[1] && sidedistance+i < arr[1]+arr[2] && 1200 - arr[3] > groundHeight)
                        groundHeight = 1200 - arr[3];
            }
        return groundHeight;
    }
    
    //public static int getSideDistance()
    //{
      //  return sidedistance;
    //}
    
    public boolean hasEnemyDied()
    {
        if (groundDistance <= -100)
            death = true;
        return death;
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
        int seesaw = -1;
        int underblock = -1;
        int side = -1;
        while(true)
        {
            running ++;
            onBlock();
            if (Player.getSideDistance() >= sidedistance - Player.width && Player.getSideDistance() <= sidedistance + width)
                if (Player.getGD() >= groundDistance - Player.height && Player.getGD() <= groundDistance + height && Player.getPanel() == panelNum)
                {
                    Player.goomba = true;
                    if (Player.falling || Player.down)
                        death = true;
                }
                    
            if (Player.timer==500)
            {
                sidedistance = origs;
                groundDistance = origg;
                panelNum = origp;
                death = false;
            }
            if (hasEnemyDied())
            {
                sidedistance = -100;
                panelNum = -2;
                groundDistance = 0;
                falling = false;
                fallFrame = 0;
                falltime = 0;
            }
            groundHeight = groundHeight();
            if(onSeeSaw())
            {
                if(onThisSeeSaw()[1] == 0)
                {
                    SeeSaw.getSeesaws().set(onThisSeeSaw()[0], new int[]{SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[0], SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[1], SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[2]+1, SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[3]-1});
                    if (groundHeight - groundDistance < 500)
                        groundDistance -= 1;
                }
                else if(onThisSeeSaw()[1] == 1)
                {
                    SeeSaw.getSeesaws().set(onThisSeeSaw()[0], new int[]{SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[0], SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[1], SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[2]-1, SeeSaw.getSeesaws().get(onThisSeeSaw()[0])[3]+1});
                    if (groundHeight - groundDistance < 500)
                        groundDistance -= 1;
                }
            }
            //move into another panel of the game
            if (sidedistance >= 1440)
            {
                sidedistance -= 1430;
                panelNum++;
            }
            else if (sidedistance <= 10 && panelNum > 0)
            {
                sidedistance += 1430;
                panelNum--;
            }
            //falling
            if (groundHeight - groundDistance < 499 && !onblock && !onseesaw)
            {
                if (!falling)
                    falltime = running;
                falling = true;
                if ((running - falltime) % 3 == 0 && falltime != 0)
                {
                    fallFrame += 1;
                    for (int i = 0; i < Block.getBlocks().size(); i++)
                    {
                        if (sidedistance > Block.getBlocks().get(i)[0]-width && sidedistance < Block.getBlocks().get(i)[0]+50)
                        
                            if (Block.getBlocks().get(i)[1] >= 700-groundDistance && Block.getBlocks().get(i)[1] <= 700-groundDistance + 40*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) && Block.getBlocks().get(i)[1] < miny)
                            {
                                miny = Block.getBlocks().get(i)[1];
                                block = i;
                            }
                    }
                    for (int i = 0; i < SeeSaw.getSeesaws().size(); i++)
                    {
                        if (sidedistance > SeeSaw.getSeesaws().get(i)[0]-225-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]-75)
                        {
                            if (SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height1 >= 700-groundDistance && SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height1 <= 700-groundDistance + 40*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) && Block.getBlocks().get(i)[1] < miny)
                            {
                                seesaw = i;
                                side = 0;
                            }
                        }
                        else if (sidedistance > SeeSaw.getSeesaws().get(i)[0]+75-width && sidedistance < SeeSaw.getSeesaws().get(i)[0]+225)
                            if (SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height2 >= 700-groundDistance && SeeSaw.getSeesaws().get(i)[1]+SeeSaw.height2 <= 700-groundDistance + 40*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame) && Block.getBlocks().get(i)[1] < miny)
                            {
                                seesaw = i;
                                side = 1;
                            }
                    }
                    if (groundHeight-(groundDistance - 40*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame)) >= 500)
                    {
                        groundDistance = 500-groundHeight;
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
                    else if (seesaw != -1)
                    {
                        if (side == 0)
                            groundDistance = 700-SeeSaw.getSeesaws().get(seesaw)[1]-SeeSaw.height1;
                        else if (side == 1)
                            groundDistance = 700-SeeSaw.getSeesaws().get(seesaw)[1]-SeeSaw.height2;
                        fallFrame = 0;
                        falltime = 0;
                        seesaw = -1;
                        side = -1;
                        onseesaw = true;
                        falling = false;
                    }
                    else
                        groundDistance -= 40*Math.pow(1.7, -8)*Math.pow(1.7, fallFrame);
                }
            }
            if (direction == 1)
            {
                for (int i = 0; i < Block.getBlocks().size(); i++)
                    if (700-groundDistance > Block.getBlocks().get(i)[1] && 700-groundDistance <= Block.getBlocks().get(i)[1]+50)
                        if (sidedistance + width + 5 >= Block.getBlocks().get(i)[0] && sidedistance + width <= Block.getBlocks().get(i)[0])
                            block = i;
                //if (falling && sidedistance + 5 > bound1+bound2-width && inpit)
                    //sidedistance = bound1+bound2-width;
                //else
                if (block != -1)
                {
                    sidedistance = Block.getBlocks().get(block)[0]-width;
                    block = -1;
                }
                else
                    sidedistance += 1;
            }
            else if (direction == 0)
            {
                for (int i = 0; i < Block.getBlocks().size(); i++)
                    if (700-groundDistance > Block.getBlocks().get(i)[1] && 700-groundDistance <= Block.getBlocks().get(i)[1]+50)
                        if (sidedistance - 55 <= Block.getBlocks().get(i)[0] && sidedistance - 50 >= Block.getBlocks().get(i)[0])
                            block = i;                   
                //if (falling && sidedistance - 5 < bound1 && inpit)
                    //sidedistance = bound1;
                //else
                if (block != -1)
                {
                    sidedistance = Block.getBlocks().get(block)[0]+50;
                    block = -1;
                }
                else
                    sidedistance -= 1;
            }
            

            running ++;
            try{
                Thread.sleep(17);
            }catch (Exception e){}
            
            repaint();
        }
    }
}
