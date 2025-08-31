import java.util.Arrays;

public class CoinChangeII_518 {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 500;
        System.out.println(change_2(amount, coins));
    }

    /**
     * Method 1: dfs
     * */
    public static int change(int amount, int[] coins) {
        return dfs(coins, 0, amount);
    }

    /**
     * 在 coins[i..n-1] 中选择，达到 amount 的种数
     * */
    private static int dfs(int[] coins, int i, int amount) {
        // 找到了一种
        if(amount == 0) return 1;
        if(amount < 0 || i >= coins.length) return 0;

        int choose = dfs(coins, i, amount-coins[i]);
        int unChoose = dfs(coins, i+1, amount);

        return choose+unChoose;
    }

    /**
     * Method 2: 动态规划 完全背包问题
     * coins = [1,2,5]
     * */
    public static int change_2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        // 初始化 dp[i][0], 容量为0的时候的方案数
        for(int i=0;i<n;i++)
            dp[i][0] = 0;

        for(int j=0;j<=amount;j++) {
            if(j % coins[0] == 0) dp[0][j] = 1;
        }

        // 左上角被覆盖了
        // dp[0][0] = 1;

        // 开始 dp, 先遍历物品，再遍历容量
        for(int i=1;i<n;i++) {
            for(int j=0;j<=amount;j++) {
                if(j < coins[i]) {
                    // 如果放不下当前物品
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i][j-coins[i]] + dp[i-1][j];
                }
            }
        }
        return dp[n-1][amount];

    }
}
