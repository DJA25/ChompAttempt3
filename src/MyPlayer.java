import java.awt.*;
import java.util.*;
public class MyPlayer {
    //    Combine winnning and allboards for efficiency
// check algo accuracy
    public Chip[][] gameBoard;
    public int[] columns;
    public HashMap<Long, Board> findBoards = new HashMap<>();
    public HashMap<Long, Boolean> winners = new HashMap<>();
    public HashMap<Long, int[]> moves = new HashMap<>();
    public int n;

    public MyPlayer() {
        columns = new int[10];
        n = 10;
        int[] start = new int[n];
        start[0] = 1;
        winners.put(Board.boardToLong(start), false);
        genAll2(start, 0);
        System.out.println("Done algorithm");
        int[] testBoard = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        long testKey = Board.boardToLong(testBoard);
        System.out.println(winners.get(testKey));

        System.out.println();

    }

    public void genAll(int[] board, int index) {
        if (index >= n) {
            allBoards(board);
            return;
        }
        int max;
        if (index == 0) max = n;
        else max = board[index - 1];
        int min = 0;
        if (index == 0) min++;
        for (int i = min; i <= max; i++) {
            int[] copy = copyArray(board);
            copy[index] = i;
            genAll(copy, index + 1);
        }
    }
    public void genAll2(int[] board, int index) {
        if (index >= n) {
            allBoards(board);
            return;
        }
        int max;
        if (index == 0) max = n;
        else max = board[index - 1];
        int min = 0;
        if (index == 0) min++;
        for (int i = min; i <= max; i++) {
            board[index] = i;
            genAll(board, index + 1);
        }
    }

    //    public void winning(int[] board) {
//        long key = Board.boardToLong(board);
//        ArrayList<Long> neighbors = allBoards(board);
//
//        for(long l: neighbors) {
//            if(!winners.get(l)) {
//                winners.put(key, true);
//                return;
//            }
//        }
//        winners.put(key, false);
//    }

    public void allBoards(int[] curBoard) {
        long key = Board.boardToLong(curBoard);
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[i]; j++) {
                int[] newBoard = copyArray(curBoard);
                for (int k = i; k < curBoard.length; k++) {
                    newBoard[k] = Math.min(newBoard[k], j);
                }
//                printArray(newBoard);
                Long result = Board.boardToLong(newBoard);
                if (result != 0) {
                    if (!winners.get(result)) {
                        moves.put(key, new int[]{i, j});
                        winners.put(key, true);
                        return;
                    }
                }
            }
        }
        winners.put(key, false);
        moves.put(key, losingAlgo(curBoard));
    }

    public int[] losingAlgo(int[] curBoard) {
        int index = 0;
        int row = 0;
        int column = 0;
        while (curBoard[index] > 1 && index < curBoard.length - 1) {
            index++;
        }
        if (index > 0) index--;
        if (index == curBoard.length - 2 && curBoard[index + 1] > 1) index++;
        if (index == 0) {
            int height = curBoard[index];
            int width = 0;
            while (curBoard[index] > 0 && index < curBoard.length - 1) {
                width++;
                index++;
            }
            index--;
            if (curBoard[curBoard.length - 1] > 0) index++;
            if (height > width) {
                column = 0;
                row = height - 1;
                return new int[]{column, row};
            }

        }
        column = index;
        row = curBoard[index] - 1;

        return new int[]{column, row};
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public int[] copyArray(int[] old) {
        int[] n = new int[old.length];
        for (int i = 0; i < old.length; i++) {
            n[i] = old[i];
        }
        return n;
    }

    public Point move(Chip[][] pBoard) {
        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;


        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */

        int[] board = getBoard(pBoard);
        long key = Board.boardToLong(board);
        int[] move = moves.get(key);
        row = move[1];
        column = move[0];
        System.out.println(winners.get(key));
        Point myMove = new Point(row, column);
        return myMove;
    }

    public int[] getBoard(Chip[][] pBoard) {
        int[] ret = new int[10];
        for (int i = 0; i < pBoard.length; i++) {
            for (int j = 0; j < pBoard.length; j++) {
                if (pBoard[i][j].isAlive) ret[j] = ret[j] + 1;
            }
        }
        return ret;
    }

}