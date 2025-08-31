public class ValidSudoku_8 {
    public static void main(String[] args) {
        char[][] board = {
                {}

        };
    }

    public static boolean isValidSudoku(char[][] board) {
        int m = board.length, n = board[0].length;

        int[][] rows = new int[9][10];
        int[][] cols = new int[9][10];
        int[][] dias = new int[9][10];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] == '.') continue;

                int c = board[i][j] - '0';

                // check rows
                if(rows[i][c] > 0) return false;
                if(cols[j][c] > 0) return false;

                int id = 3*(i/3)+j/3;
                if(dias[id][c] > 0) return false;

                rows[i][c] = 1;
                cols[j][c] = 1;
                dias[id][c] = 1;
            }
        }
        return true;
    }

    /**
     * 优化：比特位压缩
     * */
    public static boolean isValidSudoku_2(char[][] board) {
        int m = board.length, n = board[0].length;

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] dias = new int[9];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] == '.') continue;

                int val = board[i][j] - '1';

                if(rows[i]>0 && (rows[i] & (1 << val)) > 0) return false;
                if(cols[j]>0 && (cols[j] & (1 << val)) > 0) return false;
                // 一定要加 括号
                int id = 3*(i/3)+j/3;
                if(dias[id]>0 && (dias[id] & (1 << val)) > 0) return false;

                rows[i] |= (1 << val);
                cols[j] |= (1 << val);
                dias[id] |= (1 << val);
            }
        }
        return true;
    }
}
