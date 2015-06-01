
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
    private int labelsPlaced = 0;
    
    private Collision collision = new Collision();
    private QuadTree quadTree = new QuadTree( 0, 0, 10000, 0, 10000 );
    
    private int closestLeft;
    private int closestRight;
    
    private void Forceeeeee() {
        Point[] points = null;
        // For each point - calculate every other position
        for( Point point : points ) {
            // Call the function to shift it by 1%
            startWiggling( point, points );
        }
    }
    
    private void startWiggling( Point point, Point[] remainingPoints ) {
        // Set the shift to 1 be default
        double shift = 1;
        
        // Get point collision
        List test = quadTree.retrieve( new ArrayList<Point>(), point );
        List potentialCollisions = collision.sliderCollisions( test, point );
        
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
