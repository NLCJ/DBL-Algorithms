/**
 *
 * @author Ivan Kozlov
 */
public class Method4Pos  {
   MergeSort mergesort = new MergeSort();
   
    public void Output4Pos(String s, int w, int h, int n_p, int[][] p){
        // Reorder the points to the original order
        // Yo, get your sorting shit together here.
        // CALL IT; process it, and order it 
        // See MethodSlider and then a submethod something for more info
        // P.s., before I forget, call it output and make it a Point[]
        
        // Required static output
        System.out.println("placement model: " + s );
        System.out.println("width: " + w );
        System.out.println("height: " + h );
        System.out.println("number of points: " + n_p );
        System.out.println("nubmers of labels: " + n_p );


        // Output each of the points
        for ( Point point : output ) {
            System.out.println( point.getX() + " " + point.getY() + " " + point.getPosition() );
        }
    }
    
}
