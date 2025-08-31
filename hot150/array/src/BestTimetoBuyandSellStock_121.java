public class BestTimetoBuyandSellStock_121 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }



    /**
     * Method 1: 模拟
     * */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int min = prices[0];
        int ans = 0;

        int i=0;
        while(i < n) {
            if(prices[i] <= min) {
                min = prices[i];
                i++;
            } else {
                // prices[i] > min, 更新最大值
                ans = Math.max(ans, prices[i] - min);
                i++;
            }
        }
        return ans;
    }

    /**
     * Method 2: 同时更新 cost 和 profit
     * */
    public static int maxProfit_2(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price: prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price-cost);
        }
        return profit;
    }

}
