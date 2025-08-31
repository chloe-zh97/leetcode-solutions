import java.util.Arrays;

public class MaximumSubarray_119 {
    public static void main(String[] args) {
        int[] nums = {2,-3,4,-2,2,1,-1,4};
        System.out.println(maxSubArray_2(nums));
    }

    /**
     * Method 1: Sliding window
     * */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int j = 0;
        int ans = Integer.MIN_VALUE;
        while(j < n) {
            // 以 j 为起点，i 为终点，[j, i)
            int i = j;
            i++;

            int sum = nums[j];
            ans = Math.max(sum, ans);

            while(i < n && sum + nums[i] >= 0) {
                sum += nums[i];
                ans = Math.max(sum, ans);
                i++;
            }

            j++;
        }

        return ans;
    }


    /**
     * Method 2: 动态规划
     * dp[i]: 以 nums[i] 结尾的最大子序列和
     * */
    public static int maxSubArray_2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE/2);

        int ans = nums[0];
        for(int i=1;i<n;i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
