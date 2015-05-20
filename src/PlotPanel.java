
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
    int width;
    int height;
    String s;
    Point[] pnt;

    PlotPanel(int[][] x_p, double[] shift, String[] position, int width, int height, String s, Point[] pnt) {
        this.x_p = x_p;
        this.pnt = pnt;
        this.shift = shift;
        this.position = position;
        this.width = width;
        this.height = height;
        this.s = s;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        this.setBackground(Color.white);

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < pnt.length; i ++) {
            int x = (int)pnt[i].getX();
            int y = (int)pnt[i].getY();
            g2d.fillOval(x - 2, y - 2, 4, 4);

            if (s.equals("2pos")) {
                //if (!(pnt[i].getLabels().isEmpty())) {
                    if (pnt[i].getLabels().get(0).getPlacement().equals("NE")) {
                        System.out.println("THIS FUCKING VAR"+width);
                        g2d.drawRect(x, y - MainReader.width, MainReader.height, MainReader.width);
                    } else {
                        if (pnt[i].getLabels().get(0).getPlacement().equals("NW")) {
                            System.out.println("THIS FUCKING VAR"+width);
                            g2d.drawRect(x - MainReader.height, y - MainReader.width, MainReader.height, MainReader.width);
                        }

                    }
            }
            if (s.equals("4pos")) {
                if (pnt[i].getLabels().get(0).getPlacement().equals("NE")) {
                    g2d.drawRect(x, y - width, height, width);
                }
                if (pnt[i].getLabels().get(0).getPlacement().equals("NW")) {
                    g2d.drawRect(x - height, y - width, height, width);
                }
                if (pnt[i].getLabels().get(0).getPlacement().equals("SE")) {
                    g2d.drawRect(x, y, height, width);
                }
                if (pnt[i].getLabels().get(0).getPlacement().equals("SW")) {
                    g2d.drawRect(x - height, y, height, width);
                }
            }

            if (s.equals("1slider")) {
                System.out.println("THIS FUCKING VAR"+width);
                g2d.drawRect((int) (x - pnt[i].getLabels().get(0).getShift())*width, y - width, height, width);
            }
        }

    }
            }
