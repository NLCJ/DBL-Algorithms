import java.lang.reflect.Array;

public class Point {

    private int x;
    private int y;
    private int height;
    private int width;
    private int origin;
    private double slider;
    private String position;
    private String[] twoPositionArray = { "NE", "NW" };
    
    public Point(int x, int y, int height, int width, int origin){
        this.x = x;
        this.y = y; 
        this.height = height;
        this.width = width;
        this.origin = origin;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setSlider(double slider) {
        this.slider = slider;
    }

    public double getSlider() {
        return this.slider;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
