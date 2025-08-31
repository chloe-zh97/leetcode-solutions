import java.util.HashMap;

public class SubarraySumsDivisiblebyK_974 {
    public static void main(String[] args) {
        int[] nums = {-1,2,9};
        int k = 2;
        System.out.println(subarraysDivByK(nums, k));
        //System.out.println(-1%2);
    }

    /**
     * (a - b) / 9
     * 49 - 40, 4,4
     * 51 - 42, 6,6
     * */
    public static int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, ans = 0;
        for(int i=0;i<n;i++) {
            sum = (sum + nums[i]) % k;
            if(sum < 0) {
                sum += k;
            }

            int c = map.getOrDefault(sum, 0);
            ans += c;
            map.merge(sum,1,Integer::sum);
        }
        return ans;
    }
}
