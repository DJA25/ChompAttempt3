import java.util.Comparator;
import java.util.HashSet;
import java.util.*;

public class Board implements Comparable<Board> {
    long key;
    Boolean winState = null;
    TreeSet<Board> prev = new TreeSet<>();
    TreeSet<Board> next = new TreeSet<>();
    int[] board;
    Board(long key, int[] board) {
        this.key = key;
        this.board = board;
    }
    Board(int[] board) {
        key = Board.boardToLong(board);
        this.board = board;
    }
    static long boardToLong(int[] arr) {
        int mult = 1;
        long ret = 0;
        for(int i = arr.length-1; i>=0; i--) {
            ret+=arr[i]*mult;
            mult*=10;
        }
        return ret;
    }
    static int[] longToBoard(long key) {
        int[] board = new int[10];
        for(int i = 9; i>=0; i--) {
            board[i] = (int)key%10;
            key/=10;

        }
        return board;
    }



    @Override
    public int compareTo(Board o) {
        if(this.key>0 && o.key<0) return 1;
        else if(this.key<0 && o.key>0) return -1;
        else if(this.key>o.key) return 1;
        else if (this.key == o.key) return 0;
        else return -1;
    }
}
