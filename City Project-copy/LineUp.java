    import java.awt.*;
    import java.util.*;

    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;

public class LineUp extends JComponent //implements Runnable
{
   //Random generator = new Random();
   //Graphics page;
   
   private Ground ground;
   private Pit pit1, pit2, pit3;
   private Player player1, player2;
   private Block block1, block2, block3, block4, block5, block6;
   private int running = 0;

   //-----------------------------------------------------------------
   //  Creates background + player
   //-----------------------------------------------------------------
   public LineUp () //init in applet is like constructor; runs only once
   {
      //building1 = new Buildings(0, 7, 60, 0, "left");
      ground = new Ground();
      pit1 = new Pit(0, 450, 100);
      pit2 = new Pit(1, 500, 50);
      pit3 = new Pit(2, 700, 150);
      player1 = new Player();
      block1 = new Block(0, 200, 525, 0, 0);
      block2 = new Block(0, 250, 525, 1, 1);
      block3 = new Block(0, 300, 525, 3, 2);
      block4 = new Block(0, 350, 525, 0, 3);
      block5 = new Block(0, 400, 525, 0, 4);
      block6 = new Block(0, 450, 525, 0, 5);
      //thread for the moving items
      Thread t1 = new Thread(player1);
      Thread t2 = new Thread(pit1);
      Thread t3 = new Thread(pit2);
      Thread t4 = new Thread(pit3);
      Thread t5 = new Thread(block1);
      Thread t6 = new Thread(block2);
      Thread t7 = new Thread(block3);
      Thread t8 = new Thread(block4);
      Thread t9 = new Thread(block5);
      Thread t10 = new Thread(block6);
      t1.start();
      t2.start();
      t3.start();
      t4.start();
      t5.start();
      t6.start();
      t7.start();
      t8.start();
      t9.start();
      t10.start();
   }

   //-----------------------------------------------------------------
   //  Paints the scene
   //-----------------------------------------------------------------
   public void paintComponent(Graphics g)
    {
       Graphics2D page = (Graphics2D) g;
       ground.draw(page);
       pit1.draw(page);
       pit2.draw(page);
       pit3.draw(page);
       block2.draw(page);
       player1.draw(page);
       block1.draw(page);
       block3.draw(page);
       block4.draw(page);
       block5.draw(page);
       block6.draw(page);
   }
   
   //repaints every frame
   public void nextFrame()
   {
       repaint();
   }
}
