import java.util.Arrays;
import java.util.HashMap;

public class TwoSum_3 {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    /**
     * Method 1: HashMap
     * */
    public static int[] twoSum(int[] nums, int target) {
        // val, index
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = {-1,-1};

        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

}
