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
    private double shift = 10;

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

                this.reference = new Point( this.anchor.getX(), this.anchor.getY(), -1 );
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
        this.shift = shift;
        
        // Check if the shift is 'valid'
        if( shift >= 0 && shift <= 1 ) {
            // Create a reference point for the label
            this.reference = new Point( this.anchor.getX() - MainReader.width + ( shift * MainReader.width ), this.anchor.getY(), -1 );
        } else {
            this.reference = new Point( this.anchor.getX(), this.anchor.getY(), -1 );
        }
    }

    /**
     * Gives the shift of the label
     *
     * @return the shift of the label
     */
    public double getShift() {
        return this.shift;
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