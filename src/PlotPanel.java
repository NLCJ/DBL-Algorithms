
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Ivan Kozlov
 */
public class PlotPanel extends JPanel {

    int[][] x_p;
    double[] shift;
    String[] position;
    int width = MainReader.width;
    int height = MainReader.height;
    String s;
    Point[] pnt;

    PlotPanel(int[][] x_p, double[] shift, String[] position, String s, Point[] pnt) {
        this.x_p = x_p;
        this.pnt = pnt;
        this.shift = shift;
        this.position = position;
        this.s = s;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        this.setBackground(Color.white);

       
        if(s.equals("1slider")){
        for (Point point : pnt) {
            // Get the label
            Label label = point.getLabels().get( 0 );
            double shiftLabel = label.getShift();
            
            
            int potentialCollisions = point.getPotentialCollision();
            
            switch( potentialCollisions ) {
                case 0: 
                    g2d.setColor( Color.BLACK );
                    break;
                case 1:
                    g2d.setColor( Color.BLUE );
                    break;
                case 2:
                    g2d.setColor( Color.GREEN );
                    break;
                case 3:
                    g2d.setColor( Color.ORANGE );
                    break;
                default:
                    g2d.setColor( Color.RED );
                    break;
            }
            
            // Draw the rectangle
            double x = label.getReference().getX();
            double y = 10000 - label.getReference().getY();
            
            if( shiftLabel < 0 ) {
                g2d.drawRect( (int) x, (int) y - height, width, height );
            } else {
                g2d.fillRect( (int) x, (int) y - height, width, height );
            }
            
            // Draw the point
            int pointX = (int) label.getAnchor().getX();
            int pointY = 10000 - (int) label.getAnchor().getY();
            g2d.fillOval( pointX - 2, pointY - 2, 4, 4 );
        }
        }
        if(s.equals("2pos")){
              for (Point point : pnt) {
            // Get the label

                  if(! point.getLabels().isEmpty()){

            if(!point.getLabels().isEmpty() || point.getLabels() != null){

            Label label = point.getLabels().get( 0 );
            Placement label_place = label.getPlacement();
            int potentialCollisions = point.getPotentialCollision();
            Placement[] place_var = Placement.twoPos();
            
            switch( potentialCollisions ) {
                case 0: 
                    g2d.setColor( Color.BLACK );
                    break;
                case 1:
                    g2d.setColor( Color.BLUE );
                    break;
                case 2:
                    g2d.setColor( Color.GREEN );
                    break;
                case 3:
                    g2d.setColor( Color.ORANGE );
                    break;
                default:
                    g2d.setColor( Color.RED );
                    break;
            }
            
            // Draw the rectangle
            double x = label.getReference().getX();
            double y = 10000 - label.getReference().getY();
            
            if( label_place == place_var[0] ) {
                g2d.fillRect( (int) x, (int) y - height, width, height );
            }
            if( label_place == place_var[1] ) {
                g2d.fillRect( (int) x-width, (int) y - height, width, height );
            }
            
            // Draw the point
            
            g2d.fillOval( (int)x - 2, (int)y - 2, 4, 4 );
        }
              }
        }
        }
        if(s.equals("4pos")){
             for (Point point : pnt) {
                 //System.out.println(point.getLabels()!=null);
            // Get the label

    

           if(! point.getLabels().isEmpty()){

            Label label = point.getLabels().get(0);
            Placement label_place = label.getPlacement();
            int potentialCollisions = point.getPotentialCollision();
            Placement[] place_var = Placement.fourPos();
            
            switch( potentialCollisions ) {
                case 0: 
                    g2d.setColor( Color.BLACK );
                    break;
                case 1:
                    g2d.setColor( Color.BLUE );
                    break;
                case 2:
                    g2d.setColor( Color.GREEN );
                    break;
                case 3:
                    g2d.setColor( Color.ORANGE );
                    break;
                default:
                    g2d.setColor( Color.RED );
                    break;
            }
            
            // Draw the rectangle
            double x = label.getReference().getX();
            double y = 10000 - label.getReference().getY();
            
            if( label_place == place_var[0] ) {
                g2d.fillRect( (int) x, (int) y - height, width, height );
            }
            if( label_place == place_var[2] ) {
                g2d.fillRect( (int) x-width, (int) y - height, width, height );
            }
            if( label_place == place_var[1] ) {
                g2d.fillRect( (int) x, (int) y, width, height );
            }
            if( label_place == place_var[3] ) {
                g2d.fillRect( (int) x-width, (int) y, width, height );
            } 
            g2d.fillOval( (int)x - 2, (int)y - 2, 4, 4 );
           }
        }
             for (Point point : pnt) {
            // Get the label
           
            Point label_points_only = point;
            double x = label_points_only.getX();
            double y = 10000 - label_points_only.getY();
            g2d.fillOval( (int)x - 2, (int)y - 2, 4, 4 );
            
           }
        }
           
        }
    }

