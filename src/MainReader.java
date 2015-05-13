
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Ivan Kozlov
 */
public class MainReader {

    Method4Pos pos_4 = new Method4Pos();
    Method2Pos pos_2 = new Method2Pos();
    MethodSlider slider = new MethodSlider();
    MergeSort mergesort = new MergeSort();
    private JFrame f;
    private JPanel p;
    private JLabel l;
    private JPanel plot;

    /*
     In order to view the panel correcly
     switch to full screen mode. 
     By default you will see small window
     which tells you what positioning model is used
     and message about going fullscreen 
     */
    public void Gui(String s, int h, int w, int n, Point[] pnt) {
        double[] shift = new double[pnt.length];
        int[][] x_p = new int[pnt.length][2];
        int width_ = w;
        int height_ = h;
        String[] pos_s = new String[pnt.length];
        String pos_ = s;
        f = new JFrame("Label point plot: " + s + " model");
        f.setVisible(true);
        f.setSize(500, 55);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        plot = new PlotPanel(x_p, shift, pos_s, width_, height_, pos_, pnt);
        plot.setSize(10000, 10000);
        p.setLayout(new BorderLayout());
        p.setBackground(Color.YELLOW);
        l = new JLabel(s + " go fullscreen");
        p.add(l, BorderLayout.NORTH);
        f.add(p, BorderLayout.NORTH);
        f.add(plot, BorderLayout.CENTER);
        JPanel test = new JPanel();
        plot.setPreferredSize(new Dimension(10000, 10000));
        JScrollPane scrollFrame = new JScrollPane(plot);
        plot.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(930, 970));
        f.add(scrollFrame);

        if (s.equals("2pos")) {
            for (int i = 0; i < pnt.length; i ++) {
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                pos_s[i] = pnt[i].getPosition();

            }
        }

        if (s.equals("4pos")) {
            for (int i = 0; i < pnt.length; i ++) {
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                pos_s[i] = pnt[i].getPosition();

            }
        }

        if (s.equals("1slider")) {
            for (int i = 0; i < pnt.length; i ++) {
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                shift[i] = pnt[i].getSlider();

            }
        }

    }

    void Reader() {
        try {

            File file = new File("data-of-awesomeness.txt");

            Scanner sc = new Scanner(file);

            // Get the model data
            String placement_model = sc.nextLine().substring(17);
            int width = Integer.parseInt(sc.nextLine().substring(7));
            int height = Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));

            // Create array for points
            Point[] points = new Point[number_points];
            // Place each point in the array
            for (int i = 0; i < number_points; i ++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                points[i] = new Point(x, y, height, width, i);
                
                // If slider - set the slider default to 1 and position to slider
                if( placement_model.equals( "1slider" ) ) {
                    points[i].setPosition( "slider" );
                    points[i].setSlider( 1 );
                }
            }

            mergesort.sort(points);

            // Determine what placement model is called for
            if (placement_model.equals("2pos")) {
                Point[] points_2pos = pos_2.PositionCalculator(width, height, points);
                pos_2.quadtreee(points);
                pos_2.searchClauses(points);
                //pos_2.makeLiterals();
                pos_2.Output2Position(placement_model, width, height, number_points, points);
                Gui(placement_model, width, height, number_points, points_2pos);

            }
            if (placement_model.equals("4pos")) {
                pos_4.Output4Position(placement_model, width, height, number_points, points);
                Point[] points_4pos = pos_4.getResult();
                Gui(placement_model, width, height, number_points, points_4pos);
            }
            if (placement_model.equals("1slider")) {
                slider.OutputSlider(placement_model, width, height, number_points, points);
                Point[] points_slider = slider.getResult();
                Gui(placement_model, width, height, number_points, points_slider);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new MainReader().Reader();
    }

}
