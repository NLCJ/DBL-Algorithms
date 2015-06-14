
import java.lang.reflect.Array;
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

    Collision Col = new Collision();
    Method2Pos Pos = new Method2Pos();
    private double c ;// the temperature in the annealing schedule
    private Point[] result;
    private Map<Label, Set<Label>> collisions;
    Placement oldPlacement;
    Point oldPoint;
    private List<Label> pointNewCollisions = new ArrayList<Label>();
    private List<Label> pointOldCollisions = new ArrayList<Label>();
    private int collisionsLength;
    private double OldScore = 0;
    private double NewScore = 0;
    
    ArrayList<Label> L = new ArrayList<Label>();
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
        //TODO: start experiment. Include for Quadtree experiment
        /*
        long startTime = System.nanoTime();
        */
        for (Point p : points) {
            quad.insert(p);
        }
        //TODO: end experiment. Include for QuadTree experiment
        /*
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        String testType = "initialization";
        EO.quadTreeArrays(testType, totalTime);
        */
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
        }
    }

    /**
     * Finds the collisions of the labels
     *
     * @param points The points that has the labels
     * @return the actual collisions
     */
    public Map<Label, Set<Label>> FindAllCollisions(Point[] points) {
        Map<Label, Set<Label>> colisions = new HashMap<Label, Set<Label>>();
        List<Point> possiCollisions = new ArrayList<Point>();
        //TODO: start experiment. Include for Quadtree experiment
        /*
        long startTime = System.nanoTime();
        */
        for (Point p : points) {
            possiCollisions = posCollisions(p);

//                for (int j = 0; j < possiCollisions.size(); j ++) {
//                    System.out.println(possiCollisions.get(j).getLabels().get(j) + " hoi");
//                }
            Col.fourPosAllCollisions(possiCollisions, p, colisions);

        }
        //TODO: end experiment. Include for QuadTree experiment
        /*
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        String testType = "detection";
        EO.quadTreeArrays(testType, totalTime);
        */
        //for (int i = 0; i < collisions.size(); i ++) {
        //System.out.println(collisions.size() + " hoi " +collisions.toString());
        //}
        return colisions;
    }

    /**
     * Changes a label of a random point to a random position
     *
     * @param p The array containing a point you wish to change
     */
    public boolean ChangeRandomLabel(Point[] p) {
        boolean random = true;
        int j = RandomInt(2);
//        for (Label l : collisions.keySet()) {
//            L.add(l);
//            for (Label la : collisions.get(l)) {
//
//                L.add(la);
//            }
//        }

//        int i = RandomInt(L.size() - 1);
        if (p.length == 0) {
            return false;
        }
        int i = RandomInt(p.length - 1);

        //Placement placement = L.get(i).getPlacement();
        // oldPoint = L.get(i).getAnchor();
        oldPoint = p[i];

        pointOldCollisions.clear();
        pointOldCollisions = FindPointCollisions(oldPoint);
        if (pointOldCollisions.size() == 0) {
            Point[] temp = new Point[p.length - 1];
            int k = 0;
            int l = 0;
            while (k < p.length) {
                if (p[k].equals(p[i])) {
                    k ++;
                    if (k > temp.length) {
                        break;
                    }
                }
                temp[l] = p[k];
                k ++;
                l ++;
            }
            random = ChangeRandomLabel(temp);
            return false;
        }

        if (random) {
            Placement placement = p[i].getLabels().get(0).getPlacement();
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
                    break;
                case NW:
                    if (j == 0) {
                        p[i].getLabels().get(0).setPlacement(Placement.NE);
                    } else if (j == 1) {
                        p[i].getLabels().get(0).setPlacement(Placement.SE);
                    } else if (j == 2) {
                        p[i].getLabels().get(0).setPlacement(Placement.SW);
                    }
                    break;
                case SE:
                    if (j == 0) {
                        p[i].getLabels().get(0).setPlacement(Placement.NW);
                    } else if (j == 1) {
                        p[i].getLabels().get(0).setPlacement(Placement.NE);
                    } else if (j == 2) {
                        p[i].getLabels().get(0).setPlacement(Placement.SW);
                    }
                    break;
                case SW:
                    if (j == 0) {
                        p[i].getLabels().get(0).setPlacement(Placement.NW);
                    } else if (j == 1) {
                        p[i].getLabels().get(0).setPlacement(Placement.NE);
                    } else if (j == 2) {
                        p[i].getLabels().get(0).setPlacement(Placement.SE);
                    }
                    break;
            }
        }
