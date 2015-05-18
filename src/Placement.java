/**
 * Point class representing Placements for the '2IO90 DBL ALGORITHMS'
 *
 * @author Stefan Habets
 */
public enum Placement {

    NE, SE, NW, SW, Slider;

    /**
     * Gets the placement connected to a string value
     *
     * @param s: The input string that should be converted to Placement
     * @return the correct placement, where s is similar to
     * placement.toString(). null if no similar placement exists.
     * @deprecated
     */
    public Placement get(String s) {
        if (s != null) {
            for (Placement b : Placement.values()) {
                if (s.equalsIgnoreCase(b.toString())) {
                    return b;
                }
            }
        }
        return null;
    }
    
    /**
     * Returns all placements associated with twoPos
     * 
     * @return {NE, NW}
     */
    public static Placement[] twoPos(){
        return new Placement[]{NE, NW};
    }
    
    /**
     * Returns all placements associated with twoPos
     * 
     * @return {NE, SE, NW, SW}
     */
    public static Placement[] fourPos(){
        return new Placement[]{NE, SE, NW, SW};
    }
    
    /**
     * Returns all placements associated with twoPos
     * 
     * @return {Slider}
     */
    public static Placement[] oneSlider(){
        return new Placement[]{Slider};
    }
}