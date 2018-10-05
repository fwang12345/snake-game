/**
 * Name : Frankie Wang
 * PennKey : frankiew
 * Recitation : 214
 * 
 * Execution: java Point
 * 
 * Point class that treats the game board as a grid defined by x and y 
 * coordinates
 * 
 */

public class Point {
    // Cartesian coordiantes
    private int x;
    private int y;
    
    /**
     * Description: Creates an instance of Point
     * Input: int x and y
     */
    public Point(int x, int y) {
        if (x < 0 || x > 19 || y < 0 || y > 19) {
            throw new RuntimeException("ERROR: Point outside the grid");
        }
        this.x = x;
        this.y = y;
    }
    
    /**
     * Description: Returns a string representation of Point
     * Output: String
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
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
}