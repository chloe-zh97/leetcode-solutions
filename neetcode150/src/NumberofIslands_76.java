import java.util.ArrayDeque;
import java.util.Deque;

public class NumberofIslands_76 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '1'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands_2(grid));
    }


    /**
     * Method 1: DFS
     * */
//    private static int m;
//    private static int n;
//
//    public static int numIslands(char[][] grid) {
//        m = grid.length;
//        n = grid[0].length;
//
//        int ans = 0;
//
//        boolean[][] visit = new boolean[m][n];
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(grid[i][j] == '1' && !visit[i][j]) {
//                    backtracking(grid, i, j, visit);
//                    ans += 1;
//                }
//            }
//        }
//        return ans;
//    }
//
//    private static void backtracking(char[][] grid, int row, int col, boolean[][] visit) {
//        // 越界
//        if(row < 0 || row >= m || col < 0 || col >= n) return;
//
//        // 访问过
//        if(visit[row][col] || grid[row][col] == '0') return;
//
//        visit[row][col] = true;
//        backtracking(grid, row+1, col, visit);
//        backtracking(grid, row-1, col, visit);
//        backtracking(grid, row, col+1, visit);
//        backtracking(grid, row, col-1, visit);
//    }


    /**
     * Method 2: BFS
     * */

    private static int m;
    private static int n;
    private static int[][] dirs = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };

    public static int numIslands_2(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == '1') {
                    bfs(grid, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    private static void bfs(char[][] grid, int row, int col) {
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != '1') return;

        // 只把 1 加入队列
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{row, col});
        // 标记为已经访问
        grid[row][col] = '.';

        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            for(int k=0;k<dirs.length;k++) {
                int x = cur[0] + dirs[k][0];
                int y = cur[1] + dirs[k][1];
                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    deque.offer(new int[]{x, y});
                    grid[x][y] = '.';
                }
            }
        }
    }
}
