import java.util.ArrayDeque;
import java.util.Deque;

public class SurroundedRegions_83 {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'O'}
        };

        solve_3(board);
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Method 1: DFS
     * */
//    private static int m;
//    private static int n;
//    public static void solve(char[][] board) {
//        // 放大 board
//        char[][] chess = new char[board.length+2][board[0].length+2];
//        m = chess.length;
//        n = chess[0].length;
//
//        Arrays.fill(chess[0], 'O');
//        Arrays.fill(chess[m-1], 'O');
//        for(int i=0;i<m;i++) {
//            chess[i][0] = 'O';
//            chess[i][n-1] = 'O';
//        }
//
//        for(int i=1;i<m-1;i++) {
//            for(int j=1;j<n-1;j++) {
//                chess[i][j] = board[i-1][j-1];
//            }
//        }
//
//        boolean[][] visit = new boolean[m][n];
//
//        // dfs from boarder
//        for(int j=0;j<n;j++) {
//            dfs(chess, visit, 0, j);
//            dfs(chess, visit, m-1, j);
//        }
//
//        for(int i=0;i<m;i++) {
//            dfs(chess, visit, i, 0);
//            dfs(chess, visit, i, n-1);
//        }
//
//        // fill the board
//        for(int i=0;i<board.length;i++) {
//            for(int j=0;j<board[0].length;j++) {
//                if(!visit[i+1][j+1]) board[i][j] = 'X';
//                else board[i][j] = 'O';
//            }
//        }
//    }
//
//    private static void dfs(char[][] board, boolean[][] visit, int x, int y) {
//        if(!inArea(x,y) || visit[x][y] || board[x][y] != 'O') return;
//
//        visit[x][y] = true;
//
//        dfs(board, visit, x-1, y);
//        dfs(board, visit, x+1, y);
//        dfs(board, visit, x, y-1);
//        dfs(board, visit, x, y+1);
//    }
//
//    private static boolean inArea(int x, int y) {
//        return x >= 0 && x<m && y >= 0 && y < n;
//    }

    /**
     * Method 2: modify in-place
     * */
//    private static int m;
//    private static int n;
//
//    public static void solve_2(char[][] board) {
//        m = board.length;
//        n = board[0].length;
//
//        boolean[][] visit = new boolean[m][n];
//
//        for(int j=0;j<n;j++) {
//            dfs(board, visit, 0, j);
//            dfs(board, visit, m-1, j);
//        }
//
//        for(int i=1;i<m-1;i++) {
//            dfs(board, visit, i, 0);
//            dfs(board, visit, i, n-1);
//        }
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(board[i][j] == 'T') board[i][j] = 'O';
//                else board[i][j] = 'X';
//            }
//        }
//
//    }
//
//    private static void dfs(char[][] board, boolean[][] visit, int x, int y) {
//        if(!inArea(x, y) || visit[x][y] || board[x][y] != 'O') return;
//
//        board[x][y] = 'T';
//
//        dfs(board, visit, x-1, y);
//        dfs(board, visit, x+1, y);
//        dfs(board, visit, x, y-1);
//        dfs(board, visit, x, y+1);
//    }
//
//    private static boolean inArea(int x, int y) {
//        return x >= 0 && x < m && y >= 0 && y < n;
//    }

    /**
     * BFS
     * */
    public static void solve_3(char[][] board) {
        int m = board.length, n = board[0].length;

        Deque<int[]> deque = new ArrayDeque<>();
        for(int j=0;j<n;j++) {
            if(board[0][j] == 'O') {
                deque.offer(new int[]{0, j});
                board[0][j] = 'T';
            }
            if(board[m-1][j] == 'O') {
                deque.offer(new int[]{m-1, j});
                board[m-1][j] = 'T';
            }
        }

        for(int i=1;i<m-1;i++) {
            if(board[i][0] == 'O') {
                deque.offer(new int[]{i,0});
                board[i][0] = 'T';
            }
            if(board[i][n-1] == 'O') {
                deque.offer(new int[]{i, n-1});
                board[i][n-1] = 'T';
            }
        }


        int[][] dirs = {
                {-1, 0},
                {1, 0},
                {0,-1},
                {0,1}
        };

        while(!deque.isEmpty()) {
            int size = deque.size();
            for(int i=0;i<size;i++) {
                int[] cur = deque.poll();
                int x = cur[0], y = cur[1];

                for(int k=0;k<dirs.length;k++) {
                    int newX = x + dirs[k][0], newY = y+dirs[k][1];
                    if(newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O') {
                        board[newX][newY] = 'T';
                    }
                }
            }
        }

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] == 'T') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }

    }
}
