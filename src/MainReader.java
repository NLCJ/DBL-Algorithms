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
    
    public void Gui(){
        f = new JFrame("Label point plot");
        f.setVisible(true);
        f.setSize(800,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        p.setBackground(Color.WHITE);
    }
    
    
    
    void Reader() {
        try {
           
            
            File file = new File("C:\\Users\\Ivan Kozlov\\Documents\\GitHub\\Peach-is-sooo-sorry\\src\\input.txt");
          
            
            
            Scanner sc = new Scanner(file);
            
            // Get the model data
            String placement_model = sc.nextLine().substring(17);
            int width =  Integer.parseInt(sc.nextLine().substring(7));
            int height =  Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));
            
            // Create array for points
            Point[] points = new Point[ number_points ];
            Gui();
            // Place each point in the array
            for (int i = 0; i < number_points; i ++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                
                points[ i ] = new Point( x, y, height, width, i );
            }
            
            mergesort.sort(points);
            for (Point point : points) {
            System.out.println(point.getX() + " " + point.getY() + " " + point.getPosition());
        }
            // Determine what placement model is called for
            if(placement_model.equals("2pos")){
                pos_2.Output2Position(placement_model, width, height, number_points, points);
            }
            if(placement_model.equals("4pos")){
                pos_4.Output4Position(placement_model, width, height, number_points, points);
            }
            if(placement_model.equals("1slider")){
                slider.OutputSlider(placement_model, width, height, number_points, points);
            }
            
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new MainReader().Reader();
    }

}
