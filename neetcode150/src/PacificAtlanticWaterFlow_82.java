import java.util.*;

public class PacificAtlanticWaterFlow_82 {
    public static void main(String[] args) {
        int[][] heights = {
                {4,2,7,3,4},
                {7,4,6,4,7},
                {6,3,5,3,6}
        };
        List<List<Integer>> ans = pacificAtlantic_2(heights);
        for(List<Integer> a: ans)
            System.out.println(a);
    }

    /**
     * Method 1: 两次遍历，BFS，将周围 >= 它高度的都标记为能访问
     * */
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,1},
//            {0,-1}
//    };
//    private static int m;
//    private static int n;
//
//    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
//        m = heights.length;
//        n = heights[0].length;
//
//        boolean[][] visit = new boolean[m][n];
//        List<List<Integer>> ans = new ArrayList<>();
//        Deque<int[]> deque = new ArrayDeque<>();
//
//        // left and top
//        for(int j=0;j<n;j++) {
//            deque.offer(new int[]{0,j});
//            visit[0][j] = true;
//        }
//
//        for(int i=1;i<m;i++) {
//            deque.offer(new int[]{i,0});
//            visit[i][0] = true;
//        }
//
//        bfs(deque, heights, visit);
//
//
//        // right and bottom
//        boolean[][] visit2 = new boolean[m][n];
//
//        for(int i=0;i<m;i++) {
//            deque.offer(new int[]{i, n-1});
//            visit2[i][n-1] = true;
//        }
//
//        for(int j=0;j<n-1;j++) {
//            deque.offer(new int[]{m-1, j});
//            visit2[m-1][j] = true;
//        }
//        bfs(deque, heights, visit2);
//
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(visit2[i][j] && visit[i][j])
//                    ans.add(Arrays.asList(i,j));
//            }
//        }
//
//        return ans;
//    }
//
//    private static void bfs(Deque<int[]> deque, int[][] heights, boolean[][] visit) {
//        while(!deque.isEmpty()) {
//            int size = deque.size();
//            for(int i=0;i<size;i++) {
//                int[] cur = deque.poll();
//                int x = cur[0], y = cur[1];
//                for(int k=0;k< dirs.length;k++) {
//                    int newX = x+dirs[k][0], newY = y+dirs[k][1];
//                    if(newX >=0 && newX < m && newY >= 0 && newY < n &&
//                            !visit[newX][newY] && heights[newX][newY] >= heights[x][y]) {
//                        visit[newX][newY] = true;
//                        deque.offer(new int[]{newX,newY});
//                    }
//                }
//            }
//        }
//    }

    /**
     * Method 2: DFS
     * */
    private static int m;
    private static int n;
    private static final int INF = Integer.MAX_VALUE / 2;

    public static List<List<Integer>> pacificAtlantic_2(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        List<List<Integer>> ans = new LinkedList<>();

        boolean[][] visit = new boolean[m][n];
        boolean[][] visit2 = new boolean[m][n];

        // top and left
        for(int j=0;j<n;j++) {
            dfs(heights, visit, 0, j, -INF);
            dfs(heights, visit2, m-1, j, -INF);
        }

        for(int i=0;i<m;i++) {
            dfs(heights, visit, i, 0, -INF);
            dfs(heights, visit2, i, n-1, -INF);
        }

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(visit[i][j] && visit2[i][j]) {
                    ans.add(Arrays.asList(i,j));
                }
            }
        }
        return ans;
    }

    private static void dfs(int[][] heights, boolean[][] visit, int x, int y, int prev) {
        if(!inArea(x,y) || visit[x][y] || heights[x][y] < prev) return;

        visit[x][y] = true;

        dfs(heights, visit, x-1, y, heights[x][y]);
        dfs(heights, visit, x+1, y, heights[x][y]);
        dfs(heights, visit, x, y-1, heights[x][y]);
        dfs(heights, visit, x, y+1, heights[x][y]);
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >=0 && y < n;
    }



}
