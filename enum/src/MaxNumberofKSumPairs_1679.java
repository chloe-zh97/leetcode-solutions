import java.util.HashMap;
import java.util.Map;

public class MaxNumberofKSumPairs_1679 {
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,3};
        int k = 6;
        System.out.println(maxOperations_2(nums, k));
    }

    public static int maxOperations(int[] nums, int k) {
        // nums[i], times
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            map.merge(nums[i], 1, Integer::sum);
        }

        int ans = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if(2*key == k) ans += val/2;
            else if(2*key < k) {
                // 只统计更小的那个数
                ans += Math.min(val, map.getOrDefault(k-key, 0));
            }
        }
        return ans;
    }


    public static int maxOperations_2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(k-nums[i])) {
                map.merge(k-nums[i], -1, Integer::sum);
                ans++;
                if(map.get(k-nums[i]) == 0) map.remove(k-nums[i]);
            } else {
                map.merge(nums[i], 1, Integer::sum);
            }
        }
        return ans;
    }
}
