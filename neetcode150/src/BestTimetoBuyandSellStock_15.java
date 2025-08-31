import java.util.Arrays;

public class BestTimetoBuyandSellStock_15 {
    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3};
        System.out.println(maxProfit_3(prices));
    }

    /**
     * Method 1: prefix + suffix
     * O(n) / O(n)
     * */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        // prefix[i+1]: prices[0...i]的最小值
        int[] prefix = new int[n+1];
        Arrays.fill(prefix, Integer.MAX_VALUE / 2);

        for(int i=0;i<n;i++)
            prefix[i+1] = Math.min(prefix[i], prices[i]);

        // suffix[i]: prices[i...n-1]的最大值
        int[] suffix = new int[n+1];
        Arrays.fill(suffix, Integer.MIN_VALUE/2);
        for(int i=n-1;i>=0;i--)
            suffix[i] = Math.max(suffix[i+1], prices[i]);

//        System.out.println("prefix:"+ Arrays.toString(prefix));
//        System.out.println("suffix:"+Arrays.toString(suffix));

        int ans = 0;
        // 遍历每一天可能buy的时机
        for(int i=0;i<n;i++) {
            // 在 prices[0...i-1]的时期买，在prices[i...n-1]时期卖
            int v = suffix[i] - prefix[i+1];
            ans = Math.max(suffix[i] - prefix[i+1], ans);
        }
        return ans;
    }


    /**
     * Method 2: 同向双指针
     * */
    public static int maxProfit_2(int[] prices) {
        int n = prices.length;
        int j = 0;
        int ans = 0;
        // 处于 j 左侧的最小值
        int curMin = prices[0];
        while(j < n) {
            ans = Math.max(ans, prices[j]-curMin);
            curMin = Math.min(curMin, prices[j]);
            j++;
        }
        return ans;
    }

    /**
     * Method 3: Sliding window
     * 在哪里更新 maxVal
     * */
    public static int maxProfit_3(int[] prices) {
        int n = prices.length;
        int i = 0, ans = 0;
        while(i < n) {
            int start = i;
            i++;

            while(i < n && prices[i] >= prices[start]) {
                // 更新最高收益值
                ans = Math.max(ans, prices[i]-prices[start]);
                i++;
            }

            // prices[i] < prices[start]
        }
        return ans;
    }
}
