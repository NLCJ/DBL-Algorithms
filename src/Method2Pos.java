
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Stefan Habets
 */
public class Method2Pos {

    private Point[] result;
    ArrayList<Clause<Point>> clauses = new ArrayList<Clause<Point>>();
    Collision c = new Collision();
    QuadTree quad = new QuadTree(0, 0, 10000, 0, 10000);

    //The method which "calculates" the position of the labels
    public Point[] PositionCalculator(int w, int h, Point[] p) {
        //Return the point in the original order
        this.result = MergeSort.originalOrder(p);
        return result;
    }

    public void quadtree(Point[] points) {
        for (Point p : points) {
            quad.insert(p);

        }
    }

    public void findCollisions(Point[] points) {
        Map<Label, Set<Label>> collisions = new HashMap<Label, Set<Label>>();
        List<Point> possiCollisions = new ArrayList<Point>();
        List<Label> poCollisions = new ArrayList<Label>();

        for (Point p : points) {
            possiCollisions = posCollisions(p);
            for (int i = 0; i < possiCollisions.size(); i++) {
                poCollisions.add(possiCollisions.get(i).getLabels().get(0));
                poCollisions.add(possiCollisions.get(i).getLabels().get(1));
            }
            Collision.allCollisions(poCollisions, p, collisions);
            poCollisions.clear();
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

        Literal<Clause[]> badPoints = TwoSat.isSatisfiable(clauses);
        while (badPoints.isPositive()) {
            for (Clause<Point> badPoint : badPoints.value()) {
                if (badPoint == null) {
                    continue;
                }
                MainReader.numberLabels--;

                badPoint.first().value().removeLabel(badPoint.first().getPlacement());
                badPoint.second().value().removeLabel(badPoint.second().getPlacement());

                for (int j = 0; j < clauses.size(); j++) {
                    if (clauses.get(j).first().value() == badPoint.first().value() || clauses.get(j).second().value() == badPoint.first().value()
                            || clauses.get(j).first().value() == badPoint.second().value() || clauses.get(j).second().value() == badPoint.second().value()) {

                        clauses.remove(j);
                        j--;
                    }
                }

                badPoints = TwoSat.isSatisfiable(clauses);
            }
        }

        if (TwoSat.g == null) {
            throw new Error("Error!");
        }
        //reverseOrder(g) eigenlijk
        Stack<Literal<Point>> stack = SCC.dfsVisitOrder(TwoSat.g);

        Map<Literal<Point>, Integer> result = new HashMap<Literal<Point>, Integer>();

        while (!stack.isEmpty()) {
            Literal<Point> p = stack.pop();
            boolean temp = false;
            for (Label l : p.value().getLabels()) {
                if (l.getPlacement() == p.getPlacement()) {
                    temp = true;
                }
            }
            if (temp) {
                //hier is ie contained, dus al het andere wegpleuren
                ArrayList<Label> labels = new ArrayList<Label>();
                for (Label l : p.value().reproduceLabels()) {
                    if (l.getPlacement() == p.getPlacement()) {
                        labels.add(l);
                    }
                }
                p.value().setLabels(labels);
            }
        }
    }

    public ArrayList<Point> posCollisions(Point p) {
        ArrayList<Point> possibleCollisions = new ArrayList<Point>();
        possibleCollisions.clear();
        quad.retrieve(possibleCollisions, p);
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
            if (!point.getLabels().isEmpty()) {
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
