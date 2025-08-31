import java.util.Arrays;

public class BestTimetoBuyandSellStockwithCooldown_111 {
    public static void main(String[] args) {
        int[] prices = {1,3,4,0,4};
        System.out.println(maxProfit_4(prices));
    }

    /**
     * Method 1: dfs 暴力法
     * */
    public static int maxProfit(int[] prices) {
        return dfs(prices, 0, true);
    }

    /**
     * 在第 day 天获得的最大收益, boolean buy 表示是否可以买入
     * dfs(i): prices[i...n-1] 可获得的最大收益
     * */
    private static int dfs(int[] prices, int i, boolean buy) {
        int n = prices.length;
        if(i >= n) return 0;

        int res = 0;
        if(buy) {
            // 如果到达第 i 天，buy的状态是 true, 表示当前可以买入，当然你可以选择不买
            int cooldawn = dfs(prices, i+1, buy);
            int buying = dfs(prices, i+1, false) - prices[i];
            res = Math.max(cooldawn, buying);
        } else {
            // 当前状态不可以买入, 第 i 天售出
            int selling = dfs(prices, i+2, true) + prices[i];
            int cooldown = dfs(prices, i+1, buy);
            res = Math.max(selling, cooldown);
        }

        return res;
    }


    /**
     * Method 2: 改记忆化搜索
     * */
    public static int maxProfit_2(int[] prices) {
        int n = prices.length;
        int[][] memos = new int[n][2];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        return dfs_2(prices, 0, 1, memos);
    }

    private static int dfs_2(int[] prices, int i, int buy, int[][] memos) {
        int n =prices.length;
        if(i>=n) return 0;

        if(memos[i][buy] != -1) return memos[i][buy];

        int cooling = dfs_2(prices,i+1, buy, memos);
        int res;
        if(buy == 1) {
            // 表示可以买
            res = Math.max(cooling, dfs_2(prices, i+1, 0, memos) - prices[i]);
        } else {
            res = Math.max(cooling, dfs_2(prices, i+2, 1, memos) + prices[i]);
        }
        memos[i][buy] = res;
        return res;
    }


    /**
     * Method 3: 改 dp
     * */
    public static int maxProfit_3(int[] prices) {
        int n = prices.length;
        // dp[i][1]: 表示第 i 天是否可以执行 buy 操作
        int[][] dp = new int[n+2][2];

        for(int i=n-1;i>=0;i--) {
            for(int buying=1;buying>=0;buying--) {
                //int cooling = dfs_2(prices,i+1, buy, memos);
                int cooling = dp[i+1][buying];
                if(buying == 1) {
                    // res = Math.max(cooling, dfs_2(prices, i+1, 0, memos) - prices[i]);
                    dp[i][1] = Math.max(cooling, dp[i+1][0] - prices[i]);
                } else {
                    //res = Math.max(cooling, dfs_2(prices, i+2, 1, memos) + prices[i]);
                     dp[i][0] = Math.max(cooling, dp[i+2][1] + prices[i]);
                   // int sell = i+2 < n ? dp[i+2][1] + prices[i] : prices[i];
                    // dp[i][0] = Math.max(sell, cooling);
                }
            }
        }

//        for(int[] d: dp)
//            System.out.println(Arrays.toString(d));

        return dp[0][1];
    }

    /**
     * Method 4: 空间压缩
     * */

    public static int maxProfit_4(int[] prices) {
        int n = prices.length;
        int dp_buy = 0, dp_sell = 0, dp1_sell = 0, dp1_buy = 0, dp2_buy = 0;

        for(int i=n-1;i>=0;i--) {
            dp_buy = Math.max(dp1_buy, dp1_sell-prices[i]);
            dp_sell = Math.max(dp1_sell, dp2_buy+prices[i]);

            dp2_buy = dp1_buy;
            dp1_buy = dp_buy;
            dp1_sell = dp_sell;
        }

        return dp1_buy;
    }
}
