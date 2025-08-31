public class MaximumProductofFirstandLastElementsofaSubsequence_3584 {
    public static void main(String[] args) {
        int[] nums = {100000};
        int m = 1;
        System.out.println(maximumProduct(nums, m));
    }

    /**
     * Method 1: 两个数的 index 差至少为 m-1
     * */
    public static long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        int curMin = Integer.MAX_VALUE;
        int curMax = Integer.MIN_VALUE;

        long ans = Long.MIN_VALUE;

        for(int i=m-1;i<n;i++) {
            int pre = nums[i-m+1];
            curMin = Math.min(curMin, pre);
            curMax = Math.max(curMax, pre);

            ans = Math.max(ans, Math.max((long)curMin*nums[i], (long)curMax*nums[i]));
        }
        return ans;
    }
}
