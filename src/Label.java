import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Label class representing labels for the '2IO90 DBL ALGORITHMS'
 *
 * @author Stefan Habets
 */
public class Label {

    private final Point anchor; //The point corresponding to the placement model
    private Point reference; //The lower left corner of the label
    private final Placement placement;

    /**
     * Creates a label following a given placement.
     *
     * new Label(p, 'NE', 1, 1) -> gives northeast label
     *
     * new Label(p, 'NE', 1, 1, 0.25) -> gives northeast label
     *
     * new Label(p, 'Slider', 1, 1) -> gives slider with shift 0
     *
     * new Label(p, 'Slider', 1, 1, 0.25) -> gives slider with shift 0.25
     *
     * @param anchor: The point corresponding to the placement model.
     * @param placement: The place the label is on.
     * @param width: width of the label.
     * @param height: height of the label.
     * @param shift: the shift of the label (a / width).
     */
    public Label(Point anchor, Placement placement, int width, int height, double... shift) {
        this.anchor = anchor;
        this.placement = placement;
        switch (placement) {
            case NE: //placement is NE
                reference = anchor;
                //reference point is equal to the anchor point
                break;
            case NW: //placement is NW
                reference = new Point(anchor.getX() - width, anchor.getY(), -1);
                //reference point needs a translation of (-width, 0)
                break;
            case SE: //placement is SE
                reference = new Point(anchor.getX(), anchor.getY() - height, -1);
                //reference point needs a translation of (0, -height)
                break;
            case SW: //placement is SW
                reference = new Point(anchor.getX() - width, anchor.getY() - height, -1);
                //reference point needs a translation of (-width, -height)
                break;
            default: //placement is Slider on default
                double a;
                if (shift.length == 0) {
                    a = 0; //in case shift is not given, assume shift is 0
                } else {
                    if (shift[0] >= 0 && shift[0] <= 1) {
                        a = shift[0] * width; //variable a as stated in the problem description                                      
                    } else {
                        a = 0; //in case shift is invalid, shift is 0
                        Logger.getLogger(MainReader.class.getName()).log(Level.WARNING, null, "Shift is invalid");
                    }
                }
                reference = new Point(anchor.getX() - (width - a), anchor.getY(), -1);
                if (reference.getY() != anchor.getY()) {
                System.out.println("niet gelijk");
                }
                //reference point needs a translation of (-(width - a), 0)
                break;
        }
    }

    /**
     * Gives the anchor point
     *
     * @return {@code anchor}
     */
    public Point getAnchor() {
        return anchor;
    }

    /**
     * Gives the reference point
     *
     * @return {@code reference}
     */
    public Point getReference() {
        return reference;
    }

    /**
     * Sets the shift of this label
     *
     * @param shift: the shift of the label (a/width)
     */
    public void setShift(double shift) {
        double a; //displacement a
        if (shift >= 0 && shift <= 1) {
            a = shift * MainReader.width; //variable a as stated in the problem description                                      
        } else {
            a = 0; //in case shift is invalid, shift is 0
            Logger.getLogger(MainReader.class.getName()).log(Level.WARNING, null, "Shift is invalid");
        }
        reference = new Point(anchor.getX() - (MainReader.width - a), anchor.getY(), -1);
        //reference point needs a translation of (-(width - a), 0)
    }

    /**
     * Gives the shift of the label
     *
     * @return the shift of the label
     */
    public double getShift() {
        return (MainReader.width - (anchor.getX() - reference.getX())) / MainReader.width;
    }

    /**
     * Shifts this label
     *
     * @param displacement: the movement of the label
     */
    public void addShift(double displacement) {
        double newX = reference.getX() + displacement;
        double distance = anchor.getX() - newX;
        if (distance <= MainReader.width && distance >= 0) {
            reference = new Point(newX, anchor.getY(), -1);
        } else {
            Logger.getLogger(MainReader.class.getName()).log(Level.WARNING, null, "Displacement is invalid");
        }
    }

    public Placement getPlacement() {
        return placement;
    }

    @Override
    public String toString() {
        return "(" + anchor.getX() + ", " + anchor.getY() + ") " + placement; //To change body of generated methods, choose Tools | Templates.
    }

    public Literal<Point> convertToLiteral() {
        return new Literal<Point>(anchor, placement.equals(Placement.NE));
    }
}