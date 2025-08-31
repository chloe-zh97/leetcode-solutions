import java.util.HashMap;

public class MaximumGoodSubarraySum_3026 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        int k = 1;
        System.out.println(maximumSubarraySum(nums, k));
    }

    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // nums[i], nums[0...i-1]的最小前缀和
        HashMap<Integer, Long> preSumMap = new HashMap<>();
        long preSum = 0L, ans = Long.MIN_VALUE;
        for(int i=0;i<n;i++) {
            preSum += nums[i];

            if(preSumMap.containsKey(nums[i]-k)) {
                long preSum2 = preSumMap.get(nums[i]-k);
                ans = Math.max(ans, preSum-preSum2);
            }

            if(preSumMap.containsKey(nums[i]+k)) {
                long preSum2 = preSumMap.get(nums[i]+k);
                ans = Math.max(ans, preSum-preSum2);
            }

            if(!preSumMap.containsKey(nums[i]) || preSumMap.get(nums[i]) > preSum-nums[i]) {
                preSumMap.put(nums[i], preSum - nums[i]);
            }
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }
}
