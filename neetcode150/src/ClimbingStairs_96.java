import java.util.Arrays;

public class ClimbingStairs_96 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(climbStairs(n));
    }

    /**
     * Method 1: DFS 不加记忆化搜索会超时
     * */
    public static int climbStairs(int n) {
        int[] memos = new int[n+5];
        Arrays.fill(memos, -1);

        return dfs(n, memos);
    }

    private static int dfs(int n, int[] memos) {
        if(n < 0) return 0;
        if(n < 3) return n;

        if(memos[n] != -1) return memos[n];

        int res = dfs(n-1, memos) + dfs(n-2, memos);
        memos[n] = res;

        return res;
    }

    /**
     * Method 2: 记忆话搜索改递归
     * dfs 改为 dp[]
     * */
    public static int climbStairs_2(int n) {
        int[] dp = new int[n+5];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3;i<=n;i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * Method 3: 优化，改为两个变量
     * */
    public static int climbStairs_3(int n) {
        int f0 = 1, f1 = 2;
        for(int i=3;i<=n;i++) {
            int newF = f1 + f0;
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }
}
