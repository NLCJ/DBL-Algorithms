
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author Ivan Kozlov
 */
public class MainReader {
    Method4Pos pos_4 = new Method4Pos();
    Method2Pos pos_2 = new Method2Pos();
    MethodSlider slider = new MethodSlider();
    
    void Reader() {
       // try {
           
            
            //File file = new File("C:\\Users\\Ivan Kozlov\\Documents\\NetBeansProjects\\DBL_Algorithms\\src\\dbl_algorithms\\input.txt");
          
            
            
            Scanner sc = new Scanner(System.in);
            
            String placement_model = sc.nextLine().substring(17);
            int width =  Integer.parseInt(sc.nextLine().substring(7));
            int height =  Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));
            int[][] points = new int[number_points][2];
            
            for (int i = 0; i < number_points; i ++) {
                points[i][0] = sc.nextInt();
                points[i][1] = sc.nextInt();
            }
            
            
            
            System.out.println("placement model: "+ placement_model);
            System.out.println("width: "+width);
            System.out.println("height: "+height);
            System.out.println("number of points: "+number_points);
            System.out.println("nubmers of labels: "+number_points);
            for(int i=0;i<number_points;i++){
                System.out.println(points[i][0]+" "+points[i][1]+" NE");
            }
            
        //} catch (FileNotFoundException ex) {
          //  Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }

    public static void main(String[] args) {
        new MainReader().Reader();
    }

}
