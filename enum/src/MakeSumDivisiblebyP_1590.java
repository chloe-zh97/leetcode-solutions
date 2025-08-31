import java.util.HashMap;

public class MakeSumDivisiblebyP_1590 {
    public static void main(String[] args) {
        int[] nums = {3,1,4,2};
        int p = 6;
        System.out.println(minSubarray(nums, p));
    }

    /**
     * sum - (s[j] - s[i]) 能被 p 整除
     * (sum - (s[j] - s[i])) % p == 0
     * sum % p == (s[j] - s[i]) % p
     * */
    public static int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        // num % p, index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            int preSum = s[i];
            if(map.containsKey(preSum % p)) {
                int id = map.get(preSum % p);
                ans = Math.min(ans, i-id);
            } else {
                map.put(preSum % p, i);
            }
        }
        return ans;
    }
}
