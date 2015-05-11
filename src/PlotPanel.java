import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Ivan Kozlov
 */

public class PlotPanel extends JPanel {
    
    int[][] x_p ;
    double[] shift;
    String[] position;
    int width;
    int height;
    String s;
    Point[] pnt;
    
     PlotPanel(int[][] x_p, double[] shift, String[] position, int width, int height, String s, Point[] pnt){
        this.x_p = x_p;
        this.pnt = pnt;
        this.shift = shift;
        this.position = position;
        this.width = width;
        this.height = height;
        this.s = s;
        
    }
    
    @Override
    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
           super.paint(g2d);
           this.setBackground(Color.white);
           
          g2d.setColor(Color.BLACK);
          for(int i = 0; i<x_p.length;i++){
              int x = x_p[i][0];
              int y = x_p[i][1];
              g2d.fillOval(x-2,y-2,4,4);
           
             if(s.equals("2pos")){
                 if(pnt[i].getPosition().equals("NE")){
                    g2d.drawRect(x, y-width, height,  width);
                 }else{
                     if(pnt[i].getPosition().equals("NW")){
                       g2d.drawRect(x-height, y-width, height,  width);  
                        }
                     }
                 }
             if(s.equals("4pos")){
                 if(pnt[i].getPosition().equals("NE")){
                    g2d.drawRect(x, y-width, height,  width);
                 }
                  if(pnt[i].getPosition().equals("NW")){
                    g2d.drawRect(x-height, y-width, height,  width);  
                   }
                  if(pnt[i].getPosition().equals("SE")){
                    g2d.drawRect(x, y, height,  width);  
                   }
                  if(pnt[i].getPosition().equals("SW")){
                    g2d.drawRect(x-height, y, height,  width);  
                   }
            }
                 
          if(s.equals("1slider")){
                    g2d.drawRect((int) (x-pnt[i].getSlider()), y-width, height,  width);
                 }
    }
    
    
}}
