
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Kozlov
 */
public class Method2Pos {

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

    public void makeLiterals(int n) {
        for (int i = 0; i < n; i ++) {
            new Literal(i, true);
            new Literal(i, false);
        }
        Literal li = new Literal(1, true);
        Literal lit = new Literal(2, false);
        makeClauses(li, lit);
        Literal lite = new Literal(1, true);
        Literal liter = new Literal(2, true);
        makeClauses(lite, liter);
        Literal litera = new Literal(1, false);
        Literal literal = new Literal(2, false);
        makeClauses(litera, literal);
        Literal lita = new Literal(1, false);
        Literal litar = new Literal(2, true);
        makeClauses(lita, litar);
        testClauses(clauses);
    }
    ArrayList clauses = new ArrayList();

    public void makeClauses(Literal one, Literal two) {
        Clause test = new Clause(one, two);
        clauses.add(test);
    }

    public void testClauses(List<Clause<Literal>> clauses) {
        if (TwoSat.isSatisfiable(clauses)) {
            System.out.println("true");

        } else {
            System.out.println("false");
        }
    }

    public void Quadtreee(Point[] p) {
        QuadTree qua = new QuadTree(1, 0, 20, 0, 20);
        ArrayList lijst = new ArrayList();
        for (Point points : p) {
            qua.insert(points);

        }
        for (Point pointss : p) {
            lijst.clear();
            qua.retrieve(lijst, pointss);

            System.out.println("nieuwe " + pointss.getX() + " " + pointss.getY());
            for (int j = 0; j < lijst.size(); j ++) {
                Point point = (Point) lijst.get(j);
                System.out.println(point.getX() + " " + point.getY());

            }
        }
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

    public void Output2Position(String s, int w, int h, int n_p, Point[] p) {
        //Reorder the points to the original order
        Point[] output = PositionCalculator(w, h, p);
        //Required static output
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
