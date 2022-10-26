import java.awt.*;
import java.util.*;
public class MyPlayer {
    //    Combine winnning and allboards for efficiency
// check algo accuracy
    public Chip[][] gameBoard;
    public int[] columns;
    public HashMap<Long, Board> findBoards = new HashMap<>();
    public HashMap<Long, Boolean> winners = new HashMap<>();
    public int n;
    public MyPlayer() {
        columns = new int[10];
        n = 10;
        int[] start = new int[n];
        start[0] = 1;
        winners.put(Board.boardToLong(start), false);
        genAll(start, 0);

//
//        for(int i = 0; i<n; i++) {
//            int last = i;
//            for(int j = i; j>=0; j--) {
//                for(int k = j; k>=0; k--) {
//                    Board b = new Board(new int[]{i,j,k});
//                    findBoards.put(b.key, b);
//                }
//            }
//            System.out.println();
//        }
//        findBoards.remove((long) 0);
//        System.out.println(findBoards.size());
//
////        int in = 0;
////        for(Board b: findBoards.values()) {
////            in++;
////            System.out.println(b.key);
////            if(in>1000) break;
////        }
//        for(Board b: findBoards.values()) {
////            System.out.print("Initial Board: ");printArray(b.board);
////            System.out.println();
//            for(Long l: allBoards(b.board)) {
////                System.out.println(l);
//                b.next.add(findBoards.get(l));
////                printArray(findBoards.get(l).board);
//                findBoards.get(l).prev.add(b);
//            }
////            System.out.println();
////            System.out.println();
//        }
//
//
//
//        int[] initial = new int[n];
//        initial[0] = 1;
////        TEMPORARY:
////        initial = new int[3];
////        initial[0] = 1;
//        long l = Board.boardToLong(initial);
//        System.out.println("wtf: " + findBoards.get(l));
//
//
//
//        getWinningStates(findBoards.get(l));
//
//        System.out.println();
//        System.out.println();
//        int[] test = {3, 3, 3};
////        ArrayList<long[]> x = allBoards(test);
//        /***
//         * This code will run just once, when the game opens.
//         * Add your code here.
//         */
//
////        for(Board b: findBoards.values()) {
////            System.out.println(b.key);
////            System.out.println(b.winState);
////            System.out.println();
////        }
////        for(Board b: findBoards.get((long) 20000).next) {
////            System.out.println(b.key);
////        }
//        int[] square = new int[n];
//        square[0] = 1;
//        for(int i = 0; i<n; i++) square[i] = n;
//
//        l = Board.boardToLong(square);
//        Board cur = findBoards.get(l);
//        System.out.println(cur);
//        System.out.println("FML");
//        System.out.println(cur.winState);
////        for(Board prev: cur.prev) {
////            printArray(prev.board);
////        }
//        System.out.println("FML");
//        System.out.println();
//        printPath(cur);
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        long best = Board.boardToLong(new int[]{10, 1, 1, 1, 1, 1, 1, 1, 1, 1});
//        Board investigate = findBoards.get(best);
//        printNext(investigate);


//        printNext(cur);
//        for(Board b: findBoards.values()) {
//            if(!b.winState){
//                printArray(b.board);
//                System.out.println(b.winState);
//            }
//        }
    }


//    public void printNext(Board start) {
//        for(Board b: start.next) {
//            if(!b.winState) {
//                printArray(b.board);
//                System.out.println(b.winState);
//            }
//        }
//    }
//
//    public void printPath(Board start) {
//        printArray(start.board);
//        System.out.println(start.winState);
//        System.out.println();
//        if (start.winState) {
//            for (Board b : start.next) {
//                if (!b.winState) {
//
//                    printPath(b);
//                    return;
//                }
//            }
//        } else if(start.next.size()>0) {
//            printPath(start.next.last());
//        }
//    }

    public void genAll(int[] board, int index) {
        if(index>=n) {
            winning(board);
            return;
        }
        int max;
        if(index == 0) max = n;
        else max = board[index-1];
        int min = 0;
        if(index==0) min++;
        for(int i = min; i<=max; i++) {
            int[] copy = copyArray(board);
            copy[index] = i;
            genAll(copy, index+1);
        }
    }

    public void winning(int[] board) {
        long key = Board.boardToLong(board);
        ArrayList<Long> neighbors = allBoards(board);
        for(long l: neighbors) {
            if(!winners.get(l)) {
                winners.put(key, true);
                return;
            }
        }
        winners.put(key, false);
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
        ret.remove((long)0);
        return ret;
    }
//    public void printArray(int[] arr) {
//        for(int i = 0; i<arr.length; i++) {
//            System.out.print(arr[i] + ", ");
//        }
//        System.out.println();
//    }
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
