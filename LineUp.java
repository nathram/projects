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
   private Block block1, block2, block3, block4, block5, block6, block7, block8, block9, block10, block11, block12, block13, block14, block15, block16, block17, block18, block19, block20;
   private SeeSaw seesaw1, seesaw2, seesaw3, seesaw4, seesaw5;
   private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9;
   private int running = 0;

   //-----------------------------------------------------------------
   //  Creates background + player
   //-----------------------------------------------------------------
   public LineUp () //init in applet is like constructor; runs only once
   {
      //building1 = new Buildings(0, 7, 60, 0, "left");
      ground = new Ground();
      //pit1 = new Pit(0, 600, 600);
      //pit2 = new Pit(1, 500, 50);
      //pit3 = new Pit(2, 700, 150);
      player1 = new Player();
      block1 = new Block(0, 400, 525, 0, 0);
      block2 = new Block(0, 450, 525, 1, 1);
      block3 = new Block(0, 500, 525, 0, 2);
      block4 = new Block(0, 550, 525, 0, 3);
      block5 = new Block(0, 600, 525, 0, 4);
      block6 = new Block(0, 450, 400, 0, 5);
      block7 = new Block(0, 500, 400, 3, 6);
      block8 = new Block(0, 550, 400, 0, 7);
      block9 = new Block(0, 600, 400, 2, 8);
      block10 = new Block(0, 650, 400, 2, 9);
      block11 = new Block(0, 700, 400, 0, 10);
      seesaw1 = new SeeSaw(300, 350, 200, 200, 100, 1, 0);
      seesaw2 = new SeeSaw(700, 200, 200, 200, 100, 1, 1);
      seesaw3 = new SeeSaw(1100, 50, 200, 200, 100, 1, 2);
      enemy1 = new Enemy(1400, 0, 0, 700);
      enemy2 = new Enemy(700, 0, 0, 400);
      enemy3 = new Enemy(300, 2, 0, 700);
      enemy4 = new Enemy(450, 2, 0, 900);
      enemy5 = new Enemy(600, 2, 0, 1100);
      enemy6 = new Enemy(775, 2, 0, 1300);
      enemy7 = new Enemy(950, 2, 0, 1500);
      enemy8 = new Enemy(1100, 2, 0, 1700);
      //thread for the moving items
      Thread t1 = new Thread(player1);
      Thread t2 = new Thread(enemy1);
      Thread t3 = new Thread(enemy2);
      Thread t4 = new Thread(seesaw1);
      Thread t5 = new Thread(block1);
      Thread t6 = new Thread(block2);
      Thread t7 = new Thread(block3);
      Thread t8 = new Thread(block4);
      Thread t9 = new Thread(block5);
      Thread t10 = new Thread(block6);
      Thread t11 = new Thread(seesaw2);
      Thread t12 = new Thread(block7);
      Thread t13 = new Thread(block8);
      Thread t14 = new Thread(block9);
      Thread t15 = new Thread(block10);
      Thread t16 = new Thread(block11);
      Thread t17 = new Thread(seesaw3);
      Thread t18 = new Thread(enemy3);
      Thread t19 = new Thread(enemy4);
      Thread t20 = new Thread(enemy5);
      Thread t21 = new Thread(enemy6);
      Thread t22 = new Thread(enemy7);
      Thread t23 = new Thread(enemy8);
      //Thread t24 = new Thread(enemy9);
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
      t11.start();
      t12.start();
      t13.start();
      t14.start();
      t15.start();
      t16.start();
      t17.start();
      t18.start();
      t19.start();
      t20.start();
      t21.start();
      t22.start();
      t23.start();
      //t24.start();
   }

   //-----------------------------------------------------------------
   //  Paints the scene
   //-----------------------------------------------------------------
   public void paintComponent(Graphics g)
    {
       Graphics2D page = (Graphics2D) g;
       ground.draw(page);
       enemy1.draw(page);
       enemy2.draw(page);
       enemy3.draw(page);
       enemy4.draw(page);
       enemy5.draw(page);
       enemy6.draw(page);
       enemy7.draw(page);
       enemy8.draw(page);
       //enemy9.draw(page);
       block2.draw(page);
       player1.draw(page);
       block1.draw(page);
       block3.draw(page);
       block4.draw(page);
       block5.draw(page);
       block6.draw(page);
       block7.draw(page);
       block8.draw(page);
       block9.draw(page);
       block10.draw(page);
       block11.draw(page);
       seesaw1.draw(page);
       seesaw2.draw(page);
       seesaw3.draw(page);
   }
   
   //repaints every frame
   public void nextFrame()
   {
       repaint();
   }
}
