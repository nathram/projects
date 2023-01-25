import java.awt.*;
    import java.util.*;
    
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import javax.swing.JComponent;
/**
 * Write a description of class testing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testing
{
    public static void main(String[] args)
    {
        ArrayList<int[]> blocks = new ArrayList<int[]>();
        blocks.add(new int[] {1, 2, 3});
        blocks.add(new int[] {4, 5, 6});
        blocks.set(1, new int[] {1, 2, 3});
        System.out.println(blocks);
    }
}
