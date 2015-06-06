
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s124006
 */
public class DataGeneratorForDiederik {
    // Variables
    String placementModel;
    int labelWidth;
    int labelHeight;
    int amountOfPointsLeft;
    int minAxisXLeft/* = 0*/;
    int minAxisYLeft/* = 0*/;
    int maxAxisXLeft/* = 10000*/;
    int maxAxisYLeft/* = 10000*/;
    int minXLeft;
    int maxXLeft;
    int minYLeft;
    int maxYLeft;
    int amountOfPointsRight;
    int minAxisXRight/* = 0*/;
    int minAxisYRight/* = 0*/;
    int maxAxisXRight/* = 10000*/;
    int maxAxisYRight/* = 10000*/;
    int minXRight;
    int maxXRight;
    int minYRight;
    int maxYRight;
    double accuracy;
    PrintWriter file;
    Scanner input;
    String distribution;
    int amountOfPoints;
    
    // Constructor
    private void askData() {
        try {
            // Try to get input from the user
            input = new Scanner( System.in ).useLocale( Locale.US );
            for (int i = 0; i < 100; i++){
            // Store the variables
            this.placementModel = "1slider";
            this.distribution = "Cluster";
            this.accuracy = 50;
            this.labelWidth = 200;
            this.labelHeight = 200;
            //Half of the total points you want
            this.amountOfPointsLeft = 5000;
            //Definition of the Left half
            this.minAxisXLeft = 0;
            this.minAxisYLeft = 0;
            this.maxAxisXLeft = 5000;
            this.maxAxisYLeft = 10000;
            //The position of the left cluster
            this.minXLeft = 0;
            this.maxXLeft = 2000;
            this.minYLeft = 8000;
            this.maxYLeft = 10000;
            //Half of the total points you want
            this.amountOfPointsRight = 5000;
            //Definition of the Right half
            this.minAxisXRight = 5000;
            this.minAxisYRight = 0;
            this.maxAxisXRight = 5000;
            this.maxAxisYRight = 10000;
            //The position of the right cluster
            this.minXRight = 8000;
            this.maxXRight = 10000;
            this.minYRight = 0;
            this.maxYRight = 2000;
            this.amountOfPoints = amountOfPointsLeft+amountOfPointsRight;
            // Initialize the file
            file = new PrintWriter("Experimental Data\\"+placementModel+distribution+labelWidth+"x"+amountOfPoints+"#"+(i+1)+".txt", "UTF-8");
            
            // Print the default information
            file.println( "placement model: " + this.placementModel );
            file.println( "width: " + this.labelWidth );
            file.println( "height: " + this.labelHeight );
            file.println( "number of points: " + this.amountOfPoints );
            
            // Add output
            output();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Generate the output
    private void output() {
        // For each point on the left side of the data
        for( int i = 0; i < amountOfPointsLeft; i++ ) {
            // Generate within limits given
            int accurateMinX = this.minXLeft;
            int accurateMinY = this.minYLeft;
            int accurateMaxX = this.maxXLeft;
            int accurateMaxY = this.maxYLeft;
                
            // Create random to test against accuracy
            if( randInt( 0, 100 ) > accuracy ) {
                // Random over whole field
                accurateMinX = this.minAxisXLeft;
                accurateMinY = this.minAxisYLeft;
                accurateMaxX = this.maxAxisXLeft;
                accurateMaxY = this.maxAxisYLeft;
            }
            
            // Get the x and y value
            int xValue = randInt( accurateMinX, accurateMaxX );
            int yValue = randInt( accurateMinY, accurateMaxY );
            
            // Write this point to the file
            file.println( xValue + " " + yValue );
        }
        // For each point on the right side of the data
        for( int i = 0; i < amountOfPointsRight; i++ ) {
            // Generate within limits given
            int accurateMinX = this.minXRight;
            int accurateMinY = this.minYRight;
            int accurateMaxX = this.maxXRight;
            int accurateMaxY = this.maxYRight;
                
            // Create random to test against accuracy
            if( randInt( 0, 100 ) > accuracy ) {
                // Random over whole field
                accurateMinX = this.minAxisXRight;
                accurateMinY = this.minAxisYRight;
                accurateMaxX = this.maxAxisXRight;
                accurateMaxY = this.maxAxisYRight;
            }
            
            // Get the x and y value
            int xValue = randInt( accurateMinX, accurateMaxX );
            int yValue = randInt( accurateMinY, accurateMaxY );
            
            // Write this point to the file
            file.println( xValue + " " + yValue );
        }
        
        // Close the file
        file.close();
    }
    
    /**
    * Returns a pseudo-random number between min and max, inclusive.
    * The difference between min and max can be at most
    * <code>Integer.MAX_VALUE - 1</code>.
    *
    * @param min Minimum value
    * @param max Maximum value.  Must be greater than min.
    * @return Integer between min and max, inclusive.
    * @see java.util.Random#nextInt(int)
    */
    public static int randInt(int min, int max) {

       // NOTE: Usually this should be a field rather than a method
       // variable so that it is not re-seeded every call.
       Random rand = new Random();

       // nextInt is normally exclusive of the top value,
       // so add 1 to make it inclusive
       int randomNum = rand.nextInt((max - min) + 1) + min;

       return randomNum;
    }
   
    public static void main(String[] args) {
        new DataGeneratorForDiederik().askData();
    }
}
