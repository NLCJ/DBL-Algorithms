import java.awt.BorderLayout;
import java.awt.Color;
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
    
    
    String pos = null; 
    double shift = 0;
    
    public void Gui(String s, int h, int w, int n, Point[] pnt){
        int[][] x_p = new int[pnt.length][2];
        f = new JFrame("Label point plot: "+s+" model");
        f.setVisible(true);
        f.setSize(930, 970);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        plot = new PlotPanel(x_p, shift, pos);
        p.setLayout(new BorderLayout());
        p.setBackground(Color.YELLOW);
        l = new JLabel(s);
        p.add(l, BorderLayout.NORTH);
        f.add(p, BorderLayout.NORTH);
        f.add(plot, BorderLayout.CENTER);
        if(s.equals("2pos")){
            for(int i=0; i<pnt.length; i++){
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                pos = pnt[i].getPosition();
                
            }
        }
        
        if(s.equals("4pos")){
            for(int i=0; i<pnt.length; i++){
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                pos = pnt[i].getPosition();
                
            }
        }
        
        if(s.equals("1slider")){
            for(int i=0; i<pnt.length; i++){
                x_p[i][0] = pnt[i].getX();
                x_p[i][1] = pnt[i].getY();
                shift = pnt[i].getSlider();
                
            }
        }
        
    }
    
  
    
    void Reader() {
        try {
           
            

            File file = new File("data-of-awesomeness.txt");

            
            
            Scanner sc = new Scanner(file);
            
            // Get the model data
            String placement_model = sc.nextLine().substring(17);
            int width =  Integer.parseInt(sc.nextLine().substring(7));
            int height =  Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));
            
            // Create array for points
            Point[] points = new Point[ number_points ];
            // Place each point in the array
            for (int i = 0; i < number_points; i ++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                
                points[ i ] = new Point( x, y, height, width, i );
            }
            
            mergesort.sort(points);   
            
            // Determine what placement model is called for
            if(placement_model.equals("2pos")){
                Point[] points_2pos = pos_2.PositionCalculator(width, height, points); 
                pos_2.Output2Position(placement_model, width, height, number_points, points);
                Gui(placement_model, width, height, number_points, points_2pos);
                pos_2.Quadtreee(points);
                pos_2.makeLiterals(number_points);

            }
            if(placement_model.equals("4pos")){
                Point[] points_4pos = pos_4.PositionCalculator(width, height, points); 
                pos_4.Output4Position(placement_model, width, height, number_points, points);
                Gui(placement_model, width, height, number_points, points_4pos);
            }
            if(placement_model.equals("1slider")){
                Point[] points_slider = slider.ShiftCalculator(width, height, points); 
                slider.OutputSlider(placement_model, width, height, number_points, points);
                Gui(placement_model, width, height,  number_points,  points_slider);
            }
            
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void main(String[] args) {
        new MainReader().Reader();
    }

}
