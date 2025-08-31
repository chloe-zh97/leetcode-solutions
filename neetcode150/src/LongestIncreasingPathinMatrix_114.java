import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LongestIncreasingPathinMatrix_114 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2}
        };
        System.out.println(longestIncreasingPath_3(matrix));
    }

    /**
     * Method 1: 暴力法
     * */
    private static int m = 0;
    private static int n = 0;
    private static int[][] dirs = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };

    public static int longestIncreasingPath(int[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        boolean[][] visit;

        int res = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                visit = new boolean[m][n];
                res = Math.max(res, dfs(matrix, i, j, -1, visit));
            }

        }

        return res;
    }

    /**
     * 在 matrix[x][y] 位置，最长 increasing path 是多少
     * */
    private static int dfs(int[][] matrix, int x, int y, int pre ,boolean[][] visit) {
        if(!inArea(x,y)) return 0;
        if(visit[x][y]) return 0;

        if(matrix[x][y] <=  pre) return 0;

        visit[x][y] = true;

        int res = 0;
        for(int[] dir: dirs){
            int newX = x+dir[0], newY = y+dir[1];
            int d = 1 + dfs(matrix, newX, newY, matrix[x][y], visit);
            res = Math.max(d, res);
        }

        return res;
    }

    private static boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    /**
     * Method 2: 记忆化搜索
     * */

    public static int longestIncreasingPath_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] memos = new int[m][n];
        for(int[] mm:memos) Arrays.fill(mm, -1);

        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(memos[i][j] == -1) {
                    ans = Math.max(ans, dfs_2(matrix, i, j, -1, memos));
                }
            }
        }
        return ans;
    }

    /**
     * matrix[x][y] 周边能达到的最长距离
     * */
    private static int dfs_2(int[][] matrix, int x, int y, int pre, int[][] memos) {
        int m = matrix.length, n = matrix[0].length;
        // 越界了
        if(x < 0 || x >= m || y < 0 || y >= n) return 0;
        if(matrix[x][y] <=  pre) return 0;

        if(memos[x][y] != -1) return memos[x][y];

        int res = 0;

        res = Math.max(1 + dfs_2(matrix, x-1, y, matrix[x][y], memos), res);
        res = Math.max(1 + dfs_2(matrix, x+1, y, matrix[x][y], memos), res);
        res = Math.max(1 + dfs_2(matrix, x, y-1, matrix[x][y], memos), res);
        res = Math.max(1 + dfs_2(matrix, x, y+1, matrix[x][y], memos), res);

        memos[x][y] = res;
        return res;
    }

    /**
     * Method 3: 拓扑排序
     * 建立入度关系
     * */
    private static int longestIncreasingPath_3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // indegrees[i][j]: (i,j) 的入度
        int[][] indegrees = new int[m][n];

        int[][] dirs = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                for(int[] dir: dirs) {
                    int newX = i+dir[0], newY = j+dir[1];
                    // 四面八方能到 (x,y) 的点
                    if(newX > -1 && newX < m && newY > -1 && newY < n && matrix[i][j] < matrix[newX][newY]) {
                        indegrees[newX][newY]++;
                    }
                }
            }
        }

        // 将入度为0的点加入到队列中
        Deque<int[]> deque = new LinkedList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(indegrees[i][j] == 0) {
                    deque.offer(new int[]{i,j});
                }
            }
        }

        int ans = 0;
        while(!deque.isEmpty()) {
            int size = deque.size();
            for(int i=0;i<size;i++) {
                int[] cur = deque.poll();
                int xx = cur[0], yy = cur[1];
                // 这个点能到的四个方向
                for(int[] dir: dirs) {
                    int newX = xx+dir[0], newY = yy+dir[1];
                    if(newX > -1 && newX < m && newY > -1 && newY < n && matrix[xx][yy] < matrix[newX][newY]) {
                        // xx -> newX
                        indegrees[newX][newY]--;
                        if(indegrees[newX][newY] ==0) {
                            deque.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            ans++;
        }
        return ans;
    }



}
