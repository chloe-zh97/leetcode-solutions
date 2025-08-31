import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange_103 {
    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        int amount = 6249;
        System.out.println(coinChange_7(coins, amount));
    }

    /**
     * Method 1: dfs, 加入了 path
     * */
    private static int ans = Integer.MAX_VALUE;
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        List<Integer> path = new ArrayList<>();
        dfs(coins, 0, amount, path);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 从 coins[i...n-1] 中选，达到 amount 的最小花费银币数量
     * */
    private static int dfs(int[] coins, int i, int amount, List<Integer> path) {
        if(amount == 0) {
            ans = Math.min(ans, path.size());
            return path.size();
        }

        if(i >= coins.length || amount < 0) return Integer.MAX_VALUE;

        // choose coins[i]
        path.add(coins[i]);
        int choose = 1 + dfs(coins, i, amount-coins[i], path);

        path.remove(path.size()-1);
        int unChoose = dfs(coins, i+1, amount,path);

        return Math.min(choose, unChoose);
    }

    /**
     * Method 2: 优化 dfs, 去掉 path
     * */
    // 这里设置为 /2 ,防止溢出
    private static int INF = Integer.MAX_VALUE / 2;
    public static int coinChange_2(int[] coins, int amount) {
        Arrays.sort(coins);
        int ans =  dfs_2(coins, 0, amount);
        return ans == INF ? -1 : ans;
    }

    /**
     * dfs_2 返回 coins[i...n-1] 达到 amount 的最小花费硬币数量
     * */
    private static int dfs_2(int[] coins, int i, int amount) {
        if(amount == 0) return 0;

        // 永远不可能达到了
        if(i >= coins.length || amount < 0) return INF;

        // 小心溢出，1+INF
        int choose = 1 + dfs_2(coins, i, amount-coins[i]);
        int noChoose = dfs_2(coins, i+1, amount);

        return Math.min(choose, noChoose);
    }

    /**
     * Method 3: 继续优化，改记忆搜索
     * */
    private static int INF_3 = Integer.MAX_VALUE / 2;
    public static int coinChange_3(int[] coins, int amount) {
        Arrays.sort(coins);
        // memos[i]: 达到 i 现金所用的最小硬币数量
        int[] memos = new int[amount+5];
        Arrays.fill(memos, -1);

        int ans = dfs_3(coins, 0, amount, memos);
        return ans == INF_3 ? -1 : ans;
    }

    private static int dfs_3(int[] coins, int i, int amount ,int[] memos) {
        int n = coins.length;
        if(amount == 0) return 0;
        if(amount < 0 || i >= n) return INF_3;

        if(memos[amount] != -1) return memos[amount];

        int choose = 1 + dfs_3(coins, i, amount-coins[i], memos);
        int unChoose = dfs_3(coins, i+1, amount, memos);

        memos[amount] = Math.min(choose, unChoose);
        return memos[amount];
    }

    /**
     * Method 4: 动态规划
     * dp[i]: 达到 amount 金额所用的最少硬币数量
     * */
    private static int INF_4 = Integer.MAX_VALUE / 2;
    public static int coinChange_4(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, INF_4);
        dp[0] = 0;
//        for(int coin: coins) dp[coin] = 1;

        for(int i=0;i<=amount;i++) {
            // dp[i] = Math.min(dp[i-1], dp[i-2] ,dp[i-5]) + 1;
            int minCost = INF_4;
            for(int coin: coins) {
                if(i-coin >= 0 && minCost > dp[i-coin]) {
                    // 更新最小花费
                    minCost = dp[i-coin];
                }
            }
            if(minCost != INF_4) dp[i] = minCost  + 1;
        }

        return dp[amount] == INF_4 ? -1 : dp[amount];
    }


    /**
     * Method 5: 二维 dp, 改为完全背包模板
     * */
    public static int coinChange_5(int[] coins, int amount) {
        int n = coins.length;
        int INF = Integer.MAX_VALUE / 2;

        // dp[i][j]: 在 coins[0...i] 中选取 amount = j，用到的最少的硬币数量
        int[][] dp = new int[n][amount+1];
        for(int[] d: dp) Arrays.fill(d, INF);

        // init dp, 由 dp[i][j] = dp[i-1][j] 可知，第0行必须初始化
        for(int j=0;j<=amount;j++) {
            if(j % coins[0] == 0) dp[0][j] = j / coins[0];
        }

        // 第0列初始化
        for(int i=0;i<n;i++)
            dp[i][0] = 0;

        // 开始 dp, 外层物品
        for(int i=1;i<n;i++) {
            // 内层容量
            for(int j=0;j<=amount;j++) {
                if(coins[i] > j) {
                    // 当前的这个物品放不下
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 能放下 coins[i]
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i]]+1);
                }
            }
        }
//        for(int i=0;i<n;i++)
//            System.out.println(Arrays.toString(dp[i]));

        return dp[n-1][amount] == INF ? -1 : dp[n-1][amount];
    }

    /**
     * Method 6: 灵神模板
     * */
    public static int coinChange_6(int[] coins, int amount) {
        int n = coins.length;
        int INF = Integer.MAX_VALUE / 2;

        // 这里习惯用n+1, 其实填充的是 dp[1]开始的值，dp[i+1][j] 由同行的左边和上一行推倒而来
        int[][] dp = new int[n+1][amount+1];
        // 第0行填充完毕
        Arrays.fill(dp[0], INF);
        dp[0][0] = 0;

        // 开始 dp, 外层物品
        for(int i=0;i<n;i++) {
            for(int j=0;j<=amount;j++) {
                if(coins[i] > j) {
                    // 只能选择不放
                    dp[i+1][j] = dp[i][j];
                } else {
                    dp[i+1][j] = Math.min(dp[i+1][j-coins[i]]+1, dp[i][j]);
                }
            }
        }

        return dp[n][amount] == INF ? -1 : dp[n][amount];
    }

    /**
     * Method 7: 继续优化
     * 循环中只涉及了 dp[i+1] 和 dp[i], 用两个数组滚动更新
     * */
    public static int coinChange_7(int[] coins, int amount) {
        int n = coins.length;
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[2][amount+1];
        Arrays.fill(dp[0], INF);
        dp[0][0] = 0;

        // 外层物品
        for(int i=0;i<n;i++) {
            for(int j=0;j<=amount;j++) {
                if(coins[i] > j) {
                    dp[1][j] = dp[0][j];
                } else {
                    dp[1][j] = Math.min(dp[1][j-coins[i]]+1, dp[0][j]);
                }
            }
            dp[0] = dp[1];
        }
        return dp[0][amount] == INF ? -1 : dp[0][amount];
    }
}
