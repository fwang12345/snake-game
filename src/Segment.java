/**
 * Name : Frankie Wang
 * PennKey : frankiew
 * Recitation : 214
 * 
 * Execution: java Segment
 * 
 * Segment class that represents each segment of a snake
 * 
 */

public class Segment {
    // Attributes
    private int x;
    private int y;
    private Segment next;
    
    /**
     * Description: Creates an instance of Segment
     * Input: int x, int y
     */
    public Segment(int x, int y) {
        this.x = x;
        this.y = y;
        next = null;
    }
    
    /**
     * Description: Creates an instance of Segment
     * Input: Point p and Segment n
     */
    public Segment(int x, int y, Segment n) {
        this.x = x;
        this.y = y;
        next = n;
    }
    
    /**
     * Description: Draws a square segment at the given point
     */
    public void draw() {
        PennDraw.square(x, y, 0.45);
    }
    
    /**
     * Description: Gets x
     * Output: int 
     */
    public int getX() {
        return x;
    }
    
    /**
     * Description: Gets y
     * Output: int
     */
    public int getY() {
        return y;
    }
    
    /**
     * Description: Gets next Segment
     * Output: Segment
     */
    public Segment getNext() {
        return next;
    }
    
    /**
     * Description: Makes the next segment null
     */
    public void removeNext() {
        next = null;
    }
}