
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ivan Kozlov
 */
public class Method4Pos {

    private double c = 100000.0;// the temperature in the annealing schedule
    private Point[] result;
    private Map<Label, Set<Label>> collisions;
    Placement oldPlacement;
    Point oldPoint;
    private double OldScore;
    private double NewScore;
    RandomGenerator rg = new RandomGenerator();
    MergeSort mergesort = new MergeSort();
    QuadTree quad = new QuadTree(0, 0, 10000, 0, 10000);

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

    public ArrayList<Point> posCollisions(Point p) {
        ArrayList<Point> possibleCollisions = new ArrayList<Point>();
        possibleCollisions.clear();
        quad.retrieve(possibleCollisions, p);
        return possibleCollisions;
    }

    /**
     * Gives all points a random initial label placement
     *
     * @param points the list points to give the random labels
     */
    public void RandomInitialPosition(Point[] points) {
        int placement;

        for (Point p : points) {
            p.setLabels(null);
        }
        for (Point p : points) {
            placement = RandomInt(3);
            ArrayList<Label> labels = new ArrayList<Label>();
            switch (placement) {
            case 0:
                labels.add(new Label(p, Placement.NW, MainReader.width, MainReader.height));
                break;
            case 1:
                labels.add(new Label(p, Placement.NE, MainReader.width, MainReader.height));
                break;
            case 2:
                labels.add(new Label(p, Placement.SW, MainReader.width, MainReader.height));
                break;
            case 3:
                labels.add(new Label(p, Placement.SE, MainReader.width, MainReader.height));
                break;
            }
            p.setLabels(labels);
//            labels.clear();
        }
    }

    /**
     * Finds the collisions of the labels
     *
     * @param points The points that has the labels
     * @return the actual collisions
     */
    public Map<Label, Set<Label>> FindCollisions(Point[] points) {
        Map<Label, Set<Label>> collisions = new HashMap<Label, Set<Label>>();
        List<Point> possiCollisions = new ArrayList<Point>();
        List<Label> poCollisions = new ArrayList<Label>();
        for (Point p : points) {
            possiCollisions = posCollisions(p);
            for (int i = 0; i < possiCollisions.size(); i++) {
                poCollisions.add(possiCollisions.get(i).getLabels().get(0));
//                for (int j = 0; j < possiCollisions.size(); j ++) {
//                    System.out.println(possiCollisions.get(j).getLabels().get(j) + " hoi");
//                }
                Collision.allCollisions(poCollisions, p, collisions);
                poCollisions.clear();;
            }
        }
        //for (int i = 0; i < collisions.size(); i ++) {
        //System.out.println(collisions.size() + " hoi " +collisions.toString());
        //}
        return collisions;
    }

    /**
     * Changes a label of a random point to a random position
     *
     * @param p The array containing a point you wish to change
     */
    public void ChangeRandomLabel(Point[] p) {
        int i = RandomInt(p.length - 1);
        int j = RandomInt(2);
        Placement placement = p[i].getLabels().get(0).getPlacement();

        oldPoint = p[i];
        oldPlacement = placement;
        switch (placement) {
        case NE:
            if (j == 0) {
                p[i].getLabels().get(0).setPlacement(Placement.NW);
            } else if (j == 1) {
                p[i].getLabels().get(0).setPlacement(Placement.SE);
            } else if (j == 2) {
                p[i].getLabels().get(0).setPlacement(Placement.SW);
            }
        case NW:
            if (j == 0) {
                p[i].getLabels().get(0).setPlacement(Placement.NE);
            } else if (j == 1) {
                p[i].getLabels().get(0).setPlacement(Placement.SE);
            } else if (j == 2) {
                p[i].getLabels().get(0).setPlacement(Placement.SW);
            }
        case SE:
            if (j == 0) {
                p[i].getLabels().get(0).setPlacement(Placement.NW);
            } else if (j == 1) {
                p[i].getLabels().get(0).setPlacement(Placement.NE);
            } else if (j == 2) {
                p[i].getLabels().get(0).setPlacement(Placement.SW);
            }
        case SW:
            if (j == 0) {
                p[i].getLabels().get(0).setPlacement(Placement.NW);
            } else if (j == 1) {
                p[i].getLabels().get(0).setPlacement(Placement.NE);
            } else if (j == 2) {
                p[i].getLabels().get(0).setPlacement(Placement.SE);
            }
        }
    }

    /**
     * The actual annealing schedule
     *
     * @param p the same as ever
     */
    public void Annealing(Point[] p) {
        quadtree(p);
        RandomInitialPosition(p);
        collisions = FindCollisions(p);
        OldScore = (double) collisions.size();
        while (c > 999990 && OldScore > 0) {
            ChangeRandomLabel(p);
            collisions = FindCollisions(p);
            NewScore = (double) collisions.size();
            if (OldScore < NewScore) {
                double AcceptanceChance = AcceptanceChance();
                double randomdouble = RandomDouble();
                if (AcceptanceChance < randomdouble) {//c needs to be a random number
                    RevertChanges();
                    NewScore = OldScore;
                }
            }
            OldScore = NewScore;
            c--;//Needs to be changed.
        }
        RemoveCollisions(p);
    }

    /**
     * Calculates the acceptance chance
     *
     * @return the acceptance chance
     */
    public double AcceptanceChance() {
        return Math.exp((OldScore - NewScore) / c);
    }

    public double RandomDouble() {

        return rg.randomDouble();
    }

    public int RandomInt(int max) {
        return rg.randomInt(max);
    }

    /**
     * Reverts the change back to the position before the change of the random
     * label.
     *
     *
     */
    public void RevertChanges() {
        oldPoint.getLabels().get(0).setPlacement(oldPlacement);

    }

    /**
     * Removes the collisions of the solution, if there are any
     *
     * @param points The same as ever
     */
    public void RemoveCollisions(Point[] points) {
        List<Label> tempList = new ArrayList<Label>();
        Map<Label,Set<Label>> tempcollisions = collisions;
        int temp = 0;
        int length;
        Label tempL = null;

        for (Label l : collisions.keySet()) {
            length = collisions.get(l).size();
            if (temp < length) {
                tempL = l;
                temp = length;
               
                
            }
            if (!tempList.contains(l)) {
            tempList.add(l);
            }
        }
            for (int i = 0; i < tempList.size(); i++) {
                        Collision.removeCollisionFromMap(tempcollisions, tempL, tempList.get(i));
                        tempList.get(i).getAnchor().removeLabel(tempList.get(i).getPlacement()); 
                        MainReader.numberLabels--;
                    }
                
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
        System.out.println("number of labels: " + MainReader.numberLabels);

        //Output each of the points
        for (Point point : output) {
            if (! point.getLabels().isEmpty()) {
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
