
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ivan Kovlov
 */
class MainReader {

    public static int width;
    public static int height;
    public static int numberLabels;

    public MainReader() {

    }

    Method2Pos pos_2 = new Method2Pos();
    Method4Pos pos_4 = new Method4Pos();
    MethodSlider slider = new MethodSlider();
    MergeSort mergesort = new MergeSort();
    public static Point[] points;
    public static Model pModel;

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
    public void Gui(String s, int n, Point[] pnt) {
        double[] shift = new double[pnt.length];
        int[][] x_p = new int[pnt.length][2];
        String[] pos_s = new String[pnt.length];
        String pos_ = s;
        f = new JFrame("Label point plot: " + s + " model");
        f.setVisible(true);
        f.setSize(500, 55);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setExtendedState(Frame.MAXIMIZED_BOTH);
        p = new JPanel();
        plot = new PlotPanel(x_p, shift, pos_s, pos_, pnt);
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
                x_p[i][0] = (int) pnt[i].getX();
                x_p[i][1] = (int) pnt[i].getY();

                if ( ! (pnt[i].getLabels().isEmpty())) {
                    pos_s[i] = pnt[i].getLabels().get(0).getPlacement().toString();
                }
            }
        }
    }

    void Reader() {
        //  System.out.println("Reading file");
        try {

            File file = new File("data-of-awesomeness.txt");
file = new File("input.txt");
            Scanner sc = new Scanner(file);

            // Get the model data
            String placement_model = sc.nextLine().substring(17);
            pModel = Model.fromString(placement_model);
            width = Integer.parseInt(sc.nextLine().substring(7));
            height = Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));
            numberLabels = number_points;

            // Create array for points
            points = new Point[number_points];
            // Place each point in the array
            for (int i = 0; i < number_points; i ++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                points[i] = new Point(x, y, i, Model.fromString(placement_model));
            }

            // Determine what placement model is called for
            if (placement_model.equals("2pos")) {
                Point[] points_2pos = pos_2.PositionCalculator(width, height, points);
                pos_2.quadtree(points);
                pos_2.findCollisions(points);
                //pos_2.searchClauses(points);
                //pos_2.makeLiterals();
                pos_2.Output2Position(placement_model, width, height, number_points, points);
                Gui(placement_model, number_points, points_2pos);
            }
            if (placement_model.equals("4pos")) {
                Point[] points_4pos = pos_4.PositionCalculator(width, height, points);
                pos_4.Output4Position(placement_model, width, height, number_points, points_4pos);
                Gui(placement_model, number_points, points_4pos);
            }
            if (placement_model.equals("1slider")) {
                Point[] points_slider = slider.originalOrder(points);
                slider.OutputSlider(placement_model, width, height, number_points, points_slider);
                Gui(placement_model, number_points, points_slider);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new MainReader().Reader();
    }
}
