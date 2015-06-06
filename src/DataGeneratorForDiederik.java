
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
    int amountOfPoints;
    int minAxisX/* = 0*/;
    int minAxisY/* = 0*/;
    int maxAxisX/* = 10000*/;
    int maxAxisY/* = 10000*/;
    int minX;
    int maxX;
    int minY;
    int maxY;
    double accuracy;
    PrintWriter file;
    Scanner input;
    String distribution;
    
    // Constructor
    private void askData() {
        try {
            // Try to get input from the user
            input = new Scanner( System.in ).useLocale( Locale.US );
            for (int i = 0; i < 100; i++){
            // Store the variables
            this.placementModel = "2pos";
            this.distribution = "Cluster";
            this.accuracy = 100;
            this.labelWidth = 10;
            this.labelHeight = 10;
            this.amountOfPoints = 10;
            this.minAxisX = 0;
            this.minAxisY = 0;
            this.maxAxisX = 10000;
            this.maxAxisY = 10000;
            this.minX = 0;
            this.maxX = 10000;
            this.minY = 0;
            this.maxY = 10000;
            
            // Initialize the file
            file = new PrintWriter("Experimental Data\\Cluster\\"+placementModel+distribution+labelWidth+"x"+amountOfPoints+"#"+(i+1)+".txt", "UTF-8");
            
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
        // For each point
        for( int i = 0; i < amountOfPoints; i++ ) {
            // Generate within limits given
            int accurateMinX = this.minX;
            int accurateMinY = this.minY;
            int accurateMaxX = this.maxX;
            int accurateMaxY = this.maxY;
                
            // Create random to test against accuracy
            if( randInt( 0, 100 ) > accuracy ) {
                // Random over whole field
                accurateMinX = this.minAxisX;
                accurateMinY = this.minAxisY;
                accurateMaxX = this.maxAxisX;
                accurateMaxY = this.maxAxisY;
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
