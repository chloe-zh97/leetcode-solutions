public class MaximumProductSubarray_104 {
    public static void main(String[] args) {
        int[] nums = {-2, 3, -4};
        System.out.println(maxProduct_2(nums));
    }

    /**
     * Method 1: 动态规划
     */
    public static int maxProduct(int[] nums) {
        int n = nums.length;
        // dp[i+1]: nums[*：i] 的最大乘积和最小乘积
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 最大
            dp[i + 1][0] = Math.max(nums[i], Math.max(dp[i][0] * nums[i], dp[i][1] * nums[i]));
            // 最小
            dp[i + 1][1] = Math.min(nums[i], Math.min(dp[i][0] * nums[i], dp[i][1] * nums[i]));
            ans = Math.max(ans, dp[i + 1][0]);
        }
        return ans;
    }

    /**
     * Method 2: 优化，变成变量
     */
    public static int maxProduct_2(int[] nums) {
        int n = nums.length;
        // f1_0: 最大值， f1_1: 最小值
        int f1_0 = 1, f1_1 = 1, f2_0, f2_1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            f2_0 = Math.max(nums[i], Math.max(f1_0 * nums[i], f1_1 * nums[i]));
            f2_1 = Math.min(nums[i], Math.min(f1_0 * nums[i], f1_1 * nums[i]));
            f1_0 = f2_0;
            f1_1 = f2_1;
            ans = Math.max(f1_0, ans);
        }
        return ans;
    }
}
