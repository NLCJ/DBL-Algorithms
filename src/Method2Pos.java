
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Stefan Habets
 */
public class Method2Pos {

    private Point[] result;
    ArrayList<Clause<Point>> clauses = new ArrayList<Clause<Point>>();
    Collision c = new Collision();
    QuadTree quad = new QuadTree(1, 0, 20, 0, 20);

    //The method which "calculates" the position of the labels
    public Point[] PositionCalculator(int w, int h, Point[] p) {
        //Return the point in the original order
        this.result = MergeSort.originalOrder(p);
        return result;
    }

    public void quadtree(Point[] points) {
        for (Point p : points) {
            p.getLabels().stream().forEach((l) -> {
                quad.insert(l);
            });
        }
    }

    public void findCollisions(Point[] points) {
        Map<Label, Set<Label>> collisions = new HashMap<Label, Set<Label>>();
        for (Point p : points) {
            Collision.allCollisions(posCollisions(p), p, collisions);
        }

        for (Label l : collisions.keySet()) {
            Set<Label> labels = collisions.get(l);
            if (labels != null) {
                for (Label l2 : labels) {
                    clauses.add(new Clause<Point>(l.convertToLiteral(), l2.convertToLiteral()));
                    clauses.add(new Clause<Point>(l2.convertToLiteral(), l.convertToLiteral()));
                }
            }
        }

        //TODO clauses are only from not to not! something wrong here!
       // System.out.println("Clauses: " + clauses);
       // System.out.println("Collisions: " + collisions);

        while (TwoSat.isSatisfiable(clauses) != null) {
            Clause<Point> badPoint = TwoSat.isSatisfiable(clauses);
            MainReader.numberLabels --;

            badPoint.first().value().removeLabel(badPoint.first().getPlacement());
            badPoint.second().value().removeLabel(badPoint.second().getPlacement());

         //   System.out.println("niet nullo " + badPoint.toString());
            for (int j = 0; j < clauses.size(); j ++) {
                if (clauses.get(j).first().value() == badPoint.first().value() || clauses.get(j).second().value() == badPoint.first().value()
                        || clauses.get(j).first().value() == badPoint.second().value() || clauses.get(j).second().value() == badPoint.second().value()) {
               //     System.out.println("Removing clause " + clauses.get(j));
                    clauses.remove(j);
                    j --;
                }
            }

//            for (int i = 0; i < clauses.size(); i ++) {
//                System.out.println(clauses.get(i));
//            }
        }

        /*for (Point p : MainReader.points) {
         System.out.println(p.getLabels());
         }*/
    }

    public List validCollisions(ArrayList possiCollisions, Label l) {
        List<Label> col = new ArrayList();
        col.clear();
        col = c.allCollisions(possiCollisions, l);
        return col;
    }

    public ArrayList posCollisions(Label l) {
        ArrayList<Label> possibleCollisions = new ArrayList();
        possibleCollisions.clear();
        quad.retrieve(possibleCollisions, l);
        return possibleCollisions;
    }

    public ArrayList<Label> posCollisions(Point p) {
        ArrayList<Label> possibleCollisions = new ArrayList<Label>();
        possibleCollisions.clear();
        for (Label l : p.getLabels()) {
            quad.retrieve(possibleCollisions, l);
        }
        return possibleCollisions;
    }

    public void Output2Position(String s, int w, int h, int n_p, Point[] p) {
        //Reorder the points to the original order
        Point[] output = PositionCalculator(w, h, p);
        //Required static output
        System.out.println("placement model: " + s);
        System.out.println("width: " + w);
        System.out.println("height: " + h);
        System.out.println("number of points: " + n_p);
        System.out.println("number of labels: " + MainReader.numberLabels);

        //Output each of the points
        for (Point point : output) {
            if ( ! point.getLabels().isEmpty()) {
                System.out.println((int) point.getX() + " " + (int) point.getY() + " " + point.getLabels().get(0).getPlacement());
            } else {
                System.out.println((int) point.getX() + " " + (int) point.getY() + " NILL");
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
