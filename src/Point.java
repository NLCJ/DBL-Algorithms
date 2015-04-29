/**
 *
 * @author Melroy and Chris
 */
public class Point {
    private final int x;
    private final int y;
    private final int origin;
    private double slider;
    
    public Point(int x, int y, int origin){
        this.x = x;
        this.y = y;
        this.origin = origin;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getOrigin() {
        return origin;
    }
    
    public void setSlider( double slider ) {
        this.slider = slider;
    }
    
    public double getSlider() {
        return this.slider;
    }
}