import java.util.Arrays;

public class CoinChangeII_110 {
    public static void main(String[] args) {
        int amount = 4;
        int[] coins = {1,2,3};
        System.out.println(change_4(amount, coins));
    }

    public static int change(int amount, int[] coins) {
        int n = coins.length;

        int[][] memos = new int[n][amount+1];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        for(int i=0;i<n;i++)
            memos[i][0] = 1;

        return dfs(coins, 0, amount, memos);

    }

    /**
     * 在 coins[i...n-1]中选硬币，达到amount数量的种类
     * */
    private static int dfs(int[] coins, int i, int amount, int[][] memos) {
        int n = coins.length;

        if(amount == 0) return 1;

        if(amount < 0 || i >= n) return 0;

        if(memos[i][amount] != -1) return memos[i][amount];

        int res = dfs(coins, i, amount-coins[i], memos)+dfs(coins, i+1, amount, memos);

        memos[i][amount] = res;
        return res;
    }

    /**
     * Method 2： 递推
     * */
    public static int change_2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int i=0;i<dp.length;i++)
            dp[i][0] = 1;

        // dp[i][j] = dp[i][j-coins[i]] + dp[i+1][j]
        for(int i=n-1;i>=0;i--) {
            for(int j=0;j<=amount;j++) {
                // 不选 coins[i]
                dp[i][j] = dp[i+1][j];
                if(j >= coins[i])
                    dp[i][j] += dp[i][j-coins[i]];
            }
        }
        return dp[0][amount];
    }

    /**
     * Method 3: 滚动数组
     * */
    public static int change_3(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int i=0;i<n;i++) {
            int[] newdp = new int[amount+1];

            for(int j=0;j<=amount;j++) {
                newdp[j] = dp[j];
                if(j >= coins[i])
                    newdp[j] += newdp[j-coins[i]];
            }

            dp = newdp;
        }

        return dp[amount];
    }

    /**
     * Method 4: 最优解法
     * */
    public static int change_4(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int i=0;i<n;i++) {
            for(int j=0;j<=amount;j++) {
                if(j >= coins[i]) {
                    dp[j] += dp[j-coins[i]];
                }
            }
        }

        return dp[amount];
    }

}
