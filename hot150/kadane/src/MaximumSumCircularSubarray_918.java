public class MaximumSumCircularSubarray_918 {
    public static void main(String[] args) {
        int[] nums = {-3,-2,-3};
        System.out.println(maxSubarraySumCircular(nums));
    }

    /**
     * Method 1: 最小子数组没有跨越边界，取中间的 maxS
     * 最小子数组跨越了边界，sum - minS
     * 两者取最大
     * */
    public static int maxSubarraySumCircular(int[] nums) {
       // 以每个 num 为结尾位置，计算以 num 为结尾的子数组，最大值数组和最小值数组
        int n = nums.length;
        // 最大值数组
        int maxS = Integer.MIN_VALUE;
        // 最小值数组
        int minS = 0;

        // 代表 nums[i-1] 结尾的最大值最小值情况
        int maxPre = 0, minPre = 0;

        int sum = 0;
        for(int x: nums) {
            maxPre = Math.max(maxPre, 0) + x;
            minPre = Math.min(minPre, 0) + x;

            // update maxS and minS
            maxS = Math.max(maxS, maxPre);
            minS = Math.min(minS, minPre);

            sum += x;
        }

        return sum == minS ? maxS: Math.max(sum-minS, maxS);
    }
}
