/**
 *
 * @author Melroy and Chris
 */
public class Point {
    private int x;
    private int y;
    private final int origin;
    private double slider;
    private String position;
    
    public Point(int x, int y, int origin){
        this.x = x;
        this.y = y;
        this.origin = origin;
    }
    
    public int getX(){
        return x;
    }
    
    public void setX( int x ) {
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY( int y ) {
        this.y = y;
    }
    
    public int getOrigin() {
        return origin;
    }
    
    public void setPosition( String position ) {
        this.position = position;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public void setSlider( double slider ) {
        this.slider = slider;
    }
    
    public double getSlider() {
        return this.slider;
    }
}