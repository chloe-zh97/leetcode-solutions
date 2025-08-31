import java.util.HashMap;

public class MaxSumofaPairWithEqualSumofDigits_2342 {
    public static void main(String[] args) {
        int[] nums = {10,12,19,14};
        System.out.println(maximumSum(nums));
    }

    public static int maximumSum(int[] nums) {
        // sum of digit, max number
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = -1;
        for(int i=0;i<nums.length;i++) {
            int sum = sumOfDigit(nums[i]);
            if(map.containsKey(sum)) {
                ans = Math.max(ans, map.get(sum)+nums[i]);
            }
            if(map.getOrDefault(sum,0) < nums[i])
                map.put(sum, nums[i]);
        }
        return ans;
    }

    private static int sumOfDigit(int x) {
        int ans = 0;
        while(x != 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }
}
