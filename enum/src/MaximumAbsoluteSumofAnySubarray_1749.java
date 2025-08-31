import java.util.Arrays;

public class MaximumAbsoluteSumofAnySubarray_1749 {
    public static void main(String[] args) {
        int[] nums = {-1};
        System.out.println(maxAbsoluteSum_2(nums));
    }

    /**
     * Method 1: 暴力法 O(n^2) 超时
     * */
    public static int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        // s[i]: 计算 nums[0,...,i-1]的前缀和
        int[] s = new int[n+1];

        int ans = 0;
        for(int i=1;i<s.length;i++) {
            s[i] = s[i-1] + nums[i-1];
            ans = Math.max(ans, Math.abs(nums[i-1]));
        }

        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                ans = Math.max(ans, Math.abs(s[j+1]-s[i]));
            }
        }
        return ans;
    }

    /**
     * 转化为求最大子数组和 以及 最小子数组和的相反数
     * */
    public static int maxAbsoluteSum_2(int[] nums) {
        int n = nums.length;
        // 最大子数组和
        int[] dpMax = new int[n];
        Arrays.fill(dpMax, Integer.MIN_VALUE);
        // 最大子数组和
        dpMax[0] = Math.max(nums[0], 0);

        // 最小子数组和
        int[] dpMin = new int[n];
        Arrays.fill(dpMin, Integer.MAX_VALUE);
        dpMin[0] = Math.min(nums[0], 0);

        int ans = Math.max(dpMax[0], -dpMin[0]);
        for(int i=1;i<dpMax.length;i++) {
            dpMax[i] = Math.max(dpMax[i-1]+nums[i], nums[i]);
            dpMin[i] = Math.min(dpMin[i-1]+nums[i], nums[i]);
            ans = Math.max(ans, Math.max(dpMax[i], -dpMin[i]));
        }

        return ans;
    }

    /**
     * 优化 dp
     * */
    public static int maxAbsoluteSum_3(int[] nums) {
        int maxF = 0, minF = 0;
        int ans = 0;

        for(int x: nums) {
            maxF = Math.max(maxF+x, x);
            minF = Math.min(minF+x, x);
            ans = Math.max(ans, Math.max(maxF, -minF));
        }
        return ans;
    }

    /**
     * 前缀和 |s[j]-s[i]| 维护最大子数组和 以及 最小子数组和
     * */
    public static int maxAbsoluteSum_4(int[] nums) {
        int mx = 0, mi = 0, s = 0;
        for(int x: nums) {
            s += x;
            mx = Math.max(mx, s);
            mi = Math.min(mi, s);
        }
        return mx-mi;
    }
}
