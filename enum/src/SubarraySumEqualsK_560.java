import java.util.HashMap;

public class SubarraySumEqualsK_560 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int k = 3;
        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, ans = 0;
        for(int i=0;i<n;i++) {
            sum += nums[i];
            int c = map.getOrDefault(sum-k, 0);
            ans += c;
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }
}
