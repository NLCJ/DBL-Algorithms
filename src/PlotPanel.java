
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

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < pnt.length; i ++) {

            if (pnt[i].getLabels() == null || pnt[i].getLabels().isEmpty()) {
                continue;
            }
            for (Label l : pnt[i].getLabels()) {
                int x = (int) l.getReference().getX();
                int y = 10000 - (int) l.getReference().getY();
                g2d.drawRect(x, y - height, height, width);
            }

            int x = (int) pnt[i].getLabels().get(0).getAnchor().getX();
            int y = 10000 - (int) pnt[i].getLabels().get(0).getAnchor().getY();
            g2d.fillOval(x - 2, y - 2, 4, 4);

        }
    }
}
