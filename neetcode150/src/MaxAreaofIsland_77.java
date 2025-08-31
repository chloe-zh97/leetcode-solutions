import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxAreaofIsland_77 {
    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        ans = maxAreaOfIsland_2(grid);
        System.out.println(ans);
    }

//    private static int ans;
//    private static int m;
//    private static int n;
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,-1},
//            {0,1}
//    };
//
//    public static int maxAreaOfIsland(int[][] grid) {
//        m = grid.length;
//        n = grid[0].length;
//        ans = 0;
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(grid[i][j] == 1) {
//                    ans = Math.max(dfs(grid, i, j), ans);
//                }
//            }
//        }
//        return ans;
//    }
//
//    /**
//     * dfs 返回 grid[i][j] 连通的面积
//     * */
//    private static int dfs(int[][] grid, int row, int col) {
//        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0) {
//            return 0;
//        }
//
//        int area = 1;
//        grid[row][col] = 0;
//
//        for(int k=0;k<dirs.length;k++) {
//            // 每个方向连通的面积
//            int x = row + dirs[k][0], y = col + dirs[k][1];
//            area += dfs(grid, x, y);
//        }
//        return area;
//    }

    /**
     * Method 2: BFS
     * */
    private static int m;
    private static int n;
    private static int ans;
    private static int[][] dirs = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };

    public static int maxAreaOfIsland_2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        ans = 0;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1) {
                    int tmp = bfs(grid, i, j);
                    ans = Math.max(ans, tmp);
                }
            }
        }
        return ans;
    }

    private static int bfs(int[][] grid, int row, int col) {
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0) return 0;

        int area = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{row, col});
        area += 1;
        grid[row][col] = 0;

        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            for(int k=0;k<dirs.length;k++) {
                int x = cur[0] + dirs[k][0], y = cur[1]+ dirs[k][1];

                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    deque.offer(new int[]{x,y});
                    area+=1;
                    grid[x][y] = 0;
                }
            }
        }
        return area;
    }
}
