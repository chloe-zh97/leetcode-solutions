import java.util.ArrayDeque;
import java.util.Deque;

public class IslandsandTreasure_80 {
    public static void main(String[] args) {
        int[][] grid = {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        };

        islandsAndTreasure_2(grid);
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }


    /**
     * Method 1: BFS
     * */
//    private static int m;
//    private static int n;
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,-1},
//            {0,1}
//    };
//
//    public static void islandsAndTreasure(int[][] grid) {
//        m = grid.length;
//        n = grid[0].length;
//
//        boolean[][] visit;
//
//        // O(m)
//        for(int i=0;i<m;i++) {
//            // O(n)
//            for(int j=0;j<n;j++) {
//                // 遍历每个 0 位置开始 bfs
//                if(grid[i][j] == 0) {
//                    visit = new boolean[m][n];
//                    bfs(grid, i, j, visit);
//                }
//            }
//        }
//    }
//
//    private static void bfs(int[][] grid, int row, int col, boolean[][] visit) {
//        // 从 grid[row][col] 开始 BFS
//        if(!inArea(row, col) || grid[row][col] != 0 || visit[row][col]) return;
//
//        int distance = 0;
//        Deque<int[]> deque = new ArrayDeque<>();
//        deque.offer(new int[]{row, col, distance});
//        visit[row][col] = true;
//
//        // O(m*n)
//        while(!deque.isEmpty()) {
//            int[] cur = deque.poll();
//            // 获取当前坐标和到 0 的距离
//            int x = cur[0], y = cur[1], d = cur[2];
//            grid[x][y] = Math.min(d, grid[x][y]);
//
//            for(int k=0;k < dirs.length;k++) {
//                int newx = x+dirs[k][0], newy = y+dirs[k][1];
//
//                if(inArea(newx, newy) && grid[newx][newy] > 0 && !visit[newx][newy]) {
//                    // 在范围内，且是陆地，没有被访问过
//                    visit[newx][newy] = true;
//                    grid[newx][newy] = Math.min(grid[newx][newy], d+1);
//                    deque.offer(new int[]{newx, newy, grid[newx][newy]});
//                }
//            }
//        }
//    }
//
//    private static boolean inArea(int i, int j) {
//        return i >= 0 && i < m && j >= 0 && j < n;
//    }


    /**
     * Method 2: 多路 BFS
     * */

    private static int m;
    private static int n;
    private static int[][] dirs = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    private static int INF = 2147483647;

    public static void islandsAndTreasure_2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        Deque<int[]> deque = new ArrayDeque<>();

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 0) {
                    // 把所有的入口都加入到队列
                    deque.offer(new int[]{i,j});
                }
            }
        }

        bfs(grid, deque);
    }

    private static void bfs(int[][] grid, Deque<int[]> deque) {
        int distance = 0;

        while(!deque.isEmpty()) {
            // 获取这层节点
            int size = deque.size();
            for(int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                int x = cur[0], y = cur[1];
                // 出队列的时候改距离
                grid[x][y] = Math.min(grid[x][y], distance);

                for(int k=0;k<dirs.length;k++) {
                    int newX = cur[0]+dirs[k][0], newY = cur[1]+dirs[k][1];
                    if(inArea(newX, newY) && grid[newX][newY] == INF) {
                        deque.offer(new int[]{newX, newY});
                    }
                }
            }
            distance++;
        }
    }

    private static boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

}
