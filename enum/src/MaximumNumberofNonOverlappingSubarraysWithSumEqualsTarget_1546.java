import java.util.HashMap;
import java.util.HashSet;

public class MaximumNumberofNonOverlappingSubarraysWithSumEqualsTarget_1546 {
    public static void main(String[] args) {
        int[] nums = {-1,3,5,1,4,2,-9};
        int target = 6;
        System.out.println(maxNonOverlapping_2(nums, target));
    }

    /**
     * dp[i+1]: nums[0...i] 中有多少子串的和等于 target
     * */
    public static int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n+1];

        // preSum, index
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0,0);

        int preSum = 0;
        for(int i=1;i<n+1;i++) {
            preSum += nums[i-1];
            // 一开始先更新 dp[i]
            dp[i] = dp[i-1];

            if(preSumMap.containsKey(preSum - target)) {
                int j = preSumMap.get(preSum-target);
                // 特殊更新
                dp[i] = Math.max(dp[i], dp[j]+1);
            }

            preSumMap.put(preSum, i);
        }
        return dp[n];
    }


    public static int maxNonOverlapping_2(int[] nums, int target) {
        // 存储前缀和
        HashSet<Integer> set = new HashSet<>();
        set.add(0);

        int n = nums.length;
        int ans = 0;
        int preSum = 0;

        for(int i=0;i<n;i++) {
            preSum += nums[i];
            if(set.contains(preSum-target)) {
                set.clear();
                set.add(0);
                preSum = 0;
                ans++;
            } else {
                set.add(preSum);
            }
        }
        return ans;
    }
}
