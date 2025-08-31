import java.util.Arrays;

public class MinimumSumofMountainTripletsII_2909 {
    public static void main(String[] args) {
        int[] nums = {6,5,4,3,4,5};
        System.out.println(minimumSum_2(nums));
    }

    /**
     * Method 1: 枚举，暴力法
     * */
    public static int minimumSum(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int i=1;i<n-1;i++) {
            int left = 0, right = n-1;
            int a = nums[left], b = nums[i], c = nums[right];
            while(left < i) {
                a = Math.min(a, nums[left++]);
            }

            while(right > i) {
                c = Math.min(c, nums[right--]);
            }

            if(a < b && b > c) {
                ans = Math.min(ans, a+b+c);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * Method 2: 前缀和
     * */
    public static int minimumSum_2(int[] nums) {
        int n = nums.length;
        // prefix[i]: nums[0...i-1]的最小值
        int[] prefix = new int[n+1];
        Arrays.fill(prefix, Integer.MAX_VALUE);

        for(int i=1;i<prefix.length;i++)
            prefix[i] = Math.min(prefix[i-1], nums[i-1]);

        // suffix[i]: nums[i...n-1]的最小值
        int[] suffix = new int[n+1];
        Arrays.fill(suffix, Integer.MAX_VALUE);
        for(int i=n-1;i>=0;--i)
            suffix[i] = Math.min(suffix[i+1], nums[i]);

        int ans = Integer.MAX_VALUE;
        for(int i=1;i<n-1;i++) {
            if(nums[i] > prefix[i] && nums[i] > suffix[i+1]) {
                int v = prefix[i]+nums[i]+suffix[i+1];
                ans = Math.min(ans, v);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
