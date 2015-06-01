
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
public class DataGenerator {
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
    
    // Constructor
    private void askData() {
        try {
            // Try to get input from the user
            input = new Scanner( System.in ).useLocale( Locale.US );
            
            // Store the variables
            System.out.print("Enter the placement model (2pos, 4pos, 1slider): ");
            this.placementModel = "2pos";//input.nextLine();
            System.out.print("Enter the width of the label: ");
            this.labelWidth = input.nextInt();
            System.out.print("Enter the height of the label: ");
            this.labelHeight = input.nextInt();
            System.out.print("Enter the amount of points: ");
            this.amountOfPoints = input.nextInt();
            System.out.print("Minimum x-axis (standard is 0): ");
            this.minAxisX = input.nextInt();
            System.out.print("Minimum y-axis (standard is 0): ");
            this.minAxisY = input.nextInt();
            System.out.print("Maximum x-axis (standard is 10k): ");
            this.maxAxisX = input.nextInt();
            System.out.print("Maximum y-axis (standard is 10k): ");
            this.maxAxisY = input.nextInt();
            System.out.print("Enter the minimum integer on the x-axis: ");
            this.minX = input.nextInt();
            System.out.print("Enter the maximum integer on the x-axis: ");
            this.maxX = input.nextInt();
            System.out.print("Enter the minimum integer on the y-axis: ");
            this.minY = input.nextInt();
            System.out.print("Enter the maximum integer on the y-axis: ");
            this.maxY = input.nextInt();
            System.out.print("Enter the accuracy (0 to 100)%: ");
            this.accuracy = input.nextDouble();
            
            // Initialize the file
            file = new PrintWriter("data-of-awesomeness-for-"+placementModel+".txt", "UTF-8");
            
            // Print the default information
            file.println( "placement model: " + this.placementModel );
            file.println( "width: " + this.labelWidth );
            file.println( "height: " + this.labelHeight );
            file.println( "number of points: " + this.amountOfPoints );
            
            // Add output
            output();
            
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
        new DataGenerator().askData();
    }
}
