/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
    MergeSort mergesort = new MergeSort();
    
    public Point[] ShiftCalculator(int w, int h, Point[] p){
            
            // Set the default slider position to 0
            for ( Point point : p ) {
                point.setSlider( 0 );
            }
        
            return originalOrder( p );
    }
    
    public Point[] originalOrder( Point[] p ) {
        // Original order output
        Point[] originalOrder = new Point[ p.length ];
        
        // For each point - place at the original position
        for ( Point point : p ) {
            originalOrder[ point.getOrigin() ] = point;
        }
        
        return originalOrder;
    }
    
    public void OutputSlider(String s, int w, int h, int n_p, Point[] p){
        // Reorder the points to the original order
        Point[] output = ShiftCalculator(w,h,p);
        
        // Required static output
        System.out.println("placement model: " + s );
        System.out.println("width: " + w );
        System.out.println("height: " + h );
        System.out.println("number of points: " + n_p );
        System.out.println("nubmers of labels: " + n_p );


        // Output each of the points
        for ( Point point : output ) {
            System.out.println( point.getX() + " " + point.getY() + " " + point.getSlider() );
        }
    }
}
