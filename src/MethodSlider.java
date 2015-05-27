import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
    MergeSort mergesort = new MergeSort();
    private Point[] result;
    private Collision collision = new Collision();
    private QuadTree quadTree = new QuadTree( 0, 0, 10000, 0, 10000 );
    
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
        
        // Start placing the points
        placePoints( p );
        findCollisions( p );

        return originalOrder( p );
    }
    
    public void placePoints( Point[] points ) {    
        // For each point
        for( int i = 0; i < points.length; i++ ) {
            // Create quad tree
            quadTree.insert( points[ i ] );
        }
    }
    
    public void findCollisions(Point[] points) {
        Map<Point, Set<Point>> collisions = new HashMap<Point, Set<Point>>();
        for (Point p : points) {
            // Get point collision
            List test = quadTree.retrieve( new ArrayList<Point>(), p );
            List potentialCollisions = collision.sliderCollisions( test, p );
            
            // Try to fix the collision for this point
            fixCollision( p, potentialCollisions );
        }
    }
    
    // Actually fix the collision
    public void fixCollision( Point point, List<Point> potentialCollisionPoints ) {
        // Store the potential collision points
        point.setPotentialCollision( potentialCollisionPoints.size() );
        
        // Active point info
        double activePointX = point.getX();
        double activePointY = point.getY();
        
        // Keep track of how many labels are on the left and right
        int pointsLeftLabel = 0;
        int pointsRightLabel = 0;
        
        // Current label conditions
        double labelX = point.getLabels().get( 0 ).getReference().getX();
        double labelY = point.getLabels().get( 0 ).getReference().getY();
        
        double leftMostLabelRightOfPointX = 0;
        double rightMostLabelLeftOfPointX = 0;
        
        // For each point determine where
        for( Point potentialCollisionPoint : potentialCollisionPoints ) {
            double potentialCollisionPointX = potentialCollisionPoint.getLabels().get( 0 ).getReference().getX();
            
            // Check if that point is to the left AND within the label width
            if( potentialCollisionPointX < activePointX  ) {
                // Point is within reach of the most left label
                pointsRightLabel++;
                
                // Update the right most label X
                if( ( potentialCollisionPointX > rightMostLabelLeftOfPointX || rightMostLabelLeftOfPointX == 0 ) &&
                        ( ( potentialCollisionPointX + MainReader.width ) > activePointX ) ) {
                    rightMostLabelLeftOfPointX = potentialCollisionPoint.getLabels().get( 0 ).getReference().getX() + MainReader.width;
                }
            } else if( potentialCollisionPointX > activePointX ) {
                // Point is within reach of the most right label
                pointsLeftLabel++;
                
                // Update the left X of the label if it is within reach
                if( ( potentialCollisionPointX < leftMostLabelRightOfPointX || leftMostLabelRightOfPointX == 0 ) && 
                        ( potentialCollisionPointX < ( activePointX + MainReader.width ) ) ) {
                    leftMostLabelRightOfPointX = potentialCollisionPoint.getLabels().get( 0 ).getReference().getX();
                }
            }
        }
        
        // Check if there can be a label placed between two points
        if( ( pointsLeftLabel + pointsRightLabel ) >= 2 && ( leftMostLabelRightOfPointX - rightMostLabelLeftOfPointX ) < MainReader.width ) {
            // Set the shift to impossible
            point.getLabels().get( 0 ).setShift( -1 );
        } else if ( pointsRightLabel > pointsLeftLabel && rightMostLabelLeftOfPointX > 0 && rightMostLabelLeftOfPointX < activePointX ) {
            double shift = ( activePointX - rightMostLabelLeftOfPointX ) / MainReader.width;
            // Set the label
            point.getLabels().get( 0 ).setShift( shift );
        } else if ( pointsRightLabel <= pointsLeftLabel && leftMostLabelRightOfPointX > 0 && leftMostLabelRightOfPointX > activePointX ) {
            double shift = ( leftMostLabelRightOfPointX - activePointX ) / MainReader.width;
            // Set the label
            point.getLabels().get( 0 ).setShift( shift );
        }
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
        System.out.println("number of labels: " + n_p );


        // Output each of the points
        for ( Point point : output ) {
            //System.out.println( (int) point.getX() + " " + (int) point.getY() + " " + point.getLabels().get(0).getShift() );
        }
    }
}
