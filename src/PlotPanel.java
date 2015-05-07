import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
/**
 *
 * @author Ivan Kozlov
 */

public class PlotPanel extends JPanel {
    
    int[][] x_p ;
    int shift;
    String position;
    
    void PlotPanel(int[][] x_p, int shift, String position){
        this.x_p = x_p;
        this.shift = shift;
        this.position = position;
    }
    
    @Override
    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
           super.paint(g2d);
           this.setBackground(Color.red);
           
           g2d.setColor(Color.WHITE);
           g2d.fillOval(69, 69, 50, 50);
           g2d.drawString("KURWA", 500, 500);
      
    }
    
    
}
