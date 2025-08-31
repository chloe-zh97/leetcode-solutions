public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1};
        int target = 11;
        System.out.println(minSubArrayLen(target, nums));
    }

    /**
     * Method 1: 滑动窗口，最短符合要求的序列
     * 1. element in
     * 2. 在 while 中更新答案
     * 3. 离开元素
     * */
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0, j = 0, curSum = 0;
        int ans = Integer.MAX_VALUE;

        while(j < n) {
            // element j in
            curSum += nums[j++];
            if(curSum < target) continue;

            // 到这一步，curSum >= target, shrink window
            while(curSum >= target) {
                curSum -= nums[i++];
                ans = Math.min(ans, j-i+1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
