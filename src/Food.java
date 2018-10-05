/**
 * Name : Frankie Wang
 * PennKey : frankiew
 * Recitation : 214
 * 
 * Execution: java Food
 * 
 * Food class that spawns food randomly at a location the snake is not
 * occupying
 * 
 */

public class Food {
    // Attributes
    private int x;
    private int y;
    
    /**
     * Description: Creates an instance of Food at a location not occupied
     * Input: boolean[][] and Snake snake
     */
    public Food(boolean[][] occupied, Snake snake) {  
        if (occupied.length != 20 || occupied[0].length != 20) {
            throw new RuntimeException("ERROR: 2-D Array does not " +
                                       "correspond to game board size");
        }
        
        // loops through available points and chooses a random one
        int numOpen = 400 - snake.getLength();
        int index = (int) (numOpen * Math.random());
        int counter = -1;
        for (int i = 0; i < occupied.length && counter != index; i++) {
            for (int j = 0; j < occupied[0].length && counter != index; j++) {
                if (!occupied[i][j]) {
                    counter++;
                    if (counter == index) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
    }
    
    /**
     * Description: Draws Food at its location
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledCircle(x, y, 0.45);
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