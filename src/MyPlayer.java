import java.awt.*;
import java.util.*;
public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public TreeMap<Long, Board> findBoards = new TreeMap<>();
    public MyPlayer() {
        columns = new int[10];
        int n = 10;

        for(int i = 0; i<n; i++) {
            int last = i;
            for(int j = 1; j<n; j++) {

            }
            for(int j = i; j>=0; j--) {
                for(int k = j; k>=0; k--) {
                    Board b = new Board(new int[]{i,j,k});
                    findBoards.put(b.key, b);
                }
            }
            System.out.println();
        }
        for(Long l: allBoards(new int[]{3, 0, 0})) System.out.println(l);

        for(Board b: findBoards.values()) {
            System.out.print("Initial Board: ");printArray(b.board);
            System.out.println();
            for(Long l: allBoards(b.board)) {
//                System.out.println(l);
                b.next.add(findBoards.get(l));
                printArray(findBoards.get(l).board);
                findBoards.get(l).prev.add(b);
            }
            System.out.println();
            System.out.println();
        }
        int[] initial = new int[n];
        initial[0] = 1;
//        TEMPORARY:
        initial = new int[3];
        initial[0] = 1;
        long l = Board.boardToLong(initial);
        System.out.println("wtf: " + findBoards.get(l));
        getWinningStates(findBoards.get(l));

        System.out.println();
        System.out.println();
        int[] test = {3, 3, 3};
//        ArrayList<long[]> x = allBoards(test);
        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */

        for(Board b: findBoards.values()) {
            System.out.println(b.key);
            System.out.println(b.winState);
            System.out.println();
        }
        for(Board b: findBoards.get((long) 20000).next) {
            System.out.println(b.key);
        }
        int[] square = new int[n];
        for(int i = 0; i<n; i++) square[i] = n;
        l = Board.boardToLong(square);
        Board cur = findBoards.get(l);

    }
    public void getWinningStates(Board losing) {
        losing.winState = false;
        for(Board b: losing.prev) {
            b.winState = true;
        }
        for(Board b: losing.prev) {
            getLosingStates(b);
        }
    }
    public void getLosingStates(Board winning) {
        for(Board b: winning.prev) {
            if(losing(b)) {
                b.winState=false;
                getWinningStates(b);
            }
        }
    }
    public boolean losing(Board b) {
        for(Board winning: b.next) {
            if(winning.winState == null) return false;
            if(winning.winState == false) {
                return false;
            }

        }
        return true;
    }
    public ArrayList<Long> allBoards(int[] curBoard) {
        ArrayList<Long> ret = new ArrayList<>();
        for(int i = 0; i< curBoard.length; i++) {
            for(int j = 0; j<curBoard[i]; j++) {
                int[] newBoard = copyArray(curBoard);
                for(int k = i; k<curBoard.length; k++) {
                    newBoard[k] = Math.min(newBoard[k],j);
                }
//                printArray(newBoard);
                Long result = Board.boardToLong(newBoard);
                if(result>0)ret.add(Board.boardToLong(newBoard));
            }
        }
        ret.remove((Integer)0);
        return ret;
    }
    public void printArray(int[] arr) {
        for(int i = 0; i<arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
    public int[] copyArray(int[] old) {
        int[] n = new int[old.length];
        for(int i = 0; i<old.length; i++) {
            n[i]=old[i];
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

        Point myMove = new Point(row, column);
        return myMove;
    }

}
