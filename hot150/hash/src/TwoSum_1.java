import java.util.Arrays;
import java.util.HashMap;

public class TwoSum_1 {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        nums = twoSum(nums, target);
        System.out.println(Arrays.toString(nums));
    }
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++) {
            if(map.containsKey(target-nums[i])) {
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
