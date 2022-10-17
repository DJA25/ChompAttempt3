import java.util.HashSet;

public class Board {
    long key;
    Boolean winState = null;
    HashSet<Board> prev = new HashSet<>();
    HashSet<Board> next = new HashSet<>();
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
            mult*=100;
        }
        return ret;
    }

}
