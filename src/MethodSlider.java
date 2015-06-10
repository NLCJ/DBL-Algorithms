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
    private int pointsPlaced = 0;
    ExperimentOutput EO = ExperimentOutput.getExperimentOutput();
    long startTime;
    long endTime;
    long totalTime;
    String testType;
    
    /**
     * Return the result
     * @return
     */
    public Point[] getResult() {
        // Return the resutls
        return this.result;
    }
    
    public Point[] ShiftCalculator(int w, int h, Point[] p){
        // Sort the points
        Point[] sortedPoints = mergesort.sort( p );
        
        // Start at the right bottom with placeing labels - thus inverting the sorted array
        //Collections.reverse( Arrays.asList( sortedPoints ) );
        
        // Start placing the points
        placePoints( sortedPoints );
        findCollisions( sortedPoints );
        
        return originalOrder( sortedPoints );
    }
    
    public void placePoints( Point[] points ) {    
        // For each point
        startTime = System.nanoTime();
        for( int i = 0; i < points.length; i++ ) {
            // Create quad tree
            quadTree.insert( points[ i ] );
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        testType = "initialization";
        EO.quadTreeArrays(testType, totalTime);
    }
    
    public void findCollisions(Point[] points) {
        Map<Point, Set<Point>> collisions = new HashMap<Point, Set<Point>>();
        startTime = System.nanoTime();
        for (Point p : points) {
            // Get point collision
            List test = quadTree.retrieve( new ArrayList<Point>(), p );
            List potentialCollisions = collision.sliderCollisions( test, p );
            // Try to fix the collision for this point
            fixCollision( p, potentialCollisions );
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        testType = "retrieval";
        EO.quadTreeArrays(testType, totalTime);
        
        //System.out.println( "Points placed: " + pointsPlaced );
    }
    
    // Actually fix the collision
    public void fixCollision( Point point, List<Point> potentialCollisionPoints ) {
        // Check for each potential collision point if it is visible
        // - If visible, compare amount of visible and invisible points to the left and to the right
        // - Check if it fits between two visible points
        // - Place the label of current point - also update the visbility
        
        // Store the potential collision points for colors
        point.setPotentialCollision( potentialCollisionPoints.size() );
        
        // Define the variables needed
        double pointX = point.getX();
        double pointY = point.getY();
        Label currentLabel = point.getLabels().get( 0 );
        int visiblePointsLeft = 0;
        int visiblePointsRight = 0;
        int invisiblePointsLeft = 0;
        int invisiblePointsRight = 0;
        double closestLeft = 0;
        double closestRight = 0;
        boolean closestLeftSet = false;
        boolean closestRightSet = false;
        
        // For each potential collision point
        for ( Point potentialCollisionPoint : potentialCollisionPoints ) {
            // Define the variables needed
            double potentialCollisionPointX = potentialCollisionPoint.getX();
            double potentialCollisionPointY = potentialCollisionPoint.getY();
            Label potentialCollisionLabel = potentialCollisionPoint.getLabels().get( 0 );
            double potentialCollisionLabelStart = potentialCollisionLabel.getReference().getX();
            double potentialCollisionLabelEnd = potentialCollisionLabelStart + MainReader.width;
            double potentialCollisionLabelY = potentialCollisionLabel.getReference().getY();
            
            // Check if the potential collision label is visible - and update the range in which the current label can be placed
            if( potentialCollisionLabel.isVisible() ) {
                // The label is already visible
                
                // Check if the potential collision point is to the left or to the right of the point
                if( potentialCollisionPointX <= pointX ) {
                    // Point is to the left
                    
                    // Update the visible labels
                    visiblePointsLeft++;
                } else if( potentialCollisionPointX <= ( pointX + MainReader.width ) ) {
                    // Point is to the right and potential collision label is within reach
                    
                    // Update the visible labels
                    visiblePointsRight++;
                }
                
                // Check if label start is at the point
                if( potentialCollisionLabelEnd == pointX ) {
                    // Set the left side
                    closestLeftSet = true;
                    closestLeft = potentialCollisionLabelEnd;
                } else if( potentialCollisionLabelStart == pointX ) {
                    // Set the right side
                    closestRightSet = true;
                    closestRight = potentialCollisionLabelStart;
                } else if( potentialCollisionLabelStart < pointX && potentialCollisionLabelEnd > pointX ) {
                    // Set closestRight AND closestLeft
                    closestLeftSet = true;
                    closestLeft = potentialCollisionLabelStart;
                    closestRightSet = true;
                    closestRight = potentialCollisionLabelEnd;
                } else if( ( !closestRightSet || ( closestRightSet && potentialCollisionLabelStart < closestRight ) ) 
                        && potentialCollisionLabelStart > pointX && potentialCollisionLabelStart <= ( pointX + MainReader.width ) ) {
                    // Set closestRight
                    closestRightSet = true;
                    closestRight = potentialCollisionLabelStart;
                }else if( ( !closestLeftSet || ( closestLeftSet && potentialCollisionLabelEnd > closestLeft ) ) 
                        && potentialCollisionLabelEnd < pointX && ( potentialCollisionLabelEnd + MainReader.width ) >= pointX ) {
                    // Set the closestLeft
                    closestLeftSet = true;
                    closestLeft = potentialCollisionLabelEnd;
                }
                
                // Check if the label end is to the left or to the right
//                if( ( potentialCollisionLabelX + MainReader.width ) <= pointX ) {
//                    // Label is to the left of the point - thus closestLeft
//
//                    // Check if it is closer to the point than currently
//                    if( ( !closestLeftSet || ( closestLeftSet && ( potentialCollisionLabelX + MainReader.width ) > closestLeft ) ) ) {
//                        // Set the closestRight
//                        closestLeftSet = true;
//                        closestLeft = potentialCollisionLabelX + MainReader.width;
//                    }
//                } else {
//                    // Label is to the right of the point and within reach - thus closestRight
//
//                    // Check if it is closer to the point than currently
//                    if( ( !closestRightSet || ( closestRightSet && ( potentialCollisionLabelX + MainReader.width ) < closestRight ) ) ) {
//                        // Set the closestRight
//                        closestRightSet = true;
//                        closestRight = potentialCollisionLabelX;
//                    }
//                }
            } else {
                // The label is not visible yet
                
                // Check if the label was already placed as invisible (lower Y) - thus not relevant anymore
                if( potentialCollisionLabelY > pointY ) {
                    // Check if the label is to the left or to the right
                    if( potentialCollisionPointX <= pointX ) {
                        // Update the invisible points
                        invisiblePointsLeft++;
                    } else {
                        // Label is to the right - update invisible points
                        invisiblePointsRight++;
                    }
                 }
            }
        }
        
        // Add one to the points placed
        double shift = 1;
        this.pointsPlaced++;
        
        if( closestLeftSet && closestRightSet ) {
            // Check if there is enough space
            if( ( closestRight - closestLeft ) > MainReader.width ) {
                // If there is plenty space, check if it should be aligned most left or most right
                if( invisiblePointsRight >= invisiblePointsLeft ) {
                    // Place as far as possible to the left
                    shift = 1 - ( ( pointX - closestLeft ) / MainReader.width );
                    currentLabel.setShift( shift );
                } else {
                    // Place as far as possible to the right
                    shift = ( closestRight - pointX ) / MainReader.width;
                    currentLabel.setShift( shift );
                }
            } else {
                // There is no space available
                currentLabel.setShift( -1 );
                // Remove one
                this.pointsPlaced--;
            }
        } else if( !closestLeftSet && closestRightSet ) {
            if( invisiblePointsRight >= invisiblePointsLeft ) {
                // Shift as far as possible
                currentLabel.setShift( 0 );
            } else {
                // Set shift
                shift = ( closestRight - pointX ) / MainReader.width;
                currentLabel.setShift( shift );
            }
        } else if( closestLeftSet && !closestRightSet ) {
            if( invisiblePointsRight >= invisiblePointsLeft ) {
                // Shift as far as possible
                shift = 1 - ( ( pointX - closestLeft ) / MainReader.width );
                currentLabel.setShift( shift );
            } else {
                currentLabel.setShift( 1 );
            }
        } else {
            if( invisiblePointsRight >= invisiblePointsLeft ) {
                // If there are more points right, sacrifice left
                currentLabel.setShift( 0 );
            } else {
                // Does not matter - set shift to one
                currentLabel.setShift( 1 );
            }
        }
        
        //System.out.println( shift );
//        // Check if there has to be taken care of labels
//        if( !closestLeftSet && !closestRightSet ) {
//            // Set the current shift to 1
//            currentLabel.setShift( 1 );
//        } else if ( !closestLeftSet && closestRightSet ) {
//            // Place label as close as possible to the right
//            shift = ( closestRight - pointX ) / MainReader.width;
//            currentLabel.setShift( shift );
//        } else if ( closestLeftSet && !closestRightSet ) {
//            // Place label as most as possible to the right
//            currentLabel.setShift( 1 );
//        } else if ( ( closestRight - closestLeft ) <= MainReader.width ) {
//            // There is no space with the current labels placed - remove
//            shift = -1;
//            currentLabel.setShift( -1 );
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
        //System.out.println("placement model: " + s );
        //System.out.println("width: " + w );
        //System.out.println("height: " + h );
        //System.out.println("number of points: " + n_p );
        //System.out.println("number of labels: " + n_p );


        // Output each of the points
        for ( Point point : output ) {
            //System.out.println( (int) point.getX() + " " + (int) point.getY() + " " + point.getLabels().get(0).getShift() );
        }
    }
}
