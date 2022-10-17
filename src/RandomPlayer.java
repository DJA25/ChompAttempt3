import java.awt.*;

/***
 * This class runs when the "Random Player" button is pressed.
 * The computer will make a random move.
 */

public class RandomPlayer {

    public Chip[][] gameBoard;

    public Point move(Chip[][] pBoard){

        System.out.println("Random Player Move");

        gameBoard = pBoard;
        Point myMove = new Point(0,0);
        //return a random move in the form of x, y or row, column

        int randomCol;
        int randomRow;

        do {
            randomRow = (int) (Math.random() * 10);
            randomCol = (int) (Math.random() * 10);
        } while(!gameBoard[randomRow][randomCol].isAlive);

        myMove = new Point(randomRow,randomCol);
        return myMove;
    }

}
