/**
 * Name : Frankie Wang
 * PennKey : frankiew
 * Recitation : 214
 * 
 * Execution: java Snake
 * 
 * Snake class that creates a snake from a linked list of segments
 * 
 */

public class Snake {
    // Attributes
    private Segment head;
    private Segment tail;
    private String direction;
    private int length;
    
    /**
     * Description: Creates an instance of Snake
     */
    public Snake() {
        // creates a snake at a random location of length 1
        int x = (int) (20 * Math.random());
        int y = (int) (20 * Math.random());
        head = new Segment(x, y);
        tail = head;
        length = 1;
        
        // creates an array of all possible directions
        String[] direcs = {"w", "s", "a", "d"};
        
        // finds the distance to each of the 4 walls
        int[] distance = new int[4];
        distance[0] = 19 - y;
        distance[1] = y;
        distance[2] = x;
        distance[3] = 19 - x;
        
        /**
         * determines which wall is furthest and sets direction towards
         * that wall
         */
        int max = 0;
        for (int i = 1; i < 4; i++) {
            if (distance[i] > distance[max]) {
                max = i;
            }
        }
        
        direction = direcs[max];
        direction = "s";
    }
    
    /**
     * Description: Draws snake
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.GREEN);
        for (Segment i = head; i != null; i = i.getNext()) {
            PennDraw.filledSquare(i.getX(), i.getY(), 0.45);
        }
    }
    
    /**
     * Description: Increases the length of snake 
     * Input: int x and int y
     * Output: boolean
     */
    public boolean grow(int x, int y) {
        // reassigns head and increments length
        if (x < 0 || x > 19 || y < 0 || y > 19) {
            return true;
        }
        
        Segment newHead = new Segment(x, y, head);
        head = newHead;
        length++;

        return false;
    }
    
    /**
     * Description: Gets length of snake
     * Output: int
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Description: Gets the head segment
     * Output: Segment
     */
    public Segment getHead() {
        return head;
    }
    
    /**
     * Description: Gets the tail segment
     * Output: Segment
     */
    public Segment getTail() {
        return tail;
    }
    
    /**
     * Description: Gets the direction of the snake
     * Output: String
     */
    public String getDirec() {
        return direction;
    }
    
    /**
     * Description: Changes the direction of the snake
     * Input: char c
     */
    public void changeDirec(char c) {
        String newDirec = "" + c;
        newDirec = newDirec.toLowerCase();
        boolean change = (newDirec.equals("w") || newDirec.equals("s") || 
                          newDirec.equals("a") || newDirec.equals("d")) && 
            !direction.equals(newDirec);
        if (change && length != 1) {
            String[] direcs = {"w", "a", "s", "d"};
            int index1 = 0;
            int index2 = 0;
            for (int i = 1; i < 4; i++) {
                if (direction.equals(direcs[i])) {
                    index1 = i;
                }
                else if (newDirec.equals(direcs[i])) {
                    index2 = i;
                }
            }
            if (index1 % 2 != index2 % 2) {
                direction = newDirec;
            }
        }
        else if (change && length == 1) {
            direction = newDirec;
        }
    }
    
    /**
     * Description: Moves the snake in the current direction
     * Output: boolean
     */
    public boolean move() {
        int x = head.getX();
        int y = head.getY();
        if (direction.equals("w")) {
            return grow(x, y + 1);
        }
        else if (direction.equals("s")) {
            return grow(x, y - 1);
        }
        else if (direction.equals("a")) {
            return grow(x - 1, y);
        }
        else {
            return grow(x + 1, y);
        }
    }
    
    /**
     * Description: Removes the last segment in the snake
     */
    public void removeTail() {
        // reassigns tail to the next to last segment
        for (Segment i = head; i.getNext() != null; i = i.getNext()) {
            if (i.getNext() == tail) {
                tail = i;
            }
        }
        
        // remove the segment after tail and decrements length
        tail.removeNext();
        length--;
    }
    
    /**
     * Description: Creates a 2-D array of boolean that determines which 
     * positions the snake is occupying
     * Output: boolean[][]
     */
    public boolean[][] occupy() {
        boolean[][] occupied = new boolean[20][20];
        
        // reassigns the boolean at an occupied location as true
        for (Segment i = head; i != null; i = i.getNext()) {
            occupied[i.getX()][i.getY()] = true;
        }
        
        return occupied;
    }
}