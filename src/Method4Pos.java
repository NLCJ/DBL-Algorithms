import java.util.Set;
/**
 *
 * @author Ivan Kozlov
 */
public class Method4Pos {
    private double c;// the temperature in the annealing schedule
    private Point[] result;
    private Set<Label> collisions;
    MergeSort mergesort = new MergeSort();
    QuadTree quad = new QuadTree(10, 0, 20, 0, 20);

    //The method which "calculates" the position of the labels
    public Point[] PositionCalculator(int w, int h, Point[] p) {
        //Return the point in the original order
        this.result = MergeSort.originalOrder(p);
        return result;
    }

    //Puts the points back into their original order as it was documented.
    public Point[] originalOrder(Point[] p) {
        //Original order output
        Point[] originalOrder = new Point[p.length];

        //For each point - place at the original position
        for (Point point : p) {
            originalOrder[point.getOrigin()] = point;
        }

        return originalOrder;
    }
    public void quadtree(Point[] points) {
        for (Point p : points) {
                quad.insert(p);
           
        }
    }
    
    /**
     * Gives all points a random initial label placement
     * @param p the list points to give the random labels
     */
     
    public void RandomInitialPosition(Point[] p){
    }
    /**
     * Finds the collisions of the labels
     * @param p The points that has the labels
     * @return the actual collisions
     */
    public Set<Label> FindCollisions(Point[] p){
        return null;
    }
    /**
     * Changes a label of a random point to a random position
     * @param p The array containing a point you wish to change
     */
    public void ChangeRandomLabel(Point[] p){
    }
    /**
     * The actual annealing schedule
     */
    public void Annealing(){
    }
    /**
     * Calculates the acceptance chance
     * @param Oldscore The "old" number of collisions
     * @param NewScore The "new" number of collisions     
     * @return the acceptance chance
     */
    
   
    public double AcceptanceChance( double Oldscore, double NewScore){
        return 1.0;
    }
    
    /**
     * Reverts the change back to the position before the change of the random 
     * label.
     * @param p the same as ever
     */
    public void RevertChanges(Point[] p){
    }
    /**
     * Removes the collisions of the solution, if there are any
     * @param p The list of points
     */
    public void RemoveCollisions(Point[] p){
    }
            
            
    public void Output4Position(String s, int w, int h, int n_p, Point[] p) {
        //Reorder the points to the original order
        Point[] output = PositionCalculator(w, h, p);

        // Store the result
        this.result = output;

        //Required static outpu
        System.out.println("placement model: " + s);
        System.out.println("width: " + w);
        System.out.println("height: " + h);
        System.out.println("number of points: " + n_p);
        System.out.println("number of labels: " + n_p);

        //Output each of the points
        for (Point point : output) {
            if ( ! point.getLabels().isEmpty()) {
                System.out.println((int) point.getX() + " " + (int) point.getY() + " " + point.getLabels().get(0).getPlacement());
            } else {
                System.out.println((int) point.getX() + " " + (int) point.getY() + " NIL");
            }
        }
    }

    /**
     * Return the result
     *
     * @return
     */
    public Point[] getResult() {
        // Return the resutls
        return this.result;
    }
}
