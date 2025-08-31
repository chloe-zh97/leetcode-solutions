import java.util.Arrays;
import java.util.HashMap;

public class FindTwoNonOverlappingSubArraysEachWithTargetSum_1477 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,6,2,3,4};
        int target = 6;
        System.out.println(minSumOfLengths_3(nums, target));
    }

    /**
     * Method 1: 动态规划 dp[i]: arr[0...i) 中满足子数组和为 target 的最小长度
     * */
    public static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        final int INF = n+1;

        // dp[i+1]: arr[0...i] 中满足子数组和为 target 的最小长度
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);

        // preSumMap, index
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 0);
        int preSum = 0, ans = INF;

        for(int i=1;i<n+1;i++) {
            preSum += arr[i-1];
            dp[i] = dp[i-1];

            if(preSumMap.containsKey(preSum-target)) {
                int j = preSumMap.get(preSum-target);

                // 子数组范围是 j,j+1,...,i-1, 长度是 i-1-j+1
                int curLen = i-j;
//                 System.out.println("i="+i+" j="+j +" curLen="+curLen);
//                 System.out.println("dp[j]="+dp[j]);
                if(dp[j] != INF) {
                    // 说明之前有合理的答案
                    ans = Math.min(ans, dp[j]+curLen);
                }
                // 之前没有合理的答案，更新 dp
                dp[i] = Math.min(dp[i-1], curLen);
//                 System.out.println("dp[i]="+dp[i]);
            }

            // 更新 map
            preSumMap.put(preSum, i);
        }
        return ans == INF ? -1 : ans;
    }


    public static int minSumOfLengths_2(int[] arr, int target) {
        int n = arr.length;
        final int INF = n+1;

        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int preSum = 0;
        int ans = INF;
        for(int i=0;i<n;i++) {
            preSum += arr[i];
            dp[i+1] = dp[i];

            if(map.containsKey(preSum-target)) {
                int j = map.get(preSum-target);
                // j+1, j+2, ..., i
                int curLen = i-j;
                if(dp[j+1] != INF) {
                    ans = Math.min(ans, dp[j+1]+curLen);
                }

                dp[i+1] = Math.min(dp[i], curLen);
            }

            map.put(preSum, i);
        }
        return ans == INF ? -1 : ans;
    }

    public static int minSumOfLengths_3(int[] arr, int target) {
        int n = arr.length;
        final int INF = n+1;

        // dp[i+1]: arr[0...i] 中和为 target 的最短子序列长度
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);

        // 滑动窗口
        int j = 0, i=0;
        int sum = 0, ans = INF;

        while(j < n) {
            sum += arr[j];
            dp[j+1] = dp[j];

            while(sum > target) {
                sum -= arr[i++];
            }

            if(sum == target) {
                // 找到一组合适的
                int curLen = j-i+1;
                // i, i+1, ..., j
                if(dp[i] != INF) {
                    ans = Math.min(ans, dp[i]+curLen);
                }

                // 更新 dp[j]
                dp[j+1] = Math.min(dp[j], curLen);
            }

            j++;
        }
        return ans==INF? -1: ans;
    }
}
