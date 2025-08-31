public class MaximumSubarray_53 {
    public static void main(String[] args) {
        int[] nums = {-1};
        System.out.println(maxSubArray(nums));
    }

    /**
     * Method 1: 滑动窗口 100%
     * */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        int sum = 0, ans = Integer.MIN_VALUE;
        while(j < n) {
            // element in
            sum += nums[j++];
            // 退出循环后 sum >= 0
            ans = Math.max(ans, sum);
            while(sum < 0) sum -= nums[i++];
        }
        return ans;
    }

    /**
     * Method 2: dp
     * */
    public static int maxSubArray_2(int[] nums) {
        // nums[i]: 以 i 为终结的最大子序列和
        int sum = nums[0];
        for(int i=1;i<nums.length;i++) {
            nums[i] += Math.max(nums[i-1], 0);
            sum = Math.max(sum, nums[i]);
        }
        return sum;
    }
}
