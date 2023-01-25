 
 
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
 
/**
 *
 * @author javapointers
 */
public class JButtonTest implements ActionListener {
    JFrame frame;
    JButton button;
     
    public JButtonTest() {
        frame = new JFrame();
        frame.setTitle("My JFrame");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        button = new JButton("Click Me!");
        button.addActionListener(this);
         
        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.setVisible(true);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("You have clicked the button");
    }
     
    public static void main(String args[]){
        JButtonTest test = new JButtonTest();
    }
}