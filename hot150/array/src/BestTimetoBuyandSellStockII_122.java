public class BestTimetoBuyandSellStockII_122 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        System.out.println(maxProfit_2(prices));
    }
    /**
     * Method 1: sliding window
     * */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int i = 0;
        int profit = 0;
        while(i < n) {
            if(i+1 < n && prices[i+1] <= prices[i]) {
                i++;
                continue;
            }

            // prices[i] < prices[i+1]
            int start = i;
            i++;
            while(i < n && prices[i] >= prices[i-1]) i++;

            // 退出循环后，prices[i] < prices[i-1]
            profit += prices[i-1] - prices[start];
        }
        return profit;
    }


    /**
     * Method 2: dp
     * */
    public static int maxProfit_2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // dp[i][0]: 在第i天不持有股票的收益
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<n;i++) {
            // 第i天不持有股票的收益 = max(第i-1天持有股票的收益+prices[i], 第i-1天不持有股票的收益)
            dp[i][0] = Math.max(dp[i-1][1]+prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0]-prices[i], dp[i-1][1]);
        }
        return dp[n-1][0];
    }
}
