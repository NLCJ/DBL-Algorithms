import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
    MergeSort mergesort = new MergeSort();
    private Point[] result;
    private Collision collision = new Collision();
    
    /**
     * Return the result
     * @return
     */
    public Point[] getResult() {
        // Return the resutls
        return this.result;
    }
    
    public Point[] ShiftCalculator(int w, int h, Point[] p){
        
        // Start at the right bottom with placeing labels - thus inverting the sorted array
        Collections.reverse( Arrays.asList(p) );
            
        // Set the default slider position to 0
//        for ( Point point : p ) {
//            point.setShift( 0 );
//        }
        
        // Start placing the points
        placePoints( p );

        return originalOrder( p );
    }
    
    public void placePoints( Point[] points ) {
        // For each point
        for( int i = 1; i < points.length; i++ ) {
            // Fix the collision - if any
            fixCollision( points[ i ], points[ i - 1 ], 0.50, false );
        }
    }
    
    public void fixCollision( Point newPoint, Point oldPoint, double step, boolean die ) {
        // Check if collision
//        if( this.collision.collide( newPoint, oldPoint ) ) {
//            newPoint.setSlider( newPoint.getSlider() - step );
//            
//            if( die ) {
//                return;
//            }
//            
//            // Check if step reached maximum
//            if( Double.compare( step, 0.0078125 ) == 0 ) {
//                // Check if the slider is at the mostleft
//                if( newPoint.getSlider() == 0 ) {
//                    // Remove the slider position
//                    newPoint.setSlider( -1 );      
//                } else {
//                    fixCollision( newPoint, oldPoint, 0.0078125, true );
//                }
//                return;
//            } else {
//                // Move slider again
//                fixCollision( newPoint, oldPoint, step / 2, false );
//            }
//        } else {
//            newPoint.setSlider( newPoint.getSlider() + step );
//            
//            // Check if step reached maximum
//            if( Double.compare( step, 0.0078125 ) == 0 ) {
//                // Check if I die
//                if( !die ) {
//                    fixCollision( newPoint, oldPoint, 0.0078125, true );
//                }
//            } else {
//                // Move slider again
//                fixCollision( newPoint, oldPoint, step / 2, false );
//            }
//        }
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
        
        // Store the output for future reference
        this.result = output;
        
        // Required static output
        System.out.println("placement model: " + s );
        System.out.println("width: " + w );
        System.out.println("height: " + h );
        System.out.println("number of points: " + n_p );
        System.out.println("nubmers of labels: " + n_p );


        // Output each of the points
        for ( Point point : output ) {
            System.out.println( (int) point.getX() + " " + (int) point.getY() + " " + point.getLabels().get(0).getShift() );
        }
    }
}
