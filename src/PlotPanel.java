
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

        for (Point point : pnt) {
            // Get the label
            Label label = point.getLabels().get( 0 );
            double shiftLabel = label.getShift();
            
            // Check what color it should be
            /*if( shiftLabel < 0 ) {
                // Set color to red
                g2d.setColor( Color.RED );
            } else {
                g2d.setColor( Color.BLACK );
            }*/
            
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
                    g2d.setColor( Color.MAGENTA );
                    break;
            }
            
            // Draw the rectangle
            double x = label.getReference().getX();
            double y = 10000 - label.getReference().getY();
            
            g2d.drawRect( (int) x, (int) y - height, width, height );
            
            // Draw the point
            int pointX = (int) label.getAnchor().getX();
            int pointY = 10000 - (int) label.getAnchor().getY();
            g2d.fillOval( pointX - 2, pointY - 2, 4, 4 );
            
            System.out.println( "Shift: " + shiftLabel );
            
//            if (pnt1.getLabels() == null || pnt1.getLabels().isEmpty()) {
//                continue;
//            }
//            for (Label l : pnt1.getLabels()) {
//                if (pnt1.getLabels().get(0).getShift() == -1) {
//                    g2d.setColor(Color.RED);
//                }
//                if (pnt1.getLabels().get(0).getShift() != -1) {
//                    g2d.setColor(Color.BLACK);
//                }
//                int x = (int) l.getReference().getX();
//                int y = 10000 - (int) l.getReference().getY();
//                g2d.drawRect(x, y - height, width, height);
//                System.out.println( "Shift" + l.getShift() );
//            }
//            int x = (int) pnt1.getLabels().get(0).getAnchor().getX();
//            int y = 10000 - (int) pnt1.getLabels().get(0).getAnchor().getY();
//            g2d.fillOval(x - 2, y - 2, 4, 4);
        }
    }
}
