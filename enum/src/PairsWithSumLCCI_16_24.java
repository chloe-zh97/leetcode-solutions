import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairsWithSumLCCI_16_24 {
    public static void main(String[] args) {
        int[] nums = {5,6,5,6};
        int target = 11;
        List<List<Integer>> ans = pairSums_2(nums, target);
        for(List<Integer> row: ans)
            System.out.println(row);
    }

    /**
     * Method 1: HashMap
     * */
    public static List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        // nums[i], 可用数量
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++) {
            int c = map.getOrDefault(target-nums[i], 0);
            if(c > 0) {
                // 有可用组成的元素
                int a = Math.min(nums[i], target-nums[i]);
                int b = Math.max(nums[i], target-nums[i]);
                List<Integer> temp = new ArrayList<>();
                temp.add(a);
                temp.add(b);
                ans.add(temp);
                map.merge(target-nums[i],-1,Integer::sum);
            } else {
                map.merge(nums[i], 1, Integer::sum);
            }
        }
        return ans;
    }

    /**
     * Method 2: 排序+双指针
     * */
    public static List<List<Integer>> pairSums_2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        int left = 0, right = nums.length-1;
        while(left < right) {
            if(nums[left]+nums[right] == target) {
                List<Integer> row = Arrays.asList(nums[left],nums[right]);
                ans.add(row);
                left++;
                --right;
            } else if(nums[left]+nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
