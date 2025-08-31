public class SumofVariableLengthSubarrays_3427 {
    public static void main(String[] args) {
        int[] nums = {3,1,1,2};
        System.out.println(subarraySum(nums));
    }

    /**
     * Method 1: 前缀和
     * */
    public static int subarraySum(int[] nums) {
        int n = nums.length;
        int ans = 0;

        // s[i]: nums[0...i-1]的和
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        for(int i=0;i<n;i++) {
            int start = Math.max(0, i-nums[i]);
            ans += s[i+1]-s[start];
            // System.out.println("start="+start+" i="+i+" ans="+ans);
        }

        return ans;
    }
}
