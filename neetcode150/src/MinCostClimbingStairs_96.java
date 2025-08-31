import java.util.Arrays;

public class MinCostClimbingStairs_96 {
    public static void main(String[] args) {
        int[] cost = {1,2,1,2,1,1,1};
        System.out.println(minCostClimbingStairs_4(cost));
    }

    /**
     * Method 1: 暴力法
     * */
    public static int minCostClimbingStairs(int[] cost) {
        int cost0 = dfs(cost, 0, 0);
        int cost1 = dfs(cost, 1, 0);
        return Math.min(cost0, cost1);
    }

    /**
     * 从 i 位置跨到 top 最少需要花费多少
     * */
    private static int dfs(int[] cost, int i, int distance) {
        if(i >= cost.length) return distance;

        int cost1 = dfs(cost, i+1, distance+cost[i]);
        int cost2 = dfs(cost,i+2, distance+cost[i]);

        return Math.min(cost1, cost2);
    }

    /**
     * Method 2: 暴力法改记忆化搜索, 增加 memos 数组 超时
     * */
    public static int minCostClimbingStairs_2(int[] cost) {
        int n = cost.length;
        int[] memos = new int[n+5];
        Arrays.fill(memos, -1);

        int cost1 = dfs_2(cost, 0, memos);
        Arrays.fill(memos, -1);

        int cost2 = dfs_2(cost, 1, memos);
        return Math.min(cost1, cost2);
    }

    /**
     * 从 [i...n-1]要最少花费多少 cost
     * */
    private static int dfs_2(int[] cost, int i, int[] memos) {
        if(i >= cost.length) {
            return 0;
        }

        if(memos[i] != -1) return memos[i];

        int cost1 = dfs_2(cost, i+1, memos) + cost[i];
        int cost2 = dfs_2(cost, i+2, memos) + cost[i];
        // 存最小值
        memos[i] = Math.min(cost1, cost2);
        return  memos[i];
    }

    /**
     * Method 3: DP
     * dp[i]: 从 stair[i] 到 top 台阶的 cost 最小是多少
     * */
    public static int minCostClimbingStairs_3(int[] cost) {
        int INF = Integer.MAX_VALUE/2;
        int n = cost.length;
        if(n < 2) return 0;

        int[] dp = new int[n+5];
        Arrays.fill(dp, INF);
        dp[n-1] = cost[n-1];
        dp[n-2] = cost[n-2];

        for(int i=n-3;i>=0;i--) {
            dp[i] = Math.min(dp[i+1], dp[i+2]) + cost[i];
        }

        return Math.min(dp[0], dp[1]);
    }


    /**
     * Method 4: 优化，去除 dp 数组，改用变量
     * */
    public static int minCostClimbingStairs_4(int[] cost) {
        int n = cost.length;
        if(n < 2) return 0;

        int f0 = cost[n-1];
        int f1 = cost[n-2];

        for(int i=n-3;i>=0;i--) {
            int newF = Math.min(f0, f1) + cost[i];
            f0 = f1;
            f1 = newF;
        }
        return Math.min(f1, f0);
    }

}
