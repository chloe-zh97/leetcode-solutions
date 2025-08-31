import java.util.Arrays;

public class UniquePaths_108 {
    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths_6(m, n));
    }


    /**
     * Method 1: 暴力法
     * */
    public static int uniquePaths(int m, int n) {
        return dfs(0,0,m,n);
    }

    private static int dfs(int x, int y, int m, int n) {
        if(x < 0 || x >= m || y < 0 || y >= n) return 0;
        if(x == m-1 && y == n-1) return 1;

        return dfs(x,y+1, m, n) + dfs(x+1, y, m, n);
    }

    /**
     * Method 2: 记忆化搜索
     * */
    public static int uniquePaths_2(int m, int n) {
        int[][] memos = new int[m][n];
        for(int[] mm: memos) Arrays.fill(mm, -1);

        return dfs_2(0,0,m,n, memos);
    }

    private static int dfs_2(int x, int y, int m, int n, int[][] memos) {
        if(x < 0 || x >= m || y < 0 || y >= n) return 0;
        if(x == m-1 && y == n-1) return 1;

        if(memos[x][y] != -1) return memos[x][y];

        int res = dfs_2(x,y+1, m, n, memos) + dfs_2(x+1, y, m, n, memos);
        memos[x][y] = res;
        return memos[x][y];
    }

    /**
     * Method 3: 递推
     * */
    public static int uniquePaths_3(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        // dp[i+1][j+1]: (0,0) 到 (i,j) 的路径有多少条
        Arrays.fill(dp[1], 1);

        for(int i=1;i<=m;i++)
            dp[i][1] = 1;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i+1][j+1] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[m][n];
    }

    /**
     * Method 4: 压缩空间
     * */
    public static int uniquePaths_5(int m, int n) {
        int[] dp = new int[n+1];
        // 到第0行已经被初始化完成
        Arrays.fill(dp, 1);

        for(int i=1;i<m;i++) {
            int[] newdp = new int[n+1];

            for(int j=0;j<n;j++) {
                newdp[j+1] =newdp[j] + dp[j+1];
            }
            dp = newdp;
        }
        return dp[n];
    }

    /**
     * 上往下计算
     * */
    public static int uniquePaths_6(int m, int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        for(int i=1;i<m;i++) {
            // 第0列已经算过了
            for(int j=1;j<n;j++) {
                dp[j+1] = dp[j+1] + dp[j];
            }
        }
        return dp[n];
    }
}
