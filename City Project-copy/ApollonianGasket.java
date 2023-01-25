import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.geom.*;
import java.util.*;
public class ApollonianGasket extends JPanel
{
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 400;

    private final double SQ = Math.sqrt(3.0) / 6;

    private final int TOPX = 200, TOPY = 20;
    private final int LEFTX = 60, LEFTY = 300;
    private final int RIGHTX = 340, RIGHTY = 300;

    private int current; //current order
    
    private int counter = 0, d1, d2;
    

    //-----------------------------------------------------------------
    //  Sets the initial fractal order to the value specified.
    //-----------------------------------------------------------------
    public ApollonianGasket(int diameter1, int diameter2)
    {
        setBackground (Color.white);
        setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        d1 = diameter1;
        d2 = diameter2;
    }

    
    //-----------------------------------------------------------------
    //  Performs the initial calls to the drawCircle method.
    //-----------------------------------------------------------------
    @Override
    public void paintComponent(Graphics page)
    {
        super.paintComponent (page);

        page.setColor (Color.blue);

        drawCircle(0, 0, d1+d2, page);
        
        //recursive code here

       
    }
    
    void drawCircle(int x, int y, int diameter,Graphics page)
    {
        double radius = diameter/2;
        page.drawOval(x,y,diameter,diameter);
        page.drawOval(x,y+(int)(radius-d1/2), d1, d1);
        page.drawOval(x+d1,y+(int)(radius-d2/2), d2, d2);
        drawMoreCircles(x+radius, y+radius, x+d1/2, y+radius, x+d1+d2/2, y+radius, radius, d1/2, d2/2, page);

    }//end of drawCircle
    
    public void drawMoreCircles(double cx1, double cy1, double cx2, double cy2, double cx3, double cy3, double r1, double r2, double r3, Graphics page)
    {
        Complex c1 = new Complex(cx1, cy1);
        Complex c2 = new Complex(cx2, cy2);
        Complex c3 = new Complex(cx3, cy3);
        double k1 = 1/r1;
        double k2 = 1/r2;
        double k3 = 1/r3;
        if (r1 >= r2 + r3)
            k1 = -k1;
        else if (r2 >= r1 + r3)
            k2 = -k2;
        else if (r3 >= r1 + r2)
            k3 = -k3;
        double k4 = k1 + k2 + k3 + 2*Math.pow(k1*k2 + k2*k3 + k1*k3, 0.5);
        if (Math.abs(k4) < Math.abs(k4 - 4*Math.pow(k1*k2 + k2*k3 + k1*k3, 0.5)))
            k4 -= 4*Math.pow(k1*k2 + k2*k3 + k1*k3, 0.5);
        Complex c4sub = (((c1.times(c2).times(k1*k2)).plus(c1.times(c3).times(k1*k3)).plus(c2.times(c3).times(k2*k3))).sqrt()).times(2);
        double rad = 1/k4;
        Complex tempc4 = (c1.times(k1).plus(c2.times(k2)).plus(c3.times(k3)).plus(c4sub)).div(k4);
        c4sub = c4sub.times(-1);
        Complex c4neg = (c1.times(k1).plus(c2.times(k2)).plus(c3.times(k3)).plus(c4sub)).div(k4);
        Complex c4;
        if (k1 < 0 || k2 < 0)
        {
            if (Math.abs(Math.pow(Math.pow(tempc4.real()-cx3, 2) + Math.pow(tempc4.imag()-cy3, 2), 0.5) - r3 - rad) >
            Math.abs(Math.pow(Math.pow(c4neg.real()-cx3, 2) + Math.pow(c4neg.imag()-cy3, 2), 0.5) - r3 - rad)) 
                c4 = c4neg;
            else
                c4 = tempc4;
        }
        else
        {
            if (Math.abs(Math.pow(Math.pow(tempc4.real()-cx2, 2) + Math.pow(tempc4.imag()-cy2, 2), 0.5) - r2 - rad) >
            Math.abs(Math.pow(Math.pow(c4neg.real()-cx2, 2) + Math.pow(c4neg.imag()-cy2, 2), 0.5) - r2 - rad)) 
                c4 = c4neg;
            else
                c4 = tempc4;
        }
        //if (Math.abs(Math.pow(Math.pow(tempc4.real()-cx3, 2) + Math.pow(tempc4.imag()-cy3, 2), 0.5) - r3 - rad) > 50 ||
        //Math.abs(Math.pow(Math.pow(tempc4.real()-cx3, 2) + Math.pow(tempc4.imag()-cy3, 2), 0.5) - r3 + rad) > 50)
            //c4sub = c4sub.times(-1);
        
        page.drawOval((int)(c4.real()-rad), (int)(c4.imag()-rad), (int)(rad*2), (int)(rad*2));
        page.drawOval((int)(c4.real()-rad), d1+d2-(int)(c4.imag()+rad), (int)(rad*2), (int)(rad*2));
        counter++;
        if (rad > 5)
        {
            drawMoreCircles(c4.real(), c4.imag(), cx1, cy1, cx2, cy2, rad, r1, r2, page);
            drawMoreCircles(c4.real(), c4.imag(), cx1, cy1, cx3, cy3, rad, r1, r3, page);
            drawMoreCircles(c4.real(), c4.imag(), cx2, cy2, cx3, cy3, rad, r2, r3, page);
        }
    }
    
}