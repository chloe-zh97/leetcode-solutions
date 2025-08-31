import java.util.Arrays;

public class ValidSudoku_36 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 模拟
     * */
    public static boolean isValidSudoku(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[] visit = new boolean[9];

        // check row
        for(int i=0;i<m;i++) {
            Arrays.fill(visit, false);

            for(int j=0;j<n;j++) {
                if(board[i][j] == '.') continue;
                int c = board[i][j] - '1';
                if(visit[c]) return false;

                visit[c] = true;
            }
        }

        // check col
        for(int j=0;j<n;j++) {
            Arrays.fill(visit, false);
            for(int i=0;i<m;i++) {
                if(board[i][j] == '.') continue;
                int c = board[i][j]-'1';
                if(visit[c]) return false;
                visit[c] = true;
            }
        }

        // check square
        int[][] start = {
                {0,0},{0,3},{0,6},
                {3,0},{3,3},{3,6},
                {6,0},{6,3},{6,6},
        };
        for(int k=0;k<start.length;k++) {
            int i = start[k][0], j = start[k][1];
            if(!checkSquare(board, i,j, 3)) return false;
        }

        return true;
    }

    private static boolean checkSquare(char[][] board, int x, int y, int len) {
        boolean[] visit = new boolean[9];
        for(int i=x;i<x+len;i++) {
            for(int j=y;j<y+len;j++) {
                if(board[i][j] == '.') continue;
                int c = board[i][j]-'1';
                if(visit[c]) return false;

                visit[c] = true;
            }
        }
        return true;
    }


    /**
     * Method 2: 优化，用 row[][], col[][], box[][] 存储访问情况
     * */
    public static boolean isValidSudoku_2(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];

        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(board[i][j] == '.') continue;

                int num = board[i][j] - '0';
                if(rows[i][num] || cols[j][num] || boxes[(i/3)*3+j/3][num]) return false;

                // fill in
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[(i/3)*3+j/3][num] = true;
            }
        }
        return true;
    }
}
