import java.awt.*;
import static javax.management.Query.div;
import javax.swing.*;
/**
 *
 * @author Ivan Kozlov
 */

public class PlotPanel extends JPanel {
    
    int[][] x_p ;
    double shift;
    String position;
    
     PlotPanel(int[][] x_p, double shift, String position){
        this.x_p = x_p;
        this.shift = shift;
        this.position = position;
    }
    
    @Override
    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
           super.paint(g2d);
           this.setBackground(Color.white);
           
          g2d.setColor(Color.BLACK);
          for(int i = 0; i<x_p.length;i++){
              int x = x_p[i][0]/5;
              int y = x_p[i][1]/5;
              g2d.fillOval(x,y,3,3);
          }
         
    }
    
    
}
