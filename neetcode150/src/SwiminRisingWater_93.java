import java.util.*;

public class SwiminRisingWater_93 {
    public static void main(String[] args) {
        int[][] grid = {
//                {0,1,2,10},
//                {9,14,4,13},
//                {12,3,8,15},
//                {11,5,7,6}
                {3,2},
                {0,1}
        };
        System.out.println(swimInWater_5(grid));
    }


    /**
     * Method 1: 暴力法 O(4^(n^2))
     * */
//    private static int N;
//    private static int ans = Integer.MAX_VALUE;
//    private static int[][] dirs = {
//            {-1, 0},
//            {1, 0},
//            {0,-1},
//            {0,1}
//    };
//
//    public static int swimInWater(int[][] grid) {
//        N = grid.length;
//        boolean[][] visit = new boolean[N][N];
//        dfs(grid, 0, 0, 0, visit);
//        return ans;
//    }
//
//    private static void dfs(int[][] grid, int x, int y, int time, boolean[][] visit) {
//        if(!inArea(x, y) || visit[x][y]) return;
//
//        if(x == N-1 && y == N-1) {
//            // 找到了一条路径
//            ans = Math.min(ans, Math.max(grid[x][y], time));
//            return;
//        }
//
//        visit[x][y] = true;
//        time = Math.max(time, grid[x][y]);
//
//        for(int[] dir: dirs) {
//            int newX = x+dir[0], newY = y+dir[1];
//            dfs(grid, newX, newY, time, visit);
//        }
//        visit[x][y] = false;
//    }
//
//    private static boolean inArea(int x, int y) {
//        return x > -1 && x < N && y > -1 && y < N;
//    }


    /**
     * Method 2: DFS
     * 从整个棋盘找到最大和最小值，time 一定落在 [minH, maxH] 区间
     * 对 [minH, maxH] 进行 dfs, 如果能到达 (n-1,n-1), 则可以直接返回路径
     * */
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,-1},
//            {0,1}
//    };
//
//    public static int swimInWater_2(int[][] grid) {
//        int n = grid.length;
//        int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
//
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                minH = Math.min(minH, grid[i][j]);
//                maxH = Math.max(maxH, grid[i][j]);
//            }
//        }
//
//        boolean[][] visit = new boolean[n][n];
//
//        for(int k=minH;k<=maxH;k++) {
//            boolean res = dfs(grid, 0, 0, visit, k);
//            if(res) return k;
//
//            for (boolean[] v : visit)
//                Arrays.fill(v, false);
//        }
//
//        return -1;
//    }
//
//    private static boolean dfs(int[][] grid, int x, int y, boolean[][] visit, int time) {
//        if(!inArea(grid,x,y) || visit[x][y] || grid[x][y] > time) return false;
//
//        if(x == grid.length-1 && y == grid.length-1) return true;
//
//        visit[x][y] = true;
//
//        for(int[] dir:dirs) {
//            int newX = x+dir[0], newY=y+dir[1];
//            if(dfs(grid,newX,newY,visit,time)) return true;
//        }
//
//        visit[x][y] = false;
//        return false;
//    }
//
//    private static boolean inArea(int[][] grid, int x, int y) {
//        int n = grid.length;
//        return x > -1 && x < n && y > -1 && y < n;
//    }
//
    /**
     * Method 3: Binary Search + DFS
     * O(n^2logn)
     * */
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,-1},
//            {0,1}
//    };
//    public static int swimInWater_3(int[][] grid) {
//        int n = grid.length;
//        int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
//
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                minH = Math.min(minH, grid[i][j]);
//                maxH = Math.max(maxH, grid[i][j]);
//            }
//        }
//
//        boolean[][] visit;
//        int left = minH, right = maxH;
//
//        while(left < right) {
//            int mid = left + right >> 1;
//            visit = new boolean[n][n];
//
//            boolean res = dfs(grid, 0, 0, mid, visit);
//
//            if(res) right = mid;
//            else left = mid+1;
//
//        }
//        return left;
//    }
//
//    /**
//     * grid[x][y] 以 time 是否能到达 (n-1, n-1)
//     * */
//    private static boolean dfs(int[][] grid, int x, int y, int time, boolean[][] visit) {
//        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visit[x][y] || grid[x][y] > time) return false;
//
//        if(x == grid.length-1 && y == grid.length-1) return true;
//
//        visit[x][y] = true;
//
//        for(int[] dir: dirs) {
//            int newX = x+dir[0], newY = y+dir[1];
//            if(dfs(grid, newX, newY, time, visit)) return true;
//        }
//        visit[x][y] = false;
//
//        return false;
//    }

    /**
     * Method 4: Dijkstra Algorithm
     * grid[n-1][n-1] 到 grid[0][0] 的最短距离
     * */
