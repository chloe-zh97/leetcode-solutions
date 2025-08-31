import java.util.ArrayList;
import java.util.List;

public class CombinationSum_67 {
    public static void main(String[] args) {
        int[] nums = {3,4,5};
        int target = 16;
        List<List<Integer>> ans = combinationSum(nums, target);
        for(List<Integer> tmp: ans)
            System.out.println(tmp);
    }

    /**
     * Method 1: backtracking
     * path 的 sum == target
     * */
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        backtracking(nums, 0, target, 0, path, ans);
        return ans;
    }

    private static void backtracking(int[] nums, int sum, int target, int index, List<Integer> path, List<List<Integer>> ans) {
        if(sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if(sum > target) return;

        for(int i=index;i<nums.length;i++) {
            path.add(nums[i]);
            backtracking(nums, sum+nums[i], target, i, path, ans);
            path.remove(path.size()-1);
        }
    }
}
