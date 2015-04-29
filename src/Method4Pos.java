
/**
 *
 * @author Ivan Kozlov
 */
public class Method4Pos {

    MergeSort mergesort = new MergeSort();

    //The method which "calculates" the position of the labels
    public Point[] PositionCalculator(int w, int h, Point[] p) {
        //Set default position to NE
        for (Point point : p) {
            point.setPosition("NE");
        }
        //Return the point in the original order
        return originalOrder(p);
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
    
    public void Output4Position(String s, int w, int h, int n_p, Point[] p) {
        //Reorder the points to the original order
        Point[] output = PositionCalculator(w, h, p);
        //Required static outpu
        System.out.println("placement model: " + s);
        System.out.println("width: " + w);
        System.out.println("height: " + h);
        System.out.println("number of points: " + n_p);
        System.out.println("nubmers of labels: " + n_p);

        //Output each of the points
        for (Point point : output) {
            System.out.println(point.getX() + " " + point.getY() + " " + point.getPosition());
        }
    }
}