//    private static int INF = Integer.MAX_VALUE;
//    private static int[][] dirs = {
//            {-1,0},
//            {1,0},
//            {0,-1},
//            {0,1}
//    };
//    public static int swimInWater_4(int[][] grid) {
//        int n = grid.length;
//        int[][] minTime = new int[n][n];
//        for(int[] m: minTime)
//            Arrays.fill(m, INF);
//        minTime[0][0] = 0;
//
//        // 这个节点的邻居是否已经被加入到 minHeap 了
//        boolean[][] isInTree = new boolean[n][n];
//
//        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
//        // time, x, y
//        minHeap.offer(new int[] {0, 0, 0});
//
//        while(!minHeap.isEmpty()) {
//            int[] cur = minHeap.poll();
//            // 到 (x,y) 的当前时间
//            int w = cur[0], x = cur[1], y = cur[2];
//
//            // 加入剪枝
//            if(x == n-1 && y == n-1) return w;
//
//            if(w > minTime[x][y]) continue;
//
//            isInTree[x][y] = true;
//            minTime[x][y] = Math.max(w, grid[x][y]);
//
//            // 把它的邻居加入进来
//            for(int[] dir: dirs) {
//                int newX = x+dir[0], newY = y+dir[1];
//
//                // 在还没加入 mst 中的节点中找
//                if(newX > -1 && newX < n && newY > -1 && newY < n && !isInTree[newX][newY]) {
//                    // 到 (newX, newY) 的时间至少是多少
//                    int time = Math.max(minTime[x][y], grid[newX][newY]);
//
//                    if(time < minTime[newX][newY]) {
//                        minTime[newX][newY] = time;
//                        minHeap.offer(new int[] {time, newX, newY});
//                    }
//                }
//            }
//        }
//        return minTime[n-1][n-1];
//    }


    /**
     * Method 4: Kruskal Algorithm, (0,0) 和 (n-1, n-1) 成为同个连通图
     * */
    private static int[][] dirs = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    public static int swimInWater_5(int[][] grid) {
        int n = grid.length;

        // 存储每一个点最早能到的时间，x, y
        List<int[]> positions = new ArrayList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                positions.add(new int[]{grid[i][j], i, j});
            }
        }

        // sort positions
        Collections.sort(positions, (a,b) -> a[0]-b[0]);

        UnionFind unionFind = new UnionFind(n*n);

        // 选择最短的边
        for(int[] pos: positions) {
            int time = pos[0], x = pos[1], y = pos[2];

            // 把它的邻居边能连的连起来
            for(int[] dir: dirs) {
                int newX = x+dir[0], newY = y+dir[1];
                if(newX > -1 && newX < n && newY > -1 && newY < n && grid[newX][newY] <= time) {
                    // 能被访问到，连起来
                    unionFind.union(x*n+y, newX*n+newY);
                }

                if(unionFind.isConnect(0, n*n-1)) return time;
            }
        }
        return n*n;

    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        int cc;

        public UnionFind(int n) {
            parent = new int[n];
            Arrays.setAll(parent, i->i);

            rank = new int[n];
            Arrays.fill(rank, 1);

            cc = n;
        }
        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public boolean isConnect(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) return 0;
            if(x < y) {
                parent[y] = x;
                rank[x] += rank[y];
            } else {
                parent[x] = y;
                rank[y] += rank[x];
            }
            cc--;
            return 1;
        }
    }
}