//        L.clear();
        return true;
    }

    public int mapSize(Map<Label, Set<Label>> col) {
        int length = 0;
        for (Set<Label> s : col.values()) {
            length += s.size();
        }
        return length;
    }

    public List<Label> FindPointCollisions(Point point) {
        List<Label> pointCollisions = new ArrayList<Label>();
        List<Point> possiCollisions = new ArrayList<Point>();
        possiCollisions = posCollisions(point);
        pointCollisions = Col.fourPosCollision(possiCollisions, point);
        return pointCollisions;
    }

    /**
     * The actual annealing schedule
     *
     * @param p the same as ever
     */
    public void Annealing(Point[] p) {
        quadtree(p);
        c = (double) 100000000/p.length;

        if (p.length < 101) {
            
            RandomInitialPosition(p);
            collisions = FindAllCollisions(p);
            collisionsLength = mapSize(collisions);
            OldScore = OldScore + collisionsLength;

            OldScore = (double) OldScore;

            while (c > 1 && OldScore > 0) {

                ChangeRandomLabel(p);
                collisions =FindAllCollisions(p);
                collisionsLength = mapSize(collisions);
                // System.out.println(collisionsLength + " hoi");

                NewScore = NewScore + collisionsLength;

                // System.out.println(NewScore + " " + collisionsLength);
                NewScore = (double) NewScore;
                if (OldScore < NewScore) {
                    double AcceptanceChance = AcceptanceChance();
                    double randomdouble = RandomDouble();
                    if (AcceptanceChance < randomdouble) {
                        RevertChanges();
                        NewScore = OldScore;
                    }
                }

                OldScore = NewScore;
                NewScore = 0;
                c = c * 0.999;//Needs to be changed.
            }
            RemoveCollisions(p);
        } else {
            
            RandomInitialPosition(p);
            collisions = FindAllCollisions(p);
            collisionsLength = mapSize(collisions);
            OldScore = OldScore + collisionsLength;

            OldScore = (double) OldScore;

            while (c > 1 && OldScore > 0) {
                ChangeRandomLabel(p);
                pointNewCollisions = FindPointCollisions(oldPoint);
                collisionsLength = (pointNewCollisions.size() - pointOldCollisions.size());
                // System.out.println(collisionsLength + " hoi");

                NewScore = OldScore + collisionsLength;

                // System.out.println(NewScore + " " + collisionsLength);
                NewScore = (double) NewScore;
                if (OldScore < NewScore) {
                    double AcceptanceChance = AcceptanceChance();
                    double randomdouble = RandomDouble();
                    if (AcceptanceChance < randomdouble) {
                        RevertChanges();
                        NewScore = OldScore;
                    }
                }

                OldScore = NewScore;
                NewScore = 0;
                c = c * 0.999;//Needs to be changed.
            }
            RemoveCollisions(p);

        }
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
        collisions = FindAllCollisions(points);
        List<Label> tempList = new ArrayList<Label>();
        Map<Label, Set<Label>> tempcollisions = collisions;
        int temp = 0;
        int length;
        Label tempL = null;

        for (Label l : collisions.keySet()) {
            length = collisions.get(l).size();
            if (temp < length) {
                tempL = l;
                temp = length;

            }
            if ( ! tempList.contains(l)) {
                tempList.add(l);
            }
        }
        for (int i = 0; i < tempList.size(); i ++) {
            Collision.removeCollisionFromMap(tempcollisions, tempL, tempList.get(i));
            tempList.get(i).getAnchor().removeLabel(tempList.get(i).getPlacement());
            MainReader.numberLabels --;
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
