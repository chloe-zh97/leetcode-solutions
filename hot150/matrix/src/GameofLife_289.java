import java.util.Arrays;

public class GameofLife_289 {
    public static void main(String[] args) {
        int[][] board = {
                {1,1},
                {1,0}
        };
        gameOfLife_2(board);
        printMatrix(board);
    }
    public static void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        int[][] copy = new int[m+2][n+2];
        for(int i=0;i<copy.length;i++)
            Arrays.fill(copy[i], -1);

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)
                copy[i+1][j+1] = board[i][j];
        }

        // 在 board 上进行修改，在 copy 中进行判断
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                // 计算周围活细胞
                int cnt = countLiveCells(copy, i,j);
                if(copy[i][j] == 0) {
                    // 本身是死细胞
                    if(cnt == 3) board[i-1][j-1] = 1;
                } else {
                    if(cnt < 2 || cnt > 3) board[i-1][j-1] = 0;
                }
            }
        }
    }

    /**
     * 计算 board[i][j] 周围有几个活细胞
     * */
    private static int countLiveCells(int[][] board, int x, int y) {
        int[][] dirs = {
                {-1,0},
                {-1,1},
                {0,1},
                {1,1},
                {1,0},
                {1,-1},
                {0,-1},
                {-1,-1}
        };
        int m = board.length, n = board[0].length;
        int cnt = 0;
        for(int k=0;k<dirs.length;k++) {
            int i = x + dirs[k][0], j = y + dirs[k][1];
            if(i < 0 || i >= m || j < 0 || j >= n) continue;
            if(board[i][j] == 1) cnt += 1;
        }
        return cnt;
    }

    /**
     * Method 2: 优化，位运算，用 00, 01表示 board 的初始状态，高位表示下一代的状态
     * */
    public static void gameOfLife_2(int[][] board) {
        int m = board.length, n = board[0].length;

        // 遍历整个 board, 更新 board 的状态
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int cnt = countLiveCells_2(board, i,j);
                if(((board[i][j] & 1) == 1 && cnt >= 2 && cnt <= 3) ||
                        ((board[i][j] & 1) == 0 && cnt == 3)) {
                    board[i][j] |= 1 << 1;
                }
            }
        }

        // 还原
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private static int countLiveCells_2(int[][] board, int x, int y) {
        int m = board.length, n = board[0].length;
        int[][] dirs = {
                {-1,0}, {-1,1}, {0,1},
                {1,1}, {1,0}, {1,-1},
                {0,-1}, {-1,-1}
        };
        int cnt = 0;
        for(int k=0;k<dirs.length;k++) {
            int i = x + dirs[k][0], j = y + dirs[k][1];
            if(i < 0 || i >= m || j < 0 || j >= n) continue;
            cnt += board[i][j] & 1;
        }
        return cnt;
    }



    private static void printMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
