import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Name : Frankie Wang
 * PennKey : frankiew
 * Recitation : 214
 * 
 * Execution: java SnakeGame
 * 
 * Creates a SnakeGame which involves a snake traversing through the grid
 * Eating food results in growth 
 * The game is over when the snake runs into a wall or itself
 * 
 */

public class SnakeGame {
    /**
     * Description: Draws game board, snake, and food if not null
     * Input: Snake s and Food f
     */
    public static void drawBoard(Snake snake, Food food) {
        PennDraw.clear();
        PennDraw.setPenColor();
        PennDraw.filledSquare(9.5, 9.5, 10);
        snake.draw();
        try {
            food.draw();
        }
        catch (NullPointerException e) {
            System.out.println("Game Won");
        }
    }
    
    /**
     * Description: Draws end game screen
     * Input: int[] scores and boolean lose
     */
    public static void drawEnd(int[] scores, boolean lose) {
        if (lose) {
            PennDraw.setPenColor();
            PennDraw.text(9.5, 20.5, "Game Over");
        }
        else {
            PennDraw.setPenColor();
            PennDraw.text(9.5, 20.5, "Congratulations");
        }
        
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(2, 20.5, 2, 1);
        PennDraw.setPenColor();
        PennDraw.text(2, 20.5, "Back");
        
        try {
            FileOutputStream outStream = new FileOutputStream("highScores.txt");
            outStream.write(toByte(scores));
            outStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        // determines if the back button is pressed
        boolean back = false;
        while (!back) {
            if (PennDraw.mousePressed()) {
                double x = PennDraw.mouseX();
                double y = PennDraw.mouseY();
                if (x <= 4 && x >= 0 && y <= 21.5 && y >= 19.5) {
                    back = true;
                }
            }
        }
        drawStart(scores);
    }
    
    /**
     * Description: Draws starting screen
     * Input: int[] scores
     */
    public static void drawStart(int[] scores) {
        // draws starting screen
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(9.5, 12, 3, 1);
        PennDraw.filledRectangle(9.5, 9, 3, 1);
        PennDraw.setFontSize(40);
        PennDraw.setPenColor();
        PennDraw.text(9.5, 12, "Start Game");
        PennDraw.text(9.5, 9, "Leaderboard");
        
        // boolean that determines which button was pressed
        boolean playGame = false;
        boolean drawScore = false;
        
        // loops until a button is pressed
        while (!playGame && !drawScore) {
            if (PennDraw.mousePressed()) {
                double x = PennDraw.mouseX();
                double y = PennDraw.mouseY();
                if (x <= 12.5 && x >= 6.5 && y <= 13 && y >= 11) {
                    playGame = true;
                }
                else if (x <= 12.5 && x >= 6.5 && y <= 10 && y >= 8) {
                    drawScore = true;
                }
            }
        }
        
        // calls appropriate function
        if (playGame) {
            playGame(scores);
        }
        else {
            drawScore(scores);
        }
    }
    
    /**
     * Description: Draws leaderboard
     * Input: int[] scores
     */
    public static void drawScore(int[] scores) {
        // draws leaderboard
        PennDraw.clear();
        PennDraw.setFontSize(60);
        PennDraw.setPenColor();
        PennDraw.text(9.5, 20.5, "Leaderboard");
        PennDraw.setFontSize(40);
        for (int i = 0; i < 10; i++) {
            PennDraw.text(11, (9 - i) * 2 + 0.5, "" + scores[i]);
            if (i == 9) {
                PennDraw.text(7.75, (9 - i) * 2 + 0.5, i + 1 + ":");
            }
            else {
                PennDraw.text(8, (9 - i) * 2 + 0.5, i + 1 + ":");
            }
        }
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(2, 1, 2, 1);
        PennDraw.setPenColor();
        PennDraw.text(2, 1, "Back");
        
        // determines if the back button is pressed
        boolean back = false;
        while (!back) {
            if (PennDraw.mousePressed()) {
                double x = PennDraw.mouseX();
                double y = PennDraw.mouseY();
                if (x <= 4 && x >= 0 && y <= 2 && y >= 0) {
                    back = true;
                }
            }
        }
        drawStart(scores);
    }
    
    /**
     * Description: Determines if the game is won
     * The game is won when the serpent covers the entire grid
     * Input: Snake serpent
     * Output: boolean
     */
    public static boolean victory(Snake serpent) {
        return serpent.getLength() == 400;
    }
    
    /** 
     * Description: Determines if the game is lost
     * The game is lost if the serpent hits the hall or itself
     * Input: Snake serpent
     * Output: boolean 
     */
    public static boolean defeat(Snake serpent) {
        boolean lose = false;
        int headX = serpent.getHead().getX();
        int headY = serpent.getHead().getY();
        
        // loops through all segments to determine if any points match head
        if (!lose) {
            for (Segment i = serpent.getHead().getNext(); i != null && !lose; 
                 i = i.getNext()) {
                int x = i.getX();
                int y = i.getY();
                if (headX == x && headY == y) {
                    lose = true;
                }
            }
        }
        return lose;
    }
    
    /**
     * Description: adds score to the array of scores
     * Input: int score and int[] scores
     * Output: int[] scores
     */
    public static int[] addScore(int score, int[] scores) {
        // determines whether the score was inserted
        boolean inserted = false;
        
        // add score to the array of scores
        if (score > scores[9]) {
            for (int i = 9; i >= 0 && !inserted; i--) {
                try {
                    if (scores[i - 1] >= score) {
                        scores[i] = score;
                        inserted = true;
                    }
                    else {
                        scores[i] = scores[i - 1];
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    scores[0] = score;
                }
            }
        }
        
        return scores;
    }
    
    /**
     * Description: plays the game
     * Input: int[] scores
     */
    public static void playGame(int[] scores) {
        // removes all characters typed before game started
        while (PennDraw.hasNextKeyTyped()) {
            PennDraw.nextKeyTyped();
        }
        
        // creates initial screen including snake, food, and text
        int score = 0;
        PennDraw.clear();
        Snake serpent = new Snake();
        boolean[][] occupied = serpent.occupy();
        Food candy = new Food(occupied, serpent);
        drawBoard(serpent, candy);
        PennDraw.setPenColor();
        PennDraw.setFontSize(40);
        PennDraw.text(9.5, 20.5, "Press Any Key to Start");
        
        /** 
         * determines whether the game is finished and if the first directional
         * key was pressed
         */
        boolean lose = true;
        boolean win = false;
        
        // enable animation
        PennDraw.enableAnimation(10);
        
        // integer that counts how many frames the snake has been growing
        int numGain = 0;
        
        // starts the game when any key is pressed
        while (lose) {
            if (PennDraw.hasNextKeyTyped()) {
                PennDraw.clear();
                drawBoard(serpent, candy);
                lose = false;
            }
        }
        
        // continous loop until the game has ended
        while (!lose && !win) {       
            /*
             * determines if the direction has been changed
             * w,s,a,d represent up, down, left, and right respectively
             * a snake cannot reverse direction unless it is of length 1
             */
            if (PennDraw.hasNextKeyTyped()) {
                char c = PennDraw.nextKeyTyped();
                serpent.changeDirec(c);
            }
            
            // moves snake in the appropriate direction
            lose = serpent.move();
            int headX = serpent.getHead().getX();
            int headY = serpent.getHead().getY();
            System.out.println(headX + " " + headY + " " + serpent.getHead().getNext());
            
            // determines if a snake ate the food
            if (headX == candy.getX() && headY == candy.getY()) {
                /**
                 * removes the tail if necessary and increments numGain so that 
                 * eating food results in a length increase of 3
                 */
                if (numGain == 0) {
                    numGain += 4;
                    serpent.removeTail();
                }
                else {
                    numGain += 3;
                }
                
                // spawns food at a new location
                occupied = serpent.occupy();
                candy = new Food(occupied, serpent);
                
                // add to score
                score += 10;
            }
            
            /**
             * if the snake is not growing, remove the tail
             * otherwise, decrement numGain
             */
            if (numGain == 0) {
                serpent.removeTail();
            }
            else {
                numGain--;
            }
            // determines if the game is won or lost
            if (!lose) {
                win = victory(serpent);
                lose = defeat(serpent);
            }
            
            
            // do not advance animation if the game is lost
            if (!lose) {
                drawBoard(serpent, candy);
                PennDraw.setPenColor();
                PennDraw.text(16, 20.5, "Score: " + score);
                PennDraw.advance();
            }
            
        }
        
        // disable animation when game is finished
        PennDraw.disableAnimation();
        
        // updates scores
        scores = addScore(score, scores);
        
        // draw end game screen
        if (lose) {
            drawEnd(scores, true);
        }
        else {
            drawEnd(scores, false);
        }
    }
    
    /**
     * Description: Converts an array of scores to an array of bytes
     * Input: int[] scores
     * Output: byte[]
     */
    public static byte[] toByte(int[] scores) {  
        // converts to string
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += scores[i] + " " + '\n';
        }
        
        // converts to bytes
        byte[] b = str.getBytes();

        return b;
    }
    
    public static void main(String[] args) {
        // sets canvas size and scale
        PennDraw.setCanvasSize(800, 880);
        PennDraw.setXscale(-0.5, 19.5);
        PennDraw.setYscale(-0.5, 21.5);
        
        // creates an array of scores
        int[] scores;
        
        // reads the list of top 10 scores from a file 
        In inStream = new In("highScores.txt");
        try {
            scores = inStream.readAllInts();
            
            // throws RuntimeException if there are not exactly 10 scores
            if (scores.length != 10) {
                throw new RuntimeException("ERROR: There are " + 
                                           scores.length + 
                                           " scores rather than 10");
            }
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("ERROR: File contains non-integer " +
                                       "characters");
        }
        
        // draw starting screen
        drawStart(scores);
    }
}