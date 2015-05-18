
import java.util.ArrayList;
import java.util.List;

/**
 * Point class representing labels for the '2IO90 DBL ALGORITHMS'
 *
 * @author Chris Jansen
 */
public class Point {

    private final double x; //x coordinate, cannot be altered afterwards
    private final double y; //y coordinate, cannot be altered afterwards
    private final int origin; //pointer to position in original input
    private List<Label> labels = new ArrayList<Label>();

    /**
     * Creates a point given x and y.
     *
     * @param x: x-coordinate of the point
     * @param y: y-coordinate of the point
     * @param origin: pointer to position in original input
     */
    public Point(double x, double y, int origin) {
        this.x = x;
        this.y = y;
        this.origin = origin;
    }

    /**
     * Creates a point given x and y.
     *
     * @param x: x-coordinate of the point
     * @param y: y-coordinate of the point
     * @param origin: pointer to position in original input
     * @param model: the placement model used
     */
    public Point(double x, double y, int origin, Model model) {
        this.x = x;
        this.y = y;
        this.origin = origin;
        reproduceLabels();
    }

    public final void reproduceLabels() {
        Placement[] placements;
        switch (MainReader.pModel) {
            case TWOPOS:
                placements = Placement.twoPos();
                break;
            case FOURPOS:
                placements = Placement.fourPos();
                break;
            default:
                placements = Placement.oneSlider();
                break;
        }
        for (Placement p : placements) {
            labels.add(new Label(this, p, MainReader.width, MainReader.height));
        }
    }

    public void removeLabel(Placement p) {
        ArrayList<Label> toRemove = new ArrayList<Label>();
        for (Label l : labels) {
            if (l.getPlacement() == p) {
                toRemove.add(l);
            }
        }

        for (Label l : toRemove) {
            labels.remove(l);
        }
    }

    /**
     * Returns the x-coordinate of the point
     *
     * @return {@code x}
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point
     *
     * @return {@code y}
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the origin-coordinate of the point
     *
     * @return {@code origin}
     */
    public int getOrigin() {
        return origin;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    /**
     * String variant of a point
     *
     * @return Point(x,y)
     */
    @Override
    public String toString() {
        return "Point(" + x + "," + y + ')';
    }
}
