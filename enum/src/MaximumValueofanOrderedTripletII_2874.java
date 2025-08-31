public class MaximumValueofanOrderedTripletII_2874 {
    public static void main(String[] args) {
        int[] nums = {12,6,1,2,7};
        System.out.println(maximumTripletValue_3(nums));
    }

    /**
     * Method 1: 两次遍历，枚举 j, 让j左边的元素尽可能大，j右边的
     * */
    public static long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] suffix = new int[n+1];
        // 存储每个元素右边的最大值, 其实就是 nums[k]
        // suffix[i]: i右边的元素的最大值，包括i
        for(int i=n-1;i>=0;i--) {
            suffix[i] = Math.max(suffix[i+1], nums[i]);
        }

        int preMax = nums[0];
        long ans = 0L;
        // 枚举 j 的值
        for(int j=1;j<n-1;j++) {
            ans = Math.max(ans, (long)(preMax - nums[j]) * suffix[j+1]);
            preMax = Math.max(preMax, nums[j]);
        }
        return ans;
    }

    /**
     * Method 2: 枚举 k
     * (nums[i]-nums[j])*nums[k]
     * 需要维护 nums[i] 的最大值 maxI
     * 维护 nums[i]-nums[j] 的最大值 maxDiff
     * */
    public static long maximumTripletValue_2(int[] nums) {
        int preMax = 0, maxDiff = 0;
        long ans = 0L;
        for(int x: nums) {
            ans = Math.max(ans, (long)maxDiff * x);
            maxDiff = Math.max(maxDiff, preMax-x);
            preMax = Math.max(preMax, x);
        }
        return ans;
    }

    /**
     * 思考：如果有负数怎么办
     * nums[i]-nums[j] 和 nums[k] 均有可能是负数，所以需要维护 sufMin, preMin
     * */
    public static long maximumTripletValue_3(int[] nums) {
        int n = nums.length;
        int[] sufMin = new int[n+1];
        int[] sufMax = new int[n+1];

        for(int i=n-1;i>=0;i--) {
            // i 右边的最小值
            sufMin[i] = Math.min(sufMin[i+1], nums[i]);
            sufMax[i] = Math.max(sufMax[i+1], nums[i]);
        }

        int preMax = nums[0], preMin = nums[0];
        long ans = 0L;
        for(int j=1;j<n-1;j++) {
            ans = Math.max(ans, Math.max((preMax-nums[j]) * sufMax[j+1], (preMin-nums[j]) * sufMin[j+1]));
            preMax = Math.max(preMax, nums[j]);
            preMin = Math.min(preMin, nums[j]);
        }

        return ans;
    }
}
