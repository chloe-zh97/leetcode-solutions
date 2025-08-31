public class MaximumDifferenceBetweenIncreasingElements_2016 {
    public static void main(String[] args) {
        int[] nums = {9,4,3,2};
        System.out.println(maximumDifference(nums));
    }

    /**
     * Method 1: 枚举右边，维护左边
     * */
    public static int maximumDifference(int[] nums) {
        int n = nums.length;
        int preMin = nums[0];
        int ans = Integer.MIN_VALUE;
        for(int i=1;i<n;i++) {
            if(nums[i] > preMin) {
                ans = Math.max(ans, nums[i] - preMin);
            }
            preMin = Math.min(preMin, nums[i]);
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
