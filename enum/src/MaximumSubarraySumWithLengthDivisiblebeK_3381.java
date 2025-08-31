import java.util.Arrays;
import java.util.HashMap;

public class MaximumSubarraySumWithLengthDivisiblebeK_3381 {
    public static void main(String[] args) {
        int[] nums = {-5,1,2,-3,4};
        int k = 2;
        System.out.println(maxSubarraySum_2(nums, k));
    }

    /**
     * Method 1: 前缀和+HashMap
     * 维护和 k 同余的最小前缀和
     * */
    public static long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n+1];

        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        // 长度 (j-i) % k == 0, j和i要同余
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, (long)0);

        long ans = Long.MIN_VALUE;
        for(int i=1;i<s.length;i++) {
            if(map.containsKey(i%k)) {
                // 有同余记录
                ans = Math.max(ans, s[i]-map.get(i%k));
            }
            // 只有当没有同余记录或者是当前记录更小时才更新
            if(map.getOrDefault(i%k, Long.MAX_VALUE) > s[i])
                map.put(i%k, s[i]);
        }
        return ans;
    }


    /**
     * Method 2: 优化，用 long[] 代替 hashMap
     * */
    public static long maxSubarraySum_2(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n+1];
        for(int i=1;i<s.length;i++) {
            s[i] = s[i-1] + nums[i-1];
        }

        // 和 k 同余的最小前缀和
        long[] mo = new long[k];
        Arrays.fill(mo, Long.MAX_VALUE/2);

        long ans = Long.MIN_VALUE / 2;
        for(int i=0;i<s.length;i++) {
            int m = i%k;
            ans = Math.max(ans, s[i] - mo[m]);
            mo[m] = Math.min(mo[m], s[i]);
        }
        return ans;
    }
}
