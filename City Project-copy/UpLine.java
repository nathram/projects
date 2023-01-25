    import java.awt.*;
    import java.util.*;

    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;

public class UpLine extends JComponent //implements Runnable
{
   Random generator = new Random();
   //Graphics page;

   private Buildings building1, building2, building3, building4, building5, building6;
   private Road road1;
   private Stripes stripe1, stripe2, stripe3, stripe4;
   private Background background;
   private int running = 0;

   //-----------------------------------------------------------------
   //  Creates buildings and road
   //-----------------------------------------------------------------
   public UpLine () //init in applet is like constructor; runs only once
   {
      building1 = new Buildings(0, 7, 60, 0, "left");
      building2 = new Buildings(13, 18, 40, (int)(Math.random()*70)-10, "left");
      building3 = new Buildings(22, 24, 20, (int)(Math.random()*70)-10, "left");
      building4 = new Buildings(0, 7, 60, 0, "right");
      building5 = new Buildings(13, 18, 40, (int)(Math.random()*70)-10, "right");
      building6 = new Buildings(22, 24, 20, (int)(Math.random()*70)-10, "right");
      road1 = new Road(0, 50);
      stripe1 = new Stripes(1, 2);
      stripe2 = new Stripes(4, 7);
      stripe3 = new Stripes(11, 16);
      stripe4 = new Stripes(22, 26);
      background = new Background();
      
      //thread for the moving items
      Thread t1 = new Thread(building1);
      t1.start();
      Thread t2 = new Thread(building2);
      t2.start();
      Thread t3 = new Thread(building3);
      t3.start();
      Thread t5 = new Thread(stripe1);
      t5.start();
      Thread t6 = new Thread(stripe2);
      t6.start();
      Thread t7 = new Thread(stripe3);
      t7.start();
      Thread t8 = new Thread(stripe4);
      t8.start();
      Thread t9 = new Thread(building4);
      t9.start();
      Thread t10 = new Thread(building5);
      t10.start();
      Thread t11 = new Thread(building6);
      t11.start();
   }

   //-----------------------------------------------------------------
   //  Paints the scene
   //-----------------------------------------------------------------
   public void paintComponent(Graphics g)
    {
       Graphics2D page = (Graphics2D) g;
       background.draw(page);
       building1.draw(page);
       building2.draw(page);
       building3.draw(page);
       building4.draw(page);
       building5.draw(page);
       building6.draw(page);
       road1.draw(page);
       stripe1.draw(page);
       stripe2.draw(page);
       stripe3.draw(page);
       stripe4.draw(page);
   }
   
   //repaints every frame
   public void nextFrame()
   {
       repaint();
   }
}
