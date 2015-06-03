
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s124006
 */
public class BruteForce {
    
    private int topLabelsPlaced = 0;
    
    private Collision collision = new Collision();
    private QuadTree quadTree = new QuadTree( 0, 0, 10000, 0, 10000 );
    
    private void Forceeeeee() {
        ArrayList<Point> points = new ArrayList<>();
        
        // Start the for each loop, yeahh!!!!!! For each point, YEAHHH!!!!!!
        for( int i = 0; i < points.size(); i++ ) {
            moreForEachLoopsYEAAAHHH( 0, points, 0 );
        }
    }
    
    private void moreForEachLoopsYEAAAHHH( int currentIndex, ArrayList<Point> remainingPoints, int pointsPlaced ) {
        // The current point
        Point point = remainingPoints.get( currentIndex );
        double pointX = point.getX();
        Label label = point.getLabels().get( 0 );
        double labelStart = label.getReference().getX();
        double labelEnd = label.getReference().getY() + MainReader.width;
        
        // Set the shift
        double shift = 1;
        
        // Get the potential collisions
        List test = quadTree.retrieve( new ArrayList<Point>(), point );
        List<Point> potentialCollisions = collision.sliderCollisions( test, point );
        
        // Determine the closestLeft and closestRight
        double closestLeft = 0;
        double closestRight = 0;
        
        for( Point potentialCollision : potentialCollisions ) {
            // The label
            Label collisionLabel = potentialCollision.getLabels().get( 0 );
            double collisionLabelStart = collisionLabel.getReference().getX();
            double collisionLabelEnd = collisionLabel.getReference().getX() + MainReader.width;
            
            // Check if the label is visible
            if( collisionLabel.isVisible() ) {
                if( collisionLabelStart < pointX ) {
                    closestLeft = collisionLabelStart;
                }
            }
        }
        
        // We have to wiggle first - then call the loop
        while( shift > 0 ) {
            // Check if this label can be placed at the CURRENT position
            
            
            // Decrease the shift by 1 percent
            shift = shift - 0.01;
        }
        
        // Remove the current index
        remainingPoints.remove( currentIndex );
        
        // MORE FOR EACH LOOPS - WHICH ARE ACTUALLY FOR LOOPS
        for( int i = 0; i < remainingPoints.size(); i++ ) {
            moreForEachLoopsYEAAAHHH( i, remainingPoints, pointsPlaced );
        }
    }
    
    private void startWiggling( Point point, Point[] remainingPoints ) {
        // Set the shift to 1 be default
        double shift = 1;
        
        // Get point collision
        
        
        // As long as shift is not 0
        while( shift > 0 ) {
            // For each collision
            for( Point potentialCollision : potentialCollisions ) {
                
            }
            
            // Decrease the shift by 1%
            shift = shift - 0.01;
        }
    }
    
    public static void main(String[] args) {
        new BruteForce().Forceeeeee();
    }
}
